package usantatecla.draughts.views;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import usantatecla.draughts.controllers.StartController;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class StartViewTest extends SubViewTest {

    private static final String TITTLE = "Draughts";

    @Mock
    private StartController startController;

    @Mock
    private GameView gameView;

    @InjectMocks
    private StartView startView;

    @Before
    public void before() {
        initMocks(this);
        this.startView = spy(this.startView);
    }

    @Test
    public void test1() {

        when(startView.createGameView()).thenReturn(gameView);
        startView.interact(startController);

        verify(console).writeln(eq(TITTLE));
        verify(gameView).write(eq(startController));
        verify(startController).start();

    }
}
