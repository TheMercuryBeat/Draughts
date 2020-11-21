package usantatecla.draughts.models;

import java.util.List;

public class CorrectMovementCoordinatePairHandler extends CoordinatePairHandler {

    public CorrectMovementCoordinatePairHandler() {
        super(null);
    }

    @Override
    public Error check(Board board, Turn turn, Coordinate[] coordinates, int pair) {
        List<Piece> betweenDiagonalPieces = board.getBetweenDiagonalPieces(coordinates[pair], coordinates[pair + 1]);
        Error error = board.getPiece(coordinates[pair]).isCorrectMovement(betweenDiagonalPieces, pair, coordinates);

        if (error != null) {
            return error;
        }

        return this.checkNext(board, turn, coordinates, pair);
    }
}
