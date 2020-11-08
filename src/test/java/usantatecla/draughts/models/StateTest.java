package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StateTest {

    private State state;

    @Before
    public void before() {
        state = new State();
    }

    @Test
    public void testInitialStateValue() {
        assertThat(state.getValueState(), is(equalTo(StateValue.INITIAL)));
    }

    @Test
    public void testStateResetValue() {
        state.reset();
        assertThat(state.getValueState(), is(equalTo(StateValue.INITIAL)));
    }

    @Test
    public void testNextStateValue() {

        state.next();
        assertThat(state.getValueState(), is(equalTo(StateValue.IN_GAME)));

        state.next();
        assertThat(state.getValueState(), is(equalTo(StateValue.FINAL)));

        state.next();
        assertThat(state.getValueState(), is(equalTo(StateValue.EXIT)));
    }

    @Test(expected = AssertionError.class)
    public void testNextExitStateValue() {
        for (int i = 0; i < StateValue.values().length; i++) {
            state.next();
        }
    }

}
