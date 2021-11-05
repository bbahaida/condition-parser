package fr.mytower.spelconverter.ast.terminal;


import fr.mytower.spelconverter.ast.Terminal;

public class Property extends Terminal {

    public Property(String property) {
        super(property);
    }

    @Override
    public String getStringValue() {
        return property;
    }

    @Override
    public Integer getNumericValue() {
        return null;
    }
}
