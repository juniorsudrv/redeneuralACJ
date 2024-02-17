 package meupneuronio;
 
 import java.awt.Color;
 import java.awt.Graphics2D;
 import java.awt.image.BufferedImage;
 import java.awt.image.WritableRaster;
 import java.io.File;
 import java.io.IOException;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class MeuAJC
 {
   public static long mediaLuz(BufferedImage image) {
     BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), 10);
     result.getGraphics().drawImage(image, 0, 0, null);
     long media = 0L;
     WritableRaster raster = result.getRaster();
     int[] pixels = new int[image.getWidth()];
     for (int y = 0; y < image.getHeight(); y++) {
       raster.getPixels(0, y, image.getWidth(), 1, pixels);
       for (int i = 0; i < pixels.length; i++) {
         media += pixels[i];
       }
     } 
     
     return media / (result.getHeight() * result.getWidth());
   }
   
   public static BufferedImage thresholdImage(BufferedImage image, long threshold) {
     BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), 10);
     result.getGraphics().drawImage(image, 0, 0, null);
     WritableRaster raster = result.getRaster();
     int[] pixels = new int[image.getWidth()];
     for (int y = 0; y < image.getHeight(); y++) {
       raster.getPixels(0, y, image.getWidth(), 1, pixels);
       for (int i = 0; i < pixels.length; i++) {
         if (pixels[i] < threshold) { pixels[i] = 0; }
         else { pixels[i] = 255; }
       
       }  raster.setPixels(0, y, image.getWidth(), 1, pixels);
     } 
     return result;
   }
 
   
   public static BufferedImage image2BlackWhiteTest(BufferedImage image) {
     BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), 12);
 
     
     Graphics2D graphic = result.createGraphics();
     graphic.drawImage(image, 0, 0, Color.WHITE, null);
     graphic.dispose();
     
     return result;
   }
 
   
   public static BufferedImage image2BlackWhite(BufferedImage image1, long media) {
     return thresholdImage(image1, media);
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public static void main(String[] args) throws IOException, InterruptedException {
     File fileN = new File("C:\\Users\\junio\\Documents\\RedeNeural\\ImgN");
     File fileS = new File("C:\\Users\\junio\\Documents\\RedeNeural\\ImgS");
     File[] nfile = fileN.listFiles();
     File[] sfile = fileS.listFiles();
     double[][] d = new double[1][nfile.length + sfile.length];
     for (int cont = 0; cont < nfile.length + sfile.length; cont++)
     {
       d[0][cont] = (cont < sfile.length) ? 1.0D : 0.0D;
     }
   }
 }


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/meupneuronio/MeuAJC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */