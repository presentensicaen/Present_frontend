package fr.ensicaen.present.present.presenter.splash;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import fr.ensicaen.present.present.view.splash.SplashScreenActivity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by jueast on 04/12/17.
 */

public class SplashScreenPresnterTest {
    @Mock
    private SplashScreenActivity _view;

    private SplashPresenter _presenter;

    @Before
    public void setup() throws IOException {
        _view = mock(SplashScreenActivity.class);
        _presenter = new SplashPresenter(_view);
    }

    @Test
    public void testCheckTokenValidity() {
        _presenter.checkTokenValidity();
        verify(_view, times(1)).openLoginActivity();
        verify(_view, times(1)).finish();
    }

}
