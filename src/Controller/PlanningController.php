<?php

namespace App\Controller;

use App\Entity\Planning;
use App\Form\PlanningType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
#[Route('/planning')]
class PlanningController extends AbstractController
{
    #[Route('/', name: 'app_planning_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $plannings = $entityManager
            ->getRepository(Planning::class)
            ->findAll();

        return $this->render('planning/index.html.twig', [
            'plannings' => $plannings,
        ]);
    }

    #[Route('/new', name: 'app_planning_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $planning = new Planning();
        $form = $this->createForm(PlanningType::class, $planning);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Récupérer l'image
            $imageFile = $form->get('image')->getData();

            // Vérifier si une image a été téléchargée
            if ($imageFile) {
                // Ouvrir le fichier en mode lecture
                $file = fopen($imageFile->getPathname(), 'r');

                // Lire le contenu du fichier
                $imageContent = stream_get_contents($file);

                // Fermer le fichier
                fclose($file);

                // Stocker le contenu de l'image dans la base de données
                $planning->setImage($imageContent);
            }

            $entityManager->persist($planning);
            $entityManager->flush();

            return $this->redirectToRoute('app_planning_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('planning/new.html.twig', [
            'planning' => $planning,
            'form' => $form,
        ]);
    }
    #[Route('/planning/{id}', name: 'app_planning_show', methods: ['GET'])]
    public function show(Planning $planning): Response
    {
        $image = $planning->getImage();

    // Convertir l'image en base64
    $base64Image = base64_encode(stream_get_contents($image));

    return $this->render('planning/show.html.twig', [
        'planning' => $planning,
        'base64Image' => $base64Image,
    ]);
}
    

    #[Route('/{idplanning}/edit', name: 'app_planning_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Planning $planning, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(PlanningType::class, $planning);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Récupérer l'image
            $imageFile = $form->get('image')->getData();

            // Vérifier si une image a été téléchargée
            if ($imageFile) {
                // Ouvrir le fichier en mode lecture
                $file = fopen($imageFile->getPathname(), 'r');

                // Lire le contenu du fichier
                $imageContent = stream_get_contents($file);

                // Fermer le fichier
                fclose($file);

                // Stocker le contenu de l'image dans la base de données
                $planning->setImage($imageContent);
            }

            $entityManager->flush();

            return $this->redirectToRoute('app_planning_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('planning/edit.html.twig', [
            'planning' => $planning,
            'form' => $form,
        ]);
    }

    #[Route('/{idplanning}', name: 'app_planning_delete', methods: ['POST'])]
    public function delete(Request $request, Planning $planning, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$planning->getIdplanning(), $request->request->get('_token'))) {
            $entityManager->remove($planning);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_planning_index', [], Response::HTTP_SEE_OTHER);
    }


#[Route('/search', name: 'app_planning_search')]
public function search(Request $request): Response
{
    // Récupérer l'idplanning de la requête
    $idplanning = $request->query->get('idplanning');
    

    // Récupérer le planning correspondant à l'idplanning
    $planning = $this->getDoctrine()
        ->getRepository(Planning::class)
        ->find($idplanning);
       

    // Convertir l'image en base64
    $base64Image = null;
    if ($planning) {
        $image = $planning->getImage();
        $base64Image = base64_encode(stream_get_contents($image));
    }

    return $this->render('planning/search.html.twig', [
        'planning' => $planning,
        'base64Image' => $base64Image,
    ]);
    
}

#[Route('/stats', name: 'app_stats', methods: ['GET'])]
public function stats(EntityManagerInterface $entityManager): Response
{
    $plannings = $entityManager->getRepository(Planning::class)->findAll();

    $planningNames = [];
    $rendezvousCounts = [];
    foreach ($plannings as $planning) {
        $planningNames[] = $planning->getProgramme();
        $rendezvousCounts[] = count($planning->getRendezvous());
    }

    $stats = [];
    foreach ($plannings as $planning) {
        $stats[] = [
            'planning' => $planning,
            'count' => count($planning->getRendezvous()),
        ];
    }

  return $this->render('planning/stat.html.twig', [
        'planningNames' => $planningNames,
        'rendezvousCounts' => $rendezvousCounts,
    ]);
}



}
