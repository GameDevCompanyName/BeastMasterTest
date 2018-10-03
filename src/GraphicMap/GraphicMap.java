package GraphicMap;

import javafx.animation.PauseTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class GraphicMap {

    static String WALL = "#42434F";
    static String SPACE = "#7C8D55";

    MyRectangle[][] map;
    int fillPercent;
    int smoothingCycles;
    int smoothingParameter;
    boolean makeWalls;
    int width;
    int height;
    long seed;

    public GraphicMap(int fillPercent, int smoothingCycles, int smoothingParameter, boolean makeWalls, int width, int height, long seed) {
        map = new MyRectangle[width][height];
        int size = 600/width;
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                MyRectangle newRect = new MyRectangle();
                newRect.setWidth(size);
                newRect.setHeight(size);
                newRect.setAnimatedFill(Color.web("#881188"));
                map[x][y] = newRect;
            }
        }
        this.fillPercent = fillPercent;
        this.smoothingCycles = smoothingCycles;
        this.smoothingParameter = smoothingParameter;
        this.makeWalls = makeWalls;
        this.width = width;
        this.height = height;
        this.seed = seed;
    }

    public void proceedMap() {
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                map[x][y].setAnimatedFill(Color.web(SPACE));
            }
        }

        generateNoise();

        int smoothCounter = smoothingCycles;
        PauseTransition pause = new PauseTransition(Duration.millis(1300));
        pause.setOnFinished(event ->
                smooth(smoothCounter)
        );
        pause.play();

    }

    private void smooth(int smoothCounter) {
        if (smoothCounter == 0)
            return;
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                int walls = countSurroundingWalls(map, x, y);
                if (walls < this.smoothingParameter){
                    map[x][y].setAnimatedFill(Color.web(SPACE));
                    continue;
                }
                if (walls > this.smoothingParameter){
                    map[x][y].setAnimatedFill(Color.web(WALL));
                    continue;
                }
            }
        }
        if (smoothCounter == 1)
            return;

        PauseTransition pause = new PauseTransition(Duration.millis(1300));
        pause.setOnFinished(event ->
                smooth(smoothCounter-1)
        );
        pause.play();
    }

    private static int countSurroundingWalls(Rectangle[][] map, int x, int y) {
        int counter = 0;
        for (int xc = x - 1; xc <= x + 1; xc++) {
            for (int yc = y - 1; yc <= y + 1; yc++) {
                if (xc == x && yc == y){
                    continue;
                }
                if (xc < 0 || yc < 0 || xc >= map[0].length || yc >= map.length){
                    counter++;
                    continue;
                }
                if (map[xc][yc].getFill().equals(Color.web(WALL)))
                    counter++;
            }
        }
        return counter;
    }

    private void generateNoise() {
        Random randomGen = new Random(seed);
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                if (makeWalls){
                    if (x == 0 || y == 0 || x == map[0].length - 1 || y == map.length - 1){
                        map[x][y].setAnimatedFill(Color.web(WALL));
                        continue;
                    }
                }
                if (randomGen.nextInt(100) < fillPercent)
                    map[x][y].setAnimatedFill(Color.web(WALL));
                else
                    map[x][y].setAnimatedFill(Color.web(SPACE));
            }
        }
    }

    public GridPane getContainer() {
        GridPane container = new GridPane();
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                GridPane.setRowIndex(map[x][y], y);
                GridPane.setColumnIndex(map[x][y], x);
                container.getChildren().addAll(map[x][y]);
            }
        }
        return container;
    }
}
