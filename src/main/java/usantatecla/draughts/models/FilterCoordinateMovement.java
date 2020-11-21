package usantatecla.draughts.models;

public class FilterCoordinateMovement {

    private final CoordinatePairHandler coordinatePairHandler;

    public FilterCoordinateMovement() {

        CoordinatePairHandler correctMovement = new CorrectMovementCoordinatePairHandler();
        CoordinatePairHandler notEmptyTarget = new NotEmptyTargetCoordinatePairHandler(correctMovement);
        CoordinatePairHandler oppositePiece = new OppositePieceCoordinatePairHandler(notEmptyTarget);

        this.coordinatePairHandler = new EmptyOriginCoordinatePairHandler(oppositePiece);

    }

    public Error check(Movement movement) {
        return coordinatePairHandler.check(movement);
    }
}
