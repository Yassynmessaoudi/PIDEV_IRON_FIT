<?php

namespace App\Controller;

use App\Entity\CodePromo;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Utilisateur;
use PhpOffice\PhpSpreadsheet\Spreadsheet;
use PhpOffice\PhpSpreadsheet\Writer\Xlsx;
use Symfony\Component\HttpFoundation\Response as HttpFoundationResponse;
use Symfony\Component\HttpFoundation\StreamedResponse;

class ExportController extends AbstractController
{ 
    #[Route('/export', name:'export_data')]
    public function exportData(): HttpFoundationResponse
    {
        // Récupérer les données à exporter (par exemple, depuis la base de données)
        $data = $this->getDoctrine()->getRepository(Codepromo::class)->findAll();
        //spreadsheet (objet) de biblio phpspreadsheet
        // Créer une instance de la classe Spreadsheet de PHPExcel
        $spreadsheet = new Spreadsheet();
        $sheet = $spreadsheet->getActiveSheet();

        // Ajouter les données au tableau Excel
        $row = 1;
        foreach ($data as $item) {
            $sheet->setCellValue('A' . $row, $item->getCode());
            $sheet->setCellValue('B' . $row, $item->getDescription());
            $sheet->setCellValue('C' . $row, $item->getDatedexpiration());
            // Ajouter d'autres colonnes au besoin
            $row++;
        }

        // Créer une réponse avec le contenu du fichier Excel
        $response = new StreamedResponse(function () use ($spreadsheet) {
            $writer = new Xlsx($spreadsheet);
            $writer->save('php://output');
        });

        // Ajouter les en-têtes nécessaires pour indiquer que c'est un fichier Excel
        $response->headers->set('Content-Type', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
        $response->headers->set('Content-Disposition', 'attachment;filename="export.xlsx"');
        $response->headers->set('Cache-Control', 'max-age=0');

        return $response;
    }
}


