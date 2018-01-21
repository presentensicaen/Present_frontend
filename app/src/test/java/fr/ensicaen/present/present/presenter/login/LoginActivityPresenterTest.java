package fr.ensicaen.present.present.presenter.login;

import android.widget.Toast;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import fr.ensicaen.present.present.models.UserModel;
import fr.ensicaen.present.present.session.SessionManager;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.view.login.LoginActivity;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jueast on 04/12/17.
 */

public class LoginActivityPresenterTest {


    @Mock
    private LoginActivity _view;

    @Mock
    private Config _config;

    @Mock
    private SessionManager _session;

    private MockWebServer _webServer;

    private LoginActivityPresenter _presenter;


    @Before
    public void setup() throws IOException {
        _view = mock(LoginActivity.class);
        _config = mock(Config.class);
        _session = mock(SessionManager.class);
        _presenter = spy(new LoginActivityPresenter(_view, _config, _session));
        _webServer = new MockWebServer();
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @After
    public void teardown() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(null);
    }


    @Test
    public void testOnVerificationCompleteError() {
        when(_presenter.isUserValidated()).thenReturn(false);
        _presenter.onVerificationComplete();
        verify(_view, times(1)).hideLoadingAnimation();
        verify(_view, times(1)).showToast("Error : login failed", Toast.LENGTH_SHORT);
    }

    @Test
    public void testOnVerificationCompleteSuccess() {
        _presenter.setUser(new UserModel("Julian", "Easterly", "id"));
        when(_presenter.isUserValidated()).thenReturn(true);
        _presenter.onVerificationComplete();
        verify(_view, times(1)).hideLoadingAnimation();
        verify(_view, times(1)).goToDashboard();
        verify(_view, times(1)).finish();
        verify(_view, times(1)).showToast("Bienvenue " + _presenter.getUser().getDisplayName(), Toast.LENGTH_SHORT);
    }

    @Test
    public void testOnWindoFocusChangedWhenHasFocusFalse() {
        assertFalse(_presenter.onWindowFocusChanged(false));
        verify(_view, times(0)).animate();
    }

    @Test
    public void testOnAnimationFinished() {
        _presenter.onAnimationFinished();
        assertFalse(_presenter.getAnimationStarted());
    }

    @Test
    public void testOnWindoFocusChangedWhenHasFocusTrue() throws Exception {
        assertTrue(_presenter.onWindowFocusChanged(true));
        verify(_view, times(1)).animate();
    }

}
