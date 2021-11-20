package com.websarva.wings.android.kariru1;


import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;



import org.jetbrains.annotations.NotNull;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText book_text,location_text,isbn_text;
    private String session;//再読み込みに必要
    String book_string,isbn_string, location_string;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        location_text = findViewById(R.id.locationText);
        isbn_text = findViewById(R.id.isbnText);
        book_text = findViewById(R.id.bookText);
        //広告の初期化
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    /* 取得するjsonデータの例
    {
         "session":"fa7ca42bb7aea9130f418653ffb09769",
         "continue": 0,
         "books": {
                     "9780307593313": {
                                        "Hyogo_Kobe": {
                                                        "status": "OK",
                                                        "libkey": {
                                                                        "中央図書館": "貸出可",
                                                                         "灘図書館": "貸出可"
                                                                   },
                                                        "reserveurl": "https://www.lib.city.kobe.jp/opac/opacs/find_detailbook?mode=one_line&type=PvolBook&kobeid=CT%3A7200120682&pvolid=PV%3A7200280596"
                                                       }
                                      }
                 }
     }
     */

    //在庫検索のRequest。urlにはISBN、地名が必要
    public void searchHttpRequest(String url) throws IOException{

        String isbn_string = isbn_text.getText().toString();//ISBNの入力を取得
        String location_string = location_text.getText().toString();//地名の入力を取得

        //OkHttpClient生成
        OkHttpClient client = new OkHttpClient();

        //request生成
        Request request = new Request.Builder()
                .url(url)
                .build();

        //非同期リクエスト
        client.newCall(request)
                .enqueue(new Callback() {

                    //エラーのとき
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.e("Error",e.getMessage());
                    }

                    //正常のとき
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                        //response取り出し
                        String jsonStr = response.body().string();
                        Log.i("JSON_string","jsonStr=" + jsonStr);
                        try {
                            // ルートJSONオブジェクトを生成。
                            JSONObject rootJSON = new JSONObject(jsonStr);
                            session = rootJSON.getString("session");
                            Log.i("session",session);
                            JSONObject books = rootJSON.getJSONObject("books");
                            JSONObject isbn = books.getJSONObject(isbn_string);
                            JSONObject location = isbn.getJSONObject(location_string);
                            //在庫情報のURLを取得
                            String reserveurl = location.getString("reserveurl");
                            Log.i("reserveurl ",reserveurl);
                            if(reserveurl.equals("")==false){
                                Uri uri = Uri.parse(reserveurl);
                                //Intentで在庫情報のサイトへ
                                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                                startActivity(intent);
                            }
                        }
                        catch(JSONException ex) {
                            Log.e("JSON_ERROR", "JSON解析失敗", ex);
                        }
                    }
                });
    }

    //再読み込みのRequest。urlにはsessionのみ必要
    public void sessionHttpRequest(String url) throws IOException{

        //OkHttpClient生成
        OkHttpClient client = new OkHttpClient();

        //request生成
        Request request = new Request.Builder()
                .url(url)
                .build();

        //非同期リクエスト
        client.newCall(request)
                .enqueue(new Callback() {

                    //エラーのとき
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.e("Error",e.getMessage());
                    }

                    //正常のとき
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                        //response取り出し
                        String jsonStr = response.body().string();
                        Log.i("JSON_string","jsonStr=" + jsonStr);
                        try {
                            // ルートJSONオブジェクトを生成。
                            JSONObject rootJSON = new JSONObject(jsonStr);
                            session = rootJSON.getString("session");
                            Log.i("session",session);
                            JSONObject books = rootJSON.getJSONObject("books");
                            JSONObject isbn = books.getJSONObject(isbn_string);
                            JSONObject location = isbn.getJSONObject(location_string);
                            //在庫情報のURLを取得
                            String reserveurl = location.getString("reserveurl");
                            Log.i("reserveurl ",reserveurl);
                            if(reserveurl.equals("")==false){
                                Uri uri = Uri.parse(reserveurl);
                                //Intentで在庫情報のサイトへ
                                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                                startActivity(intent);
                            }
                        }
                        catch(JSONException ex) {
                            Log.e("JSON_ERROR", "JSON解析失敗", ex);
                        }

                    }
                });
    }

    //在庫検索ボタンを押したとき
    public void search(View view){
        //ISBNの入力を取得
       isbn_string = isbn_text.getText().toString();
       Log.i("isbn_string",isbn_string);
        //地名の入力を取得
       location_string = location_text.getText().toString();
       String search_url = "https://api.calil.jp/check?appkey=ac8bea11c70423bc1a3b4b8fde42e19d&isbn="+isbn_string+"&systemid="+location_string+"&format=json&callback=no";

        if(isbn_string.equals("")==false&&location_string.equals("")==false){
            try{
                searchHttpRequest(search_url);
            }catch(Exception e){
                Log.e("Search_Error",e.getMessage());
            }
        }

    }

    //再読み込みボタンを押したとき
    public void reload(View view){

        String reload_url = "https://api.calil.jp/check?appkey=ac8bea11c70423bc1a3b4b8fde42e19d&session="+session+"&format=json&callback=no";

        try{
            sessionHttpRequest(reload_url);
        }catch(Exception e){
            Log.e("Reload_Error",e.getMessage());
        }

    }

    //ISBN検索ボタンを押したとき
    public void searchISBN(View view){
        //書籍の名前を取得
        book_string = book_text.getText().toString();
        if(book_string.equals("")==false){
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.setClassName("com.google.android.googlequicksearchbox",
                "com.google.android.googlequicksearchbox.SearchActivity");
            //検索ワードを登録
            intent.putExtra(SearchManager.QUERY, book_string +"　ISBN");
            //Googleの検索画面へ
            startActivity(intent);
        }
    }

}
