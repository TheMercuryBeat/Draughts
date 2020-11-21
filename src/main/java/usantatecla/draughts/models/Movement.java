package usantatecla.draughts.models;

public class Movement {

    private final Board board;
    private final Turn turn;
    private final Coordinate[] coordinates;
    private final int pair;

    public Movement(Board board, Turn turn, Coordinate[] coordinates, int pair) {
        this.board = board;
        this.turn = turn;
        this.coordinates = coordinates;
        this.pair = pair;
    }

    public Board getBoard() {
        return board;
    }

    public Turn getTurn() {
        return turn;
    }

    public Coordinate[] getCoordinates() {
        return coordinates;
    }

    public int getPair() {
        return pair;
    }

}
