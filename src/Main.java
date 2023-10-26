import Common.Logger;
import Common.Pair;
import Graphic.*;
import Graphic.SwingUIContext;
import Graphic.Window;
import Lab2.Forest;
import Lab2.ForestObjectsCreator;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.random.RandomGenerator;



public class Main {

    private static Logger m_logger = new Logger();


    public  static Forest randomForest()
    {
        Forest forest = new Forest();

        ForestObjectsCreator creator = new ForestObjectsCreator(forest);

        for (int i = 0; i < 10; i++)
        {
            creator.randomPredator();
            creator.randomHebivore();
            creator.randomTree();
            creator.randomGrass();
        }

        return forest;
    }


    public static Pair<Long, Long> fillArrayListCollection(ArrayList<Forest> _forests, int _size)
    {
        m_logger.rawLog("\n\n ARRAY LIST   SIZE - " + _size + "\n\n");

        long total_time = 0;

        for (int i = 0; i < _size; i++)
        {
            long before = System.nanoTime();
            Forest forest = randomForest();
            _forests.add(forest);
            long after = System.nanoTime();

            long duration = after - before;

            total_time += duration;
        }

        m_logger.rawLog("\nOperation ADD Total count = " + _size);
        m_logger.rawLog("\nOperation ADD Total time = " + total_time / 1000);
        m_logger.rawLog("\nOperation ADD Medium time = " + (total_time / 1000 / _size));

        return new Pair<Long, Long>(total_time / 1000, total_time / 1000 / _size);
    }



    public static Pair<Long, Long> removeFromArrayListCollection(ArrayList<Forest> _forests, int _count)
    {
        long total_time = 0;

        for (int i = 0; i < _count; i++)
        {
            long before = System.nanoTime();
            int delited_idx = RandomGenerator.getDefault().nextInt(0, _forests.size() - 1);
            _forests.remove(delited_idx);
            long after = System.nanoTime();

            long duration = after - before;

            total_time += duration;
        }

        m_logger.rawLog("\nOperation REMOVE Total count = " + _count);
        m_logger.rawLog("\nOperation REMOVE Total time = " + total_time / 1000);
        m_logger.rawLog("\nOperation REMOVE Medium time = " + (total_time / 1000 / _count));

        return new Pair<Long, Long>(total_time / 1000, total_time / 1000 / _count);
    }



    public static Pair<Long, Long> fillHashMapCollection(HashMap<String, Forest> _forests, int _size)
    {
        m_logger.rawLog("\n\n HASH MAP   SIZE - " + _size + "\n\n");

        long total_time = 0;

        for (int i = 0; i < _size; i++)
        {
            long before = System.nanoTime();
            Forest forest = randomForest();
            _forests.put("Forest " + (i + 1), forest);
            long after = System.nanoTime();

            long duration = after - before;

            total_time += duration;
        }

        m_logger.rawLog("\nOperation ADD Total count = " + _size);
        m_logger.rawLog("\nOperation ADD Total time = " + total_time / 1000);
        m_logger.rawLog("\nOperation ADD Medium time = " + (total_time / 1000 / _size));

        return new Pair<Long, Long>(total_time / 1000, total_time / 1000 / _size);
    }



    public static Pair<Long, Long> removeFromHashMapCollection(HashMap<String, Forest> _forests, int _count)
    {
        long total_time = 0;

        for (int i = 0; i < _count; i++)
        {
            long before = System.nanoTime();
            int delited_idx = RandomGenerator.getDefault().nextInt(1, _forests.size());
            _forests.remove("Forest " + (delited_idx));
            long after = System.nanoTime();

            long duration = after - before;

            total_time += duration;
        }

        m_logger.rawLog("\nOperation REMOVE Total count = " + _count);
        m_logger.rawLog("\nOperation REMOVE Total time = " + total_time / 1000);
        m_logger.rawLog("\nOperation REMOVE Medium time = " + (total_time / 1000 / _count));

        return new Pair<Long, Long>(total_time / 1000, total_time / 1000 / _count);
    }


    public static void testArrayListCollection(ArrayList<Forest> _forests, int[] _testing_sizes)
    {
        for (int testing_size : _testing_sizes)
        {
            fillArrayListCollection(_forests, testing_size);
            removeFromArrayListCollection(_forests, (int)(testing_size / 10) + 1);
        }
    }


    public static void testHashMapCollection(HashMap<String, Forest> _forests, int[] _testing_sizes)
    {
        for (int testing_size : _testing_sizes)
        {
            fillHashMapCollection(_forests, testing_size);
            removeFromHashMapCollection(_forests, (int)(testing_size / 10) + 1);
        }
    }


    public static void main(String[] args)
    {
        /*m_logger.log("Application started");

        ArrayList<Forest> forests = new ArrayList<>();

        HashMap<String, Forest> forests_map = new HashMap<>();

        int[] testing_sizes = new int[]{5, 10, 100, 1000};

        testArrayListCollection(forests, testing_sizes);
        testHashMapCollection(forests_map, testing_sizes);

        m_logger.log("\n\nApplication closed");*/


        SwingUIContext context = new SwingUIContext();
        context.run(new Runnable() {
            @Override
            public void run() {

                ArrayList<Forest> forests = new ArrayList<>();

                HashMap<String, Forest> forests_map = new HashMap<>();

                Window window = new Window("Graph", 1200, 720);
                window.setResizable(false);

                int[] testing_sizes = new int[]{5, 10, 100, 1000, 10000};

                CoordinatePlane coordinatePlane = new CoordinatePlane(window.getWidth(), window.getHeight());

                ArrayList<Point> total_points = new ArrayList<>() {};
                ArrayList<Point> average_points = new ArrayList<>() {};

                for (int size:
                     testing_sizes) {
                    var pair = fillArrayListCollection(forests, size);

                    total_points.add(new Point(size, pair.first.intValue()));

                    average_points.add(new Point(size, pair.second.intValue()));
                }

                GraphDrawerLines2D total_drawer = new GraphDrawerLines2D(total_points);
                GraphDrawerLines2D average_drawer = new GraphDrawerLines2D(average_points);
                average_drawer.setColor(Color.GREEN);

                coordinatePlane.addDrawer(total_drawer);
                coordinatePlane.addDrawer(average_drawer);

                window.add(coordinatePlane);

            }
        });
    }
}