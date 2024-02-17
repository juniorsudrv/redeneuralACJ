 package OpIO;
 
 import java.awt.Point;
 import java.io.Serializable;
 import meupneuronio.NeuronioAJC;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class Dados
   implements Serializable
 {
   public String end1 = "C:\\Users\\junio\\Documents\\RedeNeural\\ImagensTestesX", end2 = "C:\\Users\\junio\\Documents\\RedeNeural\\ImagensTestesO";
 
   
   public String n1 = "X";
   public String n2 = "O";
   public NeuronioAJC rede = null;
   public String nrede = "Rede Teste X ou O";
   public Point dimensao = null;
 }


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/OpIO/Dados.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */