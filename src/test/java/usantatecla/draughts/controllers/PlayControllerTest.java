package usantatecla.draughts.controllers;

import org.junit.Test;
import org.mockito.InjectMocks;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class PlayControllerTest extends InteractorControllerTest {

    @InjectMocks
    private PlayController playController;

    @Test
    public void testGetPlayColor() {
        playController.getColor();
        verify(game).getTurnColor();
    }

    @Test
    public void testIsBlocked() {
        playController.isBlocked();
        verify(game).isBlocked();
    }

    @Test
    public void testAcceptInteractorControllersVisitor() {
        playController.accept(interactorControllersVisitor);
        verify(interactorControllersVisitor).visit(eq(playController));
    }

}
