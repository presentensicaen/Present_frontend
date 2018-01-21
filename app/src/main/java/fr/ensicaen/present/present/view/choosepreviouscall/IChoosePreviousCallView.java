package fr.ensicaen.present.present.view.choosepreviouscall;
/* 
 *
 * ENSICAEN
 * 6 Boulevard Marechal Juin
 * F-14050 Caen Cedex
 *
 * This file is owned by The Présent ! Team and created by
 * Quentin DEBROISE, Julian EASTERLY, Jeanne LEYMARIE, Pierre NICOL, and Coline SMAGGHE.
 * No portion of this document may be reproduced, copied
 * or revised without written permission of the authors.
 */

import java.util.ArrayList;

import fr.ensicaen.present.present.models.PreviousCallModel;
import fr.ensicaen.present.present.view.IGenericView;

/*
 * @author The Présent ! Team <easterly@ecole.ensicaen.fr>
 * @version 0.0.1 - 21/01/18
 */
public interface IChoosePreviousCallView extends IGenericView {

    void showPreviousCalls(ArrayList<PreviousCallModel> prevousCalls);

    void goToReviewActivity(PreviousCallModel model);
}
