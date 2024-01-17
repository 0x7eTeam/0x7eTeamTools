package com.sec421;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/gui.fxml"));
        primaryStage.setTitle("0x7eTeamTools v1.1 By 陆泽");
//        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("img/logo_2256.gif").toString()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}