package compiler;

import java.util.HashMap;

public class Block {
    String name;
    HashMap blockSymbolTable = new HashMap();

    public Block(String name) {
        this.name = name;
    }
}
