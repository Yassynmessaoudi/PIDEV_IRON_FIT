<?php
namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Security\Core\User\PasswordAuthenticatedUserInterface;
use Symfony\Component\Security\Core\User\UserInterface;

#[ORM\Entity(repositoryClass: UserRepository::class)]
#[ORM\Table(name: "user")]
#[UniqueEntity(fields: ['mail'], message: 'There is already an account with this mail')]
class User implements UserInterface, PasswordAuthenticatedUserInterface
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name: "id_user", type: "integer", nullable: false)]
    #[ORM\OneToOne(targetEntity: "User")]
#[ORM\JoinColumn(name: "id_client", referencedColumnName: "id_user")]

    private $id_user;

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
    

    #[ORM\Column(length: 255 ,type:"string", columnDefinition:"ENUM('Medecin', 'Coach', 'Client','Admin')")]
    private $role = 'NULL';

    #[ORM\Column(name: "sexe", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $sexe = 'NULL';

    public function getIdUser(): ?int
{
    return $this->id_user;
}

    public function setIdUser(?int $id_user): self
    {
        $this->id_user = $id_user;

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
/**
     * @see UserInterface
     */
    public function getRoles(): array
    {
        $roles = [];
        
        if($this->role == 'Medecin'){
            $roles[] = 'ROLE_Medecin';
        }elseif($this->role == 'Client'){   
            $roles[] = 'ROLE_Client';
        }elseif($this->role == 'Coach'){   
            $roles[] = 'ROLE_Coach';
        }elseif($this->role == 'Admin'){   
            $roles[] = 'ROLE_Admin';
        }
        // guarantee every user at least has ROLE_USER

        return array_unique($roles);
    }

    public function setRoles(array $roles)
    {
        if($this->role == 'Medecin'){
            $roles[] = 'ROLE_Medecin';
        }elseif($this->role == 'Client'){   
            $roles[] = 'ROLE_Client';
        }elseif($this->role == 'Coach'){   
            $roles[] = 'ROLE_Coach';
        }elseif($this->role == 'Admin'){   
            $roles[] = 'ROLE_Admin';
        }
    
        $this->role = $roles;
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



  

public function getPassword(): string
{
    // Retourne le mot de passe de l'utilisateur
    return $this->mdp;
}

public function getSalt(): ?string
{
    // Si vous utilisez bcrypt ou argon pour l'encodage des mots de passe (ce qui est recommandé), 
    // alors vous n'avez pas besoin d'une salt et cette méthode peut simplement retourner null
    return null;
}

public function eraseCredentials(): void
{
    // Cette méthode est utilisée pour effacer les données sensibles de l'utilisateur.
    // Dans votre cas, il semble que vous n'ayez pas de telles données, donc cette méthode peut être laissée vide.
}
public function getUserIdentifier(): string
{
    // Retourne l'identifiant de l'utilisateur, qui est généralement l'e-mail ou le nom d'utilisateur.
    // Dans votre cas, vous utilisez 'mail' comme identifiant.
    return $this->mail;
}

    public  function __toString()
    {
        return $this->username;
    }

}
