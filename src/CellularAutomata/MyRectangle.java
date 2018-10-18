package CellularAutomata;

import javafx.animation.FillTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static CellularAutomata.Constants.CHANGE_COLOR_TIME;
import static CellularAutomata.Constants.SPACE;
import static CellularAutomata.Constants.WALL;

public class MyRectangle extends Rectangle{

    Color currentColor = Color.web("#FFFFFF");
    //static final Color interColor = Color.web("#992299");

    public MyRectangle(){
        this.setOnMouseClicked(event -> {
            Color newColor;
            if (currentColor == Color.web(WALL))
                newColor = Color.web(SPACE);
            else
                newColor = Color.web(WALL);
            this.setFill(newColor);
            this.currentColor = newColor;
        });
    }

    public void setAnimatedFill(Color color) {

        this.setFill(color);
        currentColor = color;
//        FillTransition ft = new FillTransition(Duration.millis(CHANGE_COLOR_TIME), this, currentColor, color);
//        ft.play();
//        currentColor = color;

    }

}
