package deadline.library;

import android.view.animation.Animation;

import deadline.library.internal.Preconditions;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/**
 * @author deadline
 * @time 2016-11-30
 */
final class AnimationEndOnSubscribe implements Observable.OnSubscribe<Animation> {

    final Animation animation;

    AnimationEndOnSubscribe(Animation animation) {
        this.animation = animation;
    }

    @Override
    public void call(final Subscriber<? super Animation> subscriber) {

        Preconditions.checkUiThread();
        final Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        };
        animation.setAnimationListener(listener);

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                animation.setAnimationListener(null);
            }
        });
    }
}
