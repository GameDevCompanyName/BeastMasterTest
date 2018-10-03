package GraphicMap;

import javafx.application.Application;
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

        long seed = 1;
        int fillPercent = 34;
        int smoothingCycles = 3;
        int smoothingParameter = 4;
        boolean makeWalls = true;
        int width = 15;
        int height = 15;

        Label seedLabel = new Label(Long.toString(seed));
        seedLabel.setTextFill(Color.web("#EEEEFF"));
        seedLabel.setFont(new Font("Courier New", 20));

        StackPane mainPane = new StackPane();
        //mainPane.setAlignment(Pos.TOP_LEFT);

        GraphicMap map1 = new GraphicMap(
                fillPercent,
                smoothingCycles,
                smoothingParameter,
                makeWalls,
                width,
                height,
                generateSeed()
        );

        GraphicMap map2 = new GraphicMap(
                fillPercent,
                smoothingCycles,
                smoothingParameter,
                makeWalls,
                width,
                height,
                generateSeed()
        );

        GraphicMap map3 = new GraphicMap(
                fillPercent,
                smoothingCycles,
                smoothingParameter,
                makeWalls,
                width,
                height,
                generateSeed()
        );

        GraphicMap map4 = new GraphicMap(
                fillPercent,
                smoothingCycles,
                smoothingParameter,
                makeWalls,
                width,
                height,
                generateSeed()
        );

        GridPane gridMap = new GridPane();
        gridMap.add(map1.getContainer(), 0, 0);
        gridMap.add(map2.getContainer(), 1, 0);
        gridMap.add(map3.getContainer(), 0, 1);
        gridMap.add(map4.getContainer(), 1, 1);

        mainPane.getChildren().add(gridMap);

        map1.proceedMap();
        map2.proceedMap();
        map3.proceedMap();
        map4.proceedMap();

        map1.makePassage(Direction.DOWN);
        map1.makePassage(Direction.RIGHT);
        map2.makePassage(Direction.LEFT);
        map2.makePassage(Direction.DOWN);
        map3.makePassage(Direction.UP);
        map3.makePassage(Direction.RIGHT);
        map4.makePassage(Direction.UP);
        map4.makePassage(Direction.LEFT);

        Scene scene = new Scene(mainPane, Color.web("#FFFAFA"));

        scene.setOnMouseClicked(event -> {
            GraphicMap map1new = new GraphicMap(
                    fillPercent,
                    smoothingCycles,
                    smoothingParameter,
                    makeWalls,
                    width,
                    height,
                    generateSeed()
            );

            GraphicMap map2new = new GraphicMap(
                    fillPercent,
                    smoothingCycles,
                    smoothingParameter,
                    makeWalls,
                    width,
                    height,
                    generateSeed()
            );

            GraphicMap map3new = new GraphicMap(
                    fillPercent,
                    smoothingCycles,
                    smoothingParameter,
                    makeWalls,
                    width,
                    height,
                    generateSeed()
            );

            GraphicMap map4new = new GraphicMap(
                    fillPercent,
                    smoothingCycles,
                    smoothingParameter,
                    makeWalls,
                    width,
                    height,
                    generateSeed()
            );

            GridPane gridMapNew = new GridPane();
            gridMapNew.add(map1new.getContainer(), 0, 0);
            gridMapNew.add(map2new.getContainer(), 1, 0);
            gridMapNew.add(map3new.getContainer(), 0, 1);
            gridMapNew.add(map4new.getContainer(), 1, 1);

            mainPane.getChildren().add(gridMapNew);

            map1new.proceedMap();
            map2new.proceedMap();
            map3new.proceedMap();
            map4new.proceedMap();

            map1new.makePassage(Direction.DOWN);
            map1new.makePassage(Direction.RIGHT);
            map2new.makePassage(Direction.LEFT);
            map2new.makePassage(Direction.DOWN);
            map3new.makePassage(Direction.UP);
            map3new.makePassage(Direction.RIGHT);
            map4new.makePassage(Direction.UP);
            map4new.makePassage(Direction.LEFT);
            mainPane.getChildren().clear();
            mainPane.getChildren().add(gridMapNew);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private long generateSeed() {
        return new Random().nextInt(10000);
    }

}
