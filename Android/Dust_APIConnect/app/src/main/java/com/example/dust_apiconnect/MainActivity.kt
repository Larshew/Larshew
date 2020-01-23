package com.example.dust_apiconnect

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL
import java.net.URLConnection
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.*
import javax.net.ssl.HttpsURLConnection

fun isConnected(context: Context):Boolean{
    val manager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo: NetworkInfo? = manager.activeNetworkInfo?:null

    if (networkInfo != null){
        return true
    }
    return false
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = resources.getStringArray(R.array.my_array)
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        spinner.adapter = myAdapter

        if (isConnected(this)) {
            textView2.setText("Internet Connected")
        }
        else{
            textView2.setText("Internet Not Connected")
        }
        button.setOnClickListener{
            var url = URL(
                "http",
                "openapi",
                80,
                "/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=" + spinner.getSelectedItem() + "&dataTerm=month&pageNo=1&numOfRows=10&ServiceKey=V6i2Rt1FL4jsYItn2EEcQm6Aw6AB4C0WsWt9YbSXem%2FiCQjiMGQnfVzrGUtkbKZY0dA7EJawhznH%2FEb6wtHowg%3D%3D&ver=1.3"
            )
            val connection = url.openConnection()

            try {
                var item=false
                var end=false;
                var pm25Val=false
                var pm10Val=false
//,"/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName="+spinner.getSelectedItem()+"&dataTerm=month&pageNo=1&numOfRows=10&ServiceKey=V6i2Rt1FL4jsYItn2EEcQm6Aw6AB4C0WsWt9YbSXem%2FiCQjiMGQnfVzrGUtkbKZY0dA7EJawhznH%2FEb6wtHowg%3D%3D&ver=1.3"

                System.out.println("")
                System.out.println("protocol name:" + url.getProtocol());
                System.out.println("host name:" + url.getHost());
                System.out.println("file name:" + url.getFile());
                System.out.println("port name:" + url.getPort());


                val `is` = connection.inputStream
                val sb = StringBuilder()
                val br = BufferedReader(InputStreamReader(`is`, "UTF-8"))
                var result: String



/*                while (parserEvent != XmlPullParser.END_DOCUMENT) {
                    when (parserEvent) {
                        XmlPullParser.START_TAG -> {
                            if (parser.getName().equals("item")) {
                                item = true
                            }
                            if (parser.getName().equals("/item")) {
                                end = true
                            }
                            if (parser.getName().equals("pm10Value")) {
                                pm10Val = true
                            }
                            if (parser.getName().equals("pm25Value")) {
                                pm25Val = true
                            }
                        }
                        XmlPullParser.TEXT -> {
                            if (pm10Val) {
                                textView2.setText("미세먼지 값 : " + parser.getText())
                                pm10Val = false
                            }
                            if (pm25Val) {
                                textView3.setText("초미세먼지 값 : " + parser.getText())
                                pm25Val = false
                            }
                        }
                    }
                    if (end) break;
                    parserEvent = parser.next();
                }*/
            }
            catch(e:NumberFormatException){
                textView2.setText("에러가 났습니다...!!")
            }
        }
    }
}
