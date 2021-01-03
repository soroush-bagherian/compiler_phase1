package compiler;

import java.util.HashMap;

public class Method {
    private String name;
    private String returnType;
    private String accessModifier = "public";


    public Method(String name, String returnType, String accessModifier) {
        this.name = name;
        this.returnType = returnType;
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
