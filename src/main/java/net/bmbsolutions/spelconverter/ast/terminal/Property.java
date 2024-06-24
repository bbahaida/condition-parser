package net.bmbsolutions.spelconverter.ast.terminal;


import net.bmbsolutions.spelconverter.ast.Terminal;

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
