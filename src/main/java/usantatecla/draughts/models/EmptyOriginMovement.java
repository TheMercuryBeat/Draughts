package usantatecla.draughts.models;

class EmptyOriginMovement extends MovementHandler {

    public EmptyOriginMovement(MovementHandler next) {
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

        return this.nextMovement(movement);

    }
}
