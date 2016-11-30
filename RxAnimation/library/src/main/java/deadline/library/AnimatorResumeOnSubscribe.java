package deadline.library;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import deadline.library.internal.Preconditions;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/**
 * @author deadline
 * @time 2016-11-30
 */
final class AnimatorResumeOnSubscribe implements Observable.OnSubscribe<Animator> {

    final Animator animator;

    AnimatorResumeOnSubscribe(Animator animator) {
        this.animator = animator;
    }

    @Override
    public void call(final Subscriber<? super Animator> subscriber) {

        Preconditions.checkUiThread();

        final AnimatorListenerAdapter adapter = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
                if(!subscriber.isUnsubscribed()){
                    subscriber.onNext(animation);
                }
            }
        };

        animator.addListener(adapter);

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                animator.removeListener(adapter);
            }
        });
    }
}
