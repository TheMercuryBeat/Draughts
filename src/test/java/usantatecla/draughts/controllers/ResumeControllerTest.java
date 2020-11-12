package usantatecla.draughts.controllers;

import org.junit.Test;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;

public class ResumeControllerTest extends InteractorControllerTest {

    @InjectMocks
    private ResumeController resumeController;

    @Test
    public void testNextState() {
        resumeController.next();
        verify(state).next();
    }

    @Test
    public void testReset() {
        resumeController.reset();
        verify(state).reset();
        verify(game).reset();
    }

    @Test
    public void testAcceptInteractorControllersVisitor() {
        resumeController.accept(interactorControllersVisitor);
        verify(interactorControllersVisitor).visit(eq(resumeController));
    }

}
