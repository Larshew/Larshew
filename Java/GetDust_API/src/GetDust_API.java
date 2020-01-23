import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
 
public class GetDust_API {
    public static void main(String[] args) {
        BufferedReader br = null;
        try{
        	String where;
        	Scanner scan = new Scanner(System.in);
        	System.out.print("알고 싶은 지역을 입력하세요 : ");
        	where = scan.nextLine();
        			
            String urlstr = "http://openapi.airkorea.or.kr/"
            		+ "openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=" +
            		where + "&dataTerm=month&pageNo=1&numOfRows=10&ServiceKey="
            		+ "V6i2Rt1FL4jsYItn2EEcQm6Aw6AB4C0WsWt9YbSXem%2FiCQjiMGQnfVzrGUtkbKZY0dA7EJawhznH%2FEb6wtHowg%3D%3D"
            		+ "&ver=1.3";
            System.out.println(urlstr);
            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
            String result = "";
            String line;
            while((line = br.readLine()) != null) {
            	System.out.println(line);
                result = result + line + "\n";
            }
//            System.out.println(result);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
