package usantatecla.draughts.models;

class Movement {

    private final Board board;
    private final Turn turn;
    private final Coordinate[] coordinates;
    private final int pair;

    Movement(Board board, Turn turn, Coordinate[] coordinates, int pair) {
        this.board = board;
        this.turn = turn;
        this.coordinates = coordinates;
        this.pair = pair;
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
