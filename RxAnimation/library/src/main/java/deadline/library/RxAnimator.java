package deadline.library;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import deadline.library.internal.Preconditions;
import rx.Observable;

/**
 * @author deadline
 * @time 2016-11-30
 */

public class RxAnimator {

    @CheckResult
    @NonNull
    public static Observable<Animator> onStart(@NonNull Animator animator) {
        Preconditions.checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorStartOnSubscribe(animator));
    }

    @CheckResult
    @NonNull
    public static Observable<Animator> onEnd(@NonNull Animator animator) {
        Preconditions.checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorEndOnSubscribe(animator));
    }

    @CheckResult
    @NonNull
    public static Observable<Animator> onRepeat(@NonNull Animator animator) {
        Preconditions.checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorRepeatOnSubscribe(animator));
    }

    @CheckResult
    @NonNull
    public static Observable<Animator> onCancel(@NonNull Animator animator) {
        Preconditions.checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorCancelOnSubscribe(animator));
    }

    @CheckResult
    @NonNull
    public static Observable<Animator> onPause(@NonNull Animator animator) {
        Preconditions.checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorPauseOnSubscribe(animator));
    }

    @CheckResult
    @NonNull
    public static Observable<Animator> onResume(@NonNull Animator animator) {
        Preconditions.checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorResumeOnSubscribe(animator));
    }

    @CheckResult
    @NonNull
    public static Observable<ValueAnimator> onUpdate(@NonNull ValueAnimator animator) {
        Preconditions.checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorUpdateOnSubscribe(animator));
    }
}
