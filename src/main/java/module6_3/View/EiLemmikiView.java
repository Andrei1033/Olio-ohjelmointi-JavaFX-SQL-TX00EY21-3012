package module6_3.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import module6_3.Control.EiLemmikiControl;

public class EiLemmikiView extends Application {
    private Canvas canvas;
    private GraphicsContext gc;
    private EiLemmikiControl eiLemmikiControl;
    private Image eiLemmikiImage;

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 700;

    @Override
    public void init(){
        eiLemmikiControl = new EiLemmikiControl(this);
    }

    @Override
    public void start(Stage stage) {
        canvas = new Canvas(WIDTH,HEIGHT);
        gc = canvas.getGraphicsContext2D();

        //tänne tule kuva
        try {
            eiLemmikiImage = new Image(getClass().getResourceAsStream("/EiLemmiki.jpeg"));
        }
        catch (Exception e){
            System.out.println("image file not found, continuing with defaults.");
        }

        canvas.setOnMouseMoved(mouseEvent -> {
            eiLemmikiControl.setTarget(mouseEvent.getX(), mouseEvent.getY());
        });
        canvas.setOnMouseExited(mouseEvent -> {
            eiLemmikiControl.stopMooving();
        });


        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root,WIDTH,HEIGHT);

        stage.setScene(scene);
        stage.setTitle("Virtual EiLemmiki");
        stage.show();

        drawEiLemmiki();
    }

    public void drawEiLemmiki(){
        gc.clearRect(0,0,WIDTH,HEIGHT);

        gc.drawImage(eiLemmikiImage,eiLemmikiControl.getEiLemmikiX(),eiLemmikiControl.getEiLemmikiY(), 80, 80);
    }

}
