package net.bmbsolutions.spelconverter;

import org.junit.jupiter.api.Test;

import static net.bmbsolutions.spelconverter.ConverterTokenEnum.*;
import static org.assertj.core.api.Assertions.assertThat;


class SpelConverterTest {
    @Test
    void asCondition_ShouldParseString() throws Exception {
        String expression = "incoterm[0] == 1 && modeTransport.name() != 2";
        SpelConverter parser = new SpelConverter();
        SpelDTO condition = parser.asCondition(expression);
        assertThat(condition.getOperation()).isEqualTo(AND);
        assertThat(condition.getLeft().getOperation()).isEqualTo(EQ);
        assertThat(condition.getLeft().getLeft().getStringValue()).isEqualTo("incoterm[0]");
        assertThat(condition.getLeft().getRight().getNumericValue()).isEqualTo(1);
        assertThat(condition.getRight().getOperation()).isEqualTo(NOT);
        assertThat(condition.getRight().getLeft().getStringValue()).isEqualTo("modeTransport.name()");
    }

    @Test
    void asSpelExpression_ShouldParseCondition_simple() {
        String expression = "incoterm == 1";
        SpelConverter parser = new SpelConverter();
        SpelDTO condition = SpelDTO.builder()
                .operation(EQ)
                .left(SpelDTO.builder().stringValue("incoterm").build())
                .right(SpelDTO.builder().numericValue(1).build())
                .build();

        assertThat(parser.asSpelExpression(condition)).isEqualTo(expression);
    }

    @Test
    void asSpelExpression_ShouldParseCondition_complex() {
        String expression = "incoterm == 1 && modeTransport == 1 || modeTransport != 1";
        SpelConverter parser = new SpelConverter();
        SpelDTO condition = SpelDTO.builder()
                .operation(AND)
                .left(SpelDTO.builder()
                        .operation(EQ)
                        .left(SpelDTO.builder().stringValue("incoterm").build())
                        .right(SpelDTO.builder().numericValue(1).build())
                        .build())
                .right(SpelDTO.builder()
                        .operation(OR)
                        .left(SpelDTO.builder()
                                .operation(EQ)
                                .left(SpelDTO.builder().stringValue("modeTransport").build())
                                .right(SpelDTO.builder().numericValue(1).build())
                                .build())
                        .right(SpelDTO.builder()
                                .operation(NOT)
                                .left(SpelDTO.builder().stringValue("modeTransport").build())
                                .right(SpelDTO.builder().numericValue(1).build())
                                .build())
                        .build())
                .build();

        assertThat(parser.asSpelExpression(condition)).isEqualTo(expression);
    }
}
