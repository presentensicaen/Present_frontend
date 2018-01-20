package fr.ensicaen.present.present.view;
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

import java.io.IOException;

import fr.ensicaen.present.present.utils.Config;

/*
 * @author The Présent ! Team <easterly@ecole.ensicaen.fr>
 * @version 0.0.1 - 20/01/18
 */
public interface IGenericView {

    void finish();

    void showLoadingAnimation();

    void hideLoadingAnimation();

    void showToast(String message, int toastDuration);

    Config getConfigAccessor() throws IOException;
}
