package tool;

public class ReferingBeforeDeclaration {
  public static void main(String[] args) {
    var x = "hello";
    System.out.println(x);
  }

  boolean isOdd(int n) {
    if (n == 0) return false;
    return isEven(n - 1);
  }

  boolean isEven(int n) {
    if (n == 0) return true;
    return isOdd(n - 1);
  }
}
