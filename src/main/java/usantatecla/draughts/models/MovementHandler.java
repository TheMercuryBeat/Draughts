package usantatecla.draughts.models;

abstract class MovementHandler {

    private final MovementHandler nextMovementHandler;

    public MovementHandler(MovementHandler movementHandler) {
        this.nextMovementHandler = movementHandler;
    }

    protected Error nextMovement(Movement movement) {
        if (this.nextMovementHandler == null) {
            return Error.NONE;
        }

        return this.nextMovementHandler.check(movement);
    }

    public abstract Error check(Movement movement);
}
