package usantatecla.draughts.controllers;

import org.junit.Test;
import org.mockito.InjectMocks;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class StartControllerTest extends InteractorControllerTest {

    @InjectMocks
    private StartController startController;

    @Test
    public void testNextState() {
        startController.start();
        verify(state).next();
    }

    @Test
    public void testAcceptInteractorControllersVisitor() {
        startController.accept(interactorControllersVisitor);
        verify(interactorControllersVisitor).visit(eq(startController));
    }

}
