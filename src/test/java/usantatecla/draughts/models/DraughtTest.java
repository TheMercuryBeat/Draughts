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

public class DraughtTest extends PieceTest {

    private Draught whiteDraught;
    private Draught blackDraught;

    @Before
    public void before() {

        whiteDraught = new Draught(Color.WHITE);
        blackDraught = new Draught(Color.BLACK);

        Mockito.reset(originCoordinate, targetCoordinate);

    }

    @Test
    public void testIsCorrectDiagonalMovementWithTooMuchEating() {

        Error error = blackDraught.isCorrectDiagonalMovement(getAmountBetweenDiagonalPieces(2), getPair(0), originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.TOO_MUCH_EATINGS)));

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
        return blackDraught;
    }

    @Override
    protected Piece getWhitePiece() {
        return whiteDraught;
    }

}
