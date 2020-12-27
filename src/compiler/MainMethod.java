package compiler;

public class  MainMethod {
    private final String returnType = "int";
    private final String accessModifier = "public";
    private final String name = "main";

    public MainMethod() {}

    public String getReturnType() {
        return returnType;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MainMethod{" +
                "returnType='" + returnType + '\'' +
                ", accessModifier='" + accessModifier + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
