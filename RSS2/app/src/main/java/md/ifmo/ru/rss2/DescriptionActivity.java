package md.ifmo.ru.rss2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by Илья on 16.01.2015.
 */

public class DescriptionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        final Intent intent = getIntent();
        setTitle(intent.getStringExtra(FeedActivity.TITLE_EXTRA));
        final WebView wv = ((WebView) findViewById(R.id.web_view));
        wv.getSettings().setDefaultTextEncodingName("utf-8");
        wv.loadDataWithBaseURL(null,getIntent().getStringExtra(FeedActivity.DESCRIPTION_EXTRA), "text/html",
                "en_US",null);
        final WebView browser = ((WebView) findViewById(R.id.webView2));
        browser.setWebViewClient(new AppWebViewClient());
        if (intent.getStringExtra(FeedActivity.LINK_EXTRA) != null) {
            browser.loadUrl(intent.getStringExtra(FeedActivity.LINK_EXTRA));
        }
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.getStringExtra(FeedActivity.LINK_EXTRA) != null) {
                    browser.setVisibility(View.VISIBLE);
                    wv.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);
                }
            }
        });
    }

    private class AppWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
}
