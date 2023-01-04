package otomata.tugasbesar.bahasapdi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import otomata.tugasbesar.bahasapdi.LexicalParser;
import otomata.tugasbesar.bahasapdi.token.Token;
import otomata.tugasbesar.bahasapdi.token.TokenType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LexicalParserTest {

    @Test
    public void testPrint() {
        String source = "tulis \"Hello World\"";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(2, tokens.size());

        int count = 0;
        Assertions.assertEquals(TokenType.Keyword, tokens.get(count).getType());
        assertEquals("tulis", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("Hello World", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());
    }

    @Test
    public void testInput() {

        String source = "masukan a";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(2, tokens.size());

        int count = 0;
        assertEquals(TokenType.Keyword, tokens.get(count).getType());
        assertEquals("masukan", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());
    }

    @Test
    public void testAssignment() {

        String source = "a = 2 + 5";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(5, tokens.size());

        int count = 0;
        assertEquals(TokenType.Variable, tokens.get(count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("=", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("2", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("+", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("5", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());
    }

    @Test
    public void testCondition() {

        String source = "jika a > 5\n" +
                        "    tulis \"a is greater than 5\"\n" +
                        "jikalau a >= 1\n" +
                        "    tulis \"a is greater than or equal to 1\"\n" +
                        "jikatak\n" +
                        "    tulis \"a is less than 1\"\n" +
                        "sudah";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(22, tokens.size());

        int count = 0;
        assertEquals(TokenType.Keyword, tokens.get(count).getType());
        assertEquals("jika", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals(">", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("5", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("tulis", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("a is greater than 5", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("jikalau", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals(">=", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("1", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("tulis", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("a is greater than or equal to 1", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("jikatak", tokens.get(count).getValue());
        assertEquals(5, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(5, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("tulis", tokens.get(count).getValue());
        assertEquals(6, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("a is less than 1", tokens.get(count).getValue());
        assertEquals(6, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(6, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("sudah", tokens.get(count).getValue());
        assertEquals(7, tokens.get(count).getRow());
    }

    @Test
    public void testClass() {

        String source = "kelas Person [ name, age ]\n" +
                        "sudah\n" +
                        "person = new Person[\"Randy Marsh\", 45]\n" +
                        "tulis person :: name + \" is \" + person :: age + \" years old\"";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(32, tokens.size());

        int count = 0;
        assertEquals(TokenType.Keyword, tokens.get(count).getType());
        assertEquals("kelas", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("Person", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals("[", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("name", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals(",", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("age", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals("]", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("sudah", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("person", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("=", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("new", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("Person", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals("[", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("Randy Marsh", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals(",", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("45", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals("]", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("tulis", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("person", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("::", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("name", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("+", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals(" is ", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("+", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("person", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("::", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("age", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("+", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals(" years old", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());
    }

    @Test
    public void testComment() {
        String source = "# a = 5\n" +
                        "a = 5 # a is equal to 5";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(6, tokens.size());

        int count = 0;
        assertEquals(TokenType.Comment, tokens.get(count).getType());
        assertEquals("# a = 5", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("=", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("5", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Comment, tokens.get(++count).getType());
        assertEquals("# a is equal to 5", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());
    }

}