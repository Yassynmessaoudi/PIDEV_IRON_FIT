<?php

namespace App\Controller;

use App\Repository\CodePromoRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CpController extends AbstractController
{
    #[Route('/cp', name: 'app_cp')]
    public function index(): Response
    {
        return $this->render('cp/index.html.twig', [
            'controller_name' => 'CpController',
        ]);
    }
    #[Route('/cp/list', name: 'list_cp')]
public function listCodepromo(CodePromoRepository  $codepromoRepository): Response
{
    return $this->render('cp/listcp.html.twig', [
        'codepromos' => $codepromoRepository->findAll(),
    ]);

}
}