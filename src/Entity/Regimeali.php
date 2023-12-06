<?php
namespace App\Entity;

use App\Repository\RegimealiRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: RegimealiRepository::class)]
#[ORM\Table(name: "regimeali")]
#[ORM\Index(name: "idmed", columns: ["idmed"])]
class Regimeali
{
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: "IDENTITY")]
    #[ORM\Column(name: "id", type: "integer", nullable: false)]
    private $id;

    #[ORM\Column(name: "prixRegime", type: "float", precision: 10, scale: 0, nullable: true, options: ["default" => "NULL"])]
    private $prixregime = NULL;

    #[ORM\Column(name: "typeRegime", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $typeregime = 'NULL';

    #[ORM\Column(name: "nomMED", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $nommed = 'NULL';

    #[ORM\Column(name: "prenomMED", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $prenommed = 'NULL';

    #[ORM\Column(name: "reg", type: "blob", length: 0, nullable: true, options: ["default" => "NULL"])]
    private $reg = 'NULL';

    #[ORM\ManyToOne(targetEntity: "Cabinet")]
    #[ORM\JoinColumn(name: "idmed", referencedColumnName: "id")]
    private $idmed;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getPrixregime(): ?float
    {
        return $this->prixregime;
    }

    public function setPrixregime(?float $prixregime): self
    {
        $this->prixregime = $prixregime;

        return $this;
    }

    public function getTyperegime(): ?string
    {
        return $this->typeregime;
    }

    public function setTyperegime(?string $typeregime): self
    {
        $this->typeregime = $typeregime;

        return $this;
    }

    public function getNommed(): ?string
    {
        return $this->nommed;
    }

    public function setNommed(?string $nommed): self
    {
        $this->nommed = $nommed;

        return $this;
    }

    public function getPrenommed(): ?string
    {
        return $this->prenommed;
    }

    public function setPrenommed(?string $prenommed): self
    {
        $this->prenommed = $prenommed;

        return $this;
    }

    public function getReg()
    {
        return $this->reg;
    }

    public function setReg($reg): self
    {
        $this->reg = $reg;

        return $this;
    }

    public function getIdmed(): ?Cabinet
    {
        return $this->idmed;
    }

    public function setIdmed(?Cabinet $idmed): self
    {
        $this->idmed = $idmed;

        return $this;
    }
}
