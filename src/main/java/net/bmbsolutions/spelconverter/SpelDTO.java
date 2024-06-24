package net.bmbsolutions.spelconverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpelDTO {
    private ConverterTokenEnum operation;
    private int numericValue;
    private String stringValue;
    private SpelDTO left;
    private SpelDTO right;
    private String conditionExpression;
}
