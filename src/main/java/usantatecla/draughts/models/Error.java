package usantatecla.draughts.models;

public enum Error {
    BAD_FORMAT,
    EMPTY_ORIGIN,
    OPPOSITE_PIECE,
    NOT_DIAGONAL,
    NOT_EMPTY_TARGET,
    NOT_ADVANCED,
    WITHOUT_EATING,
    COLLEAGUE_EATING,
    TOO_MUCH_ADVANCED,
    TOO_MUCH_EATINGS,
    TOO_MUCH_JUMPS,
    NONE;

    public static boolean isNotError(Error error) {
        return error == null || NONE.equals(error);
    }

    public static boolean isError(Error error) {
        return !isNotError(error);
    }
}