package compiler;

import java.util.HashMap;

public class Classes {
    private String className;
    private String  inherits;

    public Classes(String className, String inherits) {
        this.className = className;
        this.inherits = inherits;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "className='" + className + '\'' +
                ", inherits='" + inherits + '\'' +
                '}';
    }
}
