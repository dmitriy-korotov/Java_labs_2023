package Lab2;

import java.util.ArrayList;


public class Predator extends Animal {

    public Predator(Forest _habitat, int _size)
    {
        this.m_habitat = _habitat;
        this.m_size = _size;
    }

    public Predator(String _dumped_view)
    {
        initFromDumpedView(_dumped_view);
    }

    @Override
    public void findFood() {
        Predator food_predator = null;
        ArrayList<Predator> predators = m_habitat.getPredators();
        for (Predator predator : predators) {
            if (predator.getID() != getID() && predator.getSize() < m_size) {
                food_predator = predator;
                break;
            }
        }
        if (food_predator != null)
        {
            predators.remove(food_predator);
            return;
        }
        Herbivore food_herbivore = null;
        ArrayList<Herbivore> herbivores = m_habitat.getHerbivores();
        for (Herbivore herbivore : herbivores) {
            if (herbivore.getSize() < m_size) {
                food_herbivore = herbivore;
                break;
            }
        }
        if (food_herbivore != null)
        {
            herbivores.remove(food_herbivore);
        }
    }

    public String toString()
    {
        return "{\n\tID: " + super.getID() + "\n\tSize: " + m_size + "\n}";
    }


    @Override
    public String dump() {
        String result = "Animal\n";
        result += "Predator\n";
        result += getID() + "\n";
        result += m_size + "\n";

        return result;
    }

    @Override
    public void initFromDumpedView(String _dumped_view)
    {
        String[] lines = _dumped_view.split("\n");
        super.m_id = Integer.parseInt(lines[0]);
        m_size = Integer.parseInt(lines[1]);
    }
}
