package net.bmbsolutions.spelconverter;

public enum ConverterTokenEnum {
    OR {
        @Override
        public String getExpression() {
            return " || ";
        }
    },
    AND {
        @Override
        public String getExpression() {
            return " && ";
        }
    },
    NOT {
        @Override
        public String getExpression() {
            return " != ";
        }
    },
    NONE {
        @Override
        public String getExpression() {
            return null;
        }
    },
    EQ {
        @Override
        public String getExpression() {
            return " == ";
        }
    },
    PROPERTY {
        @Override
        public String getExpression() {
            return null;
        }
    },
    NUMBER {
        @Override
        public String getExpression() {
            return null;
        }
    },
    EOL {
        @Override
        public String getExpression() {
            return null;
        }
    },
    EOF {
        @Override
        public String getExpression() {
            return null;
        }
    };

    public abstract String getExpression();
}
