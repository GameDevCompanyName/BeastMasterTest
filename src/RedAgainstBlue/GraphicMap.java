package RedAgainstBlue;

import javafx.animation.PauseTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

import static CellularAutomata.Constants.WALL;
import static RedAgainstBlue.Constants.*;

public class GraphicMap {

    MyRectangle[][] map;
    GridPane container;
    int fillPercent;
    int width;
    int height;
    long seed;
    boolean shouldUpdate = true;

    public GraphicMap(int fillPercent, int width, int height, long seed) {
        map = new MyRectangle[width][height];
        int size = 800/width;
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                MyRectangle newRect = new MyRectangle();
                newRect.setWidth(size);
                newRect.setHeight(size);
                newRect.setAnimatedFill(Color.web(SPACE));
                map[y][x] = newRect;
            }
        }
        this.fillPercent = fillPercent;
        this.width = width;
        this.height = height;
        this.seed = seed;
        container = new GridPane();
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                GridPane.setRowIndex(map[y][x], y);
                GridPane.setColumnIndex(map[y][x], x);
                container.getChildren().addAll(map[y][x]);
            }
        }
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
        CellType[][] newMap = new CellType[map.length][map[0].length];
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
//                if (map[y][x].getFill().equals(Color.web(SPACE)))
//                    newMap[y][x] = CellType.SPACE;
                if (map[y][x].getFill().equals(Color.web(RED)))
                    newMap[y][x] = CellType.RED;
                if (map[y][x].getFill().equals(Color.web(BLUE)))
                    newMap[y][x] = CellType.BLUE;
                boolean wasCell = !map[y][x].getFill().equals(Color.web(SPACE));
                if (!wasCell){
                    continue;
                }
                boolean wasRed = map[y][x].getFill().equals(Color.web(RED));
                int direction = hasEnemy(map, x, y, wasRed);
                if (direction != 0){
                    CellType type = wasRed ? CellType.RED : CellType.BLUE;
                    boolean conquer = new Random().nextBoolean();
                    if (!conquer){
                        continue;
                    }
                    switch (direction){
                        case 1:
                            newMap[y-1][x] = type;
                            continue;
                        case 2:
                            newMap[y][x+1] = type;
                            continue;
                        case 3:
                            newMap[y+1][x] = type;
                            continue;
                        case 4:
                            newMap[y][x-1] = type;
                            continue;
                    }
                }
                if (direction == 0){
                    int newDirection = new Random().nextInt(4) + 1;
                    CellType type = wasRed ? CellType.RED : CellType.BLUE;
                    Color color = Color.web(SPACE);
                    switch (newDirection){
                        case 1:
                            if ((y-1 >= 0 && y-1 < map[0].length)
                                    && map[y-1][x].getFill().equals(color)
                                    && newMap[y-1][x] != type){
                                newMap[y-1][x] = type;
                                newMap[y][x] = CellType.SPACE;
                            }
                            break;
                        case 2:
                            if ((x+1 >= 0 && x+1 < map[0].length) && map[y][x+1].getFill().equals(color)
                                    && newMap[y][x+1] != type){
                                newMap[y][x+1] = type;
                                newMap[y][x] = CellType.SPACE;
                            }
                            break;
                        case 3:
                            if ((y+1 >= 0 && y+1 < map[0].length) && map[y+1][x].getFill().equals(color)
                                    && newMap[y+1][x] != type){
                                newMap[y+1][x] = type;
                                newMap[y][x] = CellType.SPACE;
                            }
                            break;
                        case 4:
                            if ((x-1 >= 0 && x-1 < map[0].length) && map[y][x-1].getFill().equals(color)
                                    && newMap[y][x-1] != type){
                                newMap[y][x-1] = type;
                                newMap[y][x] = CellType.SPACE;
                            }
                            break;
                        default:
                    }
                }
            }
        }
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                if (newMap[y][x] == null){
                    map[y][x].setAnimatedFill(Color.web(SPACE));
                    continue;
                }
                switch (newMap[y][x]){
                    case SPACE:
                        map[y][x].setAnimatedFill(Color.web(SPACE));
                        break;
                    case RED:
                        map[y][x].setAnimatedFill(Color.web(RED));
                        break;
                    case BLUE:
                        map[y][x].setAnimatedFill(Color.web(BLUE));
                        break;
                }
            }
        }
        PauseTransition pause = new PauseTransition(Duration.millis(PAUSE_TIME));
        pause.setOnFinished(event ->
                update()
        );
        pause.play();
    }

    private int hasEnemy(MyRectangle[][] map, int x, int y, boolean wasRed) {
        Color enemyColor;
        if (wasRed)
            enemyColor = Color.web(BLUE);
        else
            enemyColor = Color.web(RED);
        if ((y-1 >= 0 && y-1 < map[0].length) && map[y-1][x].getFill().equals(enemyColor))
            return 1;
        if ((x+1 >= 0 && x+1 < map.length) && map[y][x+1].getFill().equals(enemyColor))
            return 2;
        if ((y+1 >= 0 && y+1 < map[0].length) && map[y+1][x].getFill().equals(enemyColor))
            return 3;
        if ((x-1 >= 0 && x-1 < map.length) && map[y][x-1].getFill().equals(enemyColor))
            return 4;

        return 0;
    }


    private void generateNoise() {
        Random randomGen = new Random(seed);
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                if (randomGen.nextInt(100) < fillPercent){
//                    map[y][x].setAnimatedFill(Color.web(RED));
                    boolean isRed = new Random().nextBoolean();
                    if (isRed)
                        map[y][x].setAnimatedFill(Color.web(RED));
                    else
                        map[y][x].setAnimatedFill(Color.web(BLUE));
                }
                else
                    map[y][x].setAnimatedFill(Color.web(SPACE));
            }
        }
    }

    public GridPane getContainer() {
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
        generateNoise();
    }

    public void clear() {
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                map[y][x].setAnimatedFill(Color.web(SPACE));
            }
        }
    }

    public void toggleGrid() {
        if (container.isGridLinesVisible())
            container.setGridLinesVisible(false);
        else
            container.setGridLinesVisible(true);
    }

    public enum CellType {
        RED, BLUE, SPACE
    }
}
