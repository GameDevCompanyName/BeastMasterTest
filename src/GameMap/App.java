package GameMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
            MapCell[][] matrix = MapGenerator.generateMap(
                    46, 6, 4, true, 120, 120, System.currentTimeMillis()
            );

            GameMap map = new GameMap(matrix);
            Scene scene = new Scene(map, Color.web("#FFFAFA"));
            primaryStage.setScene(scene);
            primaryStage.show();
    }
}
