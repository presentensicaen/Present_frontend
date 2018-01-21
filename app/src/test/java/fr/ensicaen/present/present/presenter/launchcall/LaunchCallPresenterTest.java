package fr.ensicaen.present.present.presenter.launchcall;

import android.widget.Toast;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import fr.ensicaen.present.present.models.CallModel;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.view.launchcall.LaunchCallActivity;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import okhttp3.mockwebserver.MockWebServer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by pierr on 17/01/2018.
 */

public class LaunchCallPresenterTest {


    @Mock
    private LaunchCallActivity _view;

    @Mock
    private Config _config;

    private MockWebServer _webServer;

    private LaunchCallPresenter _presenter;

    @Before
    public void setup() throws IOException {
        _view = mock(LaunchCallActivity.class);
        _config = mock(Config.class);
        _presenter = spy(new LaunchCallPresenter(_view, _config));
        _webServer = new MockWebServer();
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @After
    public void teardown() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(null);
    }


    @Test
    public void testOnVerificationCompleteError() {
        when(_presenter.isCallCreated()).thenReturn(false);
        _presenter.onVerificationComplete();
        verify(_view, times(1)).showToast("Erreur lors de la création", Toast.LENGTH_SHORT);
    }

    @Test
    public void testOnVerificationCompleteSuccess() {
        CallModel _call = new CallModel("CODE");
        _presenter.setCall(_call);
        when(_presenter.isCallCreated()).thenReturn(true);
        _presenter.onVerificationComplete();
        verify(_view, times(1)).setSuccessMessage(_call.getCode());
    }
}
