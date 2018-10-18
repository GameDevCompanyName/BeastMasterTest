package CellularAutomata;

public class Constants {

    public static final String WALL = "#4444DD";
    public static final String SPACE = "#DDCCCC";
    public static Integer PAUSE_TIME = 15;
    public static Integer CHANGE_COLOR_TIME = (PAUSE_TIME / 3);


    public static void upperSpeed() {
        PAUSE_TIME *= 2;
        CHANGE_COLOR_TIME = PAUSE_TIME / 3;
    }

    public static void lowerSpeed() {
        PAUSE_TIME /= 2;
        CHANGE_COLOR_TIME = PAUSE_TIME / 3;
        if (PAUSE_TIME < 10){
            PAUSE_TIME = 10;
            CHANGE_COLOR_TIME = PAUSE_TIME / 3;
        }
    }
}
