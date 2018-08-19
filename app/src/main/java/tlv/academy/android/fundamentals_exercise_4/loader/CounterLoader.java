package tlv.academy.android.fundamentals_exercise_4.loader;

import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.NonNull;

public class CounterLoader extends BaseAsyncTaskLoader<Integer> {

    public CounterLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    public Integer loadInBackground() {
        int end = 10;
        for (int i = 0; i <= end; i++) {
            if (isAbandoned()) {
                return i;
            }
            SystemClock.sleep(500);
        }

        return end;
    }
}
