<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\CodePromo;
use App\Form\CodepromoType;
use App\Repository\CodePromoRepository;
use Doctrine\Persistence\ManagerRegistry;

class CodePromoController extends AbstractController
{
    #[Route('/code/promo', name: 'homepage')]
    public function index(): Response
    {
        return $this->render('base.html.twig', [
            'controller_name' => 'CodePromoController',
        ]);
    }
    #[Route('/codepromo/add', name: 'codepromo_add')]
public function addCodepromo(Request $request, ManagerRegistry $manager): Response
{
    $em = $manager->getManager();
    $codepromo = new Codepromo();
    $form = $this->createForm(CodepromoType::class, $codepromo);
    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
       
        $em->persist($codepromo);
        $em->flush();

        return $this->redirectToRoute('list_codepromo');
    }

    return $this->render('codepromo/addcp.html.twig', ['form' => $form->createView()]);
}


#[Route('/codepromo/list', name: 'list_codepromo')]
public function listCodepromo(CodepromoRepository $codepromoRepository): Response
{
    return $this->render('codepromo/listcp.html.twig', [
        'codepromos' => $codepromoRepository->findAll(),
    ]);


}
#[Route('/codepromo/edit/{id}', name: 'codepromo_edit')]
public function editCodepromo(Request $request, ManagerRegistry $manager, $id, CodepromoRepository $codepromoRepository): Response
{
    $em = $manager->getManager();
    $codepromo = $codepromoRepository->find($id);
    $form = $this->createForm(CodepromoType::class, $codepromo);
    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        $em->flush();
        return $this->redirectToRoute('list_codepromo');
    }

    return $this->render('codepromo/editcp.html.twig', [
        'codepromo' => $codepromo,
        'form' => $form->createView(),
    ]);
}


#[Route('/codepromo/delete/{id}', name: 'codepromo_delete')]
public function deleteCodepromo($id, ManagerRegistry $manager, CodepromoRepository $codepromoRepository): Response
{
    $em = $manager->getManager();
    $codepromo = $codepromoRepository->find($id);

    if ($codepromo) {
        $em->remove($codepromo);
        $em->flush();
    }

    return $this->redirectToRoute('list_codepromo');
}


}



