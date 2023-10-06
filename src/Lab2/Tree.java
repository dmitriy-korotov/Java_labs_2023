package Lab2;

public class Tree extends Plant {

    private final TreeType m_tree_type;


    public Tree(TreeType _tree_type)
    {
        this.m_tree_type = _tree_type;
    }


    public TreeType getType() { return m_tree_type; }


    @Override
    public String dump() {

        StringBuilder result = new StringBuilder();
        result.append("Plant\n");
        result.append("Tree\n");
        result.append(m_tree_type.toString());

        return result.toString();
    }
}
