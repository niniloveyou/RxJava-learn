package deadline.rxanimation;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import deadline.library.RxAnimator;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(500);
        RxAnimator.onUpdate(animator)
                .map(new Func1<ValueAnimator, Float>() {
            @Override
            public Float call(ValueAnimator animator) {
                return (Float) animator.getAnimatedValue();
            }
        }).subscribe(new Action1<Float>() {
            @Override
            public void call(Float value) {
                //do something
            }
        });
        animator.start();
    }
}
