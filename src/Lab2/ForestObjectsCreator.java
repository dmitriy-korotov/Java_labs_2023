package Lab2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.random.RandomGenerator;

public class ForestObjectsCreator {

    private Forest m_forest;


    public ForestObjectsCreator(Forest _forest) { m_forest = _forest; }


    public void randomPredator()
    {
        m_forest.addNewPredator(RandomGenerator.getDefault().nextInt(1, 1000));
    }


    public void randomHebivore()
    {
        HashSet<TreeType> trees = new HashSet<>();
        HashSet<GrassType> grasses = new HashSet<>();

        int trees_count = RandomGenerator.getDefault().nextInt(3, 100);
        for (int i = 0; i < trees_count; i++)
            trees.add(randomTreeType());

        int grasses_count = RandomGenerator.getDefault().nextInt(3, 100);
        for (int i = 0; i < grasses_count; i++)
            grasses.add(randomGrassType());

        m_forest.addNewHerbivore(RandomGenerator.getDefault().nextInt(1, 1000), trees, grasses);
    }


    public void randomTree()
    {
        m_forest.addNewTree(randomTreeType());
    }


    public void randomGrass()
    {
        m_forest.addNewGrass(randomGrassType());
    }

    public TreeType randomTreeType()
    {
        ArrayList<TreeType> choice = new ArrayList<>();
        choice.add(TreeType.UpperTree);
        choice.add(TreeType.LowerTree);

        int random_index = RandomGenerator.getDefault().nextInt(0, choice.size());

        return choice.get(random_index);
    }


    public GrassType randomGrassType()
    {
        ArrayList<GrassType> choice = new ArrayList<>();
        choice.add(GrassType.UpperGrass);
        choice.add(GrassType.LowerGrass);

        int random_index = RandomGenerator.getDefault().nextInt(0, choice.size());

        return choice.get(random_index);
    }
}
