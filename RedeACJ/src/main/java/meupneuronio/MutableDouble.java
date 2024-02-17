 package meupneuronio;
 
 import java.io.Serializable;
 
 
 
 class MutableDouble
   implements Serializable
 {
   private double value;
   
   public MutableDouble(double value) {
     this.value = value;
   }
   
   public double getValue() {
     return this.value;
   }
   
   public void setValue(double value) {
     this.value = value;
   }
 }


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/meupneuronio/MutableDouble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */