package com.down.recycleviewtest;

import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    LinkedList<bean> linkedList;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkedList = new LinkedList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myAdapter = new MyAdapter(this, linkedList);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setHasFixedSize(true);
       // addDataToLinkedList();
        download a = new download();
        a.execute("asxa");
    }

    public void addDataToLinkedList() {
        for (int i = 0; i < 10; i++) {
            bean bean = new bean();
            bean.setText1("aaa");
            bean.setText2("bbb");
            linkedList.add(bean);
        }
    }


    public void getData() throws IOException {
        Document document = Jsoup.connect("https://blinksowonder.com/category/entertainment/celeberaties/").get();
        Elements elements = document.getElementsByTag("article");
        for(Element e:elements){

            String linksrc=null,linkText1=null,linkText2 = null;

            Elements elements1=e.getElementsByTag("img");
            for(Element element:elements1){
                linksrc = element.attr("abs:src");
                System.out.println("linksrc"+linksrc);
            }

            Elements elements2=e.getElementsByClass("entry-header");
            for(Element element:elements2){
                linkText1 = element.text();
                System.out.println("linkText"+linkText1);
            }

            Elements elements3=e.getElementsByClass("entry-summary");
            for(Element element:elements3){
                 linkText2 = element.text();
                System.out.println("linkText"+linkText2);
            }


            bean bean = new bean();
            bean.setText1(linkText1);
            bean.setText2(linkText2);
            bean.setImg(linksrc);
            linkedList.add(bean);




        }
    }


    public class download extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String result) {
            myAdapter.notifyDataSetChanged();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


}
