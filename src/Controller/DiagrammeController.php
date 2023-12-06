<?php

namespace App\Controller;

use App\Repository\AbonnementRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class DiagrammeController extends AbstractController
{
    #[Route('/dgm', name: 'abonnements_par_type')]
    public function abonnementsParType(AbonnementRepository $abonnementRepository): Response
{
    $abonnements = [
        'Monthly' => $abonnementRepository->getCountByType('monthly'),
        'Premium' => $abonnementRepository->getCountByType('premium'),
        'Marie' => $abonnementRepository->getCountByType('marie'),
        'Freres' => $abonnementRepository->getCountByType('freres'),
        'Mineur' => $abonnementRepository->getCountByType('mineur'),
        'Offre 1 year' => $abonnementRepository->getCountByType('offre 1 year'),
    ];

    return $this->render('diagramme/abonnements_par_type.html.twig', [
        'abonnementsParType' => $abonnements, 
    ]);
}

}
