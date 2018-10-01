package GameMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
            MapCell[][] matrix = MapGenerator.generateMap(
                    40, 5, 4, true, 40, 40, System.currentTimeMillis()
            );

            GameMap map = new GameMap();
            map.show(matrix);
            Scene scene = new Scene(map, Color.web("#FFFAFA"));
            scene.setOnMouseClicked(event -> {
                    map.show(MapGenerator.generateMap(
                        42, 5, 4, true, 40, 40, System.currentTimeMillis()
                    )
                );
            });
            primaryStage.setScene(scene);
            primaryStage.show();
    }
}
