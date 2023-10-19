import Common.Logger;
import Lab2.Forest;
import Lab2.ForestObjectsCreator;

import java.util.ArrayList;
import java.util.HashMap;
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


    public static void fillArrayListCollection(ArrayList<Forest> _forests, int _size)
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

            m_logger.rawLog("ADD; ID = " + forest.getID() + "; TIME = " + duration);
        }

        m_logger.rawLog("\nOperation ADD Total count = " + _size);
        m_logger.rawLog("\nOperation ADD Total time = " + total_time);
        m_logger.rawLog("\nOperation ADD Medium time = " + (total_time / _size));
    }



    public static void removeFromArrayListCollection(ArrayList<Forest> _forests, int _count)
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

            m_logger.rawLog("REMOVE; ID = " + delited_idx + "; TIME = " + duration);
        }

        m_logger.rawLog("\nOperation REMOVE Total count = " + _count);
        m_logger.rawLog("\nOperation REMOVE Total time = " + total_time);
        m_logger.rawLog("\nOperation REMOVE Medium time = " + (total_time / _count));
    }



    public static void fillHashMapCollection(HashMap<String, Forest> _forests, int _size)
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

            m_logger.rawLog("ADD; ID = " + forest.getID() + "; TIME = " + duration);
        }

        m_logger.rawLog("\nOperation ADD Total count = " + _size);
        m_logger.rawLog("\nOperation ADD Total time = " + total_time);
        m_logger.rawLog("\nOperation ADD Medium time = " + (total_time / _size));
    }



    public static void removeFromHashMapCollection(HashMap<String, Forest> _forests, int _count)
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

            m_logger.rawLog("REMOVE; ID = " + delited_idx + "; TIME = " + duration);
        }

        m_logger.rawLog("\nOperation REMOVE Total count = " + _count);
        m_logger.rawLog("\nOperation REMOVE Total time = " + total_time);
        m_logger.rawLog("\nOperation REMOVE Medium time = " + (total_time / _count));
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
        m_logger.log("Application started");

        ArrayList<Forest> forests = new ArrayList<>();

        HashMap<String, Forest> forests_map = new HashMap<>();

        int[] testing_sizes = new int[]{5, 10, 100, 1000};

        testArrayListCollection(forests, testing_sizes);
        testHashMapCollection(forests_map, testing_sizes);

        m_logger.log("\n\nApplication closed");
    }
}