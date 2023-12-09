<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Repository\SalledesportRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: SalledesportRepository::class)]
class Salledesport
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(type: "integer")]
    private $idsalledesport;

    #[Assert\NotBlank(message: "Veuillez renseigner le nom")]
    #[ORM\Column(type: "string", length: 150)]
    private $nom;

    #[Assert\NotBlank(message: "Veuillez renseigner l'adresse")]
    #[ORM\Column(type: "string", length: 150)]
    private $adresse;

    #[Assert\NotBlank(message: "Veuillez renseigner la capacité")]
    #[ORM\Column(type: "string", length: 150)]
    private $capacite;

    #[Assert\NotBlank(message: "Veuillez renseigner le specialite")]
    #[ORM\Column(type: "string", length: 150)]
    private $specialite;

    #[ORM\Column(type: "string", length: 255, nullable: true)]
    #[Assert\File(
        maxSize: "5M",
        mimeTypes: ["image/jpeg", "image/png", "image/gif"],
        mimeTypesMessage: "Veuillez télécharger une image valide (JPEG, PNG, GIF)"
    )]
    private $photo;

    #[ORM\OneToMany(targetEntity: Abonnement::class, mappedBy: "idsalledesport", cascade: ["remove"])]
    private $abonnements;

    public function __construct()
    {
        $this->abonnements = new ArrayCollection();
    }

    public function __toString()
    {
        return $this->nom;
    }

    public function getIdsalledesport(): ?int
    {
        return $this->idsalledesport;
    }

    public function getPhoto(): ?string
    {
        return $this->photo;
    }

    public function setPhoto(?string $photo): self
    {
        $this->photo = $photo;

        return $this;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getCapacite(): ?string
    {
        return $this->capacite;
    }

    public function setCapacite(string $capacite): self
    {
        $this->capacite = $capacite;

        return $this;
    }

    public function getSpecialite(): ?string
    {
        return $this->specialite;
    }

    public function setSpecialite(string $specialite): self
    {
        $this->specialite = $specialite;

        return $this;
    }

    public function addAbonnement(Abonnement $abonnement): self
    {
        if (!$this->abonnements->contains($abonnement)) {
            $this->abonnements[] = $abonnement;
            $abonnement->setIdsalledesport($this);
        }

        return $this;
    }

    public function removeAbonnement(Abonnement $abonnement): self
    {
        if ($this->abonnements->contains($abonnement)) {
            $this->abonnements->removeElement($abonnement);
            // set the owning side to null (unless already changed)
            if ($abonnement->getIdsalledesport() === $this) {
                $abonnement->setIdsalledesport(null);
            }
        }

        return $this;
    }
}
