package net.bmbsolutions.spelconverter.ast.terminal;

import net.bmbsolutions.spelconverter.ast.Terminal;

public class Value extends Terminal {
    public Value(int value) {
        super(value);
    }

    @Override
    public String getStringValue() {
        return null;
    }

    @Override
    public Integer getNumericValue() {
        return value;
    }
}
