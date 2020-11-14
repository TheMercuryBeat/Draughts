package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class ColorTest {

    @Mock
    private Coordinate coordinate;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testColorBlackLimit() {

        when(coordinate.isBlack()).thenReturn(true);
        when(coordinate.getRow()).thenReturn(2);

        Color color = Color.getInitialColor(coordinate);
        assertThat(color, is(equalTo(Color.BLACK)));

    }

    @Test
    public void testColorWhiteLimit() {

        when(coordinate.isBlack()).thenReturn(true);
        when(coordinate.getRow()).thenReturn(5);

        Color color = Color.getInitialColor(coordinate);
        assertThat(color, is(equalTo(Color.WHITE)));

    }

    @Test
    public void testNoColor() {

        when(coordinate.isBlack()).thenReturn(true);
        when(coordinate.getRow()).thenReturn(4);

        Color color = Color.getInitialColor(coordinate);
        assertNull(color);

    }

    @Test
    public void testCoordinateIsNotBlack() {

        when(coordinate.isBlack()).thenReturn(false);

        Color color = Color.getInitialColor(coordinate);
        assertNull(color);

    }

}
