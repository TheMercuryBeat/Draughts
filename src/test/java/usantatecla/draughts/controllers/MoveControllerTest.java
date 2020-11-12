package usantatecla.draughts.controllers;

import org.junit.Test;
import org.mockito.InjectMocks;
import usantatecla.draughts.models.Coordinate;
import usantatecla.draughts.models.Error;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class MoveControllerTest extends ControllerTest {

    @InjectMocks
    private MoveController moveController;

    @Test
    public void testMoveWithoutError() {

        Coordinate originCoordinate = new Coordinate(5, 5);
        Coordinate targetCoordinate = new Coordinate(6, 6);

        when(game.move(originCoordinate, targetCoordinate)).thenReturn(null);
        when(game.isBlocked()).thenReturn(false);

        Error error = moveController.move(originCoordinate, targetCoordinate);
        assertNull(error);

        verifyZeroInteractions(state);

    }

    @Test
    public void testMoveWithError() {

        Coordinate originCoordinate = new Coordinate(5, 5);
        Coordinate targetCoordinate = new Coordinate(6, 6);

        when(game.move(originCoordinate, targetCoordinate)).thenReturn(Error.BAD_FORMAT);
        when(game.isBlocked()).thenReturn(false);

        Error error = moveController.move(originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.BAD_FORMAT)));

        verifyZeroInteractions(state);

    }

    @Test
    public void testMoveBlocked() {

        Coordinate originCoordinate = new Coordinate(5, 5);
        Coordinate targetCoordinate = new Coordinate(6, 6);

        when(game.move(originCoordinate, targetCoordinate)).thenReturn(null);
        when(game.isBlocked()).thenReturn(true);

        Error error = moveController.move(originCoordinate, targetCoordinate);
        assertNull(error);

        verify(state).next();
    }

    @Test
    public void testMoveWithErrorAndBlocked() {

        Coordinate originCoordinate = new Coordinate(5, 5);
        Coordinate targetCoordinate = new Coordinate(6, 6);

        when(game.move(originCoordinate, targetCoordinate)).thenReturn(Error.BAD_FORMAT);
        when(game.isBlocked()).thenReturn(true);

        Error error = moveController.move(originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.BAD_FORMAT)));

        verify(state).next();
    }
}
