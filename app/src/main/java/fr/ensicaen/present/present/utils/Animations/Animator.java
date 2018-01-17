package fr.ensicaen.present.present.utils.Animations;

import android.view.View;

/**
 * Created by jueast on 04/12/17.
 */

public class Animator implements IAnimation {

    private AbstractAnimationCOR _root;

    public Animator() {
        _root = new ButtonAnimation();
        _root.setSuccessor(new TextInputAnimation());
    }

    @Override
    public void animate(View v, int delay) {
        _root.processRequest(v, delay);
    }

}
