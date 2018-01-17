package fr.ensicaen.present.present.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import fr.ensicaen.present.present.models.UserModel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by jueast on 04/12/17.
 */

public class LoginActivityPresenterTest {

    @Mock
    private LoginActivity _view;


    private LoginActivityPresenter _presenter;

    @Before
    public void setup(){
        _view = mock(LoginActivity.class);
        _presenter = new LoginActivityPresenter(_view);
    }

    //@TODO TEST


    @Test
    public void testOnWindoFocusChangedWhenHasFocusFalse() throws Exception {
        assertFalse(_presenter.onWindowFocusChanged(false));

    }

    @Test
    public void testOnWindoFocusChangedWhenHasFocusTrue() throws Exception {
        assertTrue(_presenter.onWindowFocusChanged(true));
    }

    @Test
    public void isUserValidTestFalse() {
        _presenter.setUser(null);
        assertFalse(_presenter.isUserValidated());
    }

    @Test
    public void isUserValidTestTrue() {
        _presenter.setUser(new UserModel("Julian", "Easterly", "id"));
        assertTrue(_presenter.isUserValidated());
    }
}
