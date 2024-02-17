 package Biblis;
 
 import java.awt.image.BufferedImage;
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 import javax.imageio.ImageIO;
 import javax.swing.JOptionPane;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class Ler
 {
   public void Ler() {}
   
   public boolean checanumero(String num) {
     if (num.length() == 0 || num.contains("..") || num.contains(",,") || (num
       .split(".")).length < 2 || num.split(".")[1].length() > 2 || num
       .split(".")[1].contains(",") || num.split(".")[1].contains(".") || num
       .charAt(0) == '.' || num.charAt(0) == ',') {
       JOptionPane.showMessageDialog(null, "Formato " + num + " Incorreto.\nDigite no formato correto:\nEx: 11111.00\nEx: 11,111.08");
       
       return false;
     } 
 
     
     return true;
   }
   
   public boolean simounao(String msg, String msg2) {
     Object[] options = { "Não, Sem Chance.", "Sim, sim, claro..." };
     int n = JOptionPane.showOptionDialog(null, msg, msg2, 0, 3, null, options, options[0]);
 
 
 
 
 
 
 
 
 
 
     
     if (n == 1) {
       return true;
     }
     return false;
   }
 
 
 
   
   public String AbrirS(String fileName) {
     BufferedReader in = null;
     try {
       in = new BufferedReader(new FileReader(fileName));
     }
     catch (FileNotFoundException ex) {
 
       
       Logger.getLogger(Ler.class.getName()).log(Level.SEVERE, (String)null, ex);
     } 
     
     StringBuffer buffer = new StringBuffer(); try {
       String line;
       while ((line = in.readLine()) != null) {
         buffer.append(line + "\n");
       }
     } catch (IOException ex) {
       Logger.getLogger(Ler.class.getName()).log(Level.SEVERE, (String)null, ex);
     } 
     try {
       in.close();
     } catch (IOException ex) {
       Logger.getLogger(Ler.class.getName()).log(Level.SEVERE, (String)null, ex);
     } 
     return buffer.toString();
   }
 
 
   
   public String AbrirF(File file) throws IOException {
     BufferedReader in = new BufferedReader(new FileReader(file));
     
     StringBuffer buffer = new StringBuffer();
     String line;
     while ((line = in.readLine()) != null) {
       buffer.append(line + "\n");
     }
     
     in.close();
     return buffer.toString();
   }
 
 
   
   protected void finalize() throws Throwable {}
 
   
   public void pintaImg() throws IOException {
     BufferedImage imagem = ImageIO.read(new File("C:\\imagem.jpg"));
 
     
     int w = imagem.getWidth();
     int h = imagem.getHeight();
     int[] pixels = imagem.getRGB(0, 0, w, h, null, 0, w);
 
 
 
     
     imagem.setRGB(0, 0, w, h, pixels, 0, w);
     
     ImageIO.write(imagem, "PNG", new File("arteabstrata.png"));
   }
 
 
 
 
 
 
 
 
   
   public void Escrever(String fileName, String conteudo) {
     PrintWriter out = null;
     try {
       out = new PrintWriter(new FileWriter(fileName));
     } catch (IOException ex) {
       Logger.getLogger(Ler.class.getName()).log(Level.SEVERE, (String)null, ex);
     } 
 
     
     out.write(conteudo);
     
     out.close();
   }
 
 
 
   
   public static void Escrever(File file, String conteudo) throws IOException {
     PrintWriter out = new PrintWriter(new FileWriter(file));
     out.write(conteudo);
     out.close();
   }
 
 
 
 
 
   
   public static void main(String[] args) {
     Ler ler = new Ler();
 
     
     ler.AbrirS("Aqui.doc");
   }
 }


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/Biblis/Ler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */