<?php
namespace App\Entity;

use App\Repository\CommentaireRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: CommentaireRepository::class)]
#[ORM\Table(name: "commentaire")]
#[ORM\Index(name: "FK_Post_Commentaire", columns: ["post_id"])]
#[ORM\Index(name: "FK_User_Commentaire", columns: ["user_id"])]
class Commentaire
{
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: "IDENTITY")]
    #[ORM\Column(name: "id", type: "integer", nullable: false)]
    private $id;

    #[ORM\Column(name: "content", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $content = 'NULL';

    #[ORM\Column(name: "created_At", type: "datetime", nullable: true, options: ["default" => "NULL"])]
    private $createdAt = null;

    #[ORM\Column(name: "updated_At", type: "datetime", nullable: true, options: ["default" => "NULL"])]
    private $updatedAt = null;

    #[ORM\Column(name: "reportedCount", type: "integer", nullable: true, options: ["default" => "NULL"])]
    private $reportedcount = NULL;

    #[ORM\Column(name: "isFlagged", type: "boolean", nullable: true, options: ["default" => "NULL"])]
    private $isflagged = 'NULL';

    #[ORM\Column(name: "isApproved", type: "boolean", nullable: true, options: ["default" => "NULL"])]
    private $isapproved = 'NULL';

    #[ORM\Column(name: "isDeleted", type: "boolean", nullable: true, options: ["default" => "NULL"])]
    private $isdeleted = 'NULL';

    #[ORM\ManyToOne(targetEntity: "Post")]
    #[ORM\JoinColumn(name: "post_id", referencedColumnName: "id")]
    private $post;

    #[ORM\ManyToOne(targetEntity: "User")]
    #[ORM\JoinColumn(name: "user_id", referencedColumnName: "id_user")]
    private $user;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getContent(): ?string
    {
        return $this->content;
    }

    public function setContent(?string $content): self
    {
        $this->content = $content;

        return $this;
    }

    public function getCreatedAt(): ?\DateTimeInterface
    {
        return $this->createdAt;
    }

    public function setCreatedAt(?\DateTimeInterface $createdAt): self
    {
        $this->createdAt = $createdAt;

        return $this;
    }

    public function getUpdatedAt(): ?\DateTimeInterface
    {
        return $this->updatedAt;
    }

    public function setUpdatedAt(?\DateTimeInterface $updatedAt): self
    {
        $this->updatedAt = $updatedAt;

        return $this;
    }

    public function getReportedcount(): ?int
    {
        return $this->reportedcount;
    }

    public function setReportedcount(?int $reportedcount): self
    {
        $this->reportedcount = $reportedcount;

        return $this;
    }

    public function isIsflagged(): ?bool
    {
        return $this->isflagged;
    }

    public function setIsflagged(?bool $isflagged): self
    {
        $this->isflagged = $isflagged;

        return $this;
    }

    public function isIsapproved(): ?bool
    {
        return $this->isapproved;
    }

    public function setIsapproved(?bool $isapproved): self
    {
        $this->isapproved = $isapproved;

        return $this;
    }

    public function isIsdeleted(): ?bool
    {
        return $this->isdeleted;
    }

    public function setIsdeleted(?bool $isdeleted): self
    {
        $this->isdeleted = $isdeleted;

        return $this;
    }

    public function getPost(): ?Post
    {
        return $this->post;
    }

    public function setPost(?Post $post): self
    {
        $this->post = $post;

        return $this;
    }

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;

        return $this;
    }
}
