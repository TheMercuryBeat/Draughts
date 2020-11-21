package usantatecla.draughts.models;

public class EmptyOriginCoordinatePairHandler extends CoordinatePairHandler {

    public EmptyOriginCoordinatePairHandler(CoordinatePairHandler next) {
        super(next);
    }

    @Override
    public Error check(Movement movement) {

        Board board = movement.getBoard();
        Coordinate[] coordinates = movement.getCoordinates();
        int pair = movement.getPair();

        if (board.isEmpty(coordinates[pair])) {
            return Error.EMPTY_ORIGIN;
        }

        return this.checkNext(movement);

    }
}
