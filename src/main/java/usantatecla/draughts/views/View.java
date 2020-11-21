package usantatecla.draughts.views;

import usantatecla.draughts.controllers.*;
import usantatecla.draughts.utils.Console;

public class View implements InteractorControllersVisitor {

    private static final String TITLE = "Draughts";

    private final Console console;
    private final PlayView playView;
    private final ResumeView resumeView;

    public View() {
        this.playView = new PlayView();
        this.resumeView = new ResumeView();
        this.console = new Console();
    }

    public void interact(InteractorController controller) {
        assert controller != null;
        controller.accept(this);
    }

    @Override
    public void visit(StartController startController) {
        assert startController != null;
        this.startGame(startController);
    }

    @Override
    public void visit(PlayController playController) {
        assert playController != null;
        this.playView.interact(playController);
    }

    @Override
    public void visit(ResumeController resumeController) {
        assert resumeController != null;
        this.resumeView.interact(resumeController);
    }

    void startGame(StartController startController) {
        this.console.writeln(TITLE);
        createGameView().write(startController);
        startController.start();
    }

    GameView createGameView() {
        return new GameView();
    }

}
