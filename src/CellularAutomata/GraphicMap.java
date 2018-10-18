package CellularAutomata;

import javafx.animation.PauseTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

import static CellularAutomata.Constants.PAUSE_TIME;
import static CellularAutomata.Constants.SPACE;
import static CellularAutomata.Constants.WALL;

public class GraphicMap {

    MyRectangle[][] map;
    int fillPercent;
    int width;
    int height;
    long seed;
    boolean shouldUpdate = true;

    public GraphicMap(int fillPercent, int width, int height, long seed) {
        map = new MyRectangle[width][height];
        int size = 650/width;
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                MyRectangle newRect = new MyRectangle();
                newRect.setWidth(size);
                newRect.setHeight(size);
                newRect.setAnimatedFill(Color.web("#881188"));
                map[y][x] = newRect;
            }
        }
        this.fillPercent = fillPercent;
        this.width = width;
        this.height = height;
        this.seed = seed;
    }

    public void proceedMap() {
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                map[y][x].setAnimatedFill(Color.web(SPACE));
            }
        }

        generateNoise();

        PauseTransition pause = new PauseTransition(Duration.millis(PAUSE_TIME));
        pause.setOnFinished(event ->
                update()
        );
        pause.play();

    }

    private void update() {
        if (!shouldUpdate)
            return;
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                int walls = countSurroundingWalls(map, x, y);
                boolean wasWall = map[y][x].getFill().equals(Color.web(WALL));


                boolean isWall = applyRules(wasWall, walls);

                if (isWall)
                    map[y][x].setAnimatedFill(Color.web(WALL));
                else
                    map[y][x].setAnimatedFill(Color.web(SPACE));
            }
        }

        PauseTransition pause = new PauseTransition(Duration.millis(PAUSE_TIME));
        pause.setOnFinished(event ->
                update()
        );
        pause.play();
    }

    private boolean applyRules(boolean wasWall, int walls) {
        if (wasWall){
            if (walls < 2)
                return false;
            if (walls >= 2 && walls < 4)
                return true;
            if (walls >= 4)
                return false;
        } else {
            if (walls == 3)
                return true;
        }
        return false;
    }

    private static int countSurroundingWalls(Rectangle[][] map, int x, int y) {
        int counter = 0;
        for (int xc = x - 1; xc <= x + 1; xc++) {
            for (int yc = y - 1; yc <= y + 1; yc++) {
                if (xc == x && yc == y){
                    continue;
                }
                if (xc < 0 || yc < 0 || xc >= map[0].length || yc >= map.length){
                    //counter++;
                    continue;
                }
                if (map[yc][xc].getFill().equals(Color.web(WALL)))
                    counter++;
            }
        }
        return counter;
    }

    private void generateNoise() {
        Random randomGen = new Random(seed);
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                if (randomGen.nextInt(100) < fillPercent)
                    map[y][x].setAnimatedFill(Color.web(WALL));
                else
                    map[y][x].setAnimatedFill(Color.web(SPACE));
            }
        }
    }

    public GridPane getContainer() {
        GridPane container = new GridPane();
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                GridPane.setRowIndex(map[y][x], y);
                GridPane.setColumnIndex(map[y][x], x);
                container.getChildren().addAll(map[y][x]);
            }
        }
        return container;
    }

    public void changeState() {
        if (shouldUpdate)
            shouldUpdate = false;
        else
            shouldUpdate = true;
        if (shouldUpdate)
            update();
    }

    public void randomize() {
        this.seed = new Random().nextLong();
        if (!shouldUpdate)
            generateNoise();
    }

    public void clear() {
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                map[y][x].setAnimatedFill(Color.web(SPACE));
            }
        }
    }
}
