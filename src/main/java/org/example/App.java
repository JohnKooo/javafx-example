package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;


/**
 * JavaFX App
 */
public class App extends Application {

    public static ArrayList<Circle> nodes = new ArrayList<>();
    private final static int SCENE_HEIGHT = 500;
    private final static int SCENE_WIDTH = 500;

    @Override
    public void start(Stage stage) {


        Pane pane = new Pane();
        Scene scene = new Scene(pane, SCENE_HEIGHT, SCENE_WIDTH);
        Circle circle = new Circle(20, Color.GREY);
        Circle circle1 = new Circle(10, Color.BLACK);
        nodes.add(circle);
        nodes.add(circle1);
        circle.relocate(10,10);
        circle1.relocate(8,2);
        pane.getChildren().add(circle);
        pane.getChildren().add(circle1);

        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), new EventHandler<ActionEvent>() {
            double dx = 2;
            double dy = 5;
            @Override
            public void handle(ActionEvent actionEvent) {
                circle.setLayoutX(circle.getLayoutX() + dx);
                circle.setLayoutY(circle.getLayoutY() + dy);

                circle1.setLayoutX(circle.getLayoutX() + dx);
                circle1.setLayoutY(circle.getLayoutY() + dy);

                Bounds bounds = pane.getBoundsInLocal();

                System.out.println(bounds.getMinX());
                System.out.println(bounds.getMaxX());

                if(circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius()) ||
                        circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius())) {
                    System.out.println("Out of bounds X " + circle.getLayoutX());

                    dx = -dx;
                }

                if(circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius()) ||
                        circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius())) {
                    System.out.println("Out of bounds Y " + circle.getLayoutY());
                    dy = -dy;
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


//    @Override
    public void handle(MouseEvent event) {
        System.out.println(event.getSceneX());
        System.out.println(event.getSceneY());
    }

    public static void main(String[] args) {
        launch();
    }

}