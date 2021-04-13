package com.cookandroid.englishquestion;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends Activity {

    public static Activity mainActivity;
    Button btnWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = MainActivity.this;
        setContentView(R.layout.activity_main);

        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        btnWord = (Button) findViewById(R.id.btnWord);
        btnWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Level2Activity.class);
                Random ran = new Random();                            // 난수 생성하기 위해 Random 클래스의 객체 생성
                int num = ran.nextInt(Constants.engList.size()) + 1;                // 1 ~ 1800 사이에서 임의의 수 하나 추출
                intent.putExtra("num", num);
                startActivity(intent);
            }
        });

        AssetManager aMgr = getResources().getAssets();            // 안드로이드 프로젝트 밑에 assets 폴더를 읽어오기 위해 AssetManager의 객체 생성
        InputStream is = null;                        // english.ini 파일을 읽어 오기 위한 InputStream 선언
        try {
            is = aMgr.open("english.ini");                // assets 디렉토리에 있는 english.ini 파일을 InputStream 형태로 읽어 온다.
        } catch (IOException e) {
            e.printStackTrace();                    // 읽어오는 도중의 예외가 발생한 경우
        }
        String engWordsIni = readWrods(is);                // english.ini 파일의 내용을 통째로 읽어오는 Method 호출(1)
        StringReader sReader = new StringReader(engWordsIni);        // (1)의 값을 StringReader에 담는다(2)
        BufferedReader bReader = new BufferedReader(sReader);        // (2)의 값을 BufferedReader에 담는다

        String line;
        // 아래의 while문이 끝나면 english.ini에 읽는 모든 영어 단어를 읽어서 Constants.engList 에 담는다.
        try {
            while ((line = bReader.readLine()) != null) {                // english.ini 의 끝까지 한 줄씩 읽는다.
                String[] split = line.split("@");                // 한줄을 읽어 @ 기준으로 값을 분리

                for (int i = 0; i < split.length; i++) {            // 분리된 개수(3개)만큼 for문을 돌린다.
                    String[] splitWord = split[i].split("=");
                    HashMap<String, String> word = new HashMap<String, String>();
                    word.put(splitWord[1], splitWord[2]);
                    Constants.engList.put(splitWord[0], word);        // Constants.engList에 파싱
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    // english.ini 파일의 내용을 통째로 읽어 오는 Method
    private String readWrods(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is, "EUC-KR"));    // InputStream 형태로 읽어온 english.ini 의 내용을 EUC-KR 인코딩 방식으로 읽어 BufferedReader에 담는다
            while ((line = br.readLine()) != null) {            // english.ini를 끝의 라인까지 다 읽지 않았다면
                sb.append(line + "\n");                    // sb에 읽혀진 라인을 담는다.
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {                        // 만약 english.ini를 끝의 라인까지 다 읽었다면
                try {
                    br.close();                    // BufferedReader 변수를 없앤다(더 이상 사용하지 않으므로)
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();        // 결과를 리턴한다.
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
