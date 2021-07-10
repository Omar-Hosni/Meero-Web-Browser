package sample;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    private TextField textField;
    @FXML
    private ListView<String> myListView;


    private WebEngine engine;

    private WebHistory history;

    private String homePage;

    private double webZoom;

    String[] historySearches;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

            engine = webView.getEngine();
            homePage = "www.google.com";
            webZoom = 1;
            textField.setText(homePage);
            loadPage();

    }

    public void loadPage(){
        engine.load("http://" + textField.getText());
    }

    public void refreshPage(){
        engine.reload();
    }

    public void zoomIn(){
        webZoom += 0.25;
        webView.setZoom(webZoom);
    }

    public void zoomOut(){
        webZoom -=0.25;
        webView.setZoom(webZoom);
    }

    public void displayHistory() {

            history = engine.getHistory();
            ObservableList<WebHistory.Entry> entries = history.getEntries();
            int size = entries.size();
            for (WebHistory.Entry e : entries) {
                //System.out.println(e);
                System.out.println(e.getUrl() + " " + e.getLastVisitedDate());

                myListView.getItems().addAll((e.getUrl()));
            }
    }

    public void back(){
        history = engine.getHistory();
        ObservableList <WebHistory.Entry> entries = history.getEntries();
        history.go(-1);
        textField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    public void forward(){
        history = engine.getHistory();
        ObservableList <WebHistory.Entry> entries = history.getEntries();
        history.go(1);
        textField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    public void meero(){
        engine.executeScript("window.location = \"https://www.youtube.com/channel/UChghaRaSY7Q_Tli49KK7uFQ\";");
        textField.setText("https://www.youtube.com/channel/UChghaRaSY7Q_Tli49KK7uFQ");
    }

}
