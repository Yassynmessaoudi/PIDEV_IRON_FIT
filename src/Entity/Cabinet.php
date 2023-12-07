<?php

namespace App\Entity;

use App\Repository\CabinetRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: CabinetRepository::class)]
class Cabinet
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(name: "nomMED", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    #[Assert\NotBlank (message:'Medecin Name cannot be blank')]
    #[Assert\Regex("/^[a-zA-Z\s]+$/", message:"Medecin Name should not contain special characters")]
    private ?string $nomMED = null;

    #[Assert\NotBlank (message:'Medecin First Name cannot be blank')]
    #[ORM\Column(name: "prenomMED", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private ?string $prenomMED = null;

    #[Assert\NotBlank (message:'Medecin Must have a speciality')]
    #[ORM\Column(length: 255, nullable: true)]
    private ?string $specialite = null;

    #[Assert\NotBlank (message:'Medecin Must have an address')]

    #[ORM\Column(length: 255, nullable: true)]
    private ?string $adresse = null;

    #[Assert\NotBlank (message:'Medecin Must have an email')]
    #[Assert\Email(message: 'The email {{ value }} is not a valid email.')]
    #[ORM\Column(length: 255, nullable: true)]
    private ?string $email = null;

    #[Assert\NotBlank (message:'Medecin Must have a phone number')]
    #[Assert\Regex("/^[0-9]*$/", message:"Medecin Phone Number should not contain special characters")]
    #[Assert\Length(max: 8, maxMessage: "Medecin Phone Number should not be more than 8 digits")]
    #[ORM\Column(name: "tel", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private ?string $tel = null;

    #[ORM\OneToMany(mappedBy: 'idCabinet', targetEntity: Regime::class)]
    private Collection $regimes;


    public function __construct()
    {
        $this->regimes = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomMED(): ?string
    {
        return $this->nomMED;
    }

    public function setNomMED(?string $nomMED): static
    {
        $this->nomMED = $nomMED;

        return $this;
    }

    public function getPrenomMED(): ?string
    {
        return $this->prenomMED;
    }

    public function setPrenomMED(?string $prenomMED): static
    {
        $this->prenomMED = $prenomMED;

        return $this;
    }

    public function getSpecialite(): ?string
    {
        return $this->specialite;
    }

    public function setSpecialite(?string $specialite): static
    {
        $this->specialite = $specialite;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(?string $adresse): static
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(?string $email): static
    {
        $this->email = $email;

        return $this;
    }

    public function getTel(): ?string
    {
        return $this->tel;
    }

    public function setTel(string $tel): static
    {
        $this->tel = $tel;

        return $this;
    }

    /**
     * @return Collection<int, Regime>
     */
    public function getRegimes(): Collection
    {
        return $this->regimes;
    }

    public function addRegime(Regime $regime): static
    {
        if (!$this->regimes->contains($regime)) {
            $this->regimes->add($regime);
            $regime->setNomMED($this);
        }

        return $this;
    }
    public function __toString(): string
    {
        return $this->getNomMED();
    }

    public function removeRegime(Regime $regime): static
    {
        if ($this->regimes->removeElement($regime)) {
            // set the owning side to null (unless already changed)
            if ($regime->getIdCabinet() === $this) {
                $regime->setIdCabinet(null);
            }
        }

        return $this;
    }

}
