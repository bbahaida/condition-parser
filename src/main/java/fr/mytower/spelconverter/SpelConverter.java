package fr.mytower.spelconverter;

import fr.mytower.spelconverter.ast.AST;
import fr.mytower.spelconverter.ast.NonTerminal;
import fr.mytower.spelconverter.ast.Terminal;
import fr.mytower.spelconverter.ast.nonterminal.And;
import fr.mytower.spelconverter.ast.nonterminal.Eq;
import fr.mytower.spelconverter.ast.nonterminal.Not;
import fr.mytower.spelconverter.ast.nonterminal.Or;
import fr.mytower.spelconverter.ast.terminal.Property;
import fr.mytower.spelconverter.ast.terminal.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpelConverter {
    private AST root;
    private ConverterToken symbol;
    private SpelLexer lexer;

    public SpelConverter() {
    }


    public SpelDTO asCondition(String expression) throws Exception {
        this.lexer = new SpelLexer(expression);
        AST ast = build();
        return convertFromAst(ast, new SpelDTO());
    }

    public String asSpelExpression(SpelDTO condition) {
        return convertToString(new StringBuilder(), condition).toString();
    }

    private SpelDTO convertFromAst(AST ast, SpelDTO condition) {

        if (ast.getType().isAssignableFrom(Terminal.class)) {
            Terminal terminal = (Terminal) ast;
            condition.setOperation(null);
            if (terminal instanceof Property) {
                condition.setStringValue(terminal.getStringValue());
            }
            if (terminal instanceof Value) {
                condition.setNumericValue(terminal.getNumericValue());
            }
            return condition;
        }

        condition.setOperation(((NonTerminal) ast).getOperation());
        condition.setLeft(convertFromAst(((NonTerminal) ast).getLeft(), new SpelDTO()));
        condition.setRight(convertFromAst(((NonTerminal) ast).getRight(), new SpelDTO()));

        return condition;
    }


    private StringBuilder convertToString(StringBuilder builder, SpelDTO condition) {
        if (condition.getOperation() == null) {
            return condition.getStringValue() != null ? builder.append(condition.getStringValue())
                    : builder.append(condition.getNumericValue());
        }
        builder = convertToString(builder, condition.getLeft());
        builder.append(condition.getOperation().getExpression());
        builder = convertToString(builder, condition.getRight());
        return builder;
    }

    private AST build() throws Exception {
        log.debug("build" + this.lexer);
        expression();
        return root;
    }


    private void expression() throws Exception {
        log.debug("expression");
        term();
        while (symbol.getOp() == ConverterTokenEnum.OR) {
            Or or = new Or();
            or.setLeft(root);
            term();
            or.setRight(root);
            root = or;
        }
    }

    private void term() throws Exception {
        log.debug("term");
        factor();
        while (symbol.getOp() == ConverterTokenEnum.AND) {
            And and = new And();
            and.setLeft(root);
            factor();
            and.setRight(root);
            root = and;
        }
    }

    private void factor() throws Exception {
        symbol = lexer.nextSymbol();
        log.debug("factor:" + symbol);
        if (symbol.getOp() == ConverterTokenEnum.PROPERTY) {
            limit();
        } else {
            log.warn("Expression Malformed");
            throw new IllegalArgumentException("Expression Malformed");
        }
    }

    private void limit() throws Exception {
        log.debug("limit");
        prop();

        if (symbol.getOp() == ConverterTokenEnum.EQ) {
            Eq eq = new Eq();
            eq.setLeft(root);
            value();
            eq.setRight(root);
            root = eq;
        }
        if (symbol.getOp() == ConverterTokenEnum.NOT) {
            Not not = new Not();
            not.setLeft(root);
            value();
            not.setRight(root);
            root = not;
        }

    }

    private void prop() throws Exception {
        log.debug("prop " + symbol.getValue());
        root = new Property(symbol.getValue());
        symbol = lexer.nextSymbol();
    }

    private void value() throws Exception {
        symbol = lexer.nextSymbol();
        log.debug("value" + symbol);
        if (symbol.getOp() == ConverterTokenEnum.NUMBER) {
            root = new Value(symbol.getNumber());
            symbol = lexer.nextSymbol();
        } else if (symbol.getOp() == ConverterTokenEnum.PROPERTY) {
            root = new Property(symbol.getValue());
            symbol = lexer.nextSymbol();
        } else {
            throw new IllegalArgumentException("Bad Expression");
        }
    }
}
