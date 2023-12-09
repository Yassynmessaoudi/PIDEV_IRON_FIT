<?php

namespace App\Form;

use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Validator\Constraints\IsTrue;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\RepeatedType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;

class RegistrationFormType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('username',TextType::class) 
            ->add('mail',TextType::class, ['attr'=>['class' => 'form-control input-sm']])                
            ->add('mdp', PasswordType::class, [
                'required' => true,
                'label_attr' => ['class' => 'form-control input-sm'],                
            ])
            ->add('sexe', ChoiceType::class, array('choices' => array('Autre' => 'Autre','Homme' => 'Homme', 'Femme' => 'Femme')), ['attr'=>['class' => 'form-control ']])
            ->add('age',TextType::class)
            ->add('role', ChoiceType::class, [
                'choices' => [
                    'Medecin' => 'Medecin',
                    'Coach' => 'Coach',
                    'Client' => 'Client',
                ],
                'label' => 'Role',
              
            ])
            ->add('image', FileType::class, [
                'required' => false,
            ])
                                    
            ->add('Register',SubmitType::class)
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => User::class,
        ]);
    }
}
