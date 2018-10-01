package GameMap;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
            long seed = System.currentTimeMillis();
            MapCell[][] matrix = MapGenerator.generateMap(
                    40, 5, 4, true, 40, 40, seed
            );

            Label seedLabel = new Label(Long.toString(seed));
            seedLabel.setTextFill(Color.web("#120303"));

            GameMap map = new GameMap();
            map.setAlignment(Pos.TOP_LEFT);
            map.getChildren().add(seedLabel);
            map.show(matrix);
            Scene scene = new Scene(map, Color.web("#FFFAFA"));
            scene.setOnMouseClicked(event -> {
                    seedLabel.setText(String.valueOf(System.currentTimeMillis()));
                    map.show(MapGenerator.generateMap(
                        42, 5, 4, true, 40, 40, Long.parseLong(seedLabel.getText())
                    )
                );
            });
            primaryStage.setScene(scene);
            primaryStage.show();
    }
}
