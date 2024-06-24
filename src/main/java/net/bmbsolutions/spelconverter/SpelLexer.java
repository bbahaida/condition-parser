package net.bmbsolutions.spelconverter;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.StringTokenizer;

@Slf4j
public class SpelLexer {
    public static final String REGEX = "\\w+\\.*\\w*[\\[(]*[']*\\w*[']*[])]*";
    private final StringTokenizer input;
    private final ConverterToken symbol;

    public SpelLexer(String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("expression should not be null");
        }
        input = new StringTokenizer(expression.trim());
        this.symbol = new ConverterToken();
        this.symbol.setOp(ConverterTokenEnum.NONE);
    }

    public ConverterToken nextSymbol() throws IOException {
        if (input.hasMoreTokens()) {
            String token = input.nextToken();
            switch (token) {
                case "&&":
                    this.symbol.setOp(ConverterTokenEnum.AND);
                    break;
                case "||":
                    this.symbol.setOp(ConverterTokenEnum.OR);
                    break;
                case "==":
                    this.symbol.setOp(ConverterTokenEnum.EQ);
                    break;
                case "!=":
                    this.symbol.setOp(ConverterTokenEnum.NOT);
                    break;
                default:
                    if (isNumeric(token)) {
                        this.symbol.setOp(ConverterTokenEnum.NUMBER);
                        this.symbol.setNumber(Integer.parseInt(token));
                        break;
                    }
                    if (isValidProperty(token)) {
                        this.symbol.setOp(ConverterTokenEnum.PROPERTY);
                        this.symbol.setValue(token);
                        break;
                    }
                    throw new IllegalArgumentException("Invalid operator");
            }
        }

        log.debug("Symbol: {}", this.symbol.getOp().toString());

        return this.symbol;
    }

    private boolean isValidProperty(String token) {
        return token.matches(REGEX);
    }

    private boolean isNumeric(String token) {
        if (token == null) {
            return false;
        }
        try {
            Integer.parseInt(token);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
