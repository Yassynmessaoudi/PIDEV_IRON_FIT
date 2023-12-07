<?php

namespace App\Controller;

use App\Entity\Client;
use App\Entity\Coach;
use App\Entity\Planning;
use App\Entity\User;
use App\Entity\RendezVous;
use App\Form\RecommandeType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;

#[Route('/mainplanning')]
class VueClientController extends AbstractController
{
    #[Route('/', name: 'app_mainplanning', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $plannings = $entityManager
            ->getRepository(Planning::class)
            ->findAll();
    
        foreach ($plannings as $key => $planning) {
    $image = $planning->getImage();
    if ($image !== null) {
        $images[$key] = base64_encode(stream_get_contents($image));
    }
}

    
        return $this->render('vueclient/acceuil.html.twig', [
            'plannings' => $plannings,
            'images' => $images,
        ]);
    }
    
    #[Route('/mainplanning/{id}', name: 'app_mainplanning_show', methods: ['GET'])]
    public function show(Planning $planning): Response
    {
        $image = $planning->getImage();

    // Convertir l'image en base64
    $base64Image = base64_encode(stream_get_contents($image));

    return $this->render('vueclient/show.html.twig', [
        'planning' => $planning,
        'base64Image' => $base64Image,
    ]);
}
#[Route('/mainplanning/{id}/recommande', name: 'app_mainplanning_recommande', methods: ['GET', 'POST'])]
public function recommande(Request $request, Planning $planning, MailerInterface $mailer): Response
{     
    if ($planning->getViews() >= 7) {
        $this->addFlash('error', 'Ce planning est saturé et ne peut pas être recommandé.');
        return $this->redirectToRoute('app_main');
    }

    $rendezVous = new RendezVous();
    
    // Récupérer l'utilisateur actuellement connecté et le Client correspondant
    $User = $this->getUser();
    $clientRepository = $this->getDoctrine()->getRepository(Client::class);
    $client = $clientRepository->findOneBy(['idClient' => $User->getIdUser()]);
    
    // Définir les valeurs de client, coach et planning
    $rendezVous->setIdClient($client);
    $rendezVous->setIdCoach($planning->getIdCoach());
    $rendezVous->setPlanning($planning);

    $form = $this->createForm(RecommandeType::class, $rendezVous);
    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($rendezVous);
        $planning->setViews($planning->getViews() + 1);
        $entityManager->flush();

        // Créez un email
        $email = (new Email())
            ->from('hello@example.com')
            ->to($rendezVous->getIdCoach()->getMail())
            ->subject('Nouveau rendez-vous')
            ->text('Un nouveau rendez-vous a été pris.');

        // Envoyez l'email
        $mailer->send($email);

        return $this->redirectToRoute('app_mainplanning');
    }

    return $this->render('vueclient/recommande.html.twig', [
        'planning' => $planning,
        'recommande_form' => $form->createView(),
    ]);
}

#[Route('/rendezvous', name: 'app_rendezvous', methods: ['GET'])]
public function rendezvous(EntityManagerInterface $entityManager): Response
{
    // Get the currently logged in user
    $user = $this->getUser();

    // Find the corresponding Client entity
    $client = $entityManager->getRepository(Client::class)->findOneBy(['idClient' => $user]);

    // Fetch the RendezVous entities for the client
    $rendezvous = $entityManager->getRepository(RendezVous::class)->findBy(['idClient' => $client]);

    return $this->render('vueclient/choice.html.twig', [
        'rendezvous' => $rendezvous,
    ]);
}


#[Route('/rendezvous/{username}', name: 'app_rendezvous_detail', methods: ['GET', 'POST'])]
public function rendezvousDetail($username, Request $request, EntityManagerInterface $entityManager): Response
{
    $rendezvous = null;

    $client = $entityManager->getRepository(Client::class)->findOneBy(['username' => $username]);

    if ($client) {
        $rendezvous = $entityManager->getRepository(RendezVous::class)->findBy(['idClient' => $client->getIdClient()]);
    }

    return $this->render('vueclient/choice.html.twig', [
        'rendezvous' => $rendezvous,
    ]);
}
#[Route('/client/{idRdv}', name: 'app_rdv_delete', methods: ['POST'])]
public function delete(Request $request, $idRdv, EntityManagerInterface $entityManager, MailerInterface $mailer): Response
{ 
    // Récupérer l'utilisateur actuellement connecté
    $user = $this->getUser();

    // Trouver le rendez-vous correspondant à l'idRdv
    $rendezVou = $entityManager->getRepository(RendezVous::class)->find($idRdv);

    // Vérifier si le rendez-vous existe
    if (!$rendezVou) {
        throw $this->createNotFoundException('Aucun rendez-vous trouvé pour l\'id : '.$idRdv);
    }

    // Vérifier si l'utilisateur connecté est le client du rendez-vous
    if ($rendezVou->getIdClient()->getIdClient() != $user->getIdUser()) {
        throw $this->createAccessDeniedException('Vous n\'avez pas la permission de supprimer ce rendez-vous.');
    }

    // Récupérer le planning associé au rendez-vous
    $planning = $rendezVou->getPlanning();
    
    // Décrémenter le nombre de vues du planning
    $planning->setViews($planning->getViews() - 1);

    if ($this->isCsrfTokenValid('delete'.$rendezVou->getIdRdv(), $request->request->get('_token'))) {
        try {
            $entityManager->remove($rendezVou);
            $entityManager->flush();

            // Create an email
            $email = (new Email())
                ->from('hello@example.com')
                ->to($rendezVou->getIdCoach()->getMail())
                ->subject('Rendez-vous annulé')
                ->text('Le rendez-vous a été annulé.');

            // Send the email
            $mailer->send($email);

        } catch (\Exception $e) {
            // Enregistrer le message de l'exception pour déboguer le problème
            error_log($e->getMessage());
            throw $this->createNotFoundException('Il y a eu un problème lors de la suppression du rendez-vous.');
        }
    }
    
    return $this->redirectToRoute('app_rendezvous', [], Response::HTTP_SEE_OTHER);
}

#[Route('/profile', name: 'app_profile', methods: ['GET'])]
public function profile(EntityManagerInterface $entityManager): Response
{
    // Get the currently logged in user
    $user = $this->getUser();

    // Find the corresponding Client entity
    $client = $entityManager->getRepository(Client::class)->findOneBy(['idClient' => $user->getIdUser()]);

    return $this->render('vueclient/profile.html.twig', [
        'client' => $client,
    ]);
}

#[Route('/profileadmin', name: 'app_profileadmin', methods: ['GET'])]
public function profileadmin(EntityManagerInterface $entityManager): Response
{
    // Get the currently logged in user
    $user = $this->getUser();

    // Find the corresponding Coach entity
    $coach = $entityManager->getRepository(Coach::class)->findOneBy(['idutilisateur' => $user->getIdUser()]);

    return $this->render('profile/profileadmin.html.twig', [
        'coach' => $coach,
    ]);
}


}