package usantatecla.draughts.models;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CoordinateTest {


    @Test
    public void testCoordinateInstanceLimits() {

        assertNull(Coordinate.getInstance("00"));
        assertNull(Coordinate.getInstance("01"));
        assertNull(Coordinate.getInstance("10"));

        assertNull(Coordinate.getInstance("89"));
        assertNull(Coordinate.getInstance("98"));
        assertNull(Coordinate.getInstance("99"));

        assertThat(Coordinate.getInstance("11"), is(equalTo(new Coordinate(0, 0))));
        assertThat(Coordinate.getInstance("45"), is(equalTo(new Coordinate(3, 4))));
        assertThat(Coordinate.getInstance("88"), is(equalTo(new Coordinate(7, 7))));

    }

    @Test
    public void testDirections() {

        List<CoordinatesDirection> coordinatesDirections = Arrays.asList(
                new CoordinatesDirection(new Coordinate(5, 5), new Coordinate(6, 6), Direction.NE),
                new CoordinatesDirection(new Coordinate(5, 5), new Coordinate(6, 4), Direction.SE),
                new CoordinatesDirection(new Coordinate(5, 5), new Coordinate(4, 6), Direction.NW),
                new CoordinatesDirection(new Coordinate(5, 5), new Coordinate(4, 4), Direction.SW),
                new CoordinatesDirection(new Coordinate(5, 5), new Coordinate(5, 6), null),
                new CoordinatesDirection(new Coordinate(5, 5), new Coordinate(6, 5), null),
                new CoordinatesDirection(new Coordinate(5, 5), new Coordinate(5, 4), null),
                new CoordinatesDirection(new Coordinate(5, 5), new Coordinate(4, 5), null));

        for (CoordinatesDirection coordinatesDirection : coordinatesDirections) {

            Coordinate originCoordinate = coordinatesDirection.getCoordinates().getOriginCoordinate();
            Coordinate targetCoordinate = coordinatesDirection.getCoordinates().getTargetCoordinate();

            assertThat(originCoordinate.getDirection(targetCoordinate), is(equalTo(coordinatesDirection.getDirection())));

        }
    }

    @Test
    public void testIsOnDiagonal() {

        List<Coordinates> coordinatesIsOnDiagonal = Arrays.asList(
                new Coordinates(new Coordinate(5, 5), new Coordinate(6, 6)),
                new Coordinates(new Coordinate(5, 5), new Coordinate(6, 4)),
                new Coordinates(new Coordinate(5, 5), new Coordinate(4, 6)),
                new Coordinates(new Coordinate(5, 5), new Coordinate(4, 4)));

        List<Coordinates> coordinatesIsNotOnDiagonal = Arrays.asList(
                new Coordinates(new Coordinate(5, 5), new Coordinate(5, 6)),
                new Coordinates(new Coordinate(5, 5), new Coordinate(6, 5)),
                new Coordinates(new Coordinate(5, 5), new Coordinate(5, 4)),
                new Coordinates(new Coordinate(5, 5), new Coordinate(4, 5)));

        for (Coordinates coordinate : coordinatesIsOnDiagonal) {
            assertTrue(coordinate.getOriginCoordinate().isOnDiagonal(coordinate.getTargetCoordinate()));
        }

        for (Coordinates coordinate : coordinatesIsNotOnDiagonal) {
            assertFalse(coordinate.getOriginCoordinate().isOnDiagonal(coordinate.getTargetCoordinate()));
        }

    }

    @Test
    public void testGetDiagonalDistance() {

        Coordinate originCoordinate = new Coordinate(5, 5);
        Coordinate targetCoordinate = new Coordinate(6, 6);

        int diagonalDistance = originCoordinate.getDiagonalDistance(targetCoordinate);

        assertThat(diagonalDistance, is(equalTo(1)));

    }

    @Test
    public void testGetBetweenDiagonalCoordinates() {

        Coordinate originCoordinate = new Coordinate(5, 5);
        Coordinate targetCoordinate = new Coordinate(7, 7);
        Coordinate coordinateInBetween = new Coordinate(6, 6);

        List<Coordinate> coordinatesOnDiagonal = originCoordinate.getBetweenDiagonalCoordinates(targetCoordinate);

        assertThat(coordinatesOnDiagonal.size(), is(equalTo(1)));

        for (Coordinate coordinate : coordinatesOnDiagonal) {
            assertThat(coordinate, is(coordinateInBetween));
        }

    }

    @Test
    public void testGetAllDiagonalCoordinates() {

        Coordinate originCoordinate = new Coordinate(5, 5);
        Coordinate coordinateNE = new Coordinate(6, 6);
        Coordinate coordinateSE = new Coordinate(4, 6);
        Coordinate coordinateNW = new Coordinate(6, 4);
        Coordinate coordinateSW = new Coordinate(4, 4);

        List<Coordinate> diagonalCoordinates = originCoordinate.getDiagonalCoordinates(1);
        assertThat(diagonalCoordinates, hasItems(coordinateNE, coordinateSE, coordinateNW, coordinateSW));

    }

    @Test
    public void testGetLimitDiagonalCoordinates() {

        Coordinate originCoordinateNE = new Coordinate(7, 7);
        Coordinate targetCoordinateNE = new Coordinate(6, 6);
        List<Coordinate> diagonalCoordinatesNE = originCoordinateNE.getDiagonalCoordinates(1);
        assertThat(diagonalCoordinatesNE, hasItem(targetCoordinateNE));

        Coordinate originCoordinateSE = new Coordinate(0, 0);
        Coordinate targetCoordinateSE = new Coordinate(1, 1);
        List<Coordinate> diagonalCoordinatesSE = originCoordinateSE.getDiagonalCoordinates(1);
        assertThat(diagonalCoordinatesSE, hasItem(targetCoordinateSE));

        Coordinate originCoordinateNW = new Coordinate(0, 7);
        Coordinate targetCoordinateNW = new Coordinate(1, 6);
        List<Coordinate> diagonalCoordinatesNW = originCoordinateNW.getDiagonalCoordinates(1);
        assertThat(diagonalCoordinatesNW, hasItem(targetCoordinateNW));

        Coordinate originCoordinateSW = new Coordinate(7, 0);
        Coordinate targetCoordinateSW = new Coordinate(6, 1);
        List<Coordinate> diagonalCoordinatesSW = originCoordinateSW.getDiagonalCoordinates(1);
        assertThat(diagonalCoordinatesSW, hasItem(targetCoordinateSW));

    }

    @Test
    public void testCoordinateIsBlack() {

        assertFalse(new Coordinate(5, 5).isBlack());
        assertFalse(new Coordinate(4, 4).isBlack());
        assertTrue(new Coordinate(4, 5).isBlack());
        assertTrue(new Coordinate(5, 4).isBlack());

    }

    @Test
    public void testCoordinateIsLast() {
        assertTrue(new Coordinate(7, 7).isLast());
        assertFalse(new Coordinate(5, 5).isLast());
    }

    @Test
    public void testCoordinateIsFirst() {
        assertTrue(new Coordinate(0, 0).isFirst());
        assertFalse(new Coordinate(5, 5).isFirst());
    }

}
