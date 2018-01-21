package fr.ensicaen.present.present.presenter.choosepreviouscall;
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

import fr.ensicaen.present.present.models.PreviousCallModel;

/*
 * @author The Présent ! Team <easterly@ecole.ensicaen.fr>
 * @version 0.0.1 - 21/01/18
 */
public interface IChoosePreviousCallPresenter {

    public void loadCallResults(int id);

    public void loadPreviousCalls();

    void onPrevousCallClick(PreviousCallModel previousCall);
}
