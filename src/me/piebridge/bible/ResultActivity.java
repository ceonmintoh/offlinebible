package me.piebridge.bible;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by thom on 16/7/1.
 */
public class ResultActivity extends BaseActivity {

    public static final String ITEMS = "items";
    public static final String SEARCH = "search";

    private String search;
    private ArrayList<OsisItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        search = intent.getStringExtra(SEARCH);
        items = intent.getParcelableArrayListExtra(ITEMS);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getInitialOsis() {
        return getOsis(0);
    }

    @Override
    protected int getInitialPosition() {
        return 0;
    }

    @Override
    protected int retrieveOsisCount() {
        return items.size();
    }

    @Override
    public Bundle retrieveOsis(int position, String osis) {
        Bundle bundle = super.retrieveOsis(position, osis);
        OsisItem item = items.get(position);
        bundle.putString(VERSE_START, item.verseStart);
        bundle.putString(VERSE_END, item.verseEnd);
        bundle.putString(PREV, getOsis(position - 1));
        bundle.putString(NEXT, getOsis(position + 1));
        bundle.putString(SEARCH, search);
        return bundle;
    }

    private String getOsis(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index).toOsis();
        } else {
            return "";
        }
    }

}