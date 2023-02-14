package com.example.canvas;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.scene.shape.ArcType;
import java.io.IOException;


public class HelloApplication extends Application {
    private Canvas canvas = new Canvas(300, 250);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Group root = new Group();
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Drawing Operations Test");
        drawShapes(gc);
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root,400,400));
        moveCanvas(60,50);
        drawDShape();
        drawRadialGradient(Color.RED, Color.YELLOW);
        drawLinearGradient(Color.BLUE, Color.GREEN);
        drawDropShadow(Color.GRAY, Color.BLUE, Color.GREEN, Color.RED);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void moveCanvas(int x, int y) {
        canvas.setTranslateX(x);
        canvas.setTranslateY(y);
    }

    private void drawDShape() {
        gc.beginPath();
        gc.moveTo(50, 50);
        gc.bezierCurveTo(150, 20, 150, 150, 75, 150);
        gc.closePath();
    }

    private void drawRadialGradient(Color firstColor, Color lastColor) {
        gc.setFill(new RadialGradient(0, 0, 0.5, 0.5, 0.1, true,
                CycleMethod.REFLECT,
                new Stop(0.0, firstColor),
                new Stop(1.0, lastColor)) );
        gc.fill();
    }

    private void drawDropShadow(Color firstColor, Color secondColor,
                                Color thirdColor, Color fourthColor) {
        gc.applyEffect(new DropShadow(20, 20, 0, firstColor));
        gc.applyEffect(new DropShadow(20, 0, 20, secondColor));
        gc.applyEffect(new DropShadow(20, -20, 0, thirdColor));
        gc.applyEffect(new DropShadow(20, 0, -20, fourthColor));
    }

    private void drawLinearGradient(Color firstColor, Color secondColor) {
        LinearGradient lg = new LinearGradient(0, 0, 1, 1, true,
                CycleMethod.REFLECT,
                new Stop(0.0, firstColor),
                new Stop(1.0, secondColor));
        gc.setStroke(lg);
        gc.setLineWidth(20);
        gc.stroke();
    }

    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40,10,10,40);
        gc.fillOval(10,60,30,30);
        gc.strokeOval(60,60,30,30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
        }
    }



