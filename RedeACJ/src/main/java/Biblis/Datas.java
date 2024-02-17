 package Biblis;
 
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.GregorianCalendar;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 
 
 
 
 
 
 
 
 
 
 public class Datas
 {
   public String somaMeses(String data, int meses) {
     int ano = Integer.parseInt(data.split("-")[0]);
     int mes = Integer.parseInt(data.split("-")[1]);
     int dia = Integer.parseInt(data.split("-")[2]);
     
     SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd");
     
     Calendar c = new GregorianCalendar(ano, mes, dia);
 
     
     c.add(2, meses);
     
     return sd.format(c.getTime());
   }
 
 
 
 
 
   
   public String data() {
     DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
     Date date = new Date();
     return dateFormat.format(date);
   }
   public String diames() {
     DateFormat dateFormat = new SimpleDateFormat("dd/MM");
     Date date = new Date();
     return dateFormat.format(date);
   }
   
   public String hora() {
     DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
     Date date = new Date();
     return dateFormat.format(date);
   }
   
   public String horaeminuto() {
     DateFormat dateFormat = new SimpleDateFormat("HH:mm");
     Date date = new Date();
     return dateFormat.format(date);
   }
   
   public String getDateTime() {
     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     Date date = new Date();
     return dateFormat.format(date);
   }
 
 
 
   
   public String formata10() {
     String[] v = getDateTime().split(" ");
     v[1] = v[1].split(":")[0] + ":" + v[1].split(":")[1].charAt(0) + "0:00";
     return v[0] + " " + v[1];
   }
   
   public String getDataHoraM() {
     DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
     Date date = new Date();
     return dateFormat.format(date).substring(0, dateFormat.format(date).length() - 1) + "0";
   }
 
 
 
 
   
   public String getDataHoraMD() {
     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     Date date = new Date();
     String[] v = dateFormat.format(date).split("-");
     return v[0] + "-" + (
       (Integer.parseInt(v[1]) < 10) ? "0" : "") + ((Integer.parseInt(v[1]) != 1) ? (Integer.parseInt(v[1]) - 2) : Integer.parseInt(v[1])) + "-" + v[2];
   }
 
 
   
   public String getDateTimeMysql(Date dt) {
     if (dt == null)
     {
       dt = new Date();
     }
     DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
     
     return dateFormat.format(dt);
   }
 
   
   public int diasDatasQFunciona(String dS1, String dS2) {
     long res = -1L;
     try {
       DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       df.setLenient(false);
 
       
       Date d1 = df.parse(dS1);
       
       Date d2 = df.parse(dS2);
       
       long dt = d2.getTime() - d1.getTime() + 3600000L;
       res = dt / 86400000L;
       System.out.println(res + " Resultado");
     }
     catch (ParseException ex) {
       Logger.getLogger(Datas.class.getName()).log(Level.SEVERE, (String)null, ex);
     } 
     return (int)res;
   }
 
   
   public int diasDatas(String d1, String d2) {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Calendar data1 = Calendar.getInstance();
     Calendar data2 = Calendar.getInstance();
     
     try {
       data1.setTime(sdf.parse(d1));
       
       data2.setTime(sdf.parse(d2));
     } catch (ParseException ex) {
       Logger.getLogger(Datas.class.getName()).log(Level.SEVERE, (String)null, ex);
     } 
     
     int dias = data2.get(6) - data1.get(6);
     return dias;
   }
 
 
 
 
   
   public long calcular(String dt, Date dtFinal) throws ParseException {
     Calendar start = Calendar.getInstance();
     start.set(Integer.parseInt(dt.split("-")[0]), 
         Integer.parseInt(dt.split("-")[1]), Integer.parseInt(dt.split("-")[2]));
 
     
     Calendar end = Calendar.getInstance();
     String dtf = (new SimpleDateFormat("YYYY-MM-dd")).format(dtFinal);
     end.set(Integer.parseInt(dtf.split("-")[0]), 
         Integer.parseInt(dtf.split("-")[1]), Integer.parseInt(dtf.split("-")[2]));
     Date startDate = start.getTime();
     Date endDate = end.getTime();
     long startTime = startDate.getTime();
     long endTime = endDate.getTime();
     long diffTime = endTime - startTime;
     long diffDays = diffTime / 86400000L;
     DateFormat dateFormat = DateFormat.getDateInstance();
     System.out.println("The difference between " + dateFormat
         .format(startDate) + " and " + dateFormat
         .format(endDate) + " is " + diffDays + " days.");
     
     return diffDays;
   }
   
   public Date getDateMysqlD(String dt) {
     Calendar start = Calendar.getInstance();
     start.set(Integer.parseInt(dt.split("-")[2]), 
         Integer.parseInt(dt.split("-")[1]), Integer.parseInt(dt.split("-")[0]));
 
 
 
     
     return start.getTime();
   }
   public String getDateNormal(Date dt) {
     if (dt == null)
     {
       dt = new Date();
     }
     DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
     
     return dateFormat.format(dt);
   }
   public String getDateMysql(Date dt) {
     if (dt == null)
     {
       dt = new Date();
     }
     DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
     
     return dateFormat.format(dt);
   }
   public int pegarMes(Date dt) {
     if (dt == null)
     {
       dt = new Date();
     }
     DateFormat dateFormat = new SimpleDateFormat("MM");
     
     return Integer.parseInt(dateFormat.format(dt)) - 1;
   }
   
   public int pegarAno(Date dt) {
     if (dt == null)
     {
       dt = new Date();
     }
     DateFormat dateFormat = new SimpleDateFormat("yyyy");
     
     return Integer.parseInt(dateFormat.format(dt)) - 1;
   }
   public String getDateTimeF() {
     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     Date date = new Date();
     return dateFormat.format(date);
   }
 }


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/Biblis/Datas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */