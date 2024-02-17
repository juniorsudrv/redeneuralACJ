 package Biblis;
 
 import java.awt.Color;
 import java.awt.Component;
 import java.awt.Graphics2D;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class barra_progresso
 {
   Color fd = null;
   Graphics2D g2 = null;
   boolean continua = true;
   int mx = 0;
   int my = 0;
   barra br = null;
   public barra_progresso(Component g) {
     this.g2 = (Graphics2D)g.getGraphics();
     
     this.mx = g.getWidth();
     this.my = g.getHeight();
   }
 
 
 
   
   public void inicia_progresso() {
     this.continua = true;
     this.g2.setColor(Color.gray);
     this.g2.fillRect(0, 0, this.mx, this.my);
     
     this.br = new barra();
     this.br.start();
   }
 
   
   public void para_progresso() {
     this.continua = false;
     this.g2.setColor(this.fd);
     this.g2.fillRect(0, 0, this.mx, this.my);
   }
   
   class barra
     extends Thread {
     public void run() {
       int lx = 0;
       while (barra_progresso.this.continua) {
         if (barra_progresso.this.g2 != null) {
           
           barra_progresso.this.g2.setColor(Color.orange);
           barra_progresso.this.g2.fillRect(0, 0, lx, barra_progresso.this.my);
         } 
 
         
         lx += 10;
         if (lx > barra_progresso.this.mx) {
           
           barra_progresso.this.g2.setColor(Color.gray);
           barra_progresso.this.g2.fillRect(0, 0, lx, barra_progresso.this.my);
           
           lx = 0;
         } 
         try {
           Thread.sleep(100L);
         } catch (InterruptedException ex) {
           Logger.getLogger(barra_progresso.class.getName()).log(Level.SEVERE, (String)null, ex);
         } 
       } 
     }
   }
 }


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/Biblis/barra_progresso.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */