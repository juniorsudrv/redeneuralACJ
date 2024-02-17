 package Biblis;
 
 import java.text.DecimalFormat;
 import java.text.NumberFormat;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class MemoryUtils
 {
   public static double usedMemory() {
     Runtime runtime = Runtime.getRuntime();
     return usedMemory(runtime);
   }
 
 
 
   
   public static double maxMemory() {
     Runtime runtime = Runtime.getRuntime();
     return maxMemory(runtime);
   }
   
   static double usedMemory(Runtime runtime) {
     long totalMemory = runtime.totalMemory();
     long freeMemory = runtime.freeMemory();
     double usedMemory = (totalMemory - freeMemory) / 1048576.0D;
     return usedMemory;
   }
   
   static double maxMemory(Runtime runtime) {
     long maxMemory = runtime.maxMemory();
     double memory = maxMemory / 1048576.0D;
     return memory;
   }
   
   public static void printMemoryInfo() {
     StringBuffer buffer = getMemoryInfo();
   }
 
   
   public static StringBuffer getMemoryInfo() {
     StringBuffer buffer = new StringBuffer();
     
     freeMemory();
     
     Runtime runtime = Runtime.getRuntime();
     double usedMemory = usedMemory(runtime);
     double maxMemory = maxMemory(runtime);
    
     NumberFormat f = new DecimalFormat("###,##0.0");
     
     String lineSeparator = System.getProperty("line.separator");
     buffer.append("Used memory: " + f.format(usedMemory) + "MB").append(lineSeparator);
     buffer.append("Max available memory: " + f.format(maxMemory) + "MB").append(lineSeparator);
     return buffer;
   }
  
   public static void freeMemory() {
     System.gc();
      System.runFinalization();
    }
  }


 