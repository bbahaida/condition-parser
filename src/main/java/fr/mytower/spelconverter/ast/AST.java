package fr.mytower.spelconverter.ast;

public interface AST {
    Class<? extends AST> getType();
}
