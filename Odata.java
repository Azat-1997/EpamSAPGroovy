package odata.utils;
import java.net.*;
import java.io.*;

public class Odata {

   public static HttpURLConnection odataConnect(String address) throws MalformedURLException, IOException {

         URL url = new URL(address);
         URLConnection urlConnection = url.openConnection();
         HttpURLConnection connection = null;

         if (urlConnection instanceof HttpURLConnection) {
            connection = (HttpURLConnection) urlConnection;
                     }
     
          connection.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
          connection.setRequestProperty("Method", "GET");
          connection.setRequestProperty("Accept", "*/xml");

        return connection;

   }

   public static String getText(HttpURLConnection connection) throws IOException {
         BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         String urlString = "";
         String current;
         while((current = in.readLine()) != null) {
            urlString += current;
         }
         return urlString;

   }


   public static int sendMessage(String address, String message) throws MalformedURLException, IOException {
        /*
           send message to resource and return code status.

              */      

         byte[] out = message.getBytes();
         URL url = new URL(address);
         URLConnection urlConnection = url.openConnection();
         HttpURLConnection connection = null; 

         if (urlConnection instanceof HttpURLConnection) {
            connection = (HttpURLConnection) urlConnection;
                     }

         OutputStream outMessage = null;    
         connection.setRequestProperty("Method", "POST");
         connection.setDoOutput(true);
         connection.setDoInput(true);
         connection.setConnectTimeout(200);
         connection.connect();

         try {
              outMessage = connection.getOutputStream();
              outMessage.write(out);
             } catch (Exception e) {
                    System.err.println(e.getMessage());
               }
        
        return connection.getResponseCode();

   }

  public static void main(String[] args) throws MalformedURLException, IOException{

       System.out.println("Compile is success");
       HttpURLConnection connectionTerritories = odataConnect("https://services.odata.org/V2/Northwind/Northwind.svc/Territories");
       HttpURLConnection connectionRegions = odataConnect("https://services.odata.org/V2/Northwind/Northwind.svc/Regions");
       System.out.println(getText(connectionTerritories));
       System.out.println(getText(connectionRegions));
     }

}
