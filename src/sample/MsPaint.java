package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * Created by crci1 on 1/11/2017.
 */
public class MsPaint extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    final double DEFAULT_SCENE_HEIGHT = 600;
    final double DEFAULT_SCENE_WIDTH = 800;
    double strokeSize = 2*2;
    MouseEvent event;
    boolean drawingFlag = true;


    @Override
    public void start(Stage primaryStage) {
        Group rootGroup = new Group();

//        Scene mainScene = new Scene(rootGroup, 800, 600, Color.BLACK);


        Canvas canvas = new Canvas(DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
        canvas.setFocusTraversable(true);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        graphicsContext.setLineWidth(2);

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
              if (drawingFlag) {
//                  graphicsContext.setStroke(myColor);
                  System.out.println("x: " + e.getX() + ", y: " + e.getY());
                  graphicsContext.strokeOval(e.getX(), e.getY(), strokeSize, strokeSize);
              }
            }
        });

        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                System.out.println("");
                if (e.getText().equals("d")){
                    drawingFlag = !drawingFlag;
                }
                if (e.getText().equals("a")){
                    Color myColor = Color.color(Math.random(),Math.random(),Math.random());
                    graphicsContext.setStroke(myColor);
                }
                if (e.getText().equals("c")){
                    graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                }
                if (e.getCode().toString().equals("UP") && strokeSize<= 20){
                    strokeSize++;
                }
                if (e.getCode().toString().equals("DOWN") && strokeSize>= 2){
                    strokeSize--;
                }
                System.out.println(e.getCode().toString());
            }
        });

        rootGroup.getChildren().add(canvas);
        Scene scene = new Scene(rootGroup, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
        primaryStage.setScene(scene);

        primaryStage.show();
    }


}

class MyMouseEventHandler implements EventHandler<MouseEvent> {

    GraphicsContext gc;

    public MyMouseEventHandler(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println("other mouse event handler");
        gc.strokeOval(mouseEvent.getX(), mouseEvent.getY(), 10, 10);
    }
}
