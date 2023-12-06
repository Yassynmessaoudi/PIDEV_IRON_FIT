<?php
namespace App\Entity;

use App\Repository\PlanningRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Validator\Constraints as Assert;


#[ORM\Entity(repositoryClass: PlanningRepository::class)]
#[ORM\Table(name: "planning")]
#[ORM\Index(name: "id_coach", columns: ["id_coach"])]
class Planning
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name: "idPlanning", type: "integer", nullable: false)]
    private $idplanning;

    #[ORM\Column(name: "niveauProgramme", type: "string", length: 255, nullable: true)]
    #[Assert\NotBlank(message: "Ce champ ne peut pas être vide.")]
private $niveauprogramme;

#[ORM\Column(name: "programme", type: "string", length: 255, nullable: true)]
#[Assert\NotBlank(message: "Ce champ ne peut pas être vide.")]
private $programme;

#[ORM\Column(name: "prix", type: "float", precision: 10, scale: 0, nullable: true)]
#[Assert\NotBlank(message: "Ce champ ne peut pas être vide.")]
#[Assert\Type(type: "float", message: "Le prix doit être un nombre à virgule flottante.")]
private $prix;

#[ORM\Column(name: "image", type: "blob", length: 0, nullable: true)]
#[Assert\NotBlank(message: "Ce champ ne peut pas être vide.")]
private $image;

#[ORM\Column(name: "videoLink", type: "string", length: 255, nullable: true)]
#[Assert\NotBlank(message: "Ce champ ne peut pas être vide.")]
 
private $videolink;

#[ORM\Column(name: "description", type: "text", length: 65535, nullable: true)]
#[Assert\NotBlank(message: "Ce champ ne peut pas être vide.")]
private $description;

#[ORM\Column(name: "views", type: "integer", nullable: true)]
private $views = 0;

    #[ORM\ManyToOne(targetEntity: "Coach")]
    #[ORM\JoinColumn(name: "id_coach", referencedColumnName: "IdUtilisateur")]
    #[Assert\NotBlank(message: "Ce champ ne peut pas être vide.")]
    private $idCoach;

    public function getIdPlanning(): ?int
    {
        return $this->idplanning;
    }

    public function getNiveauProgramme(): ?string
    {
        return $this->niveauprogramme;
    }

    public function setNiveauProgramme(?string $niveauprogramme): self
    {
        $this->niveauprogramme = $niveauprogramme;

        return $this;
    }

    public function getProgramme(): ?string
    {
        return $this->programme;
    }

    public function setProgramme(?string $programme): self
    {
        $this->programme = $programme;

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

    public function getImage()
    {
        return $this->image;
    }

    public function setImage($image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getVideoLink(): ?string
    {
        return $this->videolink;
    }

    public function setVideoLink(?string $videolink): self
    {
        $this->videolink = $videolink;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(?string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getViews(): ?int
    {
        return $this->views;
    }

    public function setViews(?int $views): self
    {
        $this->views = $views;

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
    
    #[ORM\OneToMany(targetEntity: "RendezVous", mappedBy: "planning", cascade: ["remove"])]
    private $rendezvous;
    

public function __construct()
{
    $this->rendezvous = new ArrayCollection();
}

public function getRendezvous(): Collection
{
    return $this->rendezvous;
}

public function addRendezvous(RendezVous $rendezvous): self
{
    if (!$this->rendezvous->contains($rendezvous)) {
        $this->rendezvous[] = $rendezvous;
        $rendezvous->setPlanning($this);
    }

    return $this;
}

public function removeRendezvous(RendezVous $rendezvous): self
{
    if ($this->rendezvous->removeElement($rendezvous)) {
        
        if ($rendezvous->getPlanning() === $this) {
            $rendezvous->setPlanning(null);
        }
    }

    return $this;


}

    public function __toString(): string
    {
        return $this->programme;
    }

    public function addRendezvou(RendezVous $rendezvou): self
    {
        if (!$this->rendezvous->contains($rendezvou)) {
            $this->rendezvous->add($rendezvou);
            $rendezvou->setPlanning($this);
        }

        return $this;
    }

    public function removeRendezvou(RendezVous $rendezvou): self
    {
        if ($this->rendezvous->removeElement($rendezvou)) {
            // set the owning side to null (unless already changed)
            if ($rendezvou->getPlanning() === $this) {
                $rendezvou->setPlanning(null);
            }
        }

        return $this;
    }

   



}
