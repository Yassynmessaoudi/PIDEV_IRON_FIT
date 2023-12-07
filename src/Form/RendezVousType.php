<?php

namespace App\Form;

use App\Entity\RendezVous;
use App\Entity\Planning;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\TimeType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;

class RendezVousType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('dateRdv', DateType::class, [
                'widget' => 'single_text',
            ])
            ->add('timeRdv', TimeType::class, [
                'input'  => 'string',
                'widget' => 'single_text',
                'with_seconds' => false,
            ])
            ->add('idClient', null, ['label' => 'Client'])
            ->add('idCoach', null, ['label' => 'Coach'])
            ->add('planning', EntityType::class, [
                'class' => Planning::class,
                'choice_label' => 'programme',
                'label' => 'Planning'
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => RendezVous::class,
        ]);
    }
}
