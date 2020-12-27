package compiler;

import java.util.HashMap;

public class Classes {
    HashMap classSymbolTable = new HashMap();
    private String className;
    private String  inherits;

    public Classes(String className, String inherits) {
        this.className = className;
        this.inherits = inherits;
    }

    public Classes(String className) {
        this.className = className;
    }

    public HashMap getClassSymbolTable() {
        return classSymbolTable;
    }

    public void setClassSymbolTable(HashMap classSymbolTable) {
        this.classSymbolTable = classSymbolTable;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInherits() {
        return inherits;
    }

    public void setInherits(String inherits) {
        this.inherits = inherits;
    }

    @Override
    public String toString() {
        return "Classes{" +
                ", className='" + className + '\'' +
                ", inherits='" + inherits + '\'' +
                '}';
    }
}
