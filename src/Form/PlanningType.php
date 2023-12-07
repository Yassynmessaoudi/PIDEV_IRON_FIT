<?php

namespace App\Form;

use App\Entity\Planning;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\CallbackTransformer;
use Symfony\Component\HttpFoundation\File\File;

class PlanningType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('niveauprogramme', ChoiceType::class, [
                'choices'  => [
                    'AVANCE' => 'AVANCE',
                    'DEBUTANT' => 'DEBUTANT',
                ],
            ])
            ->add('programme')
            ->add('prix')
            ->add('image', FileType::class, [
                'label' => 'Image (fichier PNG)',
                'mapped' => true,
                'required' => false,
            ])
            ->add('videolink')
            ->add('description')
            ->add('idCoach',null, ['label' => 'Coach'])
        ;

        $builder->get('image')
            ->addModelTransformer(new CallbackTransformer(
                function ($image) {
                    // transform the stream to a File object
                    return $image instanceof File ? $image : null;
                },
                function ($image) {
                    // transform the File object to a stream
                    return $image;
                }
            ));
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Planning::class,
        ]);
    }
}
