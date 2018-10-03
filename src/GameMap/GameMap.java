package GameMap;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameMap extends StackPane {

    private GridPane map;

    public GameMap() {
        this.map = new GridPane();
        this.getChildren().add(map);
    }

    public void show(MapCell[][] matrix){
        int size = 600/matrix.length;

        for (int x = 0; x < matrix[0].length; x++) {
            for (int y = 0; y < matrix.length; y++) {
                Rectangle rect = new Rectangle();
                rect.setWidth(size);
                rect.setHeight(size);
                if (matrix[x][y] == MapCell.SPACE)
                    rect.setFill(Color.web("#7C8D55"));
                if (matrix[x][y] == MapCell.WALL){
                    rect.setFill(Color.web("#42434F"));
                }

                GridPane.setRowIndex(rect, y);
                GridPane.setColumnIndex(rect, x);
                map.getChildren().addAll(rect);

            }
        }
    }

}
