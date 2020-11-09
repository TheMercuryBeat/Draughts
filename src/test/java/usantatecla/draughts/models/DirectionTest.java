package usantatecla.draughts.models;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DirectionTest {


    @Test
    public void testNorthEastDirection() {

        assertTrue(Direction.NE.isOnDirection(new Coordinate(5, 5)));
        assertFalse(Direction.NE.isOnDirection(new Coordinate(5, 6)));
        assertFalse(Direction.NE.isOnDirection(new Coordinate(0, 1)));
        assertFalse(Direction.NE.isOnDirection(new Coordinate(0, 0)));

    }

    @Test
    public void testNorthWestDirection() {

        assertTrue(Direction.NW.isOnDirection(new Coordinate(-5, 5)));
        assertFalse(Direction.NW.isOnDirection(new Coordinate(-5, 6)));
        assertFalse(Direction.NW.isOnDirection(new Coordinate(0, 1)));
        assertFalse(Direction.NW.isOnDirection(new Coordinate(0, 0)));

    }

    @Test
    public void testSouthEastDirection() {

        assertTrue(Direction.SE.isOnDirection(new Coordinate(5, -5)));
        assertFalse(Direction.SE.isOnDirection(new Coordinate(5, -6)));
        assertFalse(Direction.SE.isOnDirection(new Coordinate(0, -1)));
        assertFalse(Direction.SE.isOnDirection(new Coordinate(0, 0)));

    }

    @Test
    public void testSouthWestDirection() {

        assertTrue(Direction.SW.isOnDirection(new Coordinate(-5, -5)));
        assertFalse(Direction.SW.isOnDirection(new Coordinate(-5, -6)));
        assertFalse(Direction.SW.isOnDirection(new Coordinate(0, -1)));
        assertFalse(Direction.SW.isOnDirection(new Coordinate(0, 0)));

    }

    @Test
    public void testGetDistanceCoordinateNorthWest() {

        Coordinate coordinateNW = new Coordinate(-2, 2);

        Coordinate coordinate = Direction.NW.getDistanceCoordinate(this.getDistanceCoordinate(2));
        assertThat(coordinate, is(equalTo(coordinateNW)));

    }

    @Test
    public void testGetDistanceCoordinateNorthEast() {

        Coordinate coordinateNE = new Coordinate(3, 3);

        Coordinate coordinate = Direction.NE.getDistanceCoordinate(this.getDistanceCoordinate(3));
        assertThat(coordinate, is(equalTo(coordinateNE)));

    }

    @Test
    public void testGetDistanceCoordinateSouthWest() {

        Coordinate coordinateSW = new Coordinate(-4, -4);

        Coordinate coordinate = Direction.SW.getDistanceCoordinate(this.getDistanceCoordinate(4));
        assertThat(coordinate, is(equalTo(coordinateSW)));

    }

    @Test
    public void testGetDistanceCoordinateSouthEast() {

        Coordinate coordinateSE = new Coordinate(5, -5);

        Coordinate coordinate = Direction.SE.getDistanceCoordinate(this.getDistanceCoordinate(5));
        assertThat(coordinate, is(equalTo(coordinateSE)));

    }

    private int getDistanceCoordinate(int distance) {
        return distance;
    }

}
