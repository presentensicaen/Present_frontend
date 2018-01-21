package fr.ensicaen.present.present.view.choosecalltype;

import fr.ensicaen.present.present.view.IGenericView;

/**
 * Created by Jeanne on 25/12/2017.
 */

public interface IChooseCallTypeView extends IGenericView{
    void initializeGenerateCodeActivity();

    void goToLaunchCall();

    void goToSelectGroup();
}
