import java.util.*;
public class Test {

   public static void main(String args[]) {
       Scanner sc = new Scanner(System.in);
       System.out.println("Enter an algebraic expression like: 5 + 6 * 7");
      ExpressionTree y = new ExpressionTree(sc.nextLine());
      print(y);
   }
   
   public static void print(ExpressionTree y) {
      System.out.println("Prefix: " + y.prefix());
      System.out.println("Postfix: " + y.postfix());
      System.out.println("Fully parenthesised: " + y.fullyParenthesised());
      System.out.println("-----------------");
   }  
}
