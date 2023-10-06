package Lab2;

public class Grass extends Plant {

        private final GrassType m_grass_type;


        public Grass(GrassType _grass_type)
        {
            this.m_grass_type = _grass_type;
        }


        public GrassType getType() { return m_grass_type; }


        @Override
        public String dump() {

                StringBuilder result = new StringBuilder();
                result.append("Plant\n");
                result.append("Grass\n");
                result.append(m_grass_type.toString());

                return result.toString();
        }
}
