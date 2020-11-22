package usantatecla.draughts.models;

class BoardRegistry {

    private final Board board;
    private Board.BoardVersion boardVersion;

    BoardRegistry(Board board) {
        this.board = board;
        this.registry();
    }

    void registry() {
        this.boardVersion = this.board.createBoardVersion();
    }

    void undo() {
        this.board.setBoardVersion(this.boardVersion);
    }

}
