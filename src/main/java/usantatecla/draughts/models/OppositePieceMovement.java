package usantatecla.draughts.models;

class OppositePieceMovement extends MovementHandler {

    public OppositePieceMovement(MovementHandler next) {
        super(next);
    }

    @Override
    public Error check(Movement movement) {

        Board board = movement.getBoard();
        Turn turn = movement.getTurn();
        Coordinate[] coordinates = movement.getCoordinates();
        int pair = movement.getPair();

        if (turn.getOppositeColor() == board.getColor(coordinates[pair])) {
            return Error.OPPOSITE_PIECE;
        }

        return this.nextMovement(movement);
    }
}
