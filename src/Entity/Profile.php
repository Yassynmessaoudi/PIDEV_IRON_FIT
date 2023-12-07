<?php
namespace App\Entity;

use App\Repository\ProfileRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ProfileRepository::class)]
#[ORM\Table(name: "profile")]
#[ORM\Index(name: "id_user", columns: ["id_user"])]
class Profile
{
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: "IDENTITY")]
    #[ORM\Column(name: "id_profile", type: "integer", nullable: false)]
    private $idProfile;

    #[ORM\Column(name: "username", type: "string", length: 50, nullable: false)]
    private $username;

    #[ORM\Column(name: "image", type: "string", length: 100, nullable: false)]
    private $image;

    #[ORM\Column(name: "role", type: "string", length: 20, nullable: false)]
    private $role;

    #[ORM\Column(name: "age", type: "integer", nullable: false)]
    private $age;

    #[ORM\ManyToOne(targetEntity: "User")]
    #[ORM\JoinColumn(name: "id_user", referencedColumnName: "id_user")]
    private $idUser;

    public function getIdProfile(): ?int
    {
        return $this->idProfile;
    }

    public function getUsername(): ?string
    {
        return $this->username;
    }

    public function setUsername(string $username): self
    {
        $this->username = $username;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getRole(): ?string
    {
        return $this->role;
    }

    public function setRole(string $role): self
    {
        $this->role = $role;

        return $this;
    }

    public function getAge(): ?int
    {
        return $this->age;
    }

    public function setAge(int $age): self
    {
        $this->age = $age;

        return $this;
    }

    public function getIdUser(): ?User
    {
        return $this->idUser;
    }

    public function setIdUser(?User $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }
}
