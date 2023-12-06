<?php

namespace App\Form;

use App\Entity\Salledesport;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Range;

class SalledesportType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom')
            ->add('adresse')
            ->add('capacite', IntegerType::class, [
                'constraints' => [
                  new Range([
                    'min' => 0,
                    'max' => 200,
                ]),
            ],
          ])
            ->add('specialite')
            ->add('photo', FileType::class, [
                'label' => 'image',
                'mapped' => false,
                'required' => false,
                //'disabled' => true, 
            ]);
         
        
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Salledesport::class,
        ]);
    }
}
