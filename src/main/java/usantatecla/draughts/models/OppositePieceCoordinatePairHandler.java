package usantatecla.draughts.models;

public class OppositePieceCoordinatePairHandler extends CoordinatePairHandler {

    public OppositePieceCoordinatePairHandler(CoordinatePairHandler next) {
        super(next);
    }

    @Override
    public Error check(Board board, Turn turn, Coordinate[] coordinates, int pair) {

        if (turn.getOppositeColor() == board.getColor(coordinates[pair])) {
            return Error.OPPOSITE_PIECE;
        }

        return this.checkNext(board, turn, coordinates, pair);
    }
}
