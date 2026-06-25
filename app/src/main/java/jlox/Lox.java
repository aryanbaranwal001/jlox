package jlox;

// We also need to manage the memory of the values of Lox
// A handy object representation and a really nice garbage collector

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// C for a .java file, there can only be one public (top-level) class, whose name must match with
// C file
// C mainClass is set to Lox.main in build.kts, for running the main()
public class Lox {

  private static final Interpreter interpreter = new Interpreter();

  // syntax error flag
  static boolean hadError = false;

  // runtime error flag
  static boolean hadRuntimeError = false;

  public static void main(String[] args) throws IOException {
    if (args.length > 1) {
      System.out.println("Usage: jlox [script]");
      System.exit(64);

    } else if (args.length == 1) {
      // parsing the source code
      runFile(args[0]);

    } else {
      return;
      // runPrompt();
    }
  }

  private static void runFile(String path) throws IOException {

    // T get to the bottom of this .defaultCharset, encoding decoding
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));

    // error in syntax
    if (hadError) System.exit(65);

    // error in semantics
    if (hadRuntimeError) System.exit(70);
  }

  // private static void runPrompt() throws IOException {
  //   InputStreamReader input = new InputStreamReader(System.in);
  //   BufferedReader reader = new BufferedReader(input);
  //   for (; ; ) {
  //     System.out.print("> ");
  //     String line = reader.readLine();
  //     if (line == null) break;
  //     run(line);
  //     hadError = false;
  //   }
  // }

  // C static means can be called without the instance
  private static void run(String source) {
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();

    // for (Token token : tokens) {
    //   System.out.println(token);
    // }

    Parser parser = new Parser(tokens);
    List<Stmt> statements = parser.parse();

    // // Stop if there was a syntax error.
    if (hadError) return;
    // System.out.println(new AstPrinter().print(statements));
    interpreter.interpret(statements);
  }

  static void error(int line, String message) {
    report(line, "", message);
  }

  private static void report(int line, String where, String message) {
    System.err.println("[line " + line + "] Error" + where + ": " + message);
    hadError = true;
  }

  static void error(Token token, String message) {
    if (token.type == TokenType.EOF) {
      report(token.line, " at end", message);
    } else {
      report(token.line, " at '" + token.lexeme + "'", message);
    }
  }

  static void runtimeError(RuntimeError error) {
    System.err.println(error.getMessage() + "\n[line " + error.token.line + "]");
    hadRuntimeError = true;
  }
}
