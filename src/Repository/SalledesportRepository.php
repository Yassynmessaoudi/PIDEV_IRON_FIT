<?php

namespace App\Repository;

use App\Entity\Salledesport;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Salledesport>
 *
 * @method Salledesport|null find($id, $lockMode = null, $lockVersion = null)
 * @method Salledesport|null findOneBy(array $criteria, array $orderBy = null)
 * @method Salledesport[]    findAll()
 * @method Salledesport[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class SalledesportRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Salledesport::class);
    }

//    /**
//     * @return Salledesport[] Returns an array of Salledesport objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('s')
//            ->andWhere('s.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('s.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Salledesport
//    {
//        return $this->createQueryBuilder('s')
//            ->andWhere('s.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
public function findSallesAvecAbonnements()
    {
        return $this->createQueryBuilder('s')
            ->leftJoin('s.abonnements', 'a')
            ->select('s.nom as nom, COUNT(a) as abonnements')
            ->groupBy('s.nom')
            ->getQuery()
            ->getResult();
    }
    public function findByMultipleCriteria($nom, $adresse, $capacite, $specialite)
{
    $queryBuilder = $this->createQueryBuilder('s');

    if ($nom) {
        $queryBuilder->andWhere('s.nom LIKE :nom')
                    ->setParameter('nom', '%'.$nom.'%');
    }

    if ($adresse) {
        $queryBuilder->andWhere('s.adresse LIKE :adresse')
                    ->setParameter('adresse', '%'.$adresse.'%');
    }

    if ($capacite) {
        $queryBuilder->andWhere('s.capacite = :capacite')
                    ->setParameter('capacite', $capacite);
    }

    if ($specialite) {
        $queryBuilder->andWhere('s.specialite = :specialite')
                    ->setParameter('specialite', $specialite);
    }

    return $queryBuilder->getQuery()->getResult();
}

}
