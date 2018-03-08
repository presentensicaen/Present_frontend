package fr.ensicaen.present.present.view.subscribe;

import java.io.IOException;

import fr.ensicaen.present.present.utils.Config;

/**
 * Created by leymarie on 07/03/18.
 */

public interface ISubscribeView {
    public Config getConfigAccessor() throws IOException;
}
