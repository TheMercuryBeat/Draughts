package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TurnTest {

    private Turn turn;

    @Before
    public void before() {
        turn = new Turn();
    }

    @Test
    public void testFirstTurnWithWhiteColor() {
        assertThat(turn.getColor(), is(equalTo(Color.WHITE)));
    }

    @Test
    public void testSecondTurnWithBlackColor() {
        turn.change();
        assertThat(turn.getColor(), is(equalTo(Color.BLACK)));
    }

    @Test
    public void testGetOppositeColorsWithTurnChanges() {
        assertThat(turn.getOppositeColor(), is(equalTo(Color.BLACK)));
        turn.change();
        assertThat(turn.getOppositeColor(), is(equalTo(Color.WHITE)));
    }
}
