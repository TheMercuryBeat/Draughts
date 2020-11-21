package usantatecla.draughts.models;

public abstract class MovementHandler {

    protected MovementHandler nextMovementHandler;

    public MovementHandler(MovementHandler movementHandler) {
        this.nextMovementHandler = movementHandler;
    }

    public Error nextMovement(Movement movement) {
        if (this.nextMovementHandler == null) {
            return null;
        }

        return this.nextMovementHandler.check(movement);
    }

    public abstract Error check(Movement movement);
}
