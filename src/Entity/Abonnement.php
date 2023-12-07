<?php
namespace App\Entity;


use App\Repository\AbonnementRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: AbonnementRepository::class)]
#[ORM\Table(name: "abonnement")]
#[ORM\Index(name: "idsalledesport", columns: ["idsalledesport"])]
class Abonnement
{
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: "IDENTITY")]
    #[ORM\Column(name: "id", type: "integer", nullable: false)]
    private $id;

    #[ORM\Column(name: "type", type: "string", length: 150, nullable: true, options: ["default" => "NULL"])]
    private $type = 'NULL';

    #[ORM\Column(name: "datedebut", type: "string", length: 150, nullable: true, options: ["default" => "NULL"])]
    private $datedebut = 'NULL';

    #[ORM\Column(name: "datefin", type: "string", length: 150, nullable: true, options: ["default" => "NULL"])]
    private $datefin = 'NULL';

    #[ORM\Column(name: "prix", type: "float", precision: 255, scale: 0, nullable: true, options: ["default" => "NULL"])]
    private $prix = NULL;

    #[ORM\ManyToOne(targetEntity: "Salledesport")]
    #[ORM\JoinColumn(name: "idsalledesport", referencedColumnName: "idsalledesport")]
    private $idsalledesport;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(?string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getDatedebut(): ?string
    {
        return $this->datedebut;
    }

    public function setDatedebut(?string $datedebut): self
    {
        $this->datedebut = $datedebut;

        return $this;
    }

    public function getDatefin(): ?string
    {
        return $this->datefin;
    }

    public function setDatefin(?string $datefin): self
    {
        $this->datefin = $datefin;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(?float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getIdsalledesport(): ?Salledesport
    {
        return $this->idsalledesport;
    }

    public function setIdsalledesport(?Salledesport $idsalledesport): self
    {
        $this->idsalledesport = $idsalledesport;

        return $this;
    }
}
