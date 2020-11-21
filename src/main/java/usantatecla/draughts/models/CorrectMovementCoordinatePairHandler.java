package usantatecla.draughts.models;

import java.util.List;

public class CorrectMovementCoordinatePairHandler extends CoordinatePairHandler {

    public CorrectMovementCoordinatePairHandler() {
        super(null);
    }

    @Override
    public Error check(Movement movement) {

        Board board = movement.getBoard();
        Coordinate[] coordinates = movement.getCoordinates();
        int pair = movement.getPair();

        List<Piece> betweenDiagonalPieces = board.getBetweenDiagonalPieces(coordinates[pair], coordinates[pair + 1]);
        Error error = board.getPiece(coordinates[pair]).isCorrectMovement(betweenDiagonalPieces, pair, coordinates);

        if (error != null) {
            return error;
        }

        return this.checkNext(movement);
    }
}
