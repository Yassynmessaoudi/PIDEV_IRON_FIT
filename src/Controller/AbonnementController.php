<?php

namespace App\Controller;

use App\Entity\Abonnement;
use App\Form\AbonnementType;
use App\Repository\AbonnementRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use App\Entity\Salledesport;
use Twilio\Rest\Client;

class AbonnementController extends AbstractController

{
    #[Route('/homepage', name: 'homepage', methods: ['GET'])]
    public function indexx(): Response
    {
        return $this->render('base.html.twig');
    }
    

    #[Route('/IndexAbonnement', name: 'app_abonnement_index', methods: ['GET'])]
    public function index(Request $request, AbonnementRepository $abonnementRepository): Response
    {
        $type = $request->query->get('type');
        $prix = $request->query->get('prix');
    
     
        $abonnements = $abonnementRepository->findByCriteria($type, $prix);
        $totalAbonnement = $abonnementRepository->createQueryBuilder('s')
        ->select('COUNT(s.id)')
        ->getQuery()
        ->getSingleScalarResult();
    
        return $this->render('abonnement/index.html.twig', [
            'abonnements' => $abonnements,
            'totalabonnement'=> $totalAbonnement,
        ]);
    }
    
    
    #[Route('/IndexuserAbonnement', name: 'app_abonnement_indexuser', methods: ['GET'])]
    public function indexuser(Request $request, AbonnementRepository $abonnementRepository): Response
    {
        $type = $request->query->get('type');
        $prix = $request->query->get('prix');
    
 
        $abonnements = $abonnementRepository->findByCriteria($type, $prix);
      
          $abonnements = $abonnementRepository->findByCriteria($type, $prix);
          $totalAbonnement = $abonnementRepository->createQueryBuilder('s')
          ->select('COUNT(s.id)')
          ->getQuery()
          ->getSingleScalarResult();
    
        return $this->render('abonnement/indexuser.html.twig', [
            'abonnements' => $abonnements,
            'totalabonnement'=> $totalAbonnement,
        ]);
    }

  
    #[Route('/NewAbonnement', name: 'app_abonnement_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $abonnement = new Abonnement();
        $form = $this->createForm(AbonnementType::class, $abonnement);
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            $type = $abonnement->getType();
    
            $prix = null;
    
            switch ($type) {
                case 'monthly':
                    $prix = 100;
                    break;
                case 'premium':
                    $prix = 80;
                    break;
                case 'marie':
                    $prix = 120;
                    break;
                case 'freres':
                case 'mineur':
                    $prix = 110;
                    break;
                case 'offre 1 year':
                    $prix = 500;
                    break;
                default:
            }
    
            $abonnement->setPrix($prix);
    
    
            $entityManager->persist($abonnement);
            $entityManager->flush();
    
        
            $salleDeSport = $abonnement->getIdsalledesport();
            if ($salleDeSport) {
                $currentCapacity = $salleDeSport->getCapacite();
                if ($currentCapacity > 0) {
                    $salleDeSport->setCapacite($currentCapacity - 1);
                    $entityManager->persist($salleDeSport);
                    $entityManager->flush();
                }
            }
            $this->addFlash('success', 'L abonnement a été ajouté aux salle de sport avec succès.');
            return $this->redirectToRoute('app_abonnement_index', [], Response::HTTP_SEE_OTHER);
        }
    
        return $this->renderForm('abonnement/new.html.twig', [
            'abonnement' => $abonnement,
            'form' => $form,
        ]);
    }


    #[Route('/NewuserAbonnement/{idsalledesport}', name: 'app_abonnement_newuser', methods: ['GET', 'POST'])]
    public function newuser(Request $request, EntityManagerInterface $entityManager, MailerInterface $mailer, int $idsalledesport): Response
    {
      
        $salleDeSport = $this->getDoctrine()->getRepository(Salledesport::class)->find($idsalledesport);
      
 
        if (!$salleDeSport) {
            throw $this->createNotFoundException('Salle de sport non trouvée');
        }
    
        $abonnement = new Abonnement();
  
        $abonnement->setIdsalledesport($salleDeSport);
    
        $form = $this->createForm(AbonnementType::class, $abonnement);
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            $type = $abonnement->getType();
    
            $prix = null;
    
            switch ($type) {
                case 'monthly':
                    $prix = 100;
                    break;
                case 'premium':
                    $prix = 80;
                    break;
                case 'marie':
                    $prix = 120;
                    break;
                case 'freres':
                case 'mineur':
                    $prix = 110;
                    break;
                case 'offre 1 year':
                    $prix = 500;
                    break;
                default:
            }
    
            $abonnement->setPrix($prix);
    
            $entityManager->persist($abonnement);
            $entityManager->flush();
    
            $currentCapacity = $salleDeSport->getCapacite();
            if ($currentCapacity > 0) {
                $salleDeSport->setCapacite($currentCapacity - 1);
                $entityManager->persist($salleDeSport);
                $entityManager->flush();
            }
    $abonnementType = $abonnement->getType(); 
    $sid = 'AC3ea7e1fcfaceb9a2e1e55726abda4c92';
    $token = '23d2a01ee3064c1d38d995a27d24c028';
    $twilioNumber = '+15735615024'; 
    $adminPhoneNumber = '+21696652530'; 

    $twilio = new Client($sid, $token);

    $messageBody = "Nouvel abonnement ajouté. Type: " . $abonnementType; 

    $twilio->messages->create(
        $adminPhoneNumber,
        [
            'from' => $twilioNumber,
            'body' => $messageBody,
        ]
    );
    $this->addFlash('success', 'L abonnement a été ajouté aux salle de sport avec succès.');
    
            return $this->redirectToRoute('app_abonnement_indexuser', [], Response::HTTP_SEE_OTHER);
        }
    
        return $this->renderForm('abonnement/newuser.html.twig', [
            'abonnement' => $abonnement,
            'form' => $form,
        ]);
    }
    

    #[Route('/ShowAbonnement/{id}', name: 'app_abonnement_show', methods: ['GET'])]
    public function show(Abonnement $abonnement): Response
    {
        return $this->render('abonnement/show.html.twig', [
            'abonnement' => $abonnement,
        ]);
    }
    #[Route('/Showuser/{id}', name: 'app_abonnement_showuser', methods: ['GET'])]
    public function showuser(Abonnement $abonnement): Response
    {
        return $this->render('abonnement/showuser.html.twig', [
            'abonnement' => $abonnement,
        ]);
    }

    #[Route('/{id}/EditAbonnement', name: 'app_abonnement_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Abonnement $abonnement, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(AbonnementType::class, $abonnement);
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            $type = $abonnement->getType();
            $prix = null;
    
            switch ($type) {
                case 'monthly':
                    $prix = 100;
                    break;
                case 'premium':
                    $prix = 80;
                    break;
                case 'marie':
                    $prix = 120;
                    break;
                case 'freres':
                case 'mineur':
                    $prix = 110;
                    break;
                case 'offre 1 year':
                    $prix = 500;
                    break;
                default:
                   
            }
    
         
            $abonnement->setPrix($prix);
    
            $entityManager->flush();
            $this->addFlash('success', 'L abonnement a été modifié aux salle de sport avec succès.');
            return $this->redirectToRoute('app_abonnement_index', [], Response::HTTP_SEE_OTHER);
        }
    
        return $this->renderForm('abonnement/edit.html.twig', [
            'abonnement' => $abonnement,
            'form' => $form,
        ]);
    }
    

    #[Route('/DeleteAbonnement/{id}', name: 'app_abonnement_delete', methods: ['POST'])]
    public function delete(Request $request, Abonnement $abonnement, EntityManagerInterface $entityManager): Response
    {
        $entityManager->remove($abonnement);
        $entityManager->flush();
    
        $salleDeSport = $abonnement->getIdsalledesport();
        if ($salleDeSport) {
            $currentCapacity = $salleDeSport->getCapacite();
            $salleDeSport->setCapacite($currentCapacity + 1);
            $entityManager->persist($salleDeSport);
            $entityManager->flush();
        }
    
        return $this->redirectToRoute('app_abonnement_index', [], Response::HTTP_SEE_OTHER);
    }
    
}
