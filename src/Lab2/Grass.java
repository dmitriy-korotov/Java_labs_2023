package Lab2;

public class Grass extends Plant {

        private GrassType m_grass_type;


        public Grass(GrassType _grass_type)
        {
            this.m_grass_type = _grass_type;
        }

        public Grass(String _dumped_view) { initFromDumpedView(_dumped_view); }


        public GrassType getType() { return m_grass_type; }


        public String toString()
        {
                return "{\n\ttype: " + m_grass_type.toString() + "\n}";
        }

        @Override
        public String dump() {

                StringBuilder result = new StringBuilder();
                result.append("Plant\n");
                result.append("Grass\n");
                result.append(m_grass_type.toString());

                return result.toString();
        }

        @Override
        public void initFromDumpedView(String _dumped_view)
        {
                String[] lines = _dumped_view.split("\n");
                m_grass_type = GrassType.valueOf(lines[0]);
        }
}
