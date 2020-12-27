package compiler;

import java.util.HashMap;

public class Method {
    private String name;
    private String returnType;
    private String accessModifier = "public";
    HashMap methodSymbolTable = new HashMap();


    public Method() {
    }

    public Method(String name, String returnType, String accessModifier) {
        this.name = name;
        this.returnType = returnType;
        this.accessModifier = accessModifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }
    public String getVarName(String var){

        int a=0;
        int b=var.length();
        String str ="";

        if (var.contains("=")){
            a = var.indexOf("=");
            return var.substring(3 , a);
        }

        str=var.substring(3 , b);
        return str;
    }


    @Override
    public String toString() {
        return "Method{" +
                "name='" + name + '\'' +
                ", returnType='" + returnType + '\'' +
                ", accessModifier='" + accessModifier + '\'' +
                '}';
    }
}
