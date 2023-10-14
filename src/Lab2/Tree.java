package Lab2;

public class Tree extends Plant {

    private TreeType m_tree_type;


    public Tree(TreeType _tree_type)
    {
        this.m_tree_type = _tree_type;
    }

    public Tree(String _dumped_view) { initFromDumpedView(_dumped_view); }

    public TreeType getType() { return m_tree_type; }


    public String toString()
    {
        return "{\n\ttype: " + m_tree_type.toString() + "\n}";
    }

    @Override
    public String dump() {

        StringBuilder result = new StringBuilder();
        result.append("Plant\n");
        result.append("Tree\n");
        result.append(m_tree_type.toString());

        return result.toString();
    }

    @Override
    public void initFromDumpedView(String _dumped_view)
    {
        String[] lines = _dumped_view.split("\n");
        m_tree_type = TreeType.valueOf(lines[0]);
    }
}
