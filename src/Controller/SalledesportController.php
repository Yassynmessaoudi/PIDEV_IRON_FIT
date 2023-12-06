<?php

namespace App\Controller;

use App\Entity\Salledesport;
use App\Form\SalledesportType;
use App\Repository\SalledesportRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;

class SalledesportController extends AbstractController
{
    #[Route('/IndexSalledesport', name: 'app_salledesport_index', methods: ['GET'])]
    public function index(Request $request, SalledesportRepository $salledesportRepository): Response
    {
        $nom = $request->query->get('nom');
        $adresse = $request->query->get('adresse');
        $capacite = $request->query->get('capacite');
        $specialite = $request->query->get('specialite');
    
        $salledesports = $salledesportRepository->findByMultipleCriteria($nom, $adresse, $capacite, $specialite);
        $entityManager = $this->getDoctrine()->getManager();
        $salleDeSportRepository = $entityManager->getRepository(Salledesport::class);

   
        $totalSalleDeSport = $salleDeSportRepository->createQueryBuilder('s')
            ->select('COUNT(s.idsalledesport)')
            ->getQuery()
            ->getSingleScalarResult();
    
        return $this->render('salledesport/index.html.twig', [
            'salledesports' => $salledesports,
            'totalSalleDeSport' => $totalSalleDeSport,
        ]);
    }
    #[Route('/IndexuserSalledesport', name: 'app_salledesport_indexuser', methods: ['GET'])]
    public function indexuser(Request $request, SalledesportRepository $salledesportRepository): Response
    {
        $nom = $request->query->get('nom');
        $adresse = $request->query->get('adresse');
        $capacite = $request->query->get('capacite');
        $specialite = $request->query->get('specialite');
    
   
        $salledesports = $salledesportRepository->findByMultipleCriteria($nom, $adresse, $capacite, $specialite);
        $entityManager = $this->getDoctrine()->getManager();
        $salleDeSportRepository = $entityManager->getRepository(Salledesport::class);

     
        $totalSalleDeSport = $salleDeSportRepository->createQueryBuilder('s')
            ->select('COUNT(s.idsalledesport)')
            ->getQuery()
            ->getSingleScalarResult();
    
    
        return $this->render('salledesport/indexuser.html.twig', [
            'salledesports' => $salledesports,
            'totalSalleDeSport' => $totalSalleDeSport,
        ]);
    }
   
    #[Route('/ExportTablePDF', name: 'app_salledesport_export_table_pdf', methods: ['GET'])]
    public function exportTablePdf(SalledesportRepository $salledesportRepository): Response
    {
  
        $salledesports = $salledesportRepository->findAll();
    
   
        $options = new Options();
        $options->set('isHtml5ParserEnabled', true);
        $options->set('isPhpEnabled', true);
    
        $dompdf = new Dompdf($options);
    
  
        $html = $this->renderView('salledesport/export_table_pdf.html.twig', [
            'salledesports' => $salledesports,
        ]);
    
   
        $dompdf->loadHtml($html);

        $dompdf->setPaper('A4', 'portrait');
    
     
        $dompdf->render();
    
        $response = new Response($dompdf->output());
        $response->headers->set('Content-Type', 'application/pdf');
    
    
        $response->headers->set('Content-Disposition', 'attachment; filename="export_table_salledesports.pdf"');

    
        return $response;
    }


    #[Route('/NewSalledesport', name: 'app_salledesport_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $salledesport = new Salledesport();
        $form = $this->createForm(SalledesportType::class, $salledesport);
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            $photoFile = $form->get('photo')->getData();
    
            if ($photoFile) {
                try {
                    $uploadDirectory = $this->getParameter('kernel.project_dir') . '/public/uploads/photos';
                    if (!file_exists($uploadDirectory)) {
                        mkdir($uploadDirectory, 0777, true);
                    }
                    $newFilename = uniqid().'.'.$photoFile->guessExtension();
                    $photoFile->move($uploadDirectory, $newFilename);
                    $salledesport->setPhoto('/uploads/photos/'.$newFilename);
                } catch (FileException $e) {
                    // Gérer l'exception si le fichier ne peut pas être déplacé
                    $this->addFlash('error', 'Une erreur s\'est produite lors du téléchargement de la photo.');
                    return $this->redirectToRoute('app_salledesport_new', [], Response::HTTP_SEE_OTHER);
                }
            }
    
            $entityManager->persist($salledesport);
            $entityManager->flush();
            $this->addFlash('success', 'Le salle de sport a été ajouté avec succès.');
            return $this->redirectToRoute('app_salledesport_index', [], Response::HTTP_SEE_OTHER);
        }
    
        return $this->renderForm('salledesport/new.html.twig', [
            'salledesport' => $salledesport,
            'form' => $form,
        ]);
    }

    #[Route('/ShowSalleDeSport/{idsalledesport}', name: 'app_salledesport_show', methods: ['GET'])]
    public function show(Salledesport $salledesport): Response
    {
        return $this->render('salledesport/show.html.twig', [
            'salledesport' => $salledesport,
        ]);
    }
    #[Route('/Showuser//{idsalledesport}', name: 'app_salledesport_showuser', methods: ['GET'])]
    public function showuser(Salledesport $salledesport): Response
    {
        return $this->render('salledesport/showuser.html.twig', [
            'salledesport' => $salledesport,
        ]);
    }

    #[Route('/{idsalledesport}/edit', name: 'app_salledesport_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Salledesport $salledesport, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(SalledesportType::class, $salledesport);
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            $photoFile = $form->get('photo')->getData();
    
            // Vérifie si un nouveau fichier a été téléchargé
            if ($photoFile) {
                $uploadDirectory = $this->getParameter('kernel.project_dir') . '/public/uploads/photos';
                $newFilename = uniqid().'.'.$photoFile->guessExtension();
                $photoFile->move($uploadDirectory, $newFilename);
                $salledesport->setPhoto('/uploads/photos/'.$newFilename);
            } else {
                // Aucun fichier téléchargé, la photo existante reste la même
                $salledesport->setPhoto($salledesport->getPhoto());
            }
    
            $entityManager->flush();
            $this->addFlash('success', 'Le salle de sport a été modifié avec succès.');
            return $this->redirectToRoute('app_salledesport_index', [], Response::HTTP_SEE_OTHER);
        }
    
        return $this->renderForm('salledesport/edit.html.twig', [
            'salledesport' => $salledesport,
            'form' => $form,
        ]);
    }
    
    

    #[Route('/{idsalledesport}', name: 'app_salledesport_delete', methods: ['POST'])]
    public function delete(Request $request, Salledesport $salledesport, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$salledesport->getIdsalledesport(), $request->request->get('_token'))) {
            $entityManager->remove($salledesport);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_salledesport_index', [], Response::HTTP_SEE_OTHER);
    }
}
