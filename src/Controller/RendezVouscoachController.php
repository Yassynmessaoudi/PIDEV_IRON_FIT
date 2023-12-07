<?php

namespace App\Controller;

use App\Entity\RendezVous;
use App\Form\RendezVousType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/rendezvouscoach')]
class RendezVouscoachController extends AbstractController
{
    #[Route('/', name: 'app_rendez_vouscoach_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $rendezVouses = $entityManager
            ->getRepository(RendezVous::class)
            ->findAll();

            return $this->render('rendez_vouscoach/index.html.twig', [
            'rendez_vouses' => $rendezVouses,
        ]);
    }

    #[Route('/new', name: 'app_rendez_vouscoach_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $rendezVou = new RendezVous();
        $form = $this->createForm(RendezVousType::class, $rendezVou);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($rendezVou);
            $rendezVou->getPlanning()->setViews($rendezVou->getPlanning()->getViews() + 1);
            $entityManager->flush();

            return $this->redirectToRoute('app_rendez_vouscoach_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('rendez_vouscoach/new.html.twig', [
            'rendez_vou' => $rendezVou,
            'form' => $form,
        ]);
    }

    #[Route('/{idRdv}', name: 'app_rendez_vouscoach_show', methods: ['GET'])]
    #[Route('/rendezvouscoach/{idRdv}', name: 'app_rendez_vouscoach_show', methods: ['GET'])]
    public function show(RendezVous $rendezVou): Response
    {
        return $this->render('rendez_vouscoach/show.html.twig', [
            'rendez_vou' => $rendezVou,
        ]);
    }
    
    
    #[Route('/{idRdv}/edit', name: 'app_rendez_vouscoach_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, RendezVous $rendezVou, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(RendezVousType::class, $rendezVou);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_rendez_vouscoach_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('rendez_vouscoach/edit.html.twig', [
            'rendez_vou' => $rendezVou,
            'form' => $form,
        ]);
    }

    #[Route('/{idRdv}', name: 'app_rendez_vouscoach_delete', methods: ['POST'])]
    public function delete(Request $request, RendezVous $rendezVou, EntityManagerInterface $entityManager): Response
    { // Récupérer le planning associé au rendez-vous
        $planning = $rendezVou->getPlanning();
    
        // Décrémenter le nombre de vues du planning
        $planning->setViews($planning->getViews() - 1);
        
        if ($this->isCsrfTokenValid('delete'.$rendezVou->getIdRdv(), $request->request->get('_token'))) {
            $entityManager->remove($rendezVou);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_rendez_vouscoach_index', [], Response::HTTP_SEE_OTHER);
    }

    
    #[Route('/search', name: 'app_rendez_vouscoach_search', methods: ['GET'])]
    public function search(Request $request): Response
{
    // Récupérer l'idRdv de la requête
    $idRdv = $request->query->get('idRdv');

    // Vérifier si idRdv est fourni
    if (!$idRdv) {
        // Afficher un message d'erreur
        $this->addFlash('error', 'Veuillez fournir un idRdv.');
    }

    // Récupérer le rendez-vous correspondant à l'idRdv
    $rendezVou = $this->getDoctrine()
        ->getRepository(RendezVous::class)
        ->find($idRdv);

    // Vérifier si le rendez-vous existe
    if (!$rendezVou) {
        $this->addFlash('error', 'Aucun rendez-vous trouvé avec l\'id ' . $idRdv);
    }

    // Afficher le rendez-vous
    return $this->render('rendez_vouscoach/search.html.twig', [
        'rendez_vou' => $rendezVou,
    ]);
}

    
}
