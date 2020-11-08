package usantatecla.draughts.controllers;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ResumeControllerTest extends InteractorControllerTest {

    private ResumeController resumeController;

    @Before
    public void beforeResumeController() {
        resumeController = new ResumeController(game, state);
    }

    @Test
    public void testNextState() {
        resumeController.next();
        verify(state, atLeastOnce()).next();
    }

    @Test
    public void testReset() {
        resumeController.reset();
        verify(state, atLeastOnce()).reset();
        verify(game, atLeastOnce()).reset();
    }

    @Test
    public void testAcceptInteractorControllersVisitor() {
        resumeController.accept(interactorControllersVisitor);
        verify(interactorControllersVisitor, atLeastOnce()).visit(eq(resumeController));
    }

}
