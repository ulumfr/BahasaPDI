package otomata.tugasbesar.bahasapdi;

import lombok.SneakyThrows;
import otomata.tugasbesar.bahasapdi.context.MemoryContext;
import otomata.tugasbesar.bahasapdi.context.definition.DefinitionContext;
import otomata.tugasbesar.bahasapdi.statement.CompositeStatement;
import otomata.tugasbesar.bahasapdi.token.Token;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class pdiDriver {

    private static final String fullVer = "PDI PATCH BANTENG (didirikan, Jan 10 1973)";

    /**
     * ANSI Color Term
     */
    public static final String RESET = "\033[0m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String YELLOW_UNDERLINED = "\033[4;93m";
    public static final String WHITE_BOLD = "\033[1;97m";

    static pdiDriver main = new pdiDriver();

    public static String getFullVer() {
        return fullVer;
    }

    @SneakyThrows
    public void execute() {

        boolean exit = false;
        Scanner userInput = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

        do {
            LocalDateTime now = LocalDateTime.now();
            System.out.printf(GREEN_BOLD + "[%s]" + WHITE_BOLD + " PDI >> " + RESET, dtf.format(now));
            LexicalParser lexicalParser = new LexicalParser(userInput.nextLine());
            List<Token> tokens = lexicalParser.parse();

            DefinitionContext.pushScope(DefinitionContext.newScope());
            MemoryContext.pushScope(MemoryContext.newScope());
            try {
                CompositeStatement statement = new CompositeStatement();
                StatementParser.parse(tokens, statement);
                statement.execute();
            } finally {}
        } while (!exit);
    }

    @SneakyThrows
    public void execute(Path params) {

        int index = params.toString().lastIndexOf('.');
        if(params.toString().substring(index + 1).equals("pdi")) {
            String sources = Files.readString(params);

            LexicalParser lexicalParser = new LexicalParser(sources);
            List<Token> tokens = lexicalParser.parse();

            DefinitionContext.pushScope(DefinitionContext.newScope());
            MemoryContext.pushScope(MemoryContext.newScope());
            try {
                CompositeStatement statement = new CompositeStatement();
                StatementParser.parse(tokens, statement);
                statement.execute();
            } finally {
                DefinitionContext.endScope();
                MemoryContext.endScope();
            }
        } else {
            System.out.println("Only compatible with .pdi file");
        }
    }

    @SneakyThrows
    public static void main(String[] args) {

        pdiLoader loader = new pdiLoader();

        for (int i = 0; i < 100; i++) {
            loader.animate(i + "");
            Thread.sleep(5);
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("");
        System.out.println(getFullVer());
        System.out.println("https://www.pdiperjuangan.id/");
        System.out.println("KETIK \"bantuan\", \"kredit\", atau \"versi\" Untuk Informasi Lebih Lanjut.");
        System.out.println("");

        if (args.length == 0) {
            System.out.println(YELLOW_UNDERLINED + "SANGAT SIBUK MENGURUS RAKYAT" + RESET);
            main.execute();
        } else {
            main.execute(Path.of(args[0]));
        }
    }
}
