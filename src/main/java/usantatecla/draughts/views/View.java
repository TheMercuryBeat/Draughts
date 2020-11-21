package usantatecla.draughts.views;

import usantatecla.draughts.controllers.*;
import usantatecla.draughts.utils.Console;
import usantatecla.draughts.utils.YesNoDialog;

public class View implements InteractorControllersVisitor {

    private static final String TITLE = "Draughts";
    private static final String MESSAGE = "¿Queréis jugar otra?";

    private final Console console;
    private final PlayView playView;
    private final YesNoDialog yesNoDialog;

    public View() {
        this.playView = new PlayView();
        this.console = new Console();
        this.yesNoDialog = new YesNoDialog();
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
        this.resumeGame(resumeController);
    }

    void startGame(StartController startController) {
        this.console.writeln(TITLE);
        this.createGameView().write(startController);
        startController.start();
    }

    GameView createGameView() {
        return new GameView();
    }

    void resumeGame(ResumeController resumeController) {
        if (this.yesNoDialog.read(MESSAGE))
            resumeController.reset();
        else
            resumeController.next();
    }

}
