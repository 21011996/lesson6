package md.ifmo.ru.rss2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by Илья on 16.01.2015.
 */

public class DescriptionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Intent intent = getIntent();
        setTitle(intent.getStringExtra(FeedActivity.TITLE_EXTRA));
        WebView wv = ((WebView) findViewById(R.id.web_view));
        wv.getSettings().setDefaultTextEncodingName("utf-8");
        wv.loadDataWithBaseURL(null,getIntent().getStringExtra(FeedActivity.DESCRIPTION_EXTRA), "text/html",
                "en_US",null);
    }
}
