package tlv.academy.android.fundamentals_exercise_4.counterasynctask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tlv.academy.android.fundamentals_exercise_4.simpleasynctask.IAsyncTaskEvents;
import tlv.academy.android.fundamentals_exercise_4.R;

public class AsyncTaskActivity extends Activity implements IAsyncTaskEvents, View.OnClickListener {

    private TextView mTxtValue;

    private CounterAsyncTask mAsyncTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        mTxtValue = findViewById(R.id.txtAsyncValue);

        Button btnCreate = findViewById(R.id.btnAsyncCreate);
        Button btnStart = findViewById(R.id.btnAsyncStart);
        Button btnCancel = findViewById(R.id.btnAsyncCancel);

        btnCreate.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(@NonNull View v) {
        switch (v.getId()) {
            case R.id.btnAsyncCreate:
                doAsyncTaskCreate();
                break;

            case R.id.btnAsyncStart:
                doAsyncTaskStart();
                break;

            case R.id.btnAsyncCancel:
                doAsyncTaskCancel();
                break;
        }
    }

    private void doAsyncTaskCreate() {
        Toast.makeText(this, getString(R.string.msg_oncreate), Toast.LENGTH_SHORT).show();
        mAsyncTask = new CounterAsyncTask(this);
    }


    private void doAsyncTaskStart() {
        if ((mAsyncTask == null) || (mAsyncTask.isCancelled())) {
            Toast.makeText(this, R.string.msg_should_create_task, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.msg_onstart), Toast.LENGTH_SHORT).show();
            mAsyncTask.execute(1, 10);
        }
    }

    private void doAsyncTaskCancel() {
        mAsyncTask.cancel(true);
    }


    /***
     // IAsyncTaskEvent's methods - start:
     ***/

    @Override
    public void onPreExecute() {
        Toast.makeText(this, getString(R.string.msg_preexecute), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostExecute() {
        Toast.makeText(this, getString(R.string.msg_postexecute), Toast.LENGTH_SHORT).show();
        mTxtValue.setText(R.string.done);
    }

    @Override
    public void onProgressUpdate(Integer integer) {
        mTxtValue.setText(String.valueOf(integer));
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, getString(R.string.msg_oncancel), Toast.LENGTH_SHORT).show();
    }

    /***
     //  IAsyncTaskEvent's methods - end
     ***/


    @Override
    protected void onDestroy() {
        if (mAsyncTask != null) {
            mAsyncTask.cancel(false);
            mAsyncTask = null;
        }
        super.onDestroy();
    }

    public static void start(@NonNull Context context) {
        context.startActivity(new Intent(context, AsyncTaskActivity.class));
    }
}
