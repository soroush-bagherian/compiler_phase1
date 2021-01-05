package compiler;

import java.util.HashMap;

public class Scope {
    String name;
    HashMap hashMap = new HashMap();

    @Override
    public String toString() {
        return "Scope{" +
                "name='" + name + '\'' +
                '}';
    }
}
