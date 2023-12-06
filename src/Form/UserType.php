<?php

namespace App\Form;

use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Validator\Constraints\Image;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;

class UserType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('username', TextType::class, [
                'label' => 'Username',
                'label_attr' => ['style' => 'color: white;'], // Set label color to white
            ])
            ->add('age', TextType::class, [
                'label' => 'Age',
                'label_attr' => ['style' => 'color: white;'], // Set label color to white
            ])
            ->add('sexe', ChoiceType::class, [
                'choices' => [
                    'Masculin' => 'Masculin',
                    'Féminin' => 'Féminin',
                ],
                'label_attr' => ['style' => 'color: white;'], // Set label color to white
            ])
            ->add('role', ChoiceType::class, [
                'choices' => [
                    'Medecin' => 'Medecin',
                    'Coach' => 'Coach',
                    'Client' => 'Client',
                ],
                'label' => 'Role',
                'label_attr' => ['style' => 'color: white;'],
            ])
            
            ->add('mail', TextType::class, [
                'label' => 'Email',
                'label_attr' => ['style' => 'color: white;'], // Set label color to white
            ])
            ->add('mdp', PasswordType::class, [
                'required' => true,
                'label' => 'Password',
                'label_attr' => ['style' => 'color: white;'], // Set label color to white
                'attr' => ['style' => 'width: 200px;'], // Set text field width
            ])
            ->add('Save', SubmitType::class, [
                'label' => 'Enregistrer',
                'attr' => ['style' => 'background-color: #da2424; color: white; padding: 10px 20px; font-size: 18px; font-weight: bold; border: none; cursor: pointer; box-shadow: 0 0 5px rgba(0, 0, 0, 0.6);'],
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => User::class,
        ]);
    }
}
