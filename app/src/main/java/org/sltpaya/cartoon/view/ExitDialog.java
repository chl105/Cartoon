package org.sltpaya.cartoon.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.TextView;
import org.sltpaya.cartoon.R;

/**
 * Author: SLTPAYA
 * Date: 2017/3/8
 */
public class ExitDialog extends Dialog implements View.OnClickListener {

    public static int POSITIVE_BUTTON = 1;
    public static int NEGATIVE_BUTTON = 2;
    private OnClickListener mPositiveListener;
    private OnClickListener mNegativeListener;
    private TextView mDialogTitle;
    private TextView mDialogMessage;

    public ExitDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exit_dialog);
        setCanceledOnTouchOutside(false);
        initViews();
    }

    private void initViews() {
        View positiveButton = findViewById(R.id.dialog_false);
        View negativeButton = findViewById(R.id.dialog_true);
        mDialogTitle = (TextView) findViewById(R.id.dialog_title);
        mDialogMessage = (TextView) findViewById(R.id.dialog_message);
        positiveButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);
    }

    public ExitDialog setPositiveButton(OnClickListener positiveListener) {
        mPositiveListener = positiveListener;
        return this;
    }

    public ExitDialog setNegativeButton(OnClickListener negativeListener) {
        mNegativeListener = negativeListener;
        return this;
    }

    public ExitDialog setMessage(CharSequence message) {
        mDialogMessage.setText(message);
        return this;
    }

    public ExitDialog setDialogTitle(CharSequence title) {
        mDialogTitle.setText(title);
        return this;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (mPositiveListener != null && mNegativeListener != null) {
            if (id == R.id.dialog_true) {
                mPositiveListener.onClick(this, POSITIVE_BUTTON);
            }else {
                mNegativeListener.onClick(this, NEGATIVE_BUTTON);
            }
        }else {
            if (id == R.id.dialog_true) {
                this.dismiss();
                SystemClock.sleep(200);
                System.exit(0);
            }else {
                this.dismiss();
            }
        }
    }

}
