package usantatecla.draughts.views;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import usantatecla.draughts.controllers.PlayController;
import usantatecla.draughts.models.Coordinate;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PlayViewTest extends SubViewTest {

    private static final String ERROR_MESSAGE = "Error!!! Formato incorrecto";
    private static final String LOST_MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";

    private PlayController playController;

    @InjectMocks
    private PlayView playView;

    @Before
    public void beforeGameView() {
        MockitoAnnotations.initMocks(this);
        playController = spy(new PlayController(new Game(), new State()));
    }

    @Test
    public void testPlayCancel() {

        when(console.readString(anyString())).thenReturn(getCoordinates("-1"));
        doNothing().when(playController).cancel();

        playView.interact(playController);
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

        playView.interact(playController);

        verify(console, times(7)).writeln(eq(ERROR_MESSAGE));
        verify(console, never()).writeln(eq(LOST_MESSAGE));
    }

    @Test
    public void testPlayBlocked() {

        when(console.readString(anyString())).thenReturn(getCoordinates("11.22"));
        doReturn(noError()).when(playController).move(any(Coordinate.class), any(Coordinate.class));
        when(playController.isBlocked()).thenReturn(true);

        playView.interact(playController);
        verify(console).writeln(eq(LOST_MESSAGE));

    }

    @Test
    public void testPlayWithTwoCoordinates() {

        when(console.readString(anyString())).thenReturn(getCoordinates("11.22"));
        doReturn(noError()).when(playController).move(any(Coordinate.class), any(Coordinate.class));
        when(playController.isBlocked()).thenReturn(false);

        playView.interact(playController);
        verify(console, never()).writeln(eq(LOST_MESSAGE));

    }

    @Test
    public void testPlayWithThreeCoordinates() {

        when(console.readString(anyString())).thenReturn(getCoordinates("11.22.33"));
        doReturn(noError()).when(playController).move(any(Coordinate.class), any(Coordinate.class), any(Coordinate.class));
        when(playController.isBlocked()).thenReturn(false);

        playView.interact(playController);
        verify(console, never()).writeln(eq(LOST_MESSAGE));

    }

    private String getCoordinates(String coordinates) {
        return coordinates;
    }

    private Error noError() {
        return null;
    }
}
