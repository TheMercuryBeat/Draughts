package usantatecla.draughts.views;

import usantatecla.draughts.controllers.StartController;

class StartView extends SubView {

    private static final String TITTLE = "Draughts";

    StartView() {
        super();
    }

    void interact(StartController startController) {
        assert startController != null;
        this.console.writeln(StartView.TITTLE);
        createGameView().write(startController);
        startController.start();
    }

    GameView createGameView() {
        return new GameView();
    }

}
