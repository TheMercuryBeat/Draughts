package usantatecla.draughts.controllers;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class StartControllerTest extends InteractorControllerTest {

    private StartController startController;

    @Before
    public void beforeStartController() {
        startController = new StartController(game, state);
    }

    @Test
    public void testNextState() {
        startController.start();
        verify(state, atLeastOnce()).next();
    }

    @Test
    public void testAcceptInteractorControllersVisitor() {
        startController.accept(interactorControllersVisitor);
        verify(interactorControllersVisitor, atLeastOnce()).visit(eq(startController));
    }

}
