package net.bmbsolutions.spelconverter.ast;

import net.bmbsolutions.spelconverter.ConverterTokenEnum;
import lombok.Data;

@Data
public abstract class NonTerminal implements AST {
    protected AST left;
    protected AST right;

    @Override
    public Class<NonTerminal> getType() {
        return NonTerminal.class;
    }

    public abstract ConverterTokenEnum getOperation();
}
