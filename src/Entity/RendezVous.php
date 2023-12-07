<?php

namespace App\Entity;

use App\Repository\RendezVousRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: RendezVousRepository::class)]
#[ORM\Table(name: "rendez_vous")]
#[ORM\Index(name: "id_coach", columns: ["id_coach"])]
#[ORM\Index(name: "id_client", columns: ["id_client"])]
class RendezVous
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name: "id_RDV", type: "integer", nullable: false)]
    private $idRdv;
    #[ORM\Column(name: "date_RDV", type: "date", nullable: true)]
    #[Assert\NotBlank(message: "Ce champ ne peut pas être vide.")]
    
    private $dateRdv = null;

    #[ORM\Column(name: "time_RDV", type: "string", length: 255, nullable: true)]
    #[Assert\NotBlank(message: "Ce champ ne peut pas être vide.")]
    #[Assert\Time(message: "Veuillez entrer un temps.")]
    private $timeRdv;

    #[ORM\ManyToOne(targetEntity: "Coach")]
    #[ORM\JoinColumn(name: "id_coach", referencedColumnName: "IdUtilisateur")]
    #[Assert\NotBlank(message: "Ce champ ne peut pas être vide.")]
    private $idCoach;

    #[ORM\ManyToOne(targetEntity: "Client")]
    #[ORM\JoinColumn(name: "id_client", referencedColumnName: "id_client")]
   
    private $idClient;

    public function getIdRdv(): ?int
    {
        return $this->idRdv;
    }

    public function getDateRdv(): ?\DateTime
{
    return $this->dateRdv;
}


    public function setDateRdv(?\DateTime $dateRdv): self
    {
        $this->dateRdv = $dateRdv;

        return $this;
    }

    public function getTimeRdv(): ?string
    {
        return $this->timeRdv;
    }

    public function setTimeRdv(?string $timeRdv): self
    {
        $this->timeRdv = $timeRdv;

        return $this;
    }

    public function getIdCoach(): ?Coach
    {
        return $this->idCoach;
    }

    public function setIdCoach(?Coach $idCoach): self
    {
        $this->idCoach = $idCoach;

        return $this;
    }

    public function getIdClient(): ?Client
    {
        return $this->idClient;
    }

    public function setIdClient(?Client $idClient): self
    {
        $this->idClient = $idClient;

        return $this;
    }
    #[ORM\ManyToOne(targetEntity: "Planning", inversedBy: "rendezvous")]
#[ORM\JoinColumn(name: "id_planning", referencedColumnName: "idPlanning")]
#[Assert\NotBlank(message: "Ce champ ne peut pas être vide.")]
private $planning;

public function getPlanning(): ?Planning
{
    return $this->planning;
}

public function setPlanning(?Planning $planning): self
{
    $this->planning = $planning;

    return $this;
}
}
