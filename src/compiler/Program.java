package compiler;

import java.util.HashMap;

public class Program {
    HashMap programSymbolTable = new HashMap();
    String name;
    String classDec;

    public Program(String name) {
        this.name = name;
    }

    public HashMap getProgramSymbolTable() {
        return programSymbolTable;
    }

    public void setProgramSymbolTable(HashMap programSymbolTable) {
        this.programSymbolTable = programSymbolTable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassDec() {
        return classDec;
    }

    public void setClassDec(String classDec) {
        this.classDec = classDec;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programSymbolTable=" + programSymbolTable +
                ", name='" + name + '\'' +
                ", classDec='" + classDec + '\'' +
                '}';
    }
}
