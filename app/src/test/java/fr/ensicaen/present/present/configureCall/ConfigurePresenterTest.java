package fr.ensicaen.present.present.configureCall;

import org.mockito.Mock;
import android.os.Handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import fr.ensicaen.present.present.models.CallModel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by pierr on 17/01/2018.
 */

public class ConfigurePresenterTest {

    @Mock
    private ConfigureCallActivity _view;

    @Mock
    private Handler _handler;

    private ConfigurePresenter _presenter;

    @Before
    public void setup(){
        _view = mock(ConfigureCallActivity.class);
        _presenter = new ConfigurePresenter(_view, createMockHandler());
    }

    private Handler createMockHandler() {
        Handler handler = mock(Handler.class);
        when(handler.postDelayed(any(Runnable.class), anyLong())).thenAnswer(invocation -> {
            ((Runnable) invocation.getArgument(0)).run();
            return null;
        });

        return handler;
    }
/*
    @Test
    public void testPayload(){
        CallModel c = new CallModel("CODE1");
        System.out.print(c.toString());
        assertTrue(true);
    }*/

    @Test
    public void test(){
        _presenter.createCall();
        System.out.println(_presenter.getCode());

        assertTrue(true);
    }

}
