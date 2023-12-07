<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\UserRepository;



class StatistiqueControlleryasmine extends AbstractController
{
    #[Route('/statistique', name: 'app_statistique')]
    public function index(): Response
    {
        return $this->render('statistique/index.html.twig', [
            'controller_name' => 'StatistiqueControlleryasmine',
        ]);
    }
    #[Route('/statistique', name: 'app_statistique')]
    public function statistique(UserRepository $userRepository): Response
    {
        $totalUtilisateurs = $userRepository->count([]);
        $hommes = $userRepository->countBySexe('Homme');
        $femmes = $userRepository->countBySexe('Femme');

        $pourcentageHommes = ($hommes / $totalUtilisateurs) * 100;
        $pourcentageFemmes = ($femmes / $totalUtilisateurs) * 100;

        return $this->render('statistiqueyasmine/sexe.html.twig', [
            'totalUtilisateurs' => $totalUtilisateurs,
            'hommes' => $hommes,
            'femmes' => $femmes,
            'pourcentageHommes' => $pourcentageHommes,
            'pourcentageFemmes' => $pourcentageFemmes,
        ]);
    }
}
