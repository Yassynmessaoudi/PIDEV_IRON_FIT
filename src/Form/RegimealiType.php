<?php

namespace App\Form;

use App\Entity\Regimeali;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class RegimealiType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('prixregime')
            ->add('typeregime')
            ->add('nommed')
            ->add('prenommed')
            ->add('idmed')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Regimeali::class,
        ]);
    }
}
