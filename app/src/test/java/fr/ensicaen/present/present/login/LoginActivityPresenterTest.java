package fr.ensicaen.present.present.login;

import android.os.Handler;
import android.os.Message;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jueast on 04/12/17.
 */

public class LoginActivityPresenterTest {

    @Mock
    private LoginActivity _mock;

    @Mock
    private Handler _handler;

    private LoginActivityPresenter _presenter;

    @Before
    public void setup(){
        _mock = mock(LoginActivity.class);
        _presenter = new LoginActivityPresenter(_mock);


    }



    @Test
    public void testOnWindoFocusChangedWhenHasFocusFalse() throws Exception {
        assertFalse(_presenter.onWindowFocusChanged(false));
    }


}
