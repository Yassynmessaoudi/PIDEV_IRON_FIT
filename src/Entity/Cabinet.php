<?php
namespace App\Entity;

use App\Repository\CabinetRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: CabinetRepository::class)]
#[ORM\Table(name: "cabinet")]
class Cabinet
{
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: "IDENTITY")]
    #[ORM\Column(name: "id", type: "integer", nullable: false)]
    private $id;

    #[ORM\Column(name: "nomMED", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $nommed = 'NULL';

    #[ORM\Column(name: "prenomMED", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $prenommed = 'NULL';

    #[ORM\Column(name: "specialite", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $specialite = 'NULL';

    #[ORM\Column(name: "adresse", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $adresse = 'NULL';

    #[ORM\Column(name: "email", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $email = 'NULL';

    #[ORM\Column(name: "tel", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $tel = 'NULL';

    public function getId(): ?int
    {
        return $this->id;
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

    public function getSpecialite(): ?string
    {
        return $this->specialite;
    }

    public function setSpecialite(?string $specialite): self
    {
        $this->specialite = $specialite;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(?string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(?string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getTel(): ?string
    {
        return $this->tel;
    }

    public function setTel(?string $tel): self
    {
        $this->tel = $tel;

        return $this;
    }

}

