 package Biblis;
 
 
 
 
 
 
 
 
 
 
 
 public class SO
 {
   public int rso() {
     if (System.getProperties().get("os.name").toString().charAt(0) == 'L' || System.getProperties().get("os.name").toString().charAt(0) == 'l') {
       return 1;
     }
     
     return 2;
   }
   
   public static void main(String[] args) {
     System.out.println((new SO()).rso() + "");
   }
 }


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/Biblis/SO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */