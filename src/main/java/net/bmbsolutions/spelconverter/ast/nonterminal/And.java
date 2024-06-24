package net.bmbsolutions.spelconverter.ast.nonterminal;


import net.bmbsolutions.spelconverter.ConverterTokenEnum;
import net.bmbsolutions.spelconverter.ast.NonTerminal;

import static net.bmbsolutions.spelconverter.ConverterTokenEnum.AND;

public class And extends NonTerminal {
    @Override
    public ConverterTokenEnum getOperation() {
        return AND;
    }
}
