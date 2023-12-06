<?php

namespace App\Form;

use App\Entity\Product;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Vich\UploaderBundle\Form\Type\VichImageType;
class ProductType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('productName', null, [
            'constraints' => [
                new Assert\NotBlank(),
                new Assert\Length(['min' => 2, 'max' => 255]),
            ],
        ])
        ->add('description', null, [
            'constraints' => [
                new Assert\NotBlank(),
                new Assert\Length(['min' => 5]),
            ],
        ])
      //  ->add('image', null, [
       //     'required' => false,
        //    'mapped' => false,
      //  ])
        ->add('price', null, [
            'constraints' => [
                new Assert\NotBlank(),
                new Assert\Positive(),
            ],
        ])
        ->add('quantityInStock', null, [
            'constraints' => [
                new Assert\NotBlank(),
                new Assert\Positive(),
            ],
        ])
        ->add('idc')
        ->add('imageFile', VichImageType::class, [
            'label' => 'Image du produit',
            'required' => false, // Si l'image n'est pas obligatoire
            'mapped' => true,
              // Ne pas mapper ce champ sur une propriété de l'entité
        ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Product::class,
        ]);
    }
}
