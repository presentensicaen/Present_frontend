package fr.ensicaen.present.present.utils.Animations;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;

/**
 * Created by jueast on 04/12/17.
 */

public class TextInputAnimation extends AbstractAnimationCOR {
    @Override
    protected boolean viewIsOfThisType(View v) {
        return v instanceof EditText;
    }

    @Override
    public void animate(View v, int delay) {
        ViewPropertyAnimatorCompat viewAnimator;
        viewAnimator = ViewCompat.animate(v)
                .translationY(50).alpha(1)
                .setStartDelay(delay + 500)
                .setDuration(1000);
        viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
    }
}
