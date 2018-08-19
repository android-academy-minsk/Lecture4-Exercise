package tlv.academy.android.fundamentals_exercise_4.loader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tlv.academy.android.fundamentals_exercise_4.R;

public class LoaderActivity extends AppCompatActivity
        implements View.OnClickListener, LoaderManager.LoaderCallbacks<Integer> {

    private static final int LOADER_COUNTER = 1;

    private TextView mTxtValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        mTxtValue = findViewById(R.id.txtAsyncValue);
        Button btnStart = findViewById(R.id.btnAsyncStart);
        Button btnCancel = findViewById(R.id.btnAsyncCancel);

        btnStart.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(@NonNull View v) {
        switch (v.getId()) {

            case R.id.btnAsyncStart:
                doAsyncTaskStart();
                break;

            case R.id.btnAsyncCancel:
                doAsyncTaskCancel();
                break;
        }
    }

    private void doAsyncTaskStart() {
        getSupportLoaderManager().initLoader(LOADER_COUNTER, null, this);
    }

    private void doAsyncTaskCancel() {
        getSupportLoaderManager().destroyLoader(LOADER_COUNTER);
    }

    @NonNull
    @Override
    public Loader<Integer> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == LOADER_COUNTER) {
            return new CounterLoader(this);
        } else {
            throw new IllegalArgumentException("Unknown loader id");
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Integer> loader, Integer data) {
        if (loader.getId() == LOADER_COUNTER) {
            Toast.makeText(this, getString(R.string.msg_postexecute), Toast.LENGTH_SHORT).show();
            mTxtValue.setText(R.string.done);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Integer> loader) {
        if (loader.getId() == LOADER_COUNTER) {
            mTxtValue.setText(null);
        }
    }

    public static void start(@NonNull Context context) {
        context.startActivity(new Intent(context, LoaderActivity.class));
    }
}



