<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class DefaultController extends AbstractController
{
    #[Route('/acceuil', name: 'app_default')]
    public function index(): Response
    {
        return $this->render('acceuil.html.twig', [
            'controller_name' => 'DefaultController',
        ]);
    }
   
    #[Route('/dashboradAdmin', name: 'app_admin')]
    public function dashborad(): Response
    {
        return $this->render('basedb.html.twig', [
            'controller_name' => 'DefaultController',
        ]);
    }
    #[Route('/Coach', name: 'app_coach')]
    public function dashboradCoach(): Response
    {
        return $this->render('basedb.html.twig', [
            'controller_name' => 'DefaultController',
        ]);
    }
    #[Route('/Medecin', name: 'app_medecin')]
    public function dashboradMedecin(): Response
    {
        return $this->render('basedb.html.twig', [
            'controller_name' => 'DefaultController',
        ]);
    }
    #[Route('/Client', name: 'app_client')]
    public function Client(): Response
    {
        return $this->render('base.html.twig', [
            'controller_name' => 'DefaultController',
        ]);
    }

}
