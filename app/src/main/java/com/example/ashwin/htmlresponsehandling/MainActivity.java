package com.example.ashwin.htmlresponsehandling;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Product> mProductsList = new ArrayList<>();

    private TextView mProductsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initTestData1();
        //initTestData2();
    }

    private void initViews() {
        mProductsTextView = (TextView) findViewById(R.id.productsTextView);
    }

    private void initTestData1() {
        AssetManager assetManager = getResources().getAssets();
        try {
            // Get string data
            StringBuilder buf = new StringBuilder();
            InputStream html = assetManager.open("test1.html");
            BufferedReader in = new BufferedReader(new InputStreamReader(html, "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }

            in.close();

            String response = buf.toString();

            Log.i("debuglogging", TAG + " : string : " + response);

            // Parse html string
            Document doc = Jsoup.parse(response);
            Elements products = doc.select("div.product");

            String result = "";
            for (Element product : products) {
                Elements elements = product.children();
                String title = "", price = "";
                for (Element element : elements) {
                    if ((element.className()).equals("title")) {
                        title = element.text();
                    } else if ((element.className()).equals("price")) {
                        price = element.text();
                    }
                }
                mProductsList.add(new Product(title, price));
                String p = title + " : " + price + "\n";
                result += "\n" + p;
            }

            mProductsTextView.setText(result);

        } catch (Exception e) {
            Log.e("debuglogging", TAG + " : Error" + e.toString());
        }
    }

    private void initTestData2() {
        AssetManager assetManager = getResources().getAssets();
        try {
            // Get string data
            StringBuilder buf = new StringBuilder();
            InputStream html = assetManager.open("test2.html");
            BufferedReader in = new BufferedReader(new InputStreamReader(html, "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }

            in.close();

            String response = buf.toString();

            Log.i("debuglogging", TAG + " : string : " + response);

            // Parse html string
            Document doc = Jsoup.parse(response);
            Elements products = doc.select("div.product-tuple-description");

            String result = "";
            for (Element product : products) {
                Elements elements = product.children();
                String title = "", price = "";
                for (Element element : elements) {
                    if ((element.className()).equals("product-title")) {
                        title = element.text();
                    } else if ((element.className()).equals("lfloat product-price")) {
                        price = element.text();
                    }
                }
                mProductsList.add(new Product(title, price));
                String p = title + " : " + price + "\n";
                result += "\n" + p;
            }

            mProductsTextView.setText(result);

        } catch (Exception e) {
            Log.e("debuglogging", TAG + " : Error" + e.toString());
        }
    }

}
