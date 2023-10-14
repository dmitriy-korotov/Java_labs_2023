package Lab2;

public abstract class Animal {

    protected Forest m_habitat;
    protected int m_id;
    protected int m_size;


    private static int m_animals_count = 0;


    public Animal() { m_id = generateAnimalID(); }
    public Animal(int _id) { m_id = _id; }


    public int getID()
    {
        return m_id;
    }


    public int getSize()
    {
        return m_size;
    }


    private static int generateAnimalID()
    {
        return ++m_animals_count;
    }


    public abstract void findFood();


    public abstract String dump();

    public abstract void initFromDumpedView(String _dumped_view);

}
