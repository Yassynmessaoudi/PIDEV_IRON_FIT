<?php

namespace App\Entity;

use App\Repository\ForumRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ForumRepository::class)]
#[ORM\Table(name: "forum")]
class Forum
{
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: "IDENTITY")]
    #[ORM\Column(name: "id_forum", type: "integer", nullable: false)]
    private $idForum;

    #[ORM\Column(name: "date_sys", type: "date", nullable: true, options: ["default" => "NULL"])]
    private $dateSys = null;

    #[ORM\Column(name: "admin", type: "integer", nullable: true, options: ["default" => "NULL"])]
    private $admin = NULL;

    #[ORM\Column(name: "intitule_forum", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $intituleForum = 'NULL';

    public function getIdForum(): ?int
    {
        return $this->idForum;
    }

    public function getDateSys(): ?\DateTimeInterface
    {
        return $this->dateSys;
    }

    public function setDateSys(?\DateTimeInterface $dateSys): self
    {
        $this->dateSys = $dateSys;

        return $this;
    }

    public function getAdmin(): ?int
    {
        return $this->admin;
    }

    public function setAdmin(?int $admin): self
    {
        $this->admin = $admin;

        return $this;
    }

    public function getIntituleForum(): ?string
    {
        return $this->intituleForum;
    }

    public function setIntituleForum(?string $intituleForum): self
    {
        $this->intituleForum = $intituleForum;

        return $this;
    }


    public function __toString()
    {
        return "Forum{" . 
                "id=" . (int)($this->id) . ", " .
                "date=" . $this->date->format('Y-m-d H:i:s') . ", " .
                "Admin=" . (int)($this->Admin) . ", " .
                "intitule=" . (string)$this->intitule .
                ", sujet=" . (string)$this->sujet .
                ", IDforum=" . implode(", ", array_map(function($iDforum) { return (string)$iDforum; }, $this->IDforum->toArray())) .
                "}";
    }
}
