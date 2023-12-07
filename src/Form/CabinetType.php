<?php

namespace App\Form;

use App\Entity\Cabinet;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class CabinetType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nomMED')
            ->add('prenomMED')
            ->add('specialite', ChoiceType::class, [
                'choices' => [
                    'Sports Medicine' => 'Sports Medicine',
                    'Weight Loss Cabinet' => 'Weight Loss Cabinet',
                    'Muscle Building Cabinet' => 'Muscle Building Cabinet',
                    'Endurance Athlete Cabinet' => 'Endurance Athlete Cabinet',
                    'Bodybuilding Cabinet' => 'Bodybuilding Cabinet',
                    'General Health Cabinet' => 'General Health Cabinet',
                ]])
            ->add('adresse')
            ->add('email')
            ->add('tel')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Cabinet::class,
        ]);
    }
}
