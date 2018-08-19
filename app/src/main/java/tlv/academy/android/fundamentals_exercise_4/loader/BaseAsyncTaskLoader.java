package tlv.academy.android.fundamentals_exercise_4.loader;

import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

public abstract class BaseAsyncTaskLoader<D> extends AsyncTaskLoader<D> {

    private D mData;
    private boolean mLoaded;

    public BaseAsyncTaskLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mLoaded) {
            deliverResult(mData);
        } else {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(D data) {
        mLoaded = true;
        mData = data;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        mLoaded = false;
        mData = null;
        super.onReset();
    }
}
