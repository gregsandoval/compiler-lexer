package compiler.lexer.token;

import java.util.HashMap;
import java.util.Map;

public final class IntegerToken extends TypedToken<Integer> {
  private static Map<String, IntegerToken> identityMap = new HashMap<>();

  public IntegerToken(String str) {
    super(str, 3);
  }

  public static IntegerToken build(String string) {
    return identityMap.computeIfAbsent(string, IntegerToken::new);
  }

  @Override
  protected Integer parse(String str) {
    return Integer.parseInt(str);
  }

  @Override
  public String toStringExtra() {
    return " int= " + this.value + ")";
  }
}
