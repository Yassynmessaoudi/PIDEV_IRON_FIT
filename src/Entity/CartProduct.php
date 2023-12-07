<?php

namespace App\Entity;

use App\Repository\CartProductRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: CartProductRepository::class)]
#[ORM\Table(name: "cart_product")]
#[ORM\UniqueConstraint(name: "UNIQ_cart_product", columns: ["cart_id", "product_id"])]
#[ORM\Index(name: "product_id", columns: ["product_id"])]
class CartProduct
{
    #[ORM\ManyToOne(targetEntity: "Product")]
    #[ORM\JoinColumn(name: "product_id", referencedColumnName: "id")]
    private $product;

    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: "NONE")]
    #[ORM\OneToOne(targetEntity: "Cart")]
    #[ORM\JoinColumn(name: "cart_id", referencedColumnName: "id")]
    private $cart;

    public function getProduct(): ?Product
    {
        return $this->product;
    }

    public function setProduct(?Product $product): self
    {
        $this->product = $product;

        return $this;
    }

    public function getCart(): ?Cart
    {
        return $this->cart;
    }

    public function setCart(?Cart $cart): self
    {
        $this->cart = $cart;

        return $this;
    }
}
