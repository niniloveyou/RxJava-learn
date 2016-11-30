package deadline.library;

import android.os.Looper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.animation.Animation;

import deadline.library.internal.Preconditions;
import rx.Observable;

/**
 * @author deadline
 * @time 2016-11-30
 */

public class RxAnimation {

    @CheckResult
    @NonNull
    public static Observable<Animation> onStart(@NonNull Animation animation) {
        Preconditions.checkNotNull(animation, "animation == null");
        return Observable.create(new AnimationStartOnSubscribe(animation));
    }

    @CheckResult
    @NonNull
    public static Observable<Animation> onEnd(@NonNull Animation animation) {
        Preconditions.checkNotNull(animation, "animation == null");
        return Observable.create(new AnimationEndOnSubscribe(animation));
    }

    @CheckResult
    @NonNull
    public static Observable<Animation> onRepeat(@NonNull Animation animation) {
        Preconditions.checkNotNull(animation, "animation == null");
        return Observable.create(new AnimationRepeatOnSubscribe(animation));
    }



}
