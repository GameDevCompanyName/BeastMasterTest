package GraphicMap;

import javafx.animation.PauseTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class GraphicMap {

    static String WALL = "#423535";
    private String SPACE;

    MyRectangle[][] map;
    private int fillPercent;
    private int smoothingCycles;
    private int smoothingParameter;
    private boolean makeWalls;
    private int width;
    private int height;
    private long seed;

    public GraphicMap(int fillPercent, int smoothingCycles, int smoothingParameter, boolean makeWalls, int width, int height, long seed, String spaceColor) {
        this.SPACE = spaceColor;
        map = new MyRectangle[width][height];
        int size = 250/width;
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
                map[y][x].setAnimatedFill(Color.web(SPACE));
            }
        }

        generateNoise();

        int smoothCounter = smoothingCycles;
//        PauseTransition pause = new PauseTransition(Duration.millis(250));
//        pause.setOnFinished(event ->
//                smooth(smoothCounter)
//        );
//        pause.play();
        smooth(smoothCounter);

    }

    private void smooth(int smoothCounter) {
        if (smoothCounter == 0)
            return;
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                int walls = countSurroundingWalls(map, x, y);
                if (walls < this.smoothingParameter){
                    map[y][x].setAnimatedFill(Color.web(SPACE));
                    continue;
                }
                if (walls > this.smoothingParameter){
                    map[y][x].setAnimatedFill(Color.web(WALL));
                    continue;
                }
            }
        }
        if (smoothCounter == 1)
            return;

//        PauseTransition pause = new PauseTransition(Duration.millis(250));
//        pause.setOnFinished(event ->
//                smooth(smoothCounter-1)
//        );
//        pause.play();

        smooth(smoothCounter - 1);
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
                if (makeWalls){
                    if (x == 0 || y == 0 || x == map[0].length - 1 || y == map.length - 1){
                        map[y][x].setAnimatedFill(Color.web(WALL));
                        continue;
                    }
                }
                if (randomGen.nextInt(100) < fillPercent)
                    map[y][x].setAnimatedFill(Color.web(WALL));
                else
                    map[y][x].setAnimatedFill(Color.web(SPACE));
            }
        }
    }

    public Pane getContainer() {
        GridPane container = new GridPane();
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                GridPane.setRowIndex(map[y][x], y);
                GridPane.setColumnIndex(map[y][x], x);
                container.getChildren().addAll(map[y][x]);
            }
        }
        Pane pane = new Pane();
        pane.getChildren().add(container);
        return pane;
    }

    public void makePassage(Direction dir) {

        int maxX = map[0].length - 1;
        int lowX = 0;
        int maxY = map.length - 1;
        int lowY = 0;

        switch (dir){
            case DOWN:
                lowX = maxX = (map[0].length - 1)/2;
                lowY = (map.length - 1)/2;
                break;
            case UP:
                lowX = maxX = (map[0].length - 1)/2;
                maxY = (map.length - 1)/2;
                break;
            case LEFT:
                lowY = maxY = (map.length - 1)/2;
                maxX = (map[0].length - 1)/2;
                break;
            case RIGHT:
                lowY = maxY = (map.length - 1)/2;
                lowX = (map[0].length - 1)/2;
        }

        for (int x = lowX; x <= maxX; x++){
            for (int y = lowY; y <= maxY; y++){
                map[y][x].setAnimatedFill(Color.web(SPACE));
            }
        }

    }
}
