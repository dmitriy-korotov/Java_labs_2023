package Graphic;

import java.awt.*;
import java.util.ArrayList;

public interface IGraphDrawer2D {

    public void draw(Graphics2D _draw_context);

    public ArrayList<Point> getPoints();

    public double getMinCoordinateX();

    public double getMaxCoordinateX();

    public double getMinCoordinateY();

    public double getMaxCoordinateY();

}
