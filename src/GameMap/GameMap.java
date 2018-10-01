package GameMap;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameMap extends Pane {

    private GridPane map;
    private MapCell[][] matrix;

    public GameMap(MapCell[][] matrix) {
        this.matrix = matrix;
        this.map = new GridPane();

        int size = 600/matrix.length;

        for (int x = 0; x < matrix[0].length; x++) {
            for (int y = 0; y < matrix.length; y++) {
                if (matrix[x][y] == MapCell.SPACE)
                    continue;
                if (matrix[x][y] == MapCell.WALL){
                    Rectangle wall = new Rectangle();
                    wall.setWidth(size);
                    wall.setHeight(size);
                    wall.setFill(Color.web("#08081F"));
                    GridPane.setRowIndex(wall, y);
                    GridPane.setColumnIndex(wall, x);
                    map.getChildren().addAll(wall);
                }
            }
        }

        this.getChildren().add(map);
    }



}
