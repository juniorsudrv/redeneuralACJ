 package Biblis;
 
 import java.security.MessageDigest;
 
 
 
 
 
 
 
 
 public class Conf
 {
   public static String encripto(String user, String password) {
     String sign = user + password;
 
     
     try {
       MessageDigest md = MessageDigest.getInstance("MD5");
       md.update(sign.getBytes());
       byte[] hash = md.digest();
       StringBuffer hexString = new StringBuffer();
       for (int i = 0; i < hash.length; i++) {
         if ((0xFF & hash[i]) < 16) {
           hexString.append("0" + 
               Integer.toHexString(0xFF & hash[i]));
         } else {
           hexString.append(Integer.toHexString(0xFF & hash[i]));
         } 
       }  sign = hexString.toString();
     }
     catch (Exception nsae) {
       nsae.printStackTrace();
     } 
     return sign;
   }
 }


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/Biblis/Conf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */