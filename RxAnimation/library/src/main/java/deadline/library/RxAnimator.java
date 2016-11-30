package deadline.library;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.animation.Animation;

import deadline.library.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;

/**
 * @author deadline
 * @time 2016-11-30
 */

public final class RxAnimator {

    @CheckResult
    @NonNull
    public static Observable<Animator> onStart(@NonNull final Animator animator) {
        Preconditions.checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorStartOnSubscribe(animator));
    }

    @CheckResult
    @NonNull
    public static Observable<Animator> onEnd(@NonNull final Animator animator) {
        Preconditions.checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorEndOnSubscribe(animator));
    }

    @CheckResult
    @NonNull
    public static Observable<Animator> onRepeat(@NonNull final Animator animator) {
        Preconditions.checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorRepeatOnSubscribe(animator));
    }

    @CheckResult
    @NonNull
    public static Observable<Animator> onCancel(@NonNull final Animator animator) {
        Preconditions.checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorCancelOnSubscribe(animator));
    }

    @CheckResult
    @NonNull
    public static Observable<Animator> onPause(@NonNull final Animator animator) {
        Preconditions.checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorPauseOnSubscribe(animator));
    }

    @CheckResult
    @NonNull
    public static Observable<Animator> onResume(@NonNull final Animator animator) {
        Preconditions.checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorResumeOnSubscribe(animator));
    }

    @CheckResult
    @NonNull
    public static Observable<ValueAnimator> onUpdate(@NonNull final ValueAnimator animator) {
        Preconditions.checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorUpdateOnSubscribe(animator));
    }
}
