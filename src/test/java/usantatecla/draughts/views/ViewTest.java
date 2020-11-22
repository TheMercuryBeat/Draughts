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
import usantatecla.draughts.models.Coordinate;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;
import usantatecla.draughts.utils.Console;
import usantatecla.draughts.utils.YesNoDialog;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ViewTest {

    private static final String ERROR_MESSAGE = "Error!!! Formato incorrecto";
    private static final String LOST_MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";
    private PlayController playController;

    @Mock
    private Console console;

    @Mock
    private YesNoDialog yesNoDialog;

    @InjectMocks
    private View view;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.view = spy(this.view);
        this.playController = spy(new PlayController(new Game(), new State()));
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
    public void testPlayCancel() {

        when(console.readString(anyString())).thenReturn(getCoordinates("-1"));
        doNothing().when(playController).cancel();

        view.visit(playController);
        verify(playController).cancel();

    }

    @Test
    public void testPlayFormatIncorrect() {

        when(console.readString(anyString()))
                .thenReturn(getCoordinates("1"))
                .thenReturn(getCoordinates("11"))
                .thenReturn(getCoordinates("11"))
                .thenReturn(getCoordinates("00.11"))
                .thenReturn(getCoordinates("11.aa"))
                .thenReturn(getCoordinates("1b.2a"))
                .thenReturn(getCoordinates("1b2a"))
                .thenReturn(getCoordinates("11.22"));

        doReturn(noError()).when(playController).move(any(Coordinate.class), any(Coordinate.class));
        when(playController.isBlocked()).thenReturn(false);

        view.interact(playController);

        verify(console, times(7)).writeln(eq(ERROR_MESSAGE));
        verify(console, never()).writeln(eq(LOST_MESSAGE));
    }

    @Test
    public void testPlayBlocked() {

        when(console.readString(anyString())).thenReturn(getCoordinates("11.22"));
        doReturn(noError()).when(playController).move(any(Coordinate.class), any(Coordinate.class));
        when(playController.isBlocked()).thenReturn(true);

        view.interact(playController);
        verify(console).writeln(eq(LOST_MESSAGE));

    }

    @Test
    public void testPlayWithTwoCoordinates() {

        when(console.readString(anyString())).thenReturn(getCoordinates("11.22"));
        doReturn(noError()).when(playController).move(any(Coordinate.class), any(Coordinate.class));
        when(playController.isBlocked()).thenReturn(false);

        view.interact(playController);
        verify(console, never()).writeln(eq(LOST_MESSAGE));

    }

    @Test
    public void testPlayWithThreeCoordinates() {

        when(console.readString(anyString())).thenReturn(getCoordinates("11.22.33"));
        doReturn(noError()).when(playController).move(any(Coordinate.class), any(Coordinate.class), any(Coordinate.class));
        when(playController.isBlocked()).thenReturn(false);

        view.interact(playController);
        verify(console, never()).writeln(eq(LOST_MESSAGE));

    }

    @Test
    public void testResumeResetGame() {

        ResumeController resumeController = mock(ResumeController.class);

        when(this.yesNoDialog.read(anyString())).thenReturn(true);
        view.visit(resumeController);
        verify(resumeController).reset();

    }

    @Test
    public void testResumeFinishGame() {

        ResumeController resumeController = mock(ResumeController.class);

        when(this.yesNoDialog.read(anyString())).thenReturn(false);
        view.visit(resumeController);
        verify(resumeController).next();

    }

    private String getCoordinates(String coordinates) {
        return coordinates;
    }

    private Error noError() {
        return null;
    }

}
