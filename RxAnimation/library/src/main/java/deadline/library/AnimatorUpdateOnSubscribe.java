package deadline.library;

import android.animation.ValueAnimator;
import android.gesture.Prediction;

import deadline.library.internal.Preconditions;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/**
 * @author deadline
 * @time 2016/11/30
 */
final class AnimatorUpdateOnSubscribe implements Observable.OnSubscribe<ValueAnimator>{

    final ValueAnimator animator;

    AnimatorUpdateOnSubscribe(ValueAnimator animator){
        this.animator = animator;
    }

    @Override
    public void call(final Subscriber<? super ValueAnimator> subscriber) {

        Preconditions.checkUiThread();
        final ValueAnimator.AnimatorUpdateListener listener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(valueAnimator);
                }
            }
        };

        animator.addUpdateListener(listener);

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                animator.removeUpdateListener(listener);
            }
        });
    }
}
