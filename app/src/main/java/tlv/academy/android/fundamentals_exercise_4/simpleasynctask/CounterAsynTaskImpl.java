package tlv.academy.android.fundamentals_exercise_4.simpleasynctask;

import android.os.SystemClock;
import android.support.annotation.NonNull;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import tlv.academy.android.fundamentals_exercise_4.simpleasynctask.IAsyncTaskEvents;
import tlv.academy.android.fundamentals_exercise_4.simpleasynctask.MySimpleAsyncTask;

/**
 * Created by britt on 11/20/17.
 */

public class CounterAsynTaskImpl extends MySimpleAsyncTask<Integer> {

    @NonNull
    private final Reference<IAsyncTaskEvents> mIAsyncTaskEventsRef;

    public CounterAsynTaskImpl(@NonNull IAsyncTaskEvents asyncTaskEvents) {
        mIAsyncTaskEventsRef = new WeakReference<>(asyncTaskEvents);
    }

    @Override
    protected Integer doInBackground() {
        final int end = 10;
        for (int i = 0; i <= end; i++) {
            if (isCancelled()) {
                return i;
            }

            publishProgress(i);
            SystemClock.sleep(500);
        }
        return end;
    }

    @Override
    protected void onPreExecute() {
        IAsyncTaskEvents asyncTaskEvents = mIAsyncTaskEventsRef.get();
        if (asyncTaskEvents != null) {
            asyncTaskEvents.onPreExecute();
        }
    }

    @Override
    protected void onPostExecute() {
        IAsyncTaskEvents asyncTaskEvents = mIAsyncTaskEventsRef.get();
        if (asyncTaskEvents != null) {
            asyncTaskEvents.onPostExecute();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        IAsyncTaskEvents asyncTaskEvents = mIAsyncTaskEventsRef.get();
        if (asyncTaskEvents != null) {
            asyncTaskEvents.onProgressUpdate(values[0]);
        }
    }
}
