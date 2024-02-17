 package OpIO;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.ObjectInputStream;
 import java.io.ObjectOutputStream;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 
 
 
 
 
 
 public class IO
 {
   public static boolean inserir(String nome, Object d) throws IOException {
     try {
       FileOutputStream f = new FileOutputStream(new File(nome));
       ObjectOutputStream o = new ObjectOutputStream(f);
       o.writeObject(d);
       o.close();
       f.close();
       return true;
     
     }
     catch (FileNotFoundException ex) {
       Logger.getLogger(IO.class.getName()).log(Level.SEVERE, (String)null, ex);
       
       return false;
     } 
   }
   
   public static Object ler(String nome) {
     try {
       FileInputStream fi = new FileInputStream(new File(nome));
       
       ObjectInputStream oi = new ObjectInputStream(fi);
       Object o = oi.readObject();
       oi.close();
       fi.close();
       return o;
     } catch (Exception ex) {
       Logger.getLogger(IO.class.getName()).log(Level.SEVERE, (String)null, ex);
 
       
       return null;
     } 
   }
 }


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/OpIO/IO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */