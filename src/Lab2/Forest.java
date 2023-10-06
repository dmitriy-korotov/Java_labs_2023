package Lab2;

import java.util.ArrayList;
import java.util.HashSet;

public class Forest {

    private final ArrayList<Predator> m_predators;
    private final ArrayList<Herbivore> m_herbivores;
    private final ArrayList<Tree> m_trees;
    private final ArrayList<Grass> m_grasses;


    public Forest()
    {
        this.m_predators = new ArrayList<>();
        this.m_herbivores = new ArrayList<>();
        this.m_trees = new ArrayList<>();
        this.m_grasses = new ArrayList<>();
    }


    public ArrayList<Predator> getPredators() { return m_predators; }

    public ArrayList<Herbivore> getHerbivores() { return m_herbivores; }

    public ArrayList<Tree> getTrees() { return m_trees; }

    public ArrayList<Grass> getGrasses() { return m_grasses; }


    public Predator addNewPredator(int _size)
    {
        Predator new_predator = new Predator(this, _size);
        m_predators.add(new_predator);
        return new_predator;
    }

    public Herbivore addNewHerbivore(int _size, HashSet<TreeType> _permitted_trees,
                                     HashSet<GrassType> _permitted_grasses)
    {
        Herbivore new_herbivore = new Herbivore(this, _size);
        new_herbivore.setPermittedGrasses(_permitted_grasses);
        new_herbivore.setPermittedTrees(_permitted_trees);
        m_herbivores.add(new_herbivore);
        return new_herbivore;
    }

    public void addNewGrass(GrassType _grass_type)
    {
        m_grasses.add(new Grass(_grass_type));
    }


    public void addNewTree(TreeType _tree_type)
    {
        m_trees.add(new Tree(_tree_type));
    }



    @Override
    public String toString() {
        String result = "Predators:\n";
        for (Predator predator: m_predators) {
            result = result.concat("\t" + predator.toString() + "\n");
        }
        result += "Herbivores:\n";
        for (Herbivore herbivore: m_herbivores) {
            result = result.concat("\t" + herbivore.toString() + "\n");
        }
        result += "Trees:\n";
        for (Tree tree: m_trees) {
            result = result.concat("\t" + tree.toString() + "\n");
        }
        result += "Grasses:\n";
        for (Grass grass: m_grasses) {
            result = result.concat("\t" + grass.toString() + "\n");
        }
        return result;
    }
}