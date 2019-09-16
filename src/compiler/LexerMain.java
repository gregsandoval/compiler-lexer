package compiler;

import compiler.a5.lexicon.A5LexiconDFA;
import compiler.graph.Node;
import compiler.graph.Token;
import compiler.lexer.Lexer;
import compiler.lexer.LexerBuilder;

import java.util.List;


public class LexerMain {
  public static void main(String[] args) {
    final var text = " prog main { // Find the hypotenuse of a right triangle.\n" +
      "      print( \"Input legs> \" );\n" +
      "      var a = input( int );\n" +
      "      var b = input( int );\n" +
      "      print( \"Hypotenuse= \", ( a * a + b * b ) ^ -0.5 );\n" +
      "    }\n\n\t  \t \r \f \n  ";

    var lexer = new LexerBuilder()
      .onTransition(LexerMain::logTransition)
      .onTokenCreated(LexerMain::logAcceptedToken)
      .setStartState(A5LexiconDFA.START)
      .createLexer();

    System.out.println("Parsing: \n" + text + "\n");
    final var terminals = process(text, lexer);

    System.out.printf("Accepted %s tokens: \n", terminals.size());
    for (var i = 0; i < terminals.size(); i++) {
      System.out.printf("Token %d: ", i + 1);
      System.out.println(terminals.get(i));
    }
  }

  public static List<Token> process(String text, Lexer lexer) {
    buildLexer();
    return lexer.analyze(text);
  }

  public static void buildLexer() {

  }

  private static void logTransition(Node start, Character character, Node end) {
    System.out.printf("%-15s %-10s %-5s\n", start, "-(" + escape(String.valueOf(character)) + ")->", end);
  }

  private static void logAcceptedToken(Node start, Node end, Token token) {
    System.out.println("Accepted token: " + token.str + "\n");
  }

  public static String escape(String string) {
    return string.replace("\n", "\\n").replace("\t", "\\t").replace("\r", "\\r").replace("\f", "\\f");
  }

}
