package usantatecla.draughts.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import usantatecla.draughts.models.Color;
import usantatecla.draughts.models.Coordinate;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class ControllerTest {

    @Mock
    protected Game game;

    @Mock
    protected State state;

    @Mock
    protected Coordinate coordinate;

    @InjectMocks
    private Controller controller;

    @Before
    public void beforeController() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetColor() {
        when(game.getColor(coordinate)).thenReturn(Color.BLACK);
        Color color = controller.getColor(coordinate);
        assertThat(color, is(equalTo(Color.BLACK)));
    }

    @Test
    public void testGetDimension() {
        when(game.getDimension()).thenReturn(getDimension(7));
        int dimension = controller.getDimension();
        assertThat(dimension, is(equalTo(getDimension(7))));
    }

    private int getDimension(int dimension) {
        return dimension;
    }

}
