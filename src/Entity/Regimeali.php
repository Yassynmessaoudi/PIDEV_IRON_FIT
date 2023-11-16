<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Entity\Medecin;
use App\Repository\RegimealiRepository;

#[ORM\Entity(repositoryClass: RegimealiRepository::class)]
class Regimeali
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column]
    private ?float $prixregime = null;

    #[ORM\Column(length: 150)]
    private ?string $typeregime = null;

    #[ORM\Column(length: 150)]
    private ?string  $nommed = null;

    #[ORM\Column(length: 150)]
    private ?string  $prenommed = null;

    #[ORM\ManyToOne(targetEntity: Medecin::class, inversedBy: 'regimealis', cascade: ['persist'])]
    #[ORM\JoinColumn(name: 'idmed', referencedColumnName: 'id', onDelete: 'CASCADE')]
    private ?Medecin $idmed = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getPrixregime(): ?float
    {
        return $this->prixregime;
    }

    public function setPrixregime(float $prixregime): static
    {
        $this->prixregime = $prixregime;

        return $this;
    }

    public function getTyperegime(): ?string
    {
        return $this->typeregime;
    }

    public function setTyperegime(string $typeregime): static
    {
        $this->typeregime = $typeregime;

        return $this;
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

    public function getIdmed(): ?Medecin
    {
        return $this->idmed;
    }

    public function setIdmed(?Medecin $idmed): static
    {
        $this->idmed = $idmed;

        return $this;
    }
}
