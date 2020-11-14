package usantatecla.draughts.views;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import usantatecla.draughts.controllers.StartController;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;
import usantatecla.draughts.utils.Console;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

public class GameViewTest extends SubViewTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private StartController startController;

    @Spy
    private Console console;

    @InjectMocks
    private GameView gameView;

    @Before
    public void beforeGameView() {
        System.setOut(new PrintStream(outputStreamCaptor));

        MockitoAnnotations.initMocks(this);
        startController = spy(new StartController(new Game(), new State()));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testBoard() {

        //@formatter:off
        String board =
                " 12345678\n" +
                "1 n n n n1\n" +
                "2n n n n 2\n" +
                "3 n n n n3\n" +
                "4        4\n" +
                "5        5\n" +
                "6b b b b 6\n" +
                "7 b b b b7\n" +
                "8b b b b 8\n" +
                " 12345678\n";
        //@formatted:on

        gameView.write(startController);
        assertEquals(board, outputStreamCaptor.toString());
    }
}
