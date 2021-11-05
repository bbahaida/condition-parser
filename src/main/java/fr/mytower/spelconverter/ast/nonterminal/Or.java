package fr.mytower.spelconverter.ast.nonterminal;


import fr.mytower.spelconverter.ConverterTokenEnum;
import fr.mytower.spelconverter.ast.NonTerminal;

import static fr.mytower.spelconverter.ConverterTokenEnum.OR;

public class Or extends NonTerminal {

    @Override
    public ConverterTokenEnum getOperation() {
        return OR;
    }
}
