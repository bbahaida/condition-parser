package fr.mytower.spelconverter.ast;

import fr.mytower.spelconverter.ConverterTokenEnum;
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
