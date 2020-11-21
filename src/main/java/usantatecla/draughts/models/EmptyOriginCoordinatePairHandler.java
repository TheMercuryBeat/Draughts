package usantatecla.draughts.models;

public class EmptyOriginCoordinatePairHandler extends CoordinatePairHandler {

    public EmptyOriginCoordinatePairHandler(CoordinatePairHandler next) {
        super(next);
    }

    @Override
    public Error check(Board board, Turn turn, Coordinate[] coordinates, int pair) {

        if (board.isEmpty(coordinates[pair])) {
            return Error.EMPTY_ORIGIN;
        }

        return this.checkNext(board, turn, coordinates, pair);

    }
}
