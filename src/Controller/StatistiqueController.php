<?php

namespace App\Controller;

use App\Entity\Salledesport;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\SalledesportRepository;


class StatistiqueController extends AbstractController
{
    
    #[Route('/sts', name: 'statistiques_salles_abonnements', methods: ['GET'])]
    public function sallesAbonnements(SalledesportRepository $salledesportRepository): Response
    {
        $sallesAvecAbonnements = $salledesportRepository->findSallesAvecAbonnements();

        $labels = [];
        $data = [];

        foreach ($sallesAvecAbonnements as $salle) {
            $labels[] = $salle['nom'];
            $data[] = $salle['abonnements'];
        }

        return $this->render('statistique/salles_abonnements.html.twig', [
            'labels' => json_encode($labels),
            'data' => json_encode($data),
        ]);
    }
 
}
