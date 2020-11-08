package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {

    private Game game;

    @Before
    public void before() {
        game = new Game();
    }

    @Test
    public void testEmptyOriginMove() {

        Coordinate originCoordinate = new Coordinate(6, 2);
        Coordinate targetCoordinate = new Coordinate(7, 3);

        Error error = game.move(originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.EMPTY_ORIGIN)));

    }

    @Test
    public void testOppositePieceMove() {

        Coordinate originCoordinate = new Coordinate(2, 1);
        Coordinate targetCoordinate = new Coordinate(3, 2);

        Error error = game.move(originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.OPPOSITE_PIECE)));

    }

    @Test
    public void testNotEmptyTargetPieceMove() {

        Coordinate originCoordinate = new Coordinate(5, 4);
        Coordinate targetCoordinate = new Coordinate(4, 3);

        game.move(new Coordinate(5, 2), new Coordinate(4, 3));

        Error error = game.move(originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.OPPOSITE_PIECE)));

    }

    @Test
    public void testMove() {

        Coordinate originCoordinate = new Coordinate(5, 4);
        Coordinate targetCoordinate = new Coordinate(4, 3);

        Error error = game.move(originCoordinate, targetCoordinate);
        assertNull(error);

    }

    @Test
    public void testGetTurnColor() {

        Coordinate originCoordinate = new Coordinate(5, 4);
        Coordinate targetCoordinate = new Coordinate(4, 3);

        game.move(originCoordinate, targetCoordinate);

        Color turnColor = game.getTurnColor();
        assertThat(turnColor, is(equalTo(Color.BLACK)));

    }

    @Test
    public void testGetColorPieceWhenPieceExists() {
        Color color = game.getColor(new Coordinate(5, 4));
        assertThat(color, is(equalTo(Color.WHITE)));
    }

    @Test
    public void testGetColorPieceWhenPieceDoesNotExist() {
        Color color = game.getColor(new Coordinate(5, 5));
        assertNull(color);
    }

    @Test
    public void testGetPieceWhenPieceExists() {
        Piece piece = game.getPiece(new Coordinate(5, 4));
        assertThat(piece, is(equalTo(new Pawn(Color.WHITE))));
    }

    @Test
    public void testGetPieceWhenPieceDoesNotExist() {
        Piece piece = game.getPiece(new Coordinate(5, 5));
        assertNull(piece);
    }

}
