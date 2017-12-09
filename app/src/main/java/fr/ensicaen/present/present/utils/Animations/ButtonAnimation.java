package fr.ensicaen.present.present.utils.Animations;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

/**
 * Created by jueast on 04/12/17.
 */

public final class ButtonAnimation extends AbstractAnimationCOR {
    @Override
    protected boolean viewIsOfThisType(View v) {
        return v instanceof Button;
    }

    @Override
    public void animate(View v, int delay) {
        ViewPropertyAnimatorCompat viewAnimator;
        viewAnimator = ViewCompat.animate(v)
                .scaleY(1).scaleX(1)
                .setStartDelay(delay + 500)
                .setDuration(500);
        viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
    }
}
