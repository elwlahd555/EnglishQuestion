package com.cookandroid.englishquestion;


import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Level2Activity extends Activity {

    public static Activity level2Activity;

    private TextView ans;                // 사용자가 입력한 알파벳(한단어)


    private String engAns;                // 문제의 답에 해당하는 영어단어
    private String korAns;                // 문제의 답에 해당하는 영어단어의 뜻
    private String engAns2;                // 문제의 답에 해당하는 영어단어
    private String korAns2;                // 문제의 답에 해당하는 영어단어의 뜻
    private String engAns3;                // 문제의 답에 해당하는 영어단어
    private String korAns3;                // 문제의 답에 해당하는 영어단어의 뜻
    private String engAns4;                // 문제의 답에 해당하는 영어단어
    private String korAns4;                // 문제의 답에 해당하는 영어단어의 뜻

    @Override
    public void onBackPressed() {
        finish();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                finish();
                return true;
            case R.id.item2:
                Level2Activity level2Activity = (Level2Activity) Level2Activity.level2Activity;
                level2Activity.finish();
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_second);

        level2Activity = Level2Activity.this;
        getActionBar().setDisplayShowHomeEnabled(false);
        Button restart = (Button) findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Level2Activity level2Activity = (Level2Activity) Level2Activity.level2Activity;
                level2Activity.finish();
                Intent intent = new Intent(getApplicationContext(), Level2Activity.class);
                Random ran = new Random();                            // 난수 생성하기 위해 Random 클래스의 객체 생성
                int num = ran.nextInt(Constants.engList.size()) + 1;                // 1 ~ 1800 사이에서 임의의 수 하나 추출
                intent.putExtra("num", num);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();                                // MainActivity에서 GameStartActivity로 화면이 넘어오도록 intent를 받는다.
        int ranNum = intent.getIntExtra("num", -1);
        int ranNum2 = ranNum + 1;
        int ranNum3 = ranNum + 2;
        int ranNum4 = ranNum + 3;// 영어단어 선택을 위해 MainActivity에서 intent에 담은 랜덤 값을 읽어온다.
        // Constants.java에 선언된 engList HashMap에서 위에서 선택된 난수에 해당하는 영어단어를 읽어온다.
        HashMap<String, String> engWord = Constants.engList.get("" + ranNum);
        Set<String> keySet = engWord.keySet();
        Iterator<String> iter = keySet.iterator();

        HashMap<String, String> engWord2 = Constants.engList.get("" + ranNum2);
        Set<String> keySet2 = engWord2.keySet();
        Iterator<String> iter2 = keySet2.iterator();

        HashMap<String, String> engWord3 = Constants.engList.get("" + ranNum3);
        Set<String> keySet3 = engWord3.keySet();
        Iterator<String> iter3 = keySet3.iterator();

        HashMap<String, String> engWord4 = Constants.engList.get("" + ranNum4);
        Set<String> keySet4 = engWord4.keySet();
        Iterator<String> iter4 = keySet4.iterator();

        engAns = iter.next();            // 영어단어
        korAns = engWord.get(engAns);        // 영어단어 뜻
        engAns2 = iter2.next();
        korAns2 = engWord2.get(engAns2);
        engAns3 = iter3.next();
        korAns3 = engWord3.get(engAns3);
        engAns4 = iter4.next();
        korAns4 = engWord4.get(engAns4);


        ans = (TextView) findViewById(R.id.quiz);
        ans.setText(engAns);


        int r = new Random().nextInt(4) + 1;
        switch (r) {
            case 1:
                final Button btnans1 = (Button) findViewById(R.id.btnans1);
                btnans1.setText(korAns);
                final Button btnans2 = (Button) findViewById(R.id.btnans2);
                btnans2.setText(korAns2);
                final Button btnans3 = (Button) findViewById(R.id.btnans3);
                btnans3.setText(korAns3);
                final Button btnans4 = (Button) findViewById(R.id.btnans4);
                btnans4.setText(korAns4);

                btnans1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        btnans1.setEnabled(false);
                        btnans2.setEnabled(false);
                        btnans3.setEnabled(false);
                        btnans4.setEnabled(false);
                        btnans1.setTextColor(Color.RED);
                        ans.setTextColor(Color.RED);

                    }
                });

                btnans2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "오답입니다!", Toast.LENGTH_SHORT).show();
                        btnans2.setEnabled(false);

                    }
                });

                btnans3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "오답입니다!", Toast.LENGTH_SHORT).show();
                        btnans3.setEnabled(false);

                    }
                });

                btnans4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "오답입니다!", Toast.LENGTH_SHORT).show();
                        btnans4.setEnabled(false);

                    }
                });
                break;
            case 2:
                final Button btnans22 = (Button) findViewById(R.id.btnans2);
                btnans22.setText(korAns);
                final Button btnans11 = (Button) findViewById(R.id.btnans1);
                btnans11.setText(korAns2);
                final Button btnans33 = (Button) findViewById(R.id.btnans3);
                btnans33.setText(korAns3);
                final Button btnans44 = (Button) findViewById(R.id.btnans4);
                btnans44.setText(korAns4);

                btnans22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        btnans11.setEnabled(false);
                        btnans22.setEnabled(false);
                        btnans33.setEnabled(false);
                        btnans44.setEnabled(false);
                        btnans22.setTextColor(Color.RED);
                        ans.setTextColor(Color.RED);

                    }
                });

                btnans11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "오답입니다!", Toast.LENGTH_SHORT).show();
                        btnans11.setEnabled(false);
                    }
                });

                btnans33.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "오답입니다!", Toast.LENGTH_SHORT).show();
                        btnans33.setEnabled(false);
                    }
                });

                btnans44.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "오답입니다!", Toast.LENGTH_SHORT).show();
                        btnans44.setEnabled(false);
                    }
                });

                break;
            case 3:
                final Button btnans333 = (Button) findViewById(R.id.btnans3);
                btnans333.setText(korAns);
                final Button btnans222 = (Button) findViewById(R.id.btnans2);
                btnans222.setText(korAns2);
                final Button btnans111 = (Button) findViewById(R.id.btnans1);
                btnans111.setText(korAns3);
                final Button btnans444 = (Button) findViewById(R.id.btnans4);
                btnans444.setText(korAns4);

                btnans333.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();

                        btnans111.setEnabled(false);
                        btnans222.setEnabled(false);
                        btnans333.setEnabled(false);
                        btnans444.setEnabled(false);
                        btnans333.setTextColor(Color.RED);
                        ans.setTextColor(Color.RED);
                    }
                });

                btnans222.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "오답입니다!", Toast.LENGTH_SHORT).show();
                        btnans222.setEnabled(false);
                    }
                });

                btnans111.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "오답입니다!", Toast.LENGTH_SHORT).show();
                        btnans111.setEnabled(false);
                    }
                });

                btnans444.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "오답입니다!", Toast.LENGTH_SHORT).show();
                        btnans444.setEnabled(false);
                    }
                });

                break;
            case 4:
                final Button btnans4444 = (Button) findViewById(R.id.btnans4);
                btnans4444.setText(korAns);
                final Button btnans2222 = (Button) findViewById(R.id.btnans2);
                btnans2222.setText(korAns2);
                final Button btnans3333 = (Button) findViewById(R.id.btnans3);
                btnans3333.setText(korAns3);
                final Button btnans1111 = (Button) findViewById(R.id.btnans1);
                btnans1111.setText(korAns4);

                btnans4444.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        btnans1111.setEnabled(false);
                        btnans2222.setEnabled(false);
                        btnans3333.setEnabled(false);
                        btnans4444.setEnabled(false);
                        btnans4444.setTextColor(Color.RED);
                        ans.setTextColor(Color.RED);

                    }
                });

                btnans2222.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "오답입니다!", Toast.LENGTH_SHORT).show();
                        btnans2222.setEnabled(false);
                    }
                });

                btnans3333.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "오답입니다!", Toast.LENGTH_SHORT).show();
                        btnans3333.setEnabled(false);
                    }
                });

                btnans1111.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "오답입니다!", Toast.LENGTH_SHORT).show();
                        btnans1111.setEnabled(false);
                    }
                });

                break;
        }

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
        return sb.toString();
    }
}
