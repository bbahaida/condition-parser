package fr.mytower.spelconverter.ast.nonterminal;

import fr.mytower.spelconverter.ConverterTokenEnum;
import fr.mytower.spelconverter.ast.NonTerminal;

import static fr.mytower.spelconverter.ConverterTokenEnum.NOT;

public class Not extends NonTerminal {

    @Override
    public ConverterTokenEnum getOperation() {
        return NOT;
    }
}
