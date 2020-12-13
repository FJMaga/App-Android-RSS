package com.example.cineaficion;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class RSSFeedActivity extends ListActivity {


    private ProgressBar pDialog;
    ArrayList<HashMap<String, String>> rssItemList = new ArrayList<>();
    ArrayList<RSSItem> items = new ArrayList<>();

    RSSParser rssParser = new RSSParser();
    Toolbar toolbar;

    List<RSSItem> rssItems = new ArrayList<>();
    private static String TAG_TITLE = "title";
    private static String TAG_LINK = "link";
    private static String TAG_PUB_DATE = "pubDate";
    private static String TAG_ENCLOSURE = "enclosure";
    private static String TAG_URL = "url";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_feed);


        String rss_link = getIntent().getStringExtra("rssLink");
        new LoadRSSFeedItems().execute(rss_link);

        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //String title = ((TextView) view.findViewById(R.id.title)).getText().toString().trim();
                String url_imagen = items.get(position).getUrl();
                String titulo = items.get(position).getTitle();
                String descripcion = items.get(position).getDescription();
                Intent in = new Intent(getApplicationContext(), DetalleActivity.class);
                in.putExtra("imagenUrl", url_imagen);
                in.putExtra("tituloTexto",titulo);
                in.putExtra("detalleDescripcion",descripcion);

                startActivity(in);
            }
        });
    }

    public class LoadRSSFeedItems extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressBar(RSSFeedActivity.this, null, android.R.attr.progressBarStyleLarge);


            RelativeLayout relativeLayout = findViewById(R.id.relativeLayout);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            pDialog.setLayoutParams(lp);
            pDialog.setVisibility(View.VISIBLE);
            relativeLayout.addView(pDialog);
        }

        @Override
        protected String doInBackground(String... args) {
            // rss link url
            String rss_url = args[0];

            // list of rss items
            rssItems = rssParser.getRSSFeedItems(rss_url);

            // looping through each item
            for (RSSItem item : rssItems) {
                // creating new HashMap
                if (item.link.toString().equals(""))
                    break;
                HashMap<String, String> map = new HashMap<String, String>();

                //Aquí colocas tu objeto tipo Date
                Date myDate = new Date();

                //Aquí obtienes el formato que deseas
                item.pubdate =  new SimpleDateFormat("d MMM yyyy").format(myDate);


                items.add(new RSSItem(item.title,item.description,item.url));

                map.put(TAG_URL, item.url);
                map.put(TAG_TITLE, item.title);
                map.put(TAG_LINK, item.link);
                map.put(TAG_PUB_DATE, "contenido actualizado correctamente el " + item.pubdate); // If you want parse the date
                //map.put(TAG_ENCLOSURE, item.enclosure);


                // adding HashList to ArrayList
                rssItemList.add(map);

            }


            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    ListAdapter adapter = new SimpleAdapter(
                            RSSFeedActivity.this,
                            rssItemList, R.layout.rss_item_list_row,
                            new String[]{TAG_LINK, TAG_TITLE, TAG_PUB_DATE},
                            new int[]{R.id.page_url, R.id.title, R.id.pub_date});

                    // updating listview
                    setListAdapter(adapter);
                }
            });
            return null;
        }

        protected void onPostExecute(String args) {
            pDialog.setVisibility(View.GONE);
        }
    }
}
