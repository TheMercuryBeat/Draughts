package usantatecla.draughts.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class CancelControllerTest extends ControllerTest {

    private CancelController cancelController;

    @Before
    public void beforeCancelController() {
        Mockito.reset(game, state);
        cancelController = new CancelController(game, state);
    }

    @Test
    public void testCancel() {
        cancelController.cancel();
        verify(game, atLeastOnce()).cancel();
        verify(state, atLeastOnce()).next();
    }

}
