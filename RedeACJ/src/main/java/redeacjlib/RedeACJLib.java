 package redeacjlib;
 
 import OpIO.IO;
 import java.io.File;
 import java.io.IOException;
 import meupneuronio.NeuronioAJC;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class RedeACJLib
 {
   public static void main(String[] args) throws IOException {
     File fileN = new File("IMG0");
     File fileS = new File("IMG1");
     File[] nfile = fileN.listFiles();
     File[] sfile = fileS.listFiles();
     
     NeuronioAJC nr = new NeuronioAJC(25, NeuronioAJC.lenghtImagem(sfile[0]));
     int cont2 = 0;
     for (int cont = 0; cont < nfile.length + sfile.length; cont++) {
       
       if (cont < sfile.length) {
         nr.setValorTreino(sfile[cont], new double[] { 1.0D });
       } else {
         nr.setValorTreino(nfile[cont2++], new double[] { 0.0D });
       } 
     } 
     
     nr.treinarRede();
     IO.inserir("minharede", nr);
     nr = (NeuronioAJC)IO.ler("minharede");
     System.out.println(nr.acaoAJCSimples(nfile[0]));
   }
 }


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/redeacjlib/RedeACJLib.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */