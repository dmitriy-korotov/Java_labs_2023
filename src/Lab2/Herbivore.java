package Lab2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Herbivore extends Animal {

    private HashSet<TreeType> m_permitted_trees;
    private HashSet<GrassType> m_permitted_grasses;

    public Herbivore(Forest _habitat, int _size)
    {
        m_permitted_trees = new HashSet<>();
        m_permitted_grasses = new HashSet<>();
        this.m_habitat = _habitat;
        this.m_size = _size;
    }


    public Herbivore(String _dumped_view)
    {
        m_permitted_trees = new HashSet<>();
        m_permitted_grasses = new HashSet<>();
        initFromDumpedView(_dumped_view);
    }


    void setPermittedTrees(HashSet<TreeType> _permitted_trees) { m_permitted_trees = _permitted_trees; }

    void setPermittedGrasses(HashSet<GrassType> _permitted_grasses) { m_permitted_grasses = _permitted_grasses; }

    @Override
    public void findFood()
    {
        Grass food_grass = null;
        ArrayList<Grass> grasses = m_habitat.getGrasses();
        for (Grass grass : grasses) {
            if (m_permitted_grasses.contains(grass.getType())) {
                food_grass = grass;
                break;
            }
        }
        if (food_grass != null)
        {
            grasses.remove(food_grass);
            return;
        }
        Tree food_tree = null;
        ArrayList<Tree> trees = m_habitat.getTrees();
        for (Tree tree : trees) {
            if (m_permitted_trees.contains(tree.getType())) {
                food_tree = tree;
                break;
            }
        }
        if (food_tree != null)
        {
            trees.remove(food_tree);
        }
    }


    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append("{\n\tID: ").append(super.getID());
        result.append("\n\tSize: ").append(super.getID());

        if (!m_permitted_trees.isEmpty())
        {
            result.append("\n\tPermitted Trees:\n\t{\n");
            for (TreeType tree_type : m_permitted_trees)
            {
                result.append(tree_type == TreeType.LowerTree ? "\t\tLowerTree\n" : "\t\tUpperTree\n");
            }
            result.append("\t}");
        }

        if (!m_permitted_grasses.isEmpty())
        {
            result.append("\n\tPermitted Trees:\n\t{\n");
            for (GrassType grass_type : m_permitted_grasses)
            {
                result.append(grass_type == GrassType.LowerGrass ? "\t\tLowerGrass\n" : "\t\tUpperGrass\n");
            }
            result.append("\t}");
        }
        result.append("\n}");

        return result.toString();
    }


    @Override
    public String dump() {

        StringBuilder result = new StringBuilder("Animal\n");
        result.append("Herbivore\n");
        result.append(getID()).append("\n");
        result.append(m_size).append("\n");
        result.append("Permitted Trees:\n");
        for (TreeType tree : m_permitted_trees)
        {
            result.append(String.format("%s\n", tree.toString()));
        }
        result.append("Permitted Grasses:\n");
        for (GrassType grass : m_permitted_grasses)
        {
            result.append(String.format("%s\n", grass.toString()));
        }

        return result.toString();
    }

    @Override
    public void initFromDumpedView(String _dumped_view)
    {
        String[] lines = _dumped_view.split("\n");
        super.m_id = Integer.parseInt(lines[0]);
        m_size = Integer.parseInt(lines[1]);

        int i = 3;
        for (; i < lines.length; i++)
        {
            if (!Objects.equals(lines[i], "Upper") && !Objects.equals(lines[i], "Lower"))
                break;
            m_permitted_trees.add(TreeType.valueOf(lines[i]));
        }
        if (i == lines.length)
            return;

        for (; i < lines.length; i++)
        {
            if (!Objects.equals(lines[i], "Upper") && !Objects.equals(lines[i], "Lower"))
                break;
            m_permitted_grasses.add(GrassType.valueOf(lines[i]));
        }
    }
}
