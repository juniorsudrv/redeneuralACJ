 package meupneuronio;
 
 
 
 
 
 
 
 
 
 
 
 
 public class TesteNeuronio
 {
   public static void main(String[] args) {
     NeuronioAJC nr = new NeuronioAJC(4, 35);
 
     
     for (int cont = 0; cont < 100; cont++) {
       
       nr.setValorTreino(cont, new double[] { (cont % 2) });
     } 
 
     
     nr.setValorTreino(1000, new double[] { 0.0D });
     
     nr.setValorTreino(1001, new double[] { 1.0D });
     
     nr.treinarRede();
     
     System.out.println("" + nr.acaoAJCSimples(2051L));
     nr.treinarRede();
     System.out.println("" + nr.acaoAJCSimples(2051L));
   }
 }


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/meupneuronio/TesteNeuronio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */