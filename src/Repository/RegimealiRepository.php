<?php

namespace App\Repository;

use App\Entity\Regimeali;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Regimeali>
 *
 * @method Regimeali|null find($id, $lockMode = null, $lockVersion = null)
 * @method Regimeali|null findOneBy(array $criteria, array $orderBy = null)
 * @method Regimeali[]    findAll()
 * @method Regimeali[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class RegimealiRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Regimeali::class);
    }

//    /**
//     * @return Regimeali[] Returns an array of Regimeali objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('r')
//            ->andWhere('r.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('r.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Regimeali
//    {
//        return $this->createQueryBuilder('r')
//            ->andWhere('r.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
