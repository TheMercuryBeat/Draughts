package usantatecla.draughts.views;

import org.junit.Before;
import org.junit.Ignore;
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

@Ignore
public class ViewTest {

    @Mock
    private Console console;

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

        GameView gameView = mock(GameView.class);
        StartController startController = mock(StartController.class);

        when(view.createGameView()).thenReturn(gameView);

        view.visit(startController);

        verify(console).writeln(anyString());
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
