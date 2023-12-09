<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;
use Stripe\Checkout\Session;
use Stripe\Stripe;
use App\Controller\CartController;
use App\Entity\Cart;
use App\Entity\Product;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use App\Controller\ProductController;
use App\Repository\ProductRepository;

class PayementController extends AbstractController
{
    #[Route('/payement', name: 'app_payement')]
    public function index(): Response
    {
        return $this->render('payement/index.html.twig', [
            'controller_name' => 'PayementController',
        ]);
    }
    #[Route('/checkout', name: 'checkout')]
    public function checkout(SessionInterface $session, $stripeSK): Response
    {
        // Récupérez le panier depuis la session
        $cart = $session->get("cart", []);
    
        // Initialisez le tableau des articles pour Stripe
        $lineItems = [];
    
        // Parcourez le panier pour créer les éléments de ligne pour chaque produit
        foreach ($cart as $id => $quantity) {
            $product = $this->getDoctrine()->getRepository(Product::class)->find($id);
    
            if ($product !== null) {
                $lineItems[] = [
                    'price_data' => [
                        'currency' => 'usd',
                        'product_data' => [
                            'name' => $product->getProductName(),
                        ],
                        'unit_amount' => $product->getPrice() * 100, // Convertissez le prix en cents
                    ],
                    'quantity' => $quantity,
                ];
            }
        }
    
        // Créez la session Stripe avec les éléments de ligne du panier
        Stripe::setApiKey( $stripeSK);

        $sessionStripe = Session::create([
            'payment_method_types' => ['card'],
            'line_items' => $lineItems,
            'mode' => 'payment',
           # 'success_url' => 'https://example.com/success',
           
            'success_url' => $this->generateUrl('generate_pdf', [], UrlGeneratorInterface::ABSOLUTE_URL),
            'cancel_url' => 'https://example.com/cancel',
        ]);
    
        // Redirigez l'utilisateur vers la page de paiement Stripe
        return $this->redirect($sessionStripe->url, 303);
    }
    



    #[Route('/success-url', name: 'success_url')]
    public function successUrl(): Response
    {
        return $this->render('payment/success.html.twig', []);
    }

    
    
    #[Route('/cancel-url', name: 'cancel_url')]
    public function cancelUrl(): Response
    {
        return $this->render('payment/cancel.html.twig', []);
    }
}

