<?php

namespace App\Controller;

use App\Entity\User;
use App\Repository\UserRepository;

use App\Form\UserType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\Persistence\ManagerRegistry;
#[Route('/user')]
class UserController extends AbstractController
{





    
    #[Route('/listUti', name: 'list_uti')]
    public function listUtilisateur(UserRepository $utilisateurrepository): Response
    {
        
        return $this->render('user/listUtilisateur.html.twig', [
            'utilisateur' => $utilisateurrepository->findAll(),
        ]);
    }

    #[Route('/', name: 'app_user_index', methods: ['GET'])]
    public function index(UserRepository $userRepository): Response
    {
        return $this->render('user/index.html.twig', [
            'users' => $userRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_user_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $user = new User();
        $form = $this->createForm(UserType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($user);
            $entityManager->flush();

            return $this->redirectToRoute('list_uti', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('user/new.html.twig', [
            'user' => $user,
            'form' => $form,
        ]);
    }

    #[Route('/{idUser}', name: 'app_user_show', methods: ['GET'])]
    public function show(User $user): Response
    {
        return $this->render('user/show.html.twig', [
            'user' => $user,
        ]);
    }

    #[Route('/{idUser}/edit', name: 'app_user_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, User $idUser, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(UserType::class, $idUser);
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();
    
            return $this->redirectToRoute('list_uti', [], Response::HTTP_SEE_OTHER);
        }
    
        return $this->renderForm('user/edit.html.twig', [
            'user' => $idUser,
            'form' => $form,
        ]);
    }
    

    #[Route('/utilisateur/delete/{id}', name: 'app_user_delete')]
    public function delete($id, ManagerRegistry $manager, UserRepository $utilisateurRepository): Response
    {
        $em = $manager->getManager();
        $utilisateur = $utilisateurRepository->find($id);
    
        if (!$utilisateur) {
            throw $this->createNotFoundException('Utilisateur non trouvé');
        }
    
        $em->remove($utilisateur);
        $em->flush();
    
        return $this->redirectToRoute('list_uti');
    } 



    
}
