package com.example.dustapi_usingjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button Get_But;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.enableDefaults();

        Resources res = getResources();
        String[] items = res.getStringArray(R.array.location_array);
        ArrayList<String> list = new ArrayList<String>();

        for (String it:items){
            list.add(it);
        }
        ArrayAdapter myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);

        Spinner loc_spinner = (Spinner)findViewById(R.id.location_spinner);
        loc_spinner.setAdapter(myAdapter);

        Get_But = (Button)findViewById(R.id.Get_Button);
        Get_But.setOnClickListener(this);
    }

    public void onClick(View view){
        TextView Dust_Txt = (TextView)findViewById(R.id.Dust_Text);
        GetDustInformation gd = new GetDustInformation();
        try {
            URL url = new URL("http://openapi.airkorea.or.kr");
            gd.execute(url);
        }
        catch (Exception E){
            Dust_Txt.setText("hphoho");
        }
    }

    class GetDustInformation extends AsyncTask<URL, Integer, Long>{
        @Override
        protected void onPreExecute(){

        }

        @Override
        protected Long doInBackground(URL... urls){
            TextView Dust_Txt = (TextView)findViewById(R.id.Dust_Text);
            try {
                Spinner loc_spinner = (Spinner)findViewById(R.id.location_spinner);
                URL url = new URL("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName="
                        + loc_spinner.getSelectedItem().toString() +
                        "&dataTerm=month&pageNo=1&numOfRows=10&ServiceKey=V6i2Rt1FL4jsYItn2EEcQm6Aw6AB4C0WsWt9YbSXem%2FiCQjiMGQnfVzrGUtkbKZY0dA7EJawhznH%2FEb6wtHowg%3D%3D&ver=1.3");
                System.out.println(url);
                XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
                XmlPullParser parser = parserCreator.newPullParser();
                parser.setInput(url.openStream(), null);

                int parserEvent = parser.getEventType();
                System.out.println("파싱시작합니다.");
                Boolean pm10=false, pm25=false, end=false;
                Integer pm10val=0, pm25val=0;
                while (parserEvent != XmlPullParser.END_DOCUMENT) {
                    switch (parserEvent) {
                        case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                            System.out.print(parser.getName());
                            if (parser.getName().equals("pm10Value")) pm10=true;
                            if (parser.getName().equals("pm25Value")) pm25=true;
                            break;

                        case XmlPullParser.TEXT://parser가 내용에 접근했을때
                            System.out.print(parser.getText());
                            if (pm10){
                                System.out.println(parser.getText());
                                System.out.println("여기가문제인감33");
                                pm10val = Integer.parseInt(parser.getText());
                                System.out.println("요쪽이문제인감55");
                            }
                            else if (pm25){
                                System.out.println(parser.getText());
                                System.out.println("여기가문제인감");
                                pm25val = Integer.parseInt(parser.getText());
                                System.out.println("요쪽이문제인감");
                                end=true;
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("pm10Value")) pm10=false;
                            if (parser.getName().equals("pm25Value")) pm25=false;
                            break;
                    }
                    if (end) break;
                    parserEvent = parser.next();
                }
                Dust_Txt.setText("미세먼지 농도 : " + pm10val + ",\n\n초미세먼지 농도 : " + pm25val);
            }
            catch(Exception E){
                Dust_Txt.setText("인터넷 연결 오류!");
            }

            return 0L;
        }

        @Override
        protected void onProgressUpdate(Integer... progress){

        }

        @Override
        protected void onPostExecute(Long result){

        }
    }
}
