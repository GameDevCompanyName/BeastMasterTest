package GraphicMap;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        long seed2 = 123;
        long seed3 = 124;
        long seed4 = 1352;
        int fillPercent = 15;
        int smoothingCycles = 3;
        int smoothingParameter = 4;
        boolean makeWalls = true;
        int width = 12;
        int height = 12;

        GraphicMap map = new GraphicMap(
                fillPercent,
                smoothingCycles,
                smoothingParameter,
                makeWalls,
                width,
                height,
                seed
        );

        Label seedLabel = new Label(Long.toString(seed));
        seedLabel.setTextFill(Color.web("#EEEEFF"));
        seedLabel.setFont(new Font("Courier New", 20));

        StackPane mainPane = new StackPane();
        mainPane.setAlignment(Pos.TOP_LEFT);
        mainPane.getChildren().addAll(map.getContainer(), seedLabel);

        GridPane gridMap = new GridPane();


        map.proceedMap();

        Scene scene = new Scene(mainPane, Color.web("#FFFAFA"));

        scene.setOnMouseClicked(event -> {
            seedLabel.setText(String.valueOf(generateSeed()));
            GraphicMap newMap = new GraphicMap(
                    fillPercent,
                    smoothingCycles,
                    smoothingParameter,
                    makeWalls,
                    width,
                    height,
                    Long.parseLong(seedLabel.getText()
                    )
            );
            mainPane.getChildren().clear();
            mainPane.getChildren().addAll(newMap.getContainer(), seedLabel);
            newMap.proceedMap();
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private long generateSeed() {
        return new Random().nextInt(10000);
    }

}
