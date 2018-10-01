package GameMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
            MapCell[][] matrix = {
                    {MapCell.WALL, MapCell.WALL, MapCell.WALL, MapCell.WALL},
                    {MapCell.WALL, MapCell.SPACE, MapCell.SPACE, MapCell.SPACE},
                    {MapCell.WALL, MapCell.SPACE, MapCell.SPACE, MapCell.WALL},
                    {MapCell.WALL, MapCell.WALL, MapCell.SPACE, MapCell.SPACE}
            };

            GameMap map = new GameMap(matrix);
            Scene scene = new Scene(map, Color.web("#FFFAFA"));
            primaryStage.setScene(scene);
            primaryStage.show();
    }
}
