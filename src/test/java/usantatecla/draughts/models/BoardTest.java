package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoardTest {

    private Board board;

    @Before
    public void before() {
        board = new Board();
    }

    @Test
    public void testPutPieceInTheBoardDimensions() {

        //@formatter:off
        String boardWithPiece =
                " 01234567\n" +
                " 0 n      0\n" +
                " 1        1\n" +
                " 2        2\n" +
                " 3        3\n" +
                " 4        4\n" +
                " 5        5\n" +
                " 6        6\n" +
                " 7        7\n" +
                " 01234567\n";
        //@formatter:on

        Pawn pawn = new Pawn(Color.BLACK);
        Coordinate coordinate = new Coordinate(0, 1);
        board.put(coordinate, pawn);

        assertThat(board.toString(), is(equalTo(boardWithPiece)));

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPutPieceOutOfTheBoardDimensions() {
        Pawn pawn = new Pawn(Color.BLACK);
        Coordinate coordinate = new Coordinate(-1, 1);
        board.put(coordinate, pawn);
    }

    @Test
    public void testGetPieceInTheBoardDimensions() {

        Pawn pawn = new Pawn(Color.BLACK);
        Coordinate coordinate = new Coordinate(0, 1);
        board.put(coordinate, pawn);

        Piece piece = board.getPiece(coordinate);
        assertThat(piece, is(equalTo(pawn)));

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetPieceOutOfTheBoardDimensions() {
        Coordinate coordinate = new Coordinate(-1, 1);
        board.getPiece(coordinate);
    }

    @Test
    public void testRemovePieceInTheBoardDimensions() {

        //@formatter:off
        String boardWithPiece =
                " 01234567\n" +
                " 0 n      0\n" +
                " 1        1\n" +
                " 2        2\n" +
                " 3        3\n" +
                " 4        4\n" +
                " 5        5\n" +
                " 6        6\n" +
                " 7        7\n" +
                " 01234567\n";

        String boardWithoutPiece =
                " 01234567\n" +
                " 0        0\n" +
                " 1        1\n" +
                " 2        2\n" +
                " 3        3\n" +
                " 4        4\n" +
                " 5        5\n" +
                " 6        6\n" +
                " 7        7\n" +
                " 01234567\n";
        //@formatter:on

        Pawn pawn = new Pawn(Color.BLACK);
        Coordinate coordinate = new Coordinate(0, 1);

        board.put(coordinate, pawn);
        assertThat(board.toString(), is(equalTo(boardWithPiece)));

        board.remove(coordinate);
        assertThat(board.toString(), is(equalTo(boardWithoutPiece)));

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemovePieceOutOfTheBoardDimensions() {
        Coordinate coordinate = new Coordinate(-1, 1);
        board.remove(coordinate);
    }

    @Test(expected = AssertionError.class)
    public void testRemovePieceIsNotInTheCoordinate() {
        Coordinate coordinate = new Coordinate(0, 1);
        board.remove(coordinate);
    }

    @Test
    public void testMovePieceInTheBoardDimensions() {

        //@formatter:off
        String boardWithPieceMoved =
                " 01234567\n" +
                " 0        0\n" +
                " 1  n     1\n" +
                " 2        2\n" +
                " 3        3\n" +
                " 4        4\n" +
                " 5        5\n" +
                " 6        6\n" +
                " 7        7\n" +
                " 01234567\n";
        //@formatter:on

        Pawn pawn = new Pawn(Color.BLACK);
        Coordinate originCoordinate = new Coordinate(0, 1);
        Coordinate targetCoordinate = new Coordinate(1, 2);

        board.put(originCoordinate, pawn);
        board.move(originCoordinate, targetCoordinate);

        assertThat(board.toString(), is(equalTo(boardWithPieceMoved)));

    }

    @Test
    public void testGetBetweenDiagonalPiecesOutOfTheBoardDimensions() {

        Coordinate originCoordinate = new Coordinate(0, 1);
        Coordinate betweenCoordinate = new Coordinate(1, 2);
        Coordinate targetCoordinate = new Coordinate(2, 3);

        Pawn blackPawn = new Pawn(Color.BLACK);
        Pawn whitePawn = new Pawn(Color.WHITE);

        board.put(originCoordinate, blackPawn);
        board.put(betweenCoordinate, whitePawn);

        List<Piece> betweenDiagonalPieces = board.getBetweenDiagonalPieces(originCoordinate, targetCoordinate);

        assertThat(betweenDiagonalPieces.size(), is(equalTo(1)));
        assertThat(betweenDiagonalPieces.get(0), is(whitePawn));

    }

    @Test
    public void testGetColorPieceWhenPieceExists() {

        Coordinate coordinate = new Coordinate(0, 1);
        board.put(coordinate, new Pawn(Color.BLACK));

        Color color = board.getColor(coordinate);
        assertThat(color, is(equalTo(Color.BLACK)));

    }

    @Test
    public void testGetColorPieceWhenPieceDoesNotExist() {

        Coordinate coordinate = new Coordinate(0, 1);
        Color color = board.getColor(coordinate);
        assertNull(color);

    }

    @Test
    public void testIsEmptyWhenThereIsAnPiece() {
        Coordinate coordinate = new Coordinate(0, 1);
        board.put(coordinate, new Pawn(Color.BLACK));
        assertFalse(board.isEmpty(coordinate));
    }

    @Test
    public void testIsEmptyWhenThereIsNotAnPiece() {
        Coordinate coordinate = new Coordinate(0, 1);
        assertTrue(board.isEmpty(coordinate));
    }

}