package fr.mytower.spelconverter.ast;

public abstract class Terminal implements AST {
    protected String property;
    protected Integer value;

    public Terminal(String property) {
        this.property = property;
    }

    public Terminal(Integer value) {
        this.value = value;
    }

    @Override
    public Class<Terminal> getType() {
        return Terminal.class;
    }

    public abstract String getStringValue();

    public abstract Integer getNumericValue();

}
