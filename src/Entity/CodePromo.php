<?php

namespace App\Entity;

use App\Repository\CodePromoRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: CodePromoRepository::class)]
#[ORM\Table(name: "code_promo")]
class CodePromo
{
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: "IDENTITY")]
    #[ORM\Column(name: "Id_codepromo", type: "integer", nullable: false)]
    private $idCodepromo;

    #[ORM\Column(name: "code", type: "string", length: 10, nullable: false)]
    private $code;

    #[ORM\Column(name: "description", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $description = 'NULL';

    #[ORM\Column(name: "date_dexpiration", type: "string", length: 20, nullable: true, options: ["default" => "NULL"])]
    private $dateDexpiration = 'NULL';

    #[ORM\Column(name: "used", type: "boolean", nullable: true)]
    private $used = '0';

    public function getIdCodepromo(): ?int
    {
        return $this->idCodepromo;
    }

    public function getCode(): ?string
    {
        return $this->code;
    }

    public function setCode(string $code): self
    {
        $this->code = $code;

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

    public function getDateDexpiration(): ?string
    {
        return $this->dateDexpiration;
    }

    public function setDateDexpiration(?string $dateDexpiration): self
    {
        $this->dateDexpiration = $dateDexpiration;

        return $this;
    }

    public function isUsed(): ?bool
    {
        return $this->used;
    }

    public function setUsed(?bool $used): self
    {
        $this->used = $used;

        return $this;
    }
}
