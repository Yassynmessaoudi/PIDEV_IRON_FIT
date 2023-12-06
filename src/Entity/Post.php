<?php

namespace App\Entity;

use App\Repository\PostRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: PostRepository::class)]
#[ORM\Table(name: "post")]
#[ORM\Index(name: "FK_User_Post", columns: ["user_id"])]
class Post
{
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: "IDENTITY")]
    #[ORM\Column(name: "id", type: "integer", nullable: false)]
    private $id;

    #[ORM\Column(name: "title", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $title = 'NULL';

    #[ORM\Column(name: "content", type: "text", length: 65535, nullable: true, options: ["default" => "NULL"])]
    private $content = 'NULL';

    #[ORM\Column(name: "created_At", type: "datetime", nullable: true, options: ["default" => "NULL"])]
    private $createdAt = null;

    #[ORM\Column(name: "updated_At", type: "datetime", nullable: true, options: ["default" => "NULL"])]
    private $updatedAt = null;

    #[ORM\Column(name: "status", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $status = 'NULL';

    #[ORM\Column(name: "likes", type: "integer", nullable: true, options: ["default" => "NULL"])]
    private $likes = NULL;

    #[ORM\Column(name: "image", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $image = 'NULL';

    #[ORM\Column(name: "location", type: "string", length: 255, nullable: true, options: ["default" => "NULL"])]
    private $location = 'NULL';

    #[ORM\ManyToOne(targetEntity: "User")]
    #[ORM\JoinColumn(name: "user_id", referencedColumnName: "id_user")]
    private $user;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTitle(): ?string
    {
        return $this->title;
    }

    public function setTitle(?string $title): self
    {
        $this->title = $title;

        return $this;
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

    public function getStatus(): ?string
    {
        return $this->status;
    }

    public function setStatus(?string $status): self
    {
        $this->status = $status;

        return $this;
    }

    public function getLikes(): ?int
    {
        return $this->likes;
    }

    public function setLikes(?int $likes): self
    {
        $this->likes = $likes;

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

    public function getLocation(): ?string
    {
        return $this->location;
    }

    public function setLocation(?string $location): self
    {
        $this->location = $location;

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
