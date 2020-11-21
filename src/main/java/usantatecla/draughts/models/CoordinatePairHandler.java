package usantatecla.draughts.models;

public abstract class CoordinatePairHandler {

    protected CoordinatePairHandler next;

    public CoordinatePairHandler(CoordinatePairHandler next) {
        this.next = next;
    }

    public Error checkNext(Board board, Turn turn, Coordinate[] coordinates, int pair) {
        if (this.next == null) {
            return null;
        }

        return this.next.check(board, turn, coordinates, pair);
    }

    public abstract Error check(Board board, Turn turn, Coordinate[] coordinates, int pair);
}
