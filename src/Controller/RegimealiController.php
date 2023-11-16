<?php

namespace App\Controller;

use App\Entity\Regimeali;
use App\Form\RegimealiType;
use App\Repository\RegimealiRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/regimeali')]
class RegimealiController extends AbstractController
{
    #[Route('/', name: 'app_regimeali_index', methods: ['GET'])]
    public function index(RegimealiRepository $regimealiRepository): Response
    {
        return $this->render('regimeali/index.html.twig', [
            'regimealis' => $regimealiRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_regimeali_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $regimeali = new Regimeali();
        $form = $this->createForm(RegimealiType::class, $regimeali);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Ajouter le contrôle pour le prix du régime
            $prixregime = $regimeali->getPrixregime();
            if ($prixregime >= 100) {
                $this->addFlash('error', 'Le prix du régime doit être inférieur à 100.');
                return $this->redirectToRoute('app_regimeali_new');
            }

            $entityManager->persist($regimeali);
            $entityManager->flush();

            return $this->redirectToRoute('app_regimeali_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('regimeali/new.html.twig', [
            'regimeali' => $regimeali,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_regimeali_show', methods: ['GET'])]
    public function show(Regimeali $regimeali): Response
    {
        return $this->render('regimeali/show.html.twig', [
            'regimeali' => $regimeali,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_regimeali_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Regimeali $regimeali, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(RegimealiType::class, $regimeali);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Ajouter le contrôle pour le prix du régime
            $prixregime = $regimeali->getPrixregime();
            if ($prixregime >= 100) {
                $this->addFlash('error', 'Le prix du régime doit être inférieur à 100.');
                return $this->redirectToRoute('app_regimeali_edit', ['id' => $regimeali->getId()]);
            }

            $entityManager->flush();

            return $this->redirectToRoute('app_regimeali_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('regimeali/edit.html.twig', [
            'regimeali' => $regimeali,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_regimeali_delete', methods: ['POST'])]
    public function delete(Request $request, Regimeali $regimeali, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete' . $regimeali->getId(), $request->request->get('_token'))) {
            $entityManager->remove($regimeali);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_regimeali_index', [], Response::HTTP_SEE_OTHER);
    }

    #[Route('/homepage', name: 'homepage', methods: ['GET'])]
    public function indexx(): Response
    {
        return $this->render('base.html.twig');
    }
}
