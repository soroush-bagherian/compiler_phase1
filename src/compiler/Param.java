package compiler;

public class Param {
    String name;
    String type;

    public Param(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Param{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
