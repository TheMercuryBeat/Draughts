package usantatecla.draughts.models;

public class CoordinatesDirection {

    private Coordinates coordinates;
    private Direction direction;

    public CoordinatesDirection(Coordinate originCoordinate, Coordinate targetCoordinate, Direction direction) {
        this.coordinates = new Coordinates(originCoordinate, targetCoordinate);
        this.direction = direction;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Direction getDirection() {
        return direction;
    }
}
