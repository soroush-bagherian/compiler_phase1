package compiler;

import gen.MoolaListener;
import gen.MoolaParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Stack;

public class ProgramPrinter implements MoolaListener {

    Stack<Scope> scopeStack = new Stack<Scope>();
    Scope currentScope;
    Scope parentScope;

    @Override
    public void enterProgram(MoolaParser.ProgramContext ctx) {

        Scope programScope = new Scope();
        programScope.name = "program";
        programScope.isVisited=1;
        currentScope = programScope;
        parentScope = null;
        scopeStack.push(programScope);


        int numberOfClassDec = ctx.classDeclaration().size();

        String className;


        System.out.println("---------- " + programScope.name + " : " + ctx.start.getLine() + " ----------");

        className = ctx.mainclass.classDeclaration().className.getText();
        Classes b = new Classes(className, "inherits_");
        programScope.hashMap.put("mainClass_" + ctx.mainclass.classDeclaration().className.getText(), b);

        for (int i = 0; i < numberOfClassDec; i++) {
            className = ctx.classDeclaration().get(i).className.getText();
            Classes c = new Classes(className, "inherits_");
            programScope.hashMap.put("class_" + ctx.classDeclaration().get(i).className.getText(), c);
            System.out.print("Key = " + className + " | Value : ");
            System.out.println(programScope.hashMap.get("class_" + className));

        }


    }

    @Override
    public void exitProgram(MoolaParser.ProgramContext ctx) {

        System.out.println("---------- END OF Program----------");
    }

    @Override
    public void enterClassDeclaration(MoolaParser.ClassDeclarationContext ctx) {

        String fieldName;
        String fieldType;
        String accessModifier;
        String methodName;
        String methodType;
        String methodaccessModifier;

        Scope classScope = new Scope();
        classScope.name = "classScope_"+ctx.className.getText();

        classScope.isVisited = 1;
        currentScope = classScope;
        parentScope = scopeStack.peek();
        scopeStack.push(classScope);


        int numberOfFeildDec = ctx.fieldDeclaration().size();
        int numberOfMethodDec = ctx.methodDeclaration().size();
        System.out.println("---------- " + ctx.className.getText() + " : " + ctx.start.getLine() + " ----------");

        if (numberOfFeildDec > 0)
        for (int i = 0; i < numberOfFeildDec; i++) {
            fieldName = ctx.fieldDeclaration().get(i).fieldName.getText();
            fieldType = ctx.fieldDeclaration().get(i).fieldType.getText();
            accessModifier = ctx.fieldDeclaration().get(i).fieldAccessModifier.getText();

            Field f = new Field(fieldName, fieldType, accessModifier);
            classScope.hashMap.put("field_" + fieldName, f);


            System.out.print("Key = " + fieldName + " | Value : ");
            System.out.println(classScope.hashMap.get("field_" + fieldName));
        }

        if (numberOfMethodDec > 0)
            for (int i = 0; i < numberOfMethodDec; i++) {

                methodName = ctx.methodDeclaration().get(i).methodName.getText();
                methodType = ctx.methodDeclaration().get(i).t.getText();
                methodaccessModifier = ctx.methodDeclaration().get(i).methodAccessModifier.getText();

                Method m = new Method(methodName, methodType, methodaccessModifier);
                classScope.hashMap.put("method_" + methodName , m);

                System.out.print("Key = " + methodName + " | Value : ");
                System.out.println(classScope.hashMap.get("method_" + methodName));
            }

    }

    @Override
    public void exitClassDeclaration(MoolaParser.ClassDeclarationContext ctx) {

        scopeStack.pop();
        currentScope = parentScope;
        System.out.println("---------- END OF " + ctx.className.getText() + "----------");
    }

    @Override
    public void enterEntryClassDeclaration(MoolaParser.EntryClassDeclarationContext ctx) {
    }

    @Override
    public void exitEntryClassDeclaration(MoolaParser.EntryClassDeclarationContext ctx) {

    }

    @Override
    public void enterFieldDeclaration(MoolaParser.FieldDeclarationContext ctx) {
//        System.out.println("\t\tfield: " + ctx.fieldName.getText() +
//                "/ type=" + ctx.fieldType.getText() +
//                "/ access modifier=" + ctx.fieldAccessModifier.getText());
        // Var field = new Var(ctx.fieldType.getText() , "field" );
        //classes.put("field_"+ctx.fieldName.getText(), field);

    }

    @Override
    public void exitFieldDeclaration(MoolaParser.FieldDeclarationContext ctx) {

    }

    @Override
    public void enterAccess_modifier(MoolaParser.Access_modifierContext ctx) {

    }

    @Override
    public void exitAccess_modifier(MoolaParser.Access_modifierContext ctx) {

    }

    @Override
    public void enterMethodDeclaration(MoolaParser.MethodDeclarationContext ctx) {

        Scope methodScope = new Scope();
        methodScope.name =  ctx.methodName.getText();
        currentScope = methodScope;
        parentScope = scopeStack.peek();
        scopeStack.push(methodScope);
        System.out.println("---------- " + ctx.methodName.getText() + " : " + ctx.start.getLine() + " ----------");

    }

    @Override
    public void exitMethodDeclaration(MoolaParser.MethodDeclarationContext ctx) {

        scopeStack.pop();
        currentScope = parentScope;

        System.out.println("---------- END OF " + ctx.methodName.getText() + "----------");
    }

    @Override
    public void enterClosedStatement(MoolaParser.ClosedStatementContext ctx) {

    }

    @Override
    public void exitClosedStatement(MoolaParser.ClosedStatementContext ctx) {

    }

    @Override
    public void enterClosedConditional(MoolaParser.ClosedConditionalContext ctx) {

    }

    @Override
    public void exitClosedConditional(MoolaParser.ClosedConditionalContext ctx) {

    }

    @Override
    public void enterOpenConditional(MoolaParser.OpenConditionalContext ctx) {

    }

    @Override
    public void exitOpenConditional(MoolaParser.OpenConditionalContext ctx) {

    }

    @Override
    public void enterOpenStatement(MoolaParser.OpenStatementContext ctx) {
        Scope ifScope = new Scope();
        ifScope.name =  "if";
        currentScope = ifScope;
        parentScope = scopeStack.peek();
        scopeStack.push(ifScope);
        System.out.println("----------  if : " + ctx.start.getLine() + " ----------");

    }

    @Override
    public void exitOpenStatement(MoolaParser.OpenStatementContext ctx) {
        scopeStack.pop();
        currentScope = parentScope;
        System.out.println("---------- END OF  if ----------");
    }

    @Override
    public void enterStatement(MoolaParser.StatementContext ctx) {

    }

    @Override
    public void exitStatement(MoolaParser.StatementContext ctx) {

    }

    @Override
    public void enterStatementVarDef(MoolaParser.StatementVarDefContext ctx) {

        Var var = new Var(ctx.i1.getText());
        currentScope.hashMap.put("var_"+ctx.i1.getText() , var);

        System.out.print("Key = " + var.getName() + " | Value : ");
        System.out.println(currentScope.hashMap.get("var_" + var.getName()));

    }

    @Override
    public void exitStatementVarDef(MoolaParser.StatementVarDefContext ctx) {

    }

    @Override
    public void enterStatementBlock(MoolaParser.StatementBlockContext ctx) {

    }

    @Override
    public void exitStatementBlock(MoolaParser.StatementBlockContext ctx) {

    }

    @Override
    public void enterStatementContinue(MoolaParser.StatementContinueContext ctx) {

    }

    @Override
    public void exitStatementContinue(MoolaParser.StatementContinueContext ctx) {

    }

    @Override
    public void enterStatementBreak(MoolaParser.StatementBreakContext ctx) {

    }

    @Override
    public void exitStatementBreak(MoolaParser.StatementBreakContext ctx) {

    }

    @Override
    public void enterStatementReturn(MoolaParser.StatementReturnContext ctx) {

    }

    @Override
    public void exitStatementReturn(MoolaParser.StatementReturnContext ctx) {

    }

    @Override
    public void enterStatementClosedLoop(MoolaParser.StatementClosedLoopContext ctx) {
        Scope whileScope = new Scope();
        whileScope.name =  "while";
        currentScope = whileScope;
        parentScope = scopeStack.peek();
        scopeStack.push(whileScope);
        System.out.println("----------  while : " + ctx.start.getLine() + " ----------");

    }

    @Override
    public void exitStatementClosedLoop(MoolaParser.StatementClosedLoopContext ctx) {
        scopeStack.pop();
        currentScope = parentScope;
        System.out.println("---------- END OF  while ----------");
    }

    @Override
    public void enterStatementOpenLoop(MoolaParser.StatementOpenLoopContext ctx) {

    }

    @Override
    public void exitStatementOpenLoop(MoolaParser.StatementOpenLoopContext ctx) {

    }

    @Override
    public void enterStatementWrite(MoolaParser.StatementWriteContext ctx) {

    }

    @Override
    public void exitStatementWrite(MoolaParser.StatementWriteContext ctx) {

    }

    @Override
    public void enterStatementAssignment(MoolaParser.StatementAssignmentContext ctx) {

    }

    @Override
    public void exitStatementAssignment(MoolaParser.StatementAssignmentContext ctx) {

    }

    @Override
    public void enterStatementInc(MoolaParser.StatementIncContext ctx) {

    }

    @Override
    public void exitStatementInc(MoolaParser.StatementIncContext ctx) {

    }

    @Override
    public void enterStatementDec(MoolaParser.StatementDecContext ctx) {

    }

    @Override
    public void exitStatementDec(MoolaParser.StatementDecContext ctx) {

    }

    @Override
    public void enterExpression(MoolaParser.ExpressionContext ctx) {
    }

    @Override
    public void exitExpression(MoolaParser.ExpressionContext ctx) {

    }

    @Override
    public void enterExpressionOr(MoolaParser.ExpressionOrContext ctx) {

    }

    @Override
    public void exitExpressionOr(MoolaParser.ExpressionOrContext ctx) {

    }

    @Override
    public void enterExpressionOrTemp(MoolaParser.ExpressionOrTempContext ctx) {

    }

    @Override
    public void exitExpressionOrTemp(MoolaParser.ExpressionOrTempContext ctx) {

    }

    @Override
    public void enterExpressionAnd(MoolaParser.ExpressionAndContext ctx) {

    }

    @Override
    public void exitExpressionAnd(MoolaParser.ExpressionAndContext ctx) {

    }

    @Override
    public void enterExpressionAndTemp(MoolaParser.ExpressionAndTempContext ctx) {

    }

    @Override
    public void exitExpressionAndTemp(MoolaParser.ExpressionAndTempContext ctx) {

    }

    @Override
    public void enterExpressionEq(MoolaParser.ExpressionEqContext ctx) {

    }

    @Override
    public void exitExpressionEq(MoolaParser.ExpressionEqContext ctx) {

    }

    @Override
    public void enterExpressionEqTemp(MoolaParser.ExpressionEqTempContext ctx) {

    }

    @Override
    public void exitExpressionEqTemp(MoolaParser.ExpressionEqTempContext ctx) {

    }

    @Override
    public void enterExpressionCmp(MoolaParser.ExpressionCmpContext ctx) {

    }

    @Override
    public void exitExpressionCmp(MoolaParser.ExpressionCmpContext ctx) {

    }

    @Override
    public void enterExpressionCmpTemp(MoolaParser.ExpressionCmpTempContext ctx) {

    }

    @Override
    public void exitExpressionCmpTemp(MoolaParser.ExpressionCmpTempContext ctx) {

    }

    @Override
    public void enterExpressionAdd(MoolaParser.ExpressionAddContext ctx) {

    }

    @Override
    public void exitExpressionAdd(MoolaParser.ExpressionAddContext ctx) {

    }

    @Override
    public void enterExpressionAddTemp(MoolaParser.ExpressionAddTempContext ctx) {

    }

    @Override
    public void exitExpressionAddTemp(MoolaParser.ExpressionAddTempContext ctx) {

    }

    @Override
    public void enterExpressionMultMod(MoolaParser.ExpressionMultModContext ctx) {

    }

    @Override
    public void exitExpressionMultMod(MoolaParser.ExpressionMultModContext ctx) {

    }

    @Override
    public void enterExpressionMultModTemp(MoolaParser.ExpressionMultModTempContext ctx) {

    }

    @Override
    public void exitExpressionMultModTemp(MoolaParser.ExpressionMultModTempContext ctx) {

    }

    @Override
    public void enterExpressionUnary(MoolaParser.ExpressionUnaryContext ctx) {

    }

    @Override
    public void exitExpressionUnary(MoolaParser.ExpressionUnaryContext ctx) {

    }

    @Override
    public void enterExpressionMethods(MoolaParser.ExpressionMethodsContext ctx) {

    }

    @Override
    public void exitExpressionMethods(MoolaParser.ExpressionMethodsContext ctx) {

    }

    @Override
    public void enterExpressionMethodsTemp(MoolaParser.ExpressionMethodsTempContext ctx) {

    }

    @Override
    public void exitExpressionMethodsTemp(MoolaParser.ExpressionMethodsTempContext ctx) {

    }

    @Override
    public void enterExpressionOther(MoolaParser.ExpressionOtherContext ctx) {

    }

    @Override
    public void exitExpressionOther(MoolaParser.ExpressionOtherContext ctx) {

    }

    @Override
    public void enterMoolaType(MoolaParser.MoolaTypeContext ctx) {

    }

    @Override
    public void exitMoolaType(MoolaParser.MoolaTypeContext ctx) {

    }

    @Override
    public void enterSingleType(MoolaParser.SingleTypeContext ctx) {

    }

    @Override
    public void exitSingleType(MoolaParser.SingleTypeContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }

}
