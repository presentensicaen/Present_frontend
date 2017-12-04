package fr.ensicaen.present.present.utils.Animations;

import android.view.View;

/**
 * Created by jueast on 04/12/17.
 */

public abstract class AbstractAnimationCOR implements IAnimation {

    private AbstractAnimationCOR _successor;

    abstract protected boolean viewIsOfThisType(View v);

    public void setSuccessor(AbstractAnimationCOR successor){
        _successor = successor;
    }

    public void processRequest(View v, int delay){
        if(viewIsOfThisType(v)){
            animate(v, delay);
        }else {
            _successor.processRequest(v, delay);
        }
    }



}
