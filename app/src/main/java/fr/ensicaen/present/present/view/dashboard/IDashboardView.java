package fr.ensicaen.present.present.view.dashboard;

import fr.ensicaen.present.present.view.IGenericView;

/**
 * Created by jueast on 09/12/17.
 */

public interface IDashboardView extends IGenericView{

    void goToGenerateCode();

    void goToEnterCode();

    void goToReviewCall();
}
