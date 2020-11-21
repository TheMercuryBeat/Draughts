package usantatecla.draughts.models;

public abstract class CoordinatePairHandler {

    protected CoordinatePairHandler next;

    public CoordinatePairHandler(CoordinatePairHandler next) {
        this.next = next;
    }

    public Error checkNext(Movement movement) {
        if (this.next == null) {
            return null;
        }

        return this.next.check(movement);
    }

    public abstract Error check(Movement movement);
}
