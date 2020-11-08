package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class PawnTest extends PieceTest {

    private Pawn whitePawn;
    private Pawn blackPawn;

    @Before
    public void before() {

        whitePawn = new Pawn(Color.WHITE);
        blackPawn = new Pawn(Color.BLACK);

        Mockito.reset(originCoordinate, targetCoordinate);

    }

    @Test
    public void testIsCorrectDiagonalMovementWithoutNotAdvanced() {

        when(originCoordinate.getRow()).thenReturn(getRow(7));
        when(targetCoordinate.getRow()).thenReturn(getRow(7));

        Error error = whitePawn.isCorrectDiagonalMovement(getAmountBetweenDiagonalPieces(1), getPair(0), originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.NOT_ADVANCED)));

    }

    @Test
    public void testIsCorrectDiagonalMovementWithTooMuchDistant() {

        when(originCoordinate.getRow()).thenReturn(getRow(7));
        when(targetCoordinate.getRow()).thenReturn(getRow(6));

        when(originCoordinate.getDiagonalDistance(targetCoordinate)).thenReturn(getDiagonalDistance(3));

        Error error = whitePawn.isCorrectDiagonalMovement(getAmountBetweenDiagonalPieces(1), getPair(0), originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.TOO_MUCH_ADVANCED)));

    }

    @Test
    public void testIsCorrectDiagonalMovementWithoutEating() {

        when(originCoordinate.getRow()).thenReturn(getRow(7));
        when(targetCoordinate.getRow()).thenReturn(getRow(6));

        when(originCoordinate.getDiagonalDistance(targetCoordinate)).thenReturn(getDiagonalDistance(2));

        Error error = whitePawn.isCorrectDiagonalMovement(getAmountBetweenDiagonalPieces(2), getPair(0), originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.WITHOUT_EATING)));

    }

    @Test
    public void testIsCorrectMovement() {

        when(originCoordinate.isOnDiagonal(targetCoordinate)).thenReturn(true);
        when(originCoordinate.getRow()).thenReturn(getRow(7));
        when(targetCoordinate.getRow()).thenReturn(getRow(6));
        when(originCoordinate.getDiagonalDistance(targetCoordinate)).thenReturn(getDiagonalDistance(1));

        List<Piece> pieces = Collections.singletonList(getBlackPiece());
        Error error = getWhitePiece().isCorrectMovement(pieces, getPair(0), originCoordinate, targetCoordinate);

        assertNull(error);

    }

    @Override
    protected Piece getBlackPiece() {
        return blackPawn;
    }

    @Override
    protected Piece getWhitePiece() {
        return whitePawn;
    }

}
