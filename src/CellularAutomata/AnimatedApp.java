package CellularAutomata;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class AnimatedApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        long seed = 1538560814829l;
        int fillPercent = 50;
        int width = 50;
        int height = 70;













        GraphicMap map = new GraphicMap(
                fillPercent,
                width,
                height,
                seed
        );

        StackPane mainPane = new StackPane();
        mainPane.setAlignment(Pos.TOP_LEFT);
        mainPane.getChildren().add(map.getContainer());

        map.proceedMap();

        Scene scene = new Scene(mainPane, Color.web("#FFFAFA"));

        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.SPACE)){
                map.changeState();
            }
            if (event.getCode().equals(KeyCode.R)){
                map.randomize();
            }
            if (event.getCode().equals(KeyCode.C)){
                map.clear();
            }
            if (event.getCode().equals(KeyCode.UP)){
                Constants.upperSpeed();
            }
            if (event.getCode().equals(KeyCode.DOWN)){
                Constants.lowerSpeed();
            }
            if (event.getCode().equals(KeyCode.G)){
                map.toggleGrid();
            }
        });

//        scene.setOnMouseClicked(event -> {
//            seedLabel.setText(String.valueOf(generateSeed()));
//            GraphicMap newMap = new GraphicMap(
//                    fillPercent,
//                    width,
//                    height,
//                    Long.parseLong(seedLabel.getText()
//                    )
//            );
//            mainPane.getChildren().clear();
//            mainPane.getChildren().addAll(newMap.getContainer(), seedLabel);
//            newMap.proceedMap();
//        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private long generateSeed() {
        return new Random().nextInt(10000);
    }

}
