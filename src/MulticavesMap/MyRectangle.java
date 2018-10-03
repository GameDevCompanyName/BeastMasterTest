package MulticavesMap;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MyRectangle extends Rectangle{

    Color currentColor = Color.web("#FFFFFF");
    static final Color interColor = Color.web("#992299");

    public void setAnimatedFill(Color color) {

//        FillTransition ft = new FillTransition(Duration.millis(200), this, currentColor, color);
//        ft.play();
        this.setFill(color);
        currentColor = color;

    }

}
