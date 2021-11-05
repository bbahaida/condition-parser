package fr.mytower.spelconverter;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpelLexerTest {
    @Test
    void Test_Expression()
            throws Exception {
        String expression = "incoterm == 1";
        SpelLexer l = new SpelLexer(expression);
        ConverterToken s;
        s = l.nextSymbol();
        assertThat(s.getOp()).isEqualTo(ConverterTokenEnum.PROPERTY);
        assertThat(s.getValue()).isEqualTo("incoterm");
        s = l.nextSymbol();
        assertThat(s.getOp()).isEqualTo(ConverterTokenEnum.EQ);
        s = l.nextSymbol();
        assertThat(s.getOp()).isEqualTo(ConverterTokenEnum.NUMBER);
        assertThat(s.getNumber()).isEqualTo(1);
    }
    @Test
    void Test_NOT()
            throws Exception {
        String expression = "!=";
        SpelLexer l = new SpelLexer(expression);
        ConverterToken s;
        s = l.nextSymbol();
        assertThat(s.getOp()).isEqualTo(ConverterTokenEnum.NOT);
    }
    @Test
    void Test_AND()
            throws Exception {
        String expression = "&&";
        SpelLexer l = new SpelLexer(expression);
        ConverterToken s;
        s = l.nextSymbol();
        assertThat(s.getOp()).isEqualTo(ConverterTokenEnum.AND);
    }
}
