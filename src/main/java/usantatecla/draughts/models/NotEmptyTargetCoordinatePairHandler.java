package usantatecla.draughts.models;

public class NotEmptyTargetCoordinatePairHandler extends CoordinatePairHandler {

    public NotEmptyTargetCoordinatePairHandler(CoordinatePairHandler next) {
        super(next);
    }

    @Override
    public Error check(Board board, Turn turn, Coordinate[] coordinates, int pair) {
        if (!board.isEmpty(coordinates[pair + 1])) {
            return Error.NOT_EMPTY_TARGET;
        }
        return this.checkNext(board, turn, coordinates, pair);
    }
}
