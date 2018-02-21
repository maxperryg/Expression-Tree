import java.util.ArrayList;
import java.util.Iterator;

public class ExpressionTree extends BinaryTree<String> {
   
    public ExpressionTree(String s) {
      super();
      s = s.trim();
      s = s.replaceAll(" ", "");
      root = setLastOpp(s);
   }
   
   public String fullyParenthesised() {
       ArrayList<String> ans = new ArrayList<String>();
       parentasize(this.root, ans);
       String answer = new String();
       for (String b:ans) answer += b + " ";
       return answer;
   }

   private void parentasize(Node<String> r, ArrayList<String> ans) {
       String root = r.getData();
       Iterator<? extends Node<String>> i = r.children();
       if(!i.hasNext()){
           ans.add(root);
           return;
       }
           ans.add("(");
           parentasize(i.next(), ans);
           ans.add(root);
           parentasize(i.next(), ans);
           ans.add(")");
   }
   
   private BNode<String> setLastOpp(String s) {
       if(s.matches("[(].*[)]")){
           s = s.substring(1, s.length() - 1);
       }
       if(isOpperand(s))
           return new BNode<String>(s,null,null,null);
       for(int i = s.length() - 1; i >= 0; i--){
           String character = Character.toString(s.charAt(i));
           if(character.equals("+") || character.equals("-"))
               return new BNode<String>(character, null, setLastOpp(s.substring(0, i)), setLastOpp(s.substring(i+1)));
       }
       for(int i = s.length() - 1; i >= 0; i--){
           String character = Character.toString(s.charAt(i));
           if(character.equals(")")){
               i -= 1;
               character = Character.toString(s.charAt(i));
               while(!character.equals("(")){
                   i -= 1;
                   character = Character.toString(s.charAt(i));
               }
           }
           if(character.equals("*") || character.equals("/"))
               return new BNode<String>(character, null, setLastOpp(s.substring(0, i)), setLastOpp(s.substring(i+1)));
       }
       return null;
    }

   private boolean isOpperand( String s){
        if(!(s.contains("+") || s.contains("-") || s.contains("*") || s.contains("/")))
            return true;
         return false;
   }
   
   public final String postfix() {
      String ans = "";
      ArrayList<Node<String>> l = postOrder(); 
      for (Node<String> b:l) ans += b.getData() + " ";
      return ans;
   }

   public final String prefix() {
      String ans = "";
      ArrayList<Node<String>> l = preOrder(); 
      for (Node<String> b:l) ans += b.getData() + " ";
      return ans;
   }
   
}