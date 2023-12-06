<?php
namespace App\Form;
use App\Entity\RendezVous;
use App\Entity\Planning;
use App\Entity\Coach;
use App\Entity\Client;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\TimeType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;




class RecommandeType extends AbstractType
{public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('dateRdv', DateType::class, [
            'widget' => 'single_text',
            'required' => false,
        ])
        ->add('timeRdv', TimeType::class, [
            'input'  => 'string',
            'widget' => 'single_text',
            'with_seconds' => false,
            'required' => false,
        ])
        ->add('idClient', EntityType::class, [
            'class' => Client::class,
            'choice_label' => 'username',
            'label' => 'Client',
            'disabled' => true,
            
        ])
        ->add('idCoach', EntityType::class, [
            'class' => Coach::class,
            'choice_label' => 'username',
            'label' => 'Coach',
            'disabled' => true,
        ])
        ->add('planning', EntityType::class, [
            'class' => Planning::class,
            'choice_label' => 'programme',
            'label' => 'Planning',
            'disabled' => true,
        ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => RendezVous::class,
        ]);
    }
}
