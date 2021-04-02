package my.cik.ai.test00.connect;

//
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

public class HttpConTest {

	public static void main(String[] args) {
	   try {
	       URL url = new URL("http://localhost:8090/login.jes");
	       HttpURLConnection con = (HttpURLConnection)url.openConnection();
	       
	       con.setRequestMethod("POST");
//	       String postParams = "id=master&pw=1313";
	       con.setDoOutput(true);
	       DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//	       wr.writeBytes(postParams); // 전송
	       wr.writeBytes("id=master&pw=1313"); // 전송
	       wr.flush();
	       wr.close();
	       
	       int responseCode = con.getResponseCode();
	       BufferedReader br;
//	       정상 호출
	       if(responseCode==200) {
	    	   br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    	   String inputLine;
	    	   StringBuffer response = new StringBuffer();
	    	   while ((inputLine = br.readLine()) != null) {
	               response.append(inputLine);
	    	   }
	           br.close();
	           System.out.println(response.toString());
//	       오류 발생
	       } else {
	      	 br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	           String inputLine;
	           StringBuffer response = new StringBuffer();
	           while ((inputLine = br.readLine()) != null) {
	               response.append(inputLine);
	           }
	           br.close();
	           System.out.println(response.toString());
	       }
	   } catch (Exception e) {
	       System.out.println(e);
	   }
	}
}
