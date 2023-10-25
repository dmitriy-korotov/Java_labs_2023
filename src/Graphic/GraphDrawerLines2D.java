package Graphic;

import java.awt.*;
import java.util.ArrayList;

public class GraphDrawerLines2D implements IGraphDrawer2D {

    private Double m_min_x = 0.;
    private Double m_max_x = 0.;
    private Double m_min_y = 0.;
    private Double m_max_y = 0.;
    private ArrayList<Point> m_points = new ArrayList<Point>();
    private Color m_color = Color.BLACK;


    public GraphDrawerLines2D(ArrayList<Point> _points)
    {
        m_points = _points;
        m_max_x = __getMaxCoordinateX();
        m_min_x = __getMinCoordinateX();
        m_min_y = __getMinCoordinateY();
        m_max_y = __getMaxCoordinateY();
    }

    public void setPoints(ArrayList<Point> _points)
    {
        m_points = _points;
        m_max_x = __getMaxCoordinateX();
        m_min_x = __getMinCoordinateX();
        m_min_y = __getMinCoordinateY();
        m_max_y = __getMaxCoordinateY();
    }

    public void setColor(Color _color) { m_color = _color; }

    @Override
    public ArrayList<Point> getPoints() { return m_points; }

    public double __getMinCoordinateX()
    {
        Double min_coord_x = Double.MAX_VALUE;
        for (Point point:
             m_points) {
            min_coord_x = Math.min(min_coord_x, point.x);
        }
        return min_coord_x;
    }

    @Override
    public double getMinCoordinateX() { return m_min_x; }

    public double __getMaxCoordinateX()
    {
        Double max_coord_x = Double.MIN_VALUE;
        for (Point point:
                m_points) {
            max_coord_x = Math.max(max_coord_x, point.x);
        }
        return max_coord_x;
    }

    @Override
    public double getMaxCoordinateX() { return m_max_x; }

    public double __getMinCoordinateY()
    {
        Double min_coord_y = Double.MAX_VALUE;
        for (Point point:
                m_points) {
            min_coord_y = Math.min(min_coord_y, point.y);
        }
        return min_coord_y;
    }

    @Override
    public double getMinCoordinateY() { return m_min_y; }

    public double __getMaxCoordinateY()
    {
        Double max_coord_y = Double.MIN_VALUE;
        for (Point point:
                m_points) {
            max_coord_y = Math.max(max_coord_y, point.y);
        }
        return max_coord_y;
    }

    @Override
    public double getMaxCoordinateY() { return m_max_y; }


    @Override
    public void draw(Graphics2D _draw_context)
    {
        int radius = 10;

        _draw_context.setColor(m_color);

        Point prev_point = m_points.get(0);
        for (Point point:
             m_points) {
            _draw_context.drawLine(prev_point.x, prev_point.y,
                                   point.x, point.y);
            _draw_context.fillOval(point.x - radius / 2, point.y - radius / 2, radius, radius);

            prev_point = point;
        }
    }
}
