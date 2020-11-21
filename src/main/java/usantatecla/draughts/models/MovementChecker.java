package usantatecla.draughts.models;

public class MovementChecker {

    private final MovementHandler movementHandler;

    public MovementChecker() {

        MovementHandler correctMovement = new CorrectMovement();
        MovementHandler notEmptyTarget = new NotEmptyTargetMovement(correctMovement);
        MovementHandler oppositePiece = new OppositePieceMovement(notEmptyTarget);

        this.movementHandler = new EmptyOriginMovement(oppositePiece);

    }

    public Error check(Movement movement) {
        return movementHandler.check(movement);
    }
}
