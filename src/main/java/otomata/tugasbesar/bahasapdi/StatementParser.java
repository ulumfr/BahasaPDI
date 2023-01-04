package otomata.tugasbesar.bahasapdi;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import otomata.tugasbesar.bahasapdi.context.definition.ClassDefinition;
import otomata.tugasbesar.bahasapdi.context.definition.DefinitionContext;
import otomata.tugasbesar.bahasapdi.context.definition.DefinitionScope;
import otomata.tugasbesar.bahasapdi.context.definition.FunctionDefinition;
import otomata.tugasbesar.bahasapdi.exception.SyntaxException;
import otomata.tugasbesar.bahasapdi.expression.Expression;
import otomata.tugasbesar.bahasapdi.expression.ExpressionReader;
import otomata.tugasbesar.bahasapdi.expression.VariableExpression;
import otomata.tugasbesar.bahasapdi.expression.operator.OperatorExpression;
import otomata.tugasbesar.bahasapdi.expression.value.LogicalValue;
import otomata.tugasbesar.bahasapdi.statement.*;
import otomata.tugasbesar.bahasapdi.statement.loop.*;
import otomata.tugasbesar.bahasapdi.token.Token;
import otomata.tugasbesar.bahasapdi.token.TokenType;
import otomata.tugasbesar.bahasapdi.token.TokensStack;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Getter
public class StatementParser {

    pdiDriver engine = new pdiDriver();
    Scanner input = new Scanner(System.in);
    private final TokensStack tokens;
    private final Scanner scanner;
    private final CompositeStatement compositeStatement;

    public static void parse(StatementParser parent, CompositeStatement compositeStatement, DefinitionScope definitionScope) {
        DefinitionContext.pushScope(definitionScope);
        try {
            StatementParser parser = new StatementParser(parent.getTokens(), parent.getScanner(), compositeStatement);
            while (parser.hasNextStatement()) {
                parser.parseExpression();
            }
        } finally {
            DefinitionContext.endScope();
        }
    }

    public static void parse(List<Token> tokens, CompositeStatement compositeStatement) {
        StatementParser parser = new StatementParser(new TokensStack(tokens), new Scanner(System.in), compositeStatement);
        while (parser.hasNextStatement()) {
            parser.parseExpression();
        }
    }

    private boolean hasNextStatement() {
        if (!tokens.hasNext())
            return false;
        if (tokens.peek(TokenType.Operator, TokenType.Variable, TokenType.This))
            return true;
        if (tokens.peek(TokenType.Keyword)) {
            return !tokens.peek(TokenType.Keyword, "jikalau", "jikatak", "sudah");
        }
        return false;
    }

    private void parseExpression() {
        Token token = tokens.next(TokenType.Keyword, TokenType.Variable, TokenType.This, TokenType.Operator);
        switch (token.getType()) {
            case Variable:
            case Operator:
            case This:
                parseExpressionStatement();
                break;
            case Keyword:
                parseKeywordStatement(token);
                break;
            default:
                throw new SyntaxException(String.format("Statement can't start with the following lexeme `%s`", token));
        }
    }

    private void parseExpressionStatement() {
        tokens.back(); // go back to read an expression from the beginning
        Expression value = ExpressionReader.readExpression(tokens);
        ExpressionStatement statement = new ExpressionStatement(value);
        compositeStatement.addStatement(statement);
    }

    private void parseKeywordStatement(Token token) {
        switch (token.getValue()) {
            case "tulis":
                parsePrintStatement();
                break;
            case "masukan":
                parseInputStatement();
                break;
            case "jika":
                parseConditionStatement();
                break;
            case "kelas":
                parseClassDefinition();
                break;
            case "fungsi":
                parseFunctionDefinition();
                break;
            case "kembali":
                parseReturnStatement();
                break;
            case "ulang":
                parseLoopStatement();
                break;
            case "berhenti":
                parseBreakStatement();
                break;
            case "terus":
                parseNextStatement();
                break;
            case "run":
                System.out.print("Enter Path : ");
                engine.execute(Path.of(input.nextLine()));
                break;
            case "kredit":
                System.out.println("Thanks to alexandermakeev for amazing base code for this project,\nThanks to Rio Chandra about Jaksel Languages which inspire this project.\nSee https://hackernoon.com/building-your-own-programming-language-from-scratch for more information");
                break;
            case "bantuan":
                System.out.println("================================ BANTUAN =================================");
                System.out.println("- Bantuan : Are used to see documentation usage");
                System.out.println("- Versi : To see bahasapdi version which installed on this machine");
                System.out.println("================================ OPERASI =================================");
                System.out.println(" ");
                System.out.println("| Operator               | Value | Precedence | Example                  |\n" +
                        "| Assignment             | `=`   | 1          | `a = 5`                  |\n" +
                        "| Append value to array  | `<<`  | 1          | `array << \"value\"`       |\n" +
                        "| Logical OR             | `or`  | 2          | `true or false`          |\n" +
                        "| Logical AND            | `and` | 3          | `true and true`          |\n" +
                        "| Left Paren             | `(`   | 4          |                          |\n" +
                        "| Right Paren            | `)`   | 4          |                          |\n" +
                        "| Equals                 | `==`  | 5          | `a == 5`                 |\n" +
                        "| Not Equals             | `!=`  | 5          | `a != 5`                 |\n" +
                        "| Greater Than Or Equals | `>=`  | 5          | `a >= 5`                 |\n" +
                        "| Greater Than           | `>`   | 5          | `a > 5`                  |\n" +
                        "| Less Than Or Equals    | `<=`  | 5          | `a <= 5`                 |\n" +
                        "| Less Than              | `<`   | 5          | `a < 5`                  |\n" +
                        "| Addition               | `+`   | 6          | `a + 5`                  |\n" +
                        "| Subtraction            | `-`   | 6          | `a - 5`                  |\n" +
                        "| Multiplication         | `*`   | 7          | `a * 5`                  |\n" +
                        "| Division               | `/`   | 7          | `a / 5`                  |\n" +
                        "| Floor Division         | `//`  | 7          | `a // 5`                 |\n" +
                        "| Modulo                 | `%`   | 7          | `a % 5`                  |\n" +
                        "| NOT                    | `!`   | 8          | `!false`                 |\n" +
                        "| Class Instance         | `new` | 8          | `a = new TreeNode [ 5 ]` |\n" +
                        "| Class Property         | `::`  | 8          | `b = a :: value`         |");
                break;
            case "versi":
                System.out.println(pdiDriver.getFullVer());
                break;
            case "keluar":
                System.exit(0);
            default:
                throw new SyntaxException(String.format("Failed to parse a keyword: %s", token.getValue()));
        }
    }

    private void parsePrintStatement() {
        Expression expression = ExpressionReader.readExpression(tokens);
        PrintStatement statement = new PrintStatement(expression);
        compositeStatement.addStatement(statement);
    }

    private void parseInputStatement() {
        Token variable = tokens.next(TokenType.Variable);
        InputStatement statement = new InputStatement(variable.getValue(), scanner::nextLine);
        compositeStatement.addStatement(statement);
    }

    private void parseConditionStatement() {
        tokens.back();
        ConditionStatement conditionStatement = new ConditionStatement();

        while (!tokens.peek(TokenType.Keyword, "sudah")) {
            //read condition case
            Token type = tokens.next(TokenType.Keyword, "jika", "jikalau", "jikatak");
            Expression caseCondition;
            if (type.getValue().equals("jikatak")) {
                caseCondition = new LogicalValue(true); //else case does not have the condition
            } else {
                caseCondition = ExpressionReader.readExpression(tokens);
            }

            //read case statements
            CompositeStatement caseStatement = new CompositeStatement();
            DefinitionScope caseScope = DefinitionContext.newScope();
            StatementParser.parse(this, caseStatement, caseScope);

            //add case
            conditionStatement.addCase(caseCondition, caseStatement);
        }
        tokens.next(TokenType.Keyword, "sudah");

        compositeStatement.addStatement(conditionStatement);
    }

    private void parseClassDefinition() {
        Token type = tokens.next(TokenType.Variable);

        List<String> arguments = new ArrayList<>();

        if (tokens.peek(TokenType.GroupDivider, "[")) {

            tokens.next(TokenType.GroupDivider, "["); //terus open square bracket

            while (!tokens.peek(TokenType.GroupDivider, "]")) {
                Token argumentToken = tokens.next(TokenType.Variable);
                arguments.add(argumentToken.getValue());

                if (tokens.peek(TokenType.GroupDivider, ","))
                    tokens.next();
            }

            tokens.next(TokenType.GroupDivider, "]"); //terus close square bracket
        }

        // add class definition
        ClassStatement classStatement = new ClassStatement();
        DefinitionScope classScope = DefinitionContext.newScope();
        ClassDefinition classDefinition = new ClassDefinition(type.getValue(), arguments, classStatement, classScope);
        DefinitionContext.getScope().addClass(classDefinition);

        //parse class statements
        StatementParser.parse(this, classStatement, classScope);
        tokens.next(TokenType.Keyword, "sudah");
    }

    private void parseFunctionDefinition() {
        Token type = tokens.next(TokenType.Variable);

        List<String> arguments = new ArrayList<>();

        if (tokens.peek(TokenType.GroupDivider, "[")) {

            tokens.next(TokenType.GroupDivider, "["); //terus open square bracket

            while (!tokens.peek(TokenType.GroupDivider, "]")) {
                Token argumentToken = tokens.next(TokenType.Variable);
                arguments.add(argumentToken.getValue());

                if (tokens.peek(TokenType.GroupDivider, ","))
                    tokens.next();
            }

            tokens.next(TokenType.GroupDivider, "]"); //terus close square bracket
        }

        //add function definition
        FunctionStatement functionStatement = new FunctionStatement();
        DefinitionScope functionScope = DefinitionContext.newScope();
        FunctionDefinition functionDefinition = new FunctionDefinition(type.getValue(), arguments, functionStatement, functionScope);
        DefinitionContext.getScope().addFunction(functionDefinition);

        //parse function statements
        StatementParser.parse(this, functionStatement, functionScope);
        tokens.next(TokenType.Keyword, "sudah");
    }

    private void parseReturnStatement() {
        Expression expression = ExpressionReader.readExpression(tokens);
        ReturnStatement statement = new ReturnStatement(expression);
        compositeStatement.addStatement(statement);
    }

    private void parseLoopStatement() {
        Expression loopExpression = ExpressionReader.readExpression(tokens);
        if (loopExpression instanceof OperatorExpression || loopExpression instanceof VariableExpression) {
            AbstractLoopStatement loopStatement;

            if (loopExpression instanceof VariableExpression && tokens.peek(TokenType.Keyword, "selama")) {
                // loop <variable> in <bounds>
                VariableExpression variable = (VariableExpression) loopExpression;
                tokens.next(TokenType.Keyword, "selama");
                Expression bounds = ExpressionReader.readExpression(tokens);

                if (tokens.peek(TokenType.GroupDivider, "..")) {
                    // loop <variable> in <lower_bound>..<upper_bound>
                    tokens.next(TokenType.GroupDivider, "..");
                    Expression upperBound = ExpressionReader.readExpression(tokens);

                    if (tokens.peek(TokenType.Keyword, "by")) {
                        // loop <variable> in <lower_bound>..<upper_bound> by <step>
                        tokens.next(TokenType.Keyword, "by");
                        Expression step = ExpressionReader.readExpression(tokens);
                        loopStatement = new ForLoopStatement(variable, bounds, upperBound, step);
                    } else {
                        // use default step
                        // loop <variable> in <lower_bound>..<upper_bound>
                        loopStatement = new ForLoopStatement(variable, bounds, upperBound);
                    }

                } else {
                    // loop <variable> in <iterable>
                    loopStatement = new IterableLoopStatement(variable, bounds);
                }

            } else {
                // loop <condition>
                loopStatement = new WhileLoopStatement(loopExpression);
            }

            DefinitionScope loopScope = DefinitionContext.newScope();
            StatementParser.parse(this, loopStatement, loopScope);
            tokens.next(TokenType.Keyword, "sudah");

            compositeStatement.addStatement(loopStatement);
        }

    }

    private void parseBreakStatement() {
        BreakStatement statement = new BreakStatement();
        compositeStatement.addStatement(statement);
    }

    private void parseNextStatement() {
        NextStatement statement = new NextStatement();
        compositeStatement.addStatement(statement);
    }
}
