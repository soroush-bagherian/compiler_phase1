package compiler;

import java.util.HashMap;

public class Scope {
    String name;
    HashMap hashMap = new HashMap();
    int isVisited = 0;

    @Override
    public String toString() {
        return "Scope{" +
                "name='" + name + '\'' +
                '}';
    }
}
