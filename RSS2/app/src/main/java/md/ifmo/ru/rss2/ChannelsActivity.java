package md.ifmo.ru.rss2;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Илья on 16.01.2015.
 */

public class ChannelsActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillData();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ChannelsActivity.this, FeedActivity.class);
                intent.putExtra(FeedActivity.EXTRA_CHANNEL_ID, l);
                startActivity(intent);
            }
        });
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AddingRSS addingRSS = new AddingRSS();
                Bundle args = new Bundle();
                args.putBoolean(AddingRSS.ARG_EDIT, true);
                args.putLong(AddingRSS.ARG_CHANNEL_ID, l);
                Cursor item = (Cursor) adapterView.getItemAtPosition(i);
                args.putString(AddingRSS.ARG_CHANNEL_TITLE, item.getString(item.getColumnIndex(DBAdapter.KEY_CHANNELS_NAME)));
                args.putString(AddingRSS.ARG_CHANNEL_URL, item.getString(item.getColumnIndex(DBAdapter.KEY_CHANNELS_URL)));
                addingRSS.setArguments(args);
                getFragmentManager().beginTransaction().add(addingRSS, "").commit();
                return true;
            }
        });
    }

    private void fillData() {
        getLoaderManager().initLoader(0, null, this);
        setListAdapter(new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null,
                new String[]{DBAdapter.KEY_CHANNELS_NAME, DBAdapter.KEY_CHANNELS_URL},
                new int[]{android.R.id.text1, android.R.id.text2}, 0));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.channels, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            AddingRSS addingRSS = new AddingRSS();
            Bundle args = new Bundle();
            args.putBoolean(AddingRSS.ARG_EDIT, false);
            addingRSS.setArguments(args);
            getFragmentManager().beginTransaction().add(addingRSS, "").commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                FeedContentProvider.CHANNELS_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ((CursorAdapter)getListAdapter()).swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ((CursorAdapter)getListAdapter()).swapCursor(null);
    }
}
