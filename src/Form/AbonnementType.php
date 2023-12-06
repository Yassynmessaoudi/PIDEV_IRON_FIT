<?php

namespace App\Form;

use App\Entity\Abonnement;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\TextType; 
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Validator\Constraints\DateTime;
use Symfony\Component\Validator\Constraints\Regex;
use Symfony\Component\Validator\Constraints\Type;
use Symfony\Component\Validator\Constraints\GreaterThan;
use Symfony\Component\Validator\Constraints\LessThanOrEqual;
use VictorPrdh\RecaptchaBundle\Form\ReCaptchaType;
use Symfony\Component\Form\FormEvent;
use Symfony\Component\Form\FormEvents;
use Symfony\Component\Validator\Constraints\Expression;

use Symfony\Component\Validator\Constraints\Callback;
use Symfony\Component\Validator\Context\ExecutionContextInterface;



class AbonnementType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('type', ChoiceType::class, [
            'choices' => [
                'Monthly' => 'monthly',
                'Premium' => 'premium',
                'Marie' => 'marie',
                'Freres' => 'freres',
                'Mineur' => 'mineur',
                'Offre 1 year' => 'offre 1 year', 
            ],
            'constraints' => [
                new NotBlank(),
            ],
           
            
        ])
        ->addEventListener(FormEvents::PRE_SUBMIT, function (FormEvent $event) {
            $data = $event->getData();
            $form = $event->getForm();

            if ($data['type'] === 'monthly' || $data['type'] === 'premium' || $data['type'] === 'marie' || $data['type'] === 'freres' || $data['type'] === 'mineur') {
              
                $datedebut = new \DateTime($data['datedebut']);
                
             
                $datefin = clone $datedebut;
                $datefin->modify('+1 month');
                
               
                $data['datedebut'] = $datedebut->format('Y-m-d');
                $data['datefin'] = $datefin->format('Y-m-d');

          
                $event->setData($data);
            } elseif ($data['type'] === 'offre 1 year') {
                
                $datedebut = new \DateTime($data['datedebut']);
                
           
                $datefin = clone $datedebut;
                $datefin->modify('+1 year');
                
             
                $data['datedebut'] = $datedebut->format('Y-m-d');
                $data['datefin'] = $datefin->format('Y-m-d');

       
                $event->setData($data);
            }
        })
        ->add('datedebut', TextType::class, [
            'constraints' => [
                new Callback(function ($value, ExecutionContextInterface $context) {
               
                    if (!preg_match('/^\d{4}-\d{2}-\d{2}$/', $value)) {
                        $context->buildViolation('Le format de la date doit être YYYY-MM-DD.')
                            ->addViolation();
                        return;
                    }
                    
               
                    $enteredDate = \DateTime::createFromFormat('Y-m-d', $value);
        
                
                    if ($enteredDate < new \DateTime()) {
                        $context->buildViolation('La date doit être égale ou postérieure à la date actuelle.')
                            ->addViolation();
                    }
                }),
            ],
        ])
            ->add('datefin', TextType::class, [ 
                'constraints' => [
                    new NotBlank(),
                    new Type('string'),
                    new Regex([
                        'pattern' => '/^\d{4}-\d{2}-\d{2}$/',
                        'message' => 'Le format de la date doit être YYYY-MM-DD.',
                    ]),


                ],
            ])
            ->add('prix', null, [
                'constraints' => [
                    new NotBlank(),
                    new Type('numeric'),
                    new GreaterThan(0),
                ],
            ])
            ->add("recaptcha", ReCaptchaType::class)
            ->add('idsalledesport', EntityType::class, [
                'class' => 'App\Entity\Salledesport',
                'choice_label' => 'nom',

            ])
            ->setAttributes(['novalidate' => 'novalidate']); 
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Abonnement::class,
        ]);
    }
}
