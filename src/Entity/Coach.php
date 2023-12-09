<?php


namespace App\Entity;

use App\Repository\CoachRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: CoachRepository::class)]
#[ORM\Table(name: "coach")]
class Coach 
{
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: "NONE")]
    #[ORM\OneToOne(targetEntity: "User")]
    #[ORM\JoinColumn(name: "IdUtilisateur", referencedColumnName: "id_user")]
    private $idutilisateur;

    #[ORM\Column(name: "age", type: "integer", nullable: true, options: ["default" => "NULL"])]
    private $age = NULL;

    #[ORM\Column(name: "username", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $username = 'NULL';

    #[ORM\Column(name: "mail", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $mail = 'NULL';

    #[ORM\Column(name: "mdp", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $mdp = 'NULL';

    #[ORM\Column(name: "image", type: "string", length: 255, nullable: true)]
    private $image = null;
    

    #[ORM\Column(name: "role", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $role = 'NULL';

    #[ORM\Column(name: "sexe", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $sexe = 'NULL';

    public function getIdUtilisateur(): ?int
    {
        return $this->idutilisateur;
    }
    
    public function setIdUtilisateur(?int $idutilisateur): self
    {
        $this->idutilisateur = $idutilisateur; $this->idutilisateur->etIdUser();
    
        return $this;
    }
   

    public function getAge(): ?int
    {
        return $this->age;
    }

    public function setAge(?int $age): self
    {
        $this->age = $age;

        return $this;
    }

    public function getUsername(): ?string
    {
        return $this->username;
    }

    public function setUsername(?string $username): self
    {
        $this->username = $username;

        return $this;
    }

    public function getMail(): ?string
    {
        return $this->mail;
    }

    public function setMail(?string $mail): self
    {
        $this->mail = $mail;

        return $this;
    }

    public function getMdp(): ?string
    {
        return $this->mdp;
    }

    public function setMdp(?string $mdp): self
    {
        $this->mdp = $mdp;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(?string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getRole(): ?string
    {
        return $this->role;
    }

    public function setRole(?string $role): self
    {
        $this->role = $role;

        return $this;
    }

    public function getSexe(): ?string
    {
        return $this->sexe;
    }

    public function setSexe(?string $sexe): self
    {
        $this->sexe = $sexe;

        return $this;
    }

    public function __toString(): string
{
    // return the property that best represents this object, for example, a name
    return $this->username;
}
}