package usantatecla.draughts.models;

public class NotEmptyTargetCoordinatePairHandler extends CoordinatePairHandler {

    public NotEmptyTargetCoordinatePairHandler(CoordinatePairHandler next) {
        super(next);
    }

    @Override
    public Error check(Movement movement) {

        Board board = movement.getBoard();
        Coordinate[] coordinates = movement.getCoordinates();
        int pair = movement.getPair();

        if (!board.isEmpty(coordinates[pair + 1])) {
            return Error.NOT_EMPTY_TARGET;
        }
        return this.checkNext(movement);
    }
}