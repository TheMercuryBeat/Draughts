package usantatecla.draughts.controllers;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class PlayControllerTest extends InteractorControllerTest {

    private PlayController playController;

    @Before
    public void beforeResumeController() {
        playController = new PlayController(game, state);
    }

    @Test
    public void testGetPlayColor() {
        playController.getColor();
        verify(game, atLeastOnce()).getTurnColor();
    }

    @Test
    public void testIsBlocked() {
        playController.isBlocked();
        verify(game, atLeastOnce()).isBlocked();
    }

    @Test
    public void testAcceptInteractorControllersVisitor() {
        playController.accept(interactorControllersVisitor);
        verify(interactorControllersVisitor, atLeastOnce()).visit(eq(playController));
    }

}
