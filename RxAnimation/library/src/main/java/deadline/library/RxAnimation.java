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

public final class RxAnimation {

    @CheckResult
    @NonNull
    public static Observable<Animation> onStart(@NonNull final Animation animation) {
        Preconditions.checkNotNull(animation, "animation == null");
        return Observable.create(new AnimationStartOnSubscribe(animation));
    }

    @CheckResult
    @NonNull
    public static Observable<Animation> onEnd(@NonNull final Animation animation) {
        Preconditions.checkNotNull(animation, "animation == null");
        return Observable.create(new AnimationEndOnSubscribe(animation));
    }

    @CheckResult
    @NonNull
    public static Observable<Animation> onRepeat(@NonNull final Animation animation) {
        Preconditions.checkNotNull(animation, "animation == null");
        return Observable.create(new AnimationRepeatOnSubscribe(animation));
    }

}
