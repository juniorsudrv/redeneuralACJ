 package Biblis;
 
 import java.awt.Color;
 import java.awt.Component;
 import java.awt.Graphics2D;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class barra_progresso_perfeito
 {
   Color fd = null;
   Graphics2D g2 = null;
   boolean continua = true;
   int mx = 0;
   int my = 0;
   int plx = 0;
   Component gc = null;
   public barra_progresso_perfeito(Component g) {
     this.g2 = (Graphics2D)g.getGraphics();
     
     this.mx = g.getWidth();
     this.my = g.getHeight();
     
     this.gc = g;
   }
 
   
   public void inicia_progresso(int vlmx) {
     this.continua = true;
     this.g2.setColor(Color.gray);
     this.g2.fillRect(0, 0, this.mx, this.my);
     
     this.plx = vlmx;
   }
 
 
   
   public void para_progresso() {
     this.continua = false;
     this.g2.setColor(this.fd);
     this.g2.fillRect(0, 0, this.mx, this.my);
     this.gc.repaint();
   }
 
   
   public void setValor(int lxp) {
     this.g2.setColor(Color.orange);
     this.g2.fillRect(0, 0, this.mx * lxp * 100 / this.plx / 100, this.my);
   }
 }


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/Biblis/barra_progresso_perfeito.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */