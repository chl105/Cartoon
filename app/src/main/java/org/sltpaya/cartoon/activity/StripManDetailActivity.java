package org.sltpaya.cartoon.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.TextView;
import org.sltpaya.cartoon.R;

/**
 * Author: SLTPAYA
 * Date: 2017/3/4
 */
public class StripManDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strip_man_detail);
        Bundle extras = getIntent().getExtras();
        String bookid = extras.getString("bookid", "no");
        TextView mBookid = (TextView) findViewById(R.id.book_id);
        mBookid.setText(bookid);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
