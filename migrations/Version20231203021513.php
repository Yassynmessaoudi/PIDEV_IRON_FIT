<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20231203021513 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE messenger_messages (id BIGINT AUTO_INCREMENT NOT NULL, body LONGTEXT NOT NULL, headers LONGTEXT NOT NULL, queue_name VARCHAR(190) NOT NULL, created_at DATETIME NOT NULL, available_at DATETIME NOT NULL, delivered_at DATETIME DEFAULT NULL, INDEX IDX_75EA56E0FB7336F0 (queue_name), INDEX IDX_75EA56E0E3BD61CE (available_at), INDEX IDX_75EA56E016BA31DB (delivered_at), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE cart CHANGE productId productId INT DEFAULT NULL');
        $this->addSql('ALTER TABLE cart_product CHANGE product_id product_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE category CHANGE id id VARCHAR(255) NOT NULL, CHANGE categoryName category_name VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE client DROP FOREIGN KEY client_ibfk_2');
        $this->addSql('ALTER TABLE client ADD CONSTRAINT FK_C7440455E173B1B8 FOREIGN KEY (id_client) REFERENCES user (id_user)');
        $this->addSql('ALTER TABLE coach DROP FOREIGN KEY coach_ibfk_2');
        $this->addSql('ALTER TABLE coach ADD CONSTRAINT FK_3F596DCCEE3FD73E FOREIGN KEY (IdUtilisateur) REFERENCES user (id_user)');
        $this->addSql('ALTER TABLE code_promo CHANGE used used TINYINT(1) DEFAULT NULL');
        $this->addSql('ALTER TABLE planning CHANGE niveauProgramme niveauProgramme VARCHAR(255) DEFAULT NULL, CHANGE programme programme VARCHAR(255) DEFAULT NULL, CHANGE prix prix DOUBLE PRECISION DEFAULT NULL, CHANGE videoLink videoLink VARCHAR(255) DEFAULT NULL, CHANGE views views INT DEFAULT NULL');
        $this->addSql('ALTER TABLE product CHANGE idc idc VARCHAR(255) DEFAULT NULL');
        $this->addSql('ALTER TABLE profile DROP FOREIGN KEY profile_ibfk_1');
        $this->addSql('ALTER TABLE profile CHANGE id_user id_user INT DEFAULT NULL');
        $this->addSql('ALTER TABLE profile ADD CONSTRAINT FK_8157AA0F6B3CA4B FOREIGN KEY (id_user) REFERENCES user (id_user)');
        $this->addSql('ALTER TABLE rendez_vous CHANGE date_RDV date_RDV DATE DEFAULT NULL, CHANGE time_RDV time_RDV VARCHAR(255) DEFAULT NULL');
        $this->addSql('ALTER TABLE rendez_vous RENAME INDEX id_planning TO IDX_65E8AA0A84425363');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE messenger_messages');
        $this->addSql('ALTER TABLE cart CHANGE productId productId INT NOT NULL');
        $this->addSql('ALTER TABLE cart_product CHANGE product_id product_id INT NOT NULL');
        $this->addSql('ALTER TABLE category CHANGE id id INT AUTO_INCREMENT NOT NULL, CHANGE category_name categoryName VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE client DROP FOREIGN KEY FK_C7440455E173B1B8');
        $this->addSql('ALTER TABLE client ADD CONSTRAINT client_ibfk_2 FOREIGN KEY (id_client) REFERENCES user (id_user) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE coach DROP FOREIGN KEY FK_3F596DCCEE3FD73E');
        $this->addSql('ALTER TABLE coach ADD CONSTRAINT coach_ibfk_2 FOREIGN KEY (IdUtilisateur) REFERENCES user (id_user) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE code_promo CHANGE used used TINYINT(1) DEFAULT 0');
        $this->addSql('ALTER TABLE planning CHANGE niveauProgramme niveauProgramme VARCHAR(255) DEFAULT \'NULL\', CHANGE programme programme VARCHAR(255) DEFAULT \'NULL\', CHANGE prix prix DOUBLE PRECISION DEFAULT \'NULL\', CHANGE videoLink videoLink VARCHAR(255) DEFAULT \'NULL\', CHANGE views views INT DEFAULT 0');
        $this->addSql('ALTER TABLE product CHANGE idc idc INT NOT NULL');
        $this->addSql('ALTER TABLE profile DROP FOREIGN KEY FK_8157AA0F6B3CA4B');
        $this->addSql('ALTER TABLE profile CHANGE id_user id_user INT NOT NULL');
        $this->addSql('ALTER TABLE profile ADD CONSTRAINT profile_ibfk_1 FOREIGN KEY (id_user) REFERENCES user (id_user) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE rendez_vous CHANGE date_RDV date_RDV DATE DEFAULT \'NULL\', CHANGE time_RDV time_RDV VARCHAR(255) DEFAULT \'NULL\'');
        $this->addSql('ALTER TABLE rendez_vous RENAME INDEX idx_65e8aa0a84425363 TO id_planning');
    }
}
