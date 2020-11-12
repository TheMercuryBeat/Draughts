package usantatecla.draughts.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;
import usantatecla.draughts.models.StateValue;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;


public class LogicTest {

    @Mock
    private Game game;

    @Spy
    private State state;

    @InjectMocks
    private Logic logic;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStartController() {

        when(state.getValueState()).thenReturn(StateValue.INITIAL);

        InteractorController controller = logic.getController();
        assertThat(controller, is(instanceOf(StartController.class)));

    }

    @Test
    public void testPlayController() {

        when(state.getValueState()).thenReturn(StateValue.IN_GAME);

        InteractorController controller = logic.getController();
        assertThat(controller, is(instanceOf(PlayController.class)));

    }

    @Test
    public void testResumeController() {

        when(state.getValueState()).thenReturn(StateValue.FINAL);

        InteractorController controller = logic.getController();
        assertThat(controller, is(instanceOf(ResumeController.class)));

    }

    @Test
    public void testNullController() {

        when(state.getValueState()).thenReturn(StateValue.EXIT);

        InteractorController controller = logic.getController();
        assertNull(controller);

    }

}
