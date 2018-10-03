package GraphicMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AnimatedApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        long seed = System.currentTimeMillis();
        int fillPercent = 47;
        int smoothingCycles = 10;
        int smoothingParameter = 4;
        boolean makeWalls = false;
        int width = 60;
        int height = 60;

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
        seedLabel.setTextFill(Color.web("#120303"));

        StackPane mainPane = new StackPane();
        mainPane.getChildren().addAll(map.getContainer(), seedLabel);
        map.proceedMap();

        Scene scene = new Scene(mainPane, Color.web("#FFFAFA"));

        scene.setOnMouseClicked(event -> {
            seedLabel.setText(String.valueOf(System.currentTimeMillis()));
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

}
