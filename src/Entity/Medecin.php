<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\Collection;
use App\Repository\MedecinRepository;

#[ORM\Entity(repositoryClass: MedecinRepository::class)]
class Medecin
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 150)]
    private ?string $nommed = null;

    #[ORM\Column(length: 150)]
    private ?string $prenommed = null;

    #[ORM\Column(length: 150)]
    private ?string $specialite = null;

    #[ORM\Column(length: 150)]
    private ?string $adresse = null;

    #[ORM\Column(length: 150)]
    private ?string $email = null;

    #[ORM\Column(length: 150)]
    private ?string $tel = null;

    #[ORM\OneToMany(mappedBy: 'idmed', targetEntity: Regimeali::class, cascade: ['persist', 'remove'])]
    private Collection $regimealis;

    public function __construct()
    {
        $this->regimealis = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNommed(): ?string
    {
        return $this->nommed;
    }

    public function setNommed(string $nommed): static
    {
        $this->nommed = $nommed;

        return $this;
    }

    public function getPrenommed(): ?string
    {
        return $this->prenommed;
    }

    public function setPrenommed(string $prenommed): static
    {
        $this->prenommed = $prenommed;

        return $this;
    }

    public function getSpecialite(): ?string
    {
        return $this->specialite;
    }

    public function setSpecialite(string $specialite): static
    {
        $this->specialite = $specialite;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): static
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): static
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
     * @return Collection<int, Regimeali>
     */
    public function getRegimealis(): Collection
    {
        return $this->regimealis;
    }

    public function addRegimeali(Regimeali $regimeali): static
    {
        if (!$this->regimealis->contains($regimeali)) {
            $this->regimealis->add($regimeali);
            $regimeali->setIdmed($this);
        }

        return $this;
    }

    public function removeRegimeali(Regimeali $regimeali): static
    {
        if ($this->regimealis->removeElement($regimeali)) {
            // set the owning side to null (unless already changed)
            if ($regimeali->getIdmed() === $this) {
                $regimeali->setIdmed(null);
            }
        }

        return $this;
    }

    public function __toString()
    {
        return sprintf('%s %s', $this->getPrenommed(), $this->getNommed());
    }
}
