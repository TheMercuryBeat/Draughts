package usantatecla.draughts.views;

import usantatecla.draughts.controllers.*;
import usantatecla.draughts.models.Color;
import usantatecla.draughts.models.Coordinate;
import usantatecla.draughts.models.Error;
import usantatecla.draughts.utils.Console;
import usantatecla.draughts.utils.YesNoDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class View implements InteractorControllersVisitor {

    private static final String TITLE = "Draughts";
    private static final String COLOR_PARAM = "#color";
    private static final String[] COLOR_VALUES = {"blancas", "negras"};
    private static final String PROMPT = "Mueven las " + COLOR_PARAM + ": ";
    private static final String CANCEL_FORMAT = "-1";
    private static final String MOVEMENT_FORMAT = "[1-8]{2}(\\.[1-8]{2}){1,2}";
    private static final String ERROR_MESSAGE = "Error!!! Formato incorrecto";
    private static final String LOST_MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";
    private static final String MESSAGE = "¿Queréis jugar otra?";
    private String string;

    private final Console console;
    private final YesNoDialog yesNoDialog;

    public View() {
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
        this.playGame(playController);
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

    void playGame(PlayController playController) {
        Error error;
        do {
            error = null;
            this.string = this.read(playController.getColor());
            if (this.isCanceledFormat())
                playController.cancel();
            else if (!this.isMoveFormat()) {
                error = Error.BAD_FORMAT;
                this.writeError();
            } else {
                error = playController.move(this.getCoordinates());
                new GameView().write(playController);
                if (error == null && playController.isBlocked())
                    this.writeLost();
            }
        } while (error != null);
    }

    private String read(Color color) {
        final String titleColor = PROMPT.replace(COLOR_PARAM, COLOR_VALUES[color.ordinal()]);
        return this.console.readString(titleColor);
    }

    private boolean isCanceledFormat() {
        return string.equals(CANCEL_FORMAT);
    }

    private boolean isMoveFormat() {
        return Pattern.compile(MOVEMENT_FORMAT).matcher(string).find();
    }

    private void writeError() {
        this.console.writeln(ERROR_MESSAGE);
    }

    private Coordinate[] getCoordinates() {
        assert this.isMoveFormat();
        List<Coordinate> coordinateList = new ArrayList<Coordinate>();
        while (string.length() > 0) {
            coordinateList.add(Coordinate.getInstance(string.substring(0, 2)));
            string = string.substring(2);
            if (string.length() > 0 && string.charAt(0) == '.')
                string = string.substring(1);
        }
        Coordinate[] coordinates = new Coordinate[coordinateList.size()];
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] = coordinateList.get(i);
        }
        return coordinates;
    }

    private void writeLost() {
        this.console.writeln(LOST_MESSAGE);
    }

    void resumeGame(ResumeController resumeController) {
        if (this.yesNoDialog.read(MESSAGE))
            resumeController.reset();
        else
            resumeController.next();
    }

}
