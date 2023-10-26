/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.servicePlanning;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import models.Planning;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class StatistiqueController implements Initializable {

    @FXML
    private Label titleLabel;
    @FXML
private BarChart<String, Number> BarChart;

    /**
     * Initializes the controller class.
     */
    @Override
public void initialize(URL url, ResourceBundle rb) {
    titleLabel.setText("Planning Statistics");

    servicePlanning sp = new servicePlanning();
    Map<Planning, Integer> planningStatistics = sp.getPlanningStatistics();

    for (Map.Entry<Planning, Integer> entry : planningStatistics.entrySet()) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(entry.getKey().getProgramme());
        series.getData().add(new XYChart.Data<>(entry.getKey().getProgramme(), entry.getValue()));
        BarChart.getData().add(series);
    }
}

}
