<?php

namespace App\Controller;
use App\Entity\Product;
use App\Entity\Cart;
use App\Form\CartType;
use App\Repository\CartRepository;
use App\Repository\ProductRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Dompdf\Dompdf;
use Dompdf\Options;

#[Route('/cart')]
class CartController extends AbstractController
{
    #[Route('/', name: 'app_cart_index', methods: ['GET'])]
   
    public function index(SessionInterface $session, ProductRepository $productRepository)
    {
        $cart = $session->get("cart", []);
    
        // On "fabrique" les données
        $dataPanier = [];
        $total = 0;
    
        foreach ($cart as $id => $quantity) {
            $product = $productRepository->find($id);
    
            if ($product !== null) {
                $dataPanier[] = [
                    "product" => $product,
                    "quantity" => $quantity
                ];
                $total += $product->getPrice() * $quantity;
            } else {
                // Gérer le cas où le produit n'est pas trouvé (peut-être lever une exception ou afficher un message d'erreur)
            }
        }
    
        return $this->render('cart/index.html.twig', compact("dataPanier", "total"));
    }
    

  /*  #[Route('/new', name: 'app_cart_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $cart = new Cart();
        $form = $this->createForm(CartType::class, $cart);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($cart);
            $entityManager->flush();

            return $this->redirectToRoute('app_cart_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('cart/new.html.twig', [
            'cart' => $cart,
            'form' => $form,
        ]);
    }*/

    /*#[Route('/{id}', name: 'app_cart_show', methods: ['GET'])]
    public function show(Cart $cart): Response
    {
        return $this->render('cart/show.html.twig', [
            'cart' => $cart,
        ]);
    }*/

/*    #[Route('/{id}/edit', name: 'app_cart_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Cart $cart, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(CartType::class, $cart);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_cart_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('cart/edit.html.twig', [
            'cart' => $cart,
            'form' => $form,
        ]);
    }
*/

   /* #[Route('/{id}', name: 'app_cart_delete', methods: ['POST'])]
    public function delete(Request $request, Cart $cart, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$cart->getId(), $request->request->get('_token'))) {
            $entityManager->remove($cart);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_cart_index', [], Response::HTTP_SEE_OTHER);
    }*/
    #[Route('/add/{id}', name: 'app_cart_add', methods: ['GET'])]
    // Exemple d'utilisation dans votre contrôleur

    public function addProductToCart(Product $product, SessionInterface $session)
    {
        $cart = $session->get("cart", []);
        $id = $product->getId();
    
        if(!empty($cart[$id])){
            $cart[$id]++;
        }else{
            $cart[$id] = 1;
        }
    
        // On sauvegarde dans la session
        $session->set("cart", $cart);
    
        return $this->redirectToRoute('app_cart_index');
    }
    
  /**
     * @Route("/remove/{id}", name="remove")
     */
    public function remove(Product $product, SessionInterface $session)
{
    // On récupère le panier actuel
    $cart = $session->get("cart", []);
    $id = $product->getId();

    if(!empty($cart[$id])){
        if($cart[$id] > 1){
            $cart[$id]--;
        }else{
            unset($cart[$id]);
        }
    }

    // On sauvegarde dans la session
    $session->set("cart", $cart);

    return $this->redirectToRoute('app_cart_index');
}

    /**
     * @Route("/delete/{id}", name="delete")
     */
    public function delete(Product $product, SessionInterface $session)
    {
        // On récupère le panier actuel
        $cart = $session->get("cart", []);
        $id = $product->getId();

        if(!empty($cart[$id])){
            unset($cart[$id]);
        }

        // On sauvegarde dans la session
        $session->set("cart", $cart);

        return $this->redirectToRoute("app_cart_index");
    }

    /**
     * @Route("/delete", name="delete_all")
     */
    public function deleteAll(SessionInterface $session)
    {
        $session->remove("cart");

        return $this->redirectToRoute("app_cart_index");
    }
     /**
 * @Route("/generate-pdf", name="generate_pdf")
 */
public function generatePdf(SessionInterface $session, ProductRepository $productRepository)
{
    $cart = $session->get("cart", []);

    // On "fabrique" les données
    $dataPanier = [];
    $total = 0;

    foreach ($cart as $id => $quantity) {
        $product = $productRepository->find($id);

        if ($product !== null) {
            $dataPanier[] = [
                "product" => $product,
                "quantity" => $quantity
            ];
            $total += $product->getPrice() * $quantity;
        } else {
            // Gérer le cas où le produit n'est pas trouvé (peut-être lever une exception ou afficher un message d'erreur)
        }
    } // Assurez-vous que cette accolade est présente pour fermer correctement la boucle

    $html = $this->renderView('cart/pdf.html.twig', [
        'cart' => $cart,
        'total' => $total,
        'dataPanier' => $dataPanier,
    ]);

    // Configurez Dompdf
    $pdfOptions = new Options();
    $pdfOptions->set('defaultFont', 'Arial');

    // Instanciez Dompdf avec nos options
    $dompdf = new Dompdf($pdfOptions);

    // Chargez le HTML dans Dompdf
    $dompdf->loadHtml($html);

    // (Optionnel) Configurez la taille et l'orientation du papier
    $dompdf->setPaper('A4', 'portrait');

    // Rendre le HTML en PDF
    $dompdf->render();

    // Stockez le contenu binaire du PDF
    $output = $dompdf->output();

    // Générez un nom de fichier
    $filename = 'invoice.pdf';

    // Écrivez le PDF généré dans un fichier
    file_put_contents($filename, $output);

    // Retournez une réponse avec le fichier PDF
    return new Response($output, 200, [
        'Content-Type' => 'application/pdf',
        'Content-Disposition' => 'inline; filename="' . $filename . '"',
    ]);
}
}