<?php

namespace App\Entity;

use App\Repository\SujetRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: SujetRepository::class)]
#[ORM\Table(name: "sujet")]
#[ORM\Index(name: "id_forum", columns: ["id_forum"])]
class Sujet
{
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: "IDENTITY")]
    #[ORM\Column(name: "id_sujet", type: "integer", nullable: false)]
    private $idSujet;

    #[ORM\Column(name: "question", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $question = 'NULL';

    #[ORM\Column(name: "reponse", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $reponse = 'NULL';

    #[ORM\ManyToOne(targetEntity: "Forum")]
    #[ORM\JoinColumn(name: "id_forum", referencedColumnName: "id_forum")]
    private $idForum;

    public function getIdSujet(): ?int
    {
        return $this->idSujet;
    }

    public function getQuestion(): ?string
    {
        return $this->question;
    }

    public function setQuestion(?string $question): self
    {
        $this->question = $question;

        return $this;
    }

    public function getReponse(): ?string
    {
        return $this->reponse;
    }

    public function setReponse(?string $reponse): self
    {
        $this->reponse = $reponse;

        return $this;
    }

    public function getIdForum(): ?Forum
    {
        return $this->idForum;
    }

    public function setIdForum(?Forum $idForum): self
    {
        $this->idForum = $idForum;

        return $this;
    }
}
