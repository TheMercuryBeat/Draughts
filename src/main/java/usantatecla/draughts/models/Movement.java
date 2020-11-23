package usantatecla.draughts.models;

import java.util.ArrayList;
import java.util.List;

import static usantatecla.draughts.models.Error.isError;
import static usantatecla.draughts.models.Error.isNotError;

class Movement {

    private final Board board;
    private final Turn turn;
    private final Coordinate[] coordinates;
    private int pair;
    private List<Coordinate> removedCoordinates;

    Movement(Board board, Turn turn, Coordinate[] coordinates, int pair) {
        this.board = board;
        this.turn = turn;
        this.coordinates = coordinates;
        this.pair = pair;
        this.removedCoordinates = new ArrayList<>();
    }

    Movement(Board board, Turn turn, Coordinate[] coordinates) {
        this.board = board;
        this.turn = turn;
        this.coordinates = coordinates;
        this.pair = 0;
        this.removedCoordinates = new ArrayList<>();
    }

    public Error doMove(MovementChecker movementChecker) {
        Error error;
        do {
            error = movementChecker.check(this);
            if (isNotError(error)) {
                this.move();
                pair++;
            }
        } while (pair < coordinates.length - 1 && isNotError(error));

        error = isCorrectGlobalMove(error);

        if (isNotError(error)) {
            for (Coordinate coordinate : removedCoordinates) {
                this.board.remove(coordinate);
            }
            this.turn.change();
        }

        return error;
    }

    private void move() {
        Coordinate forRemoving = this.getBetweenDiagonalPiece();
        if (forRemoving != null) {
            removedCoordinates.add(0, forRemoving);
        }
        this.board.move(coordinates[pair], coordinates[pair + 1]);
        if (this.board.getPiece(coordinates[pair + 1]).isLimit(coordinates[pair + 1])) {
            Color color = this.board.getColor(coordinates[pair + 1]);
            this.board.remove(coordinates[pair + 1]);
            this.board.put(coordinates[pair + 1], new Draught(color));
        }
    }

    private Coordinate getBetweenDiagonalPiece() {
        assert coordinates[pair].isOnDiagonal(coordinates[pair + 1]);
        List<Coordinate> betweenCoordinates = coordinates[pair].getBetweenDiagonalCoordinates(coordinates[pair + 1]);
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
        if (coordinates.length > 2 && coordinates.length > removedCoordinates.size() + 1)
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

    public List<Coordinate> getRemovedCoordinates() {
        return removedCoordinates;
    }
}
