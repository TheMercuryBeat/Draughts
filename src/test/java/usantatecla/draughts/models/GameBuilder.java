package usantatecla.draughts.models;

import static java.lang.Character.isLowerCase;
import static java.util.Objects.nonNull;

public class GameBuilder {

    private final Board board;

    public GameBuilder() {
        this.board = new Board();
    }

    public GameBuilder board(String... board) {

        for (int indexRow = 0; indexRow < board.length; indexRow++) {
            String row = board[indexRow];

            for (int indexColumn = 0; indexColumn < row.length(); indexColumn++) {
                Color color = getColor(row.charAt(indexColumn));

                if (nonNull(color)) {

                    boolean isPawn = isLowerCase(row.charAt(indexColumn));
                    Piece piece = isPawn ? new Pawn(color) : new Draught(color);

                    this.board.put(new Coordinate(indexRow, indexColumn), piece);
                }
            }
        }

        return this;
    }

    private Color getColor(char color) {
        switch (color) {
            case 'b':
            case 'B':
                return Color.WHITE;
            case 'n':
            case 'N':
                return Color.BLACK;
            default:
                return null;
        }
    }

    public Game build() {
        return new Game(this.board);
    }


}
