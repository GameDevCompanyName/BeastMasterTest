package RedAgainstBlue;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static RedAgainstBlue.Constants.BLUE;
import static RedAgainstBlue.Constants.RED;
import static RedAgainstBlue.Constants.SPACE;

public class MyRectangle extends Rectangle{

    Color currentColor = Color.web(SPACE);
    //static final Color interColor = Color.web("#992299");

    public MyRectangle(){
        this.setOnMouseClicked(event -> {
            Color newColor;
            if (currentColor.equals(Color.web(RED)))
                newColor = Color.web(SPACE);
            else
                newColor = Color.web(BLUE);
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
