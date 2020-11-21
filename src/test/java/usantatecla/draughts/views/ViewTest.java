package usantatecla.draughts.views;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import usantatecla.draughts.controllers.InteractorController;
import usantatecla.draughts.controllers.PlayController;
import usantatecla.draughts.controllers.ResumeController;
import usantatecla.draughts.controllers.StartController;
import usantatecla.draughts.utils.Console;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ViewTest {

    private static final String TITLE = "Draughts";

    @Mock
    private Console console;

    @Mock
    private StartController startController;

    @Mock
    private GameView gameView;

    @Mock
    private PlayView playView;

    @Mock
    private ResumeView resumeView;

    @InjectMocks
    private View view;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.view = spy(this.view);
    }

    @Test
    public void testAccept() {

        InteractorController controller = mock(InteractorController.class);
        view.interact(controller);
        verify(controller).accept(eq(view));

    }

    @Test
    public void testInteractStartController() {

        when(view.createGameView()).thenReturn(gameView);

        view.visit(startController);

        verify(console).writeln(eq(TITLE));
        verify(gameView).write(eq(startController));
        verify(startController).start();

    }

    @Test
    public void testInteractPlayController() {

        PlayController playController = mock(PlayController.class);
        view.visit(playController);
        verify(playView).interact(eq(playController));

    }

    @Test
    public void testInteractResumeController() {

        ResumeController resumeController = mock(ResumeController.class);
        view.visit(resumeController);
        verify(resumeView).interact(eq(resumeController));

    }

}
