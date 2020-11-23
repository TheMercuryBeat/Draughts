package usantatecla.draughts.models;

import java.util.ArrayList;
import java.util.List;

import static usantatecla.draughts.models.Error.isError;
import static usantatecla.draughts.models.Error.isNotError;

class Movement {

    private final Board board;
    private final Turn turn;
    private final Coordinate[] coordinates;
    private final List<Coordinate> removedCoordinates = new ArrayList<>();
    private int pair = 0;

    Movement(Board board, Turn turn, Coordinate... coordinates) {

        assert coordinates[pair] != null;
        assert coordinates[pair + 1] != null;

        this.board = board;
        this.turn = turn;
        this.coordinates = coordinates;
    }

    public Error doMove(MovementChecker movementChecker) {

        Error error = this.checkAndMove(movementChecker);
        error = this.isCorrectGlobalMove(error);

        if (isNotError(error)) {
            for (Coordinate coordinate : this.removedCoordinates) {
                this.board.remove(coordinate);
            }
            this.turn.change();
        }

        return error;
    }

    private void move() {
        this.board.move(this.getOriginCoordinate(), this.getTargetCoordinate());
        this.addOppositePieceCoordinateToRemove();
        if (isBoardLimit()) {
            convertToDraught();
        }
    }

    private void addOppositePieceCoordinateToRemove() {
        Coordinate forRemoving = this.getBetweenDiagonalPiece();
        if (forRemoving != null) {
            this.removedCoordinates.add(0, forRemoving);
        }
    }

    private void convertToDraught() {
        Color color = this.board.getColor(this.getTargetCoordinate());
        this.board.remove(this.getTargetCoordinate());
        this.board.put(this.getTargetCoordinate(), new Draught(color));
    }

    private boolean isBoardLimit() {
        return this.board.getPiece(this.getTargetCoordinate()).isLimit(this.getTargetCoordinate());
    }

    private Coordinate getTargetCoordinate() {
        return this.coordinates[this.pair + 1];
    }

    private Coordinate getOriginCoordinate() {
        return this.coordinates[this.pair];
    }

    private Error checkAndMove(MovementChecker movementChecker) {
        Error error;
        do {
            error = movementChecker.check(this);
            if (isNotError(error)) {
                this.move();
                pair++;
            }
        } while (pair < coordinates.length - 1 && isNotError(error));
        return error;
    }

    private Coordinate getBetweenDiagonalPiece() {
        assert this.getOriginCoordinate().isOnDiagonal(this.getTargetCoordinate());
        List<Coordinate> betweenCoordinates = this.getOriginCoordinate().getBetweenDiagonalCoordinates(this.getTargetCoordinate());
        if (betweenCoordinates.isEmpty())
            return null;
        for (Coordinate coordinate : betweenCoordinates) {
            if (this.board.getPiece(coordinate) != null)
                return coordinate;
        }
        return null;
    }


    private Error isCorrectGlobalMove(Error error) {
        if (isError(error))
            return error;
        if (this.coordinates.length > 2 && this.coordinates.length > removedCoordinates.size() + 1)
            return Error.TOO_MUCH_JUMPS;
        return Error.NONE;
    }


    Board getBoard() {
        return board;
    }

    Turn getTurn() {
        return turn;
    }

    Coordinate[] getCoordinates() {
        return coordinates;
    }

    int getPair() {
        return pair;
    }

}
