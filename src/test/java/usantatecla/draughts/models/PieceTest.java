package usantatecla.draughts.models;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public abstract class PieceTest {

    protected Coordinate originCoordinate = Mockito.mock(Coordinate.class);
    protected Coordinate targetCoordinate = Mockito.mock(Coordinate.class);

    @Test
    public void testLimitsForWhitePiece() {

        when(targetCoordinate.isFirst()).thenReturn(true);
        assertTrue(getWhitePiece().isLimit(targetCoordinate));

        when(targetCoordinate.isFirst()).thenReturn(false);
        assertFalse(getWhitePiece().isLimit(targetCoordinate));
    }

    @Test
    public void testLimitsForBlackPiece() {

        when(targetCoordinate.isFirst()).thenReturn(false);
        when(targetCoordinate.isLast()).thenReturn(true);
        assertTrue(getBlackPiece().isLimit(targetCoordinate));

        when(targetCoordinate.isFirst()).thenReturn(true);
        when(targetCoordinate.isLast()).thenReturn(false);
        assertFalse(getBlackPiece().isLimit(targetCoordinate));
    }

    @Test
    public void testWhitePieceIsAdvanced() {

        when(originCoordinate.getRow()).thenReturn(getRow(8));
        when(targetCoordinate.getRow()).thenReturn(getRow(7));
        assertTrue(getWhitePiece().isAdvanced(originCoordinate, targetCoordinate));

        when(originCoordinate.getRow()).thenReturn(getRow(8));
        when(targetCoordinate.getRow()).thenReturn(getRow(8));
        assertFalse(getWhitePiece().isAdvanced(originCoordinate, targetCoordinate));

    }

    @Test
    public void testBlackPieceIsAdvanced() {

        when(originCoordinate.getRow()).thenReturn(getRow(0));
        when(targetCoordinate.getRow()).thenReturn(getRow(1));
        assertTrue(getBlackPiece().isAdvanced(originCoordinate, targetCoordinate));

        when(originCoordinate.getRow()).thenReturn(getRow(0));
        when(targetCoordinate.getRow()).thenReturn(getRow(0));
        assertFalse(getBlackPiece().isAdvanced(originCoordinate, targetCoordinate));

    }

    @Test
    public void testIsCorrectMovementNotDiagonal() {

        when(originCoordinate.isOnDiagonal(targetCoordinate)).thenReturn(false);

        List<Piece> pieces = Collections.singletonList(getWhitePiece());
        Error error = getBlackPiece().isCorrectMovement(pieces, getPair(0), originCoordinate, targetCoordinate);

        assertThat(error, is(equalTo(Error.NOT_DIAGONAL)));

    }

    @Test
    public void testIsCorrectMovementWithColleagueEating() {

        when(originCoordinate.isOnDiagonal(targetCoordinate)).thenReturn(true);

        List<Piece> pieces = Collections.singletonList(getWhitePiece());
        Error error = getWhitePiece().isCorrectMovement(pieces, getPair(0), originCoordinate, targetCoordinate);

        assertThat(error, is(equalTo(Error.COLLEAGUE_EATING)));

    }



    protected abstract Piece getBlackPiece();

    protected abstract Piece getWhitePiece();

    protected int getRow(int row) {
        return row;
    }

    protected int getDiagonalDistance(int numberAdvanced) {
        return numberAdvanced;
    }

    protected int getPair(int pair) {
        return pair;
    }

    protected int getAmountBetweenDiagonalPieces(int pair) {
        return pair;
    }

}
