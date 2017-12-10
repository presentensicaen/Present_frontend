package fr.ensicaen.present.present.splash;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by jueast on 04/12/17.
 */

public class SplashScreenPresnterTest {
    @Mock
    private SplashScreenActivity _mock;

    private SplashPresenter _presenter;

    @Before
    public void setup(){
        _mock = mock(SplashScreenActivity.class);
        _presenter = new SplashPresenter(_mock);
    }

    @Test
    public void testCheckTokenValidity(){
        _presenter.checkTokenValidity();
        verify(_mock, times(1)).openLoginActivity();
        verify(_mock, times(1)).finish();
    }
}
