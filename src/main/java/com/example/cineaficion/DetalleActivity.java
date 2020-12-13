package com.example.cineaficion;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity {


    static ImageView imagen;
    static TextView titulo,descripcion;
    static String url,tit,desc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        imagen = findViewById(R.id.imageView);
        titulo = findViewById(R.id.textTitle);
        descripcion = findViewById(R.id.textDetalle);

        Intent in = getIntent();
        url = in.getStringExtra("imagenUrl");
        tit = in.getStringExtra("tituloTexto");
        desc = in.getStringExtra("detalleDescripcion");

        Picasso.get().load(url).into(imagen);
        titulo.setText(tit);
        descripcion.setText(Html.fromHtml(desc,Html.FROM_HTML_MODE_LEGACY));
        descripcion.setMovementMethod(LinkMovementMethod.getInstance());
        //descripcion.setLinksClickable(true);

    }

        // PARA MOSTRAR EL LINK Y VISIONAR LA PAG WEB
        /*
        Intent in = getIntent();
        url = in.getStringExtra("url");

        if (TextUtils.isEmpty(url)) {
            Toast.makeText(getApplicationContext(), "URL not found", Toast.LENGTH_SHORT).show();
            finish();
        }

        webView = findViewById(R.id.webView);
        initWebView();
        webView.loadUrl(url);
    }

    private void initWebView() {
        webView.setWebChromeClient(new MyWebChromeClient(this));
        webView.clearCache(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                invalidateOptionsMenu();
            }
        });
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
    }

    private class MyWebChromeClient extends WebChromeClient {
        Context context;

        public MyWebChromeClient(Context context) {
            super();
            this.context = context;
        }
    }
*/
}


