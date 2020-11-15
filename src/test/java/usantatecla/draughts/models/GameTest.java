package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GameTest {

    private Game game;

    @Before
    public void before() {
        game = new Game();
    }

    @Test
    public void testGetColorPieceWhenPieceExists() {
        Color color = game.getColor(new Coordinate(5, 4));
        assertThat(color, is(equalTo(Color.WHITE)));
    }

    @Test
    public void testGetColorPieceWhenPieceDoesNotExist() {
        Color color = game.getColor(new Coordinate(5, 5));
        assertNull(color);
    }

    @Test
    public void testGetPieceWhenPieceExists() {
        Piece piece = game.getPiece(new Coordinate(5, 4));
        assertThat(piece, is(equalTo(new Pawn(Color.WHITE))));
    }

    @Test
    public void testGetPieceWhenPieceDoesNotExist() {
        Piece piece = game.getPiece(new Coordinate(5, 5));
        assertNull(piece);
    }

    @Test
    public void testGameBlocked() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "  n     ",
                        " n      ",
                        "b       ")
                .build();
        //@formatter:on

        assertTrue(this.game.isBlocked());

    }

    @Test
    public void testMoveEmptyOrigin() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ")
                .build();
        //@formatter:on

        Coordinate originCoordinate = new Coordinate(0, 0);
        Coordinate targetCoordinate = new Coordinate(1, 1);

        Error error = this.game.move(originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.EMPTY_ORIGIN)));

    }

    @Test
    public void testMoveOppositePiece() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( " n      ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        " b      ")
                .build();
        //@formatter:on

        Coordinate originCoordinate = new Coordinate(0, 1);
        Coordinate targetCoordinate = new Coordinate(1, 2);

        Error error = this.game.move(originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.OPPOSITE_PIECE)));

    }


    @Test
    public void testMoveNotEmptyTarget() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "        ",
                        "        ",
                        "   n    ",
                        "    b   ",
                        "        ",
                        "        ",
                        "        ",
                        "        ")
                .build();
        //@formatter:on

        Coordinate originCoordinate = new Coordinate(3, 4);
        Coordinate targetCoordinate = new Coordinate(2, 3);

        Error error = this.game.move(originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.NOT_EMPTY_TARGET)));

    }

    @Test
    public void testMoveSuccess() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        " b      ")
                .build();
        //@formatter:on

        Coordinate originCoordinate = new Coordinate(7, 1);
        Coordinate targetCoordinate = new Coordinate(6, 2);

        Error error = this.game.move(originCoordinate, targetCoordinate);
        Piece whitePawn = this.game.getPiece(targetCoordinate);

        assertNull(error);
        assertNotNull(whitePawn);

    }

    @Test
    public void testMoveCheckTurnColor() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "n       ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        " b      ")
                .build();
        //@formatter:on

        Coordinate originCoordinate = new Coordinate(7, 1);
        Coordinate targetCoordinate = new Coordinate(6, 2);

        Error error = this.game.move(originCoordinate, targetCoordinate);
        Piece whitePawn = this.game.getPiece(targetCoordinate);
        Color gameTurnColor = this.game.getTurnColor();

        assertNull(error);
        assertNotNull(whitePawn);
        assertThat(gameTurnColor, is(equalTo(Color.BLACK)));

    }

    @Test
    public void testMoveEatingOnePiece() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "  n     ",
                        " b      ")
                .build();
        //@formatter:on

        Coordinate originCoordinate = new Coordinate(7, 1);
        Coordinate targetCoordinate = new Coordinate(5, 3);

        Error error = this.game.move(originCoordinate, targetCoordinate);
        Piece blackPawn = this.game.getPiece(new Coordinate(6, 2));
        Piece whitePawn = this.game.getPiece(targetCoordinate);

        assertNull(error);
        assertNull(blackPawn);
        assertNotNull(whitePawn);

    }

    @Test
    public void testMoveEatingTwoPiece() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "        ",
                        "        ",
                        "        ",
                        "        ",
                        "    n   ",
                        "        ",
                        "  n     ",
                        " b      ")
                .build();
        //@formatter:on

        Coordinate originCoordinate = new Coordinate(7, 1);
        Coordinate firstTargetCoordinate = new Coordinate(5, 3);
        Coordinate secondTargetCoordinate = new Coordinate(3, 5);

        Error error = this.game.move(originCoordinate, firstTargetCoordinate, secondTargetCoordinate);
        Piece firstBlackPawn = this.game.getPiece(new Coordinate(6, 2));
        Piece secondBlackPawn = this.game.getPiece(new Coordinate(4, 4));
        Piece whitePawn = this.game.getPiece(secondTargetCoordinate);

        assertNull(error);
        assertNull(firstBlackPawn);
        assertNull(secondBlackPawn);
        assertNotNull(whitePawn);

    }

    @Test
    public void testMoveEatingThreePieceNotDiagonal() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "        ",
                        "        ",
                        "    n   ",
                        "        ",
                        "    n   ",
                        "        ",
                        "  n     ",
                        " b      ")
                .build();
        //@formatter:on

        Coordinate originCoordinate = new Coordinate(7, 1);
        Coordinate firstTargetCoordinate = new Coordinate(5, 3);
        Coordinate secondTargetCoordinate = new Coordinate(3, 5);
        Coordinate thirdTargetCoordinate = new Coordinate(4, 1);

        Error error = this.game.move(originCoordinate, firstTargetCoordinate, secondTargetCoordinate, thirdTargetCoordinate);
        Piece whitePawn = this.game.getPiece(originCoordinate);
        Piece firstBlackPawn = this.game.getPiece(new Coordinate(6, 2));
        Piece secondBlackPawn = this.game.getPiece(new Coordinate(4, 4));
        Piece thirdBlackPawn = this.game.getPiece(new Coordinate(4, 4));

        assertThat(error, is(equalTo(Error.NOT_DIAGONAL)));
        assertNotNull(firstBlackPawn);
        assertNotNull(secondBlackPawn);
        assertNotNull(thirdBlackPawn);
        assertNotNull(whitePawn);

    }

    @Test
    public void testMoveEatingThreePieceDiagonal() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "        ",
                        "        ",
                        "     n  ",
                        "        ",
                        "   n    ",
                        "        ",
                        " n      ",
                        "b       ")
                .build();
        //@formatter:on

        Coordinate originCoordinate = new Coordinate(7, 0);
        Coordinate firstTargetCoordinate = new Coordinate(5, 2);
        Coordinate secondTargetCoordinate = new Coordinate(3, 4);
        Coordinate thirdTargetCoordinate = new Coordinate(1, 6);

        Error error = this.game.move(originCoordinate, firstTargetCoordinate, secondTargetCoordinate, thirdTargetCoordinate);
        Piece whitePawn = this.game.getPiece(thirdTargetCoordinate);
        Piece firstBlackPawn = this.game.getPiece(new Coordinate(6, 1));
        Piece secondBlackPawn = this.game.getPiece(new Coordinate(4, 3));
        Piece thirdBlackPawn = this.game.getPiece(new Coordinate(2, 5));

        assertNull(error);
        assertNull(firstBlackPawn);
        assertNull(secondBlackPawn);
        assertNull(thirdBlackPawn);
        assertNotNull(whitePawn);

    }

    @Test
    public void testMoveNotAdvanced() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        " b      ",
                        "        ")
                .build();
        //@formatter:on

        Coordinate originCoordinate = new Coordinate(6, 1);
        Coordinate targetCoordinate = new Coordinate(7, 0);

        Error error = this.game.move(originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.NOT_ADVANCED)));

    }

    @Test
    public void testMoveWithEating() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        " b      ",
                        "        ")
                .build();
        //@formatter:on

        Coordinate originCoordinate = new Coordinate(6, 1);
        Coordinate targetCoordinate = new Coordinate(4, 3);

        Error error = this.game.move(originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.WITHOUT_EATING)));

    }

    @Test
    public void testMoveTooMuchAdvanced() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "  n     ",
                        " b      ",
                        "        ")
                .build();
        //@formatter:on

        Coordinate originCoordinate = new Coordinate(6, 1);
        Coordinate targetCoordinate = new Coordinate(3, 4);

        Error error = this.game.move(originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.TOO_MUCH_ADVANCED)));

    }

    @Test
    public void testMoveTooMuchEating() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "       B",
                        "      n ",
                        "     n  ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ")
                .build();

        //@formatter:on

        Coordinate originCoordinate = new Coordinate(0, 7);
        Coordinate targetCoordinate = new Coordinate(3, 4);

        Error error = this.game.move(originCoordinate, targetCoordinate);
        assertThat(error, is(equalTo(Error.TOO_MUCH_EATINGS)));

    }

    @Test
    public void testMoveTooMuchJumping() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "        ",
                        "  n     ",
                        " b      ")
                .build();

        //@formatter:on

        Coordinate originCoordinate = new Coordinate(7, 1);
        Coordinate firstTargetCoordinate = new Coordinate(5, 3);
        Coordinate secondTargetCoordinate = new Coordinate(4, 4);

        Error error = this.game.move(originCoordinate, firstTargetCoordinate, secondTargetCoordinate);
        assertThat(error, is(equalTo(Error.TOO_MUCH_JUMPS)));

    }

    // This test is ignored because this case works but it's a bug no resolve yet
    @Test
    @Ignore
    public void testMoveEatingDoubleJumps() {

        //@formatter:off
        this.game = new GameBuilder()
                .board( "       B",
                        "      n ",
                        "        ",
                        "        ",
                        "   n    ",
                        "        ",
                        "        ",
                        "        ")
                .build();

        //@formatter:on

        Coordinate originCoordinate = new Coordinate(0, 7);
        Coordinate targetCoordinate = new Coordinate(5, 2);

        Error error = this.game.move(originCoordinate, targetCoordinate);
        assertNotNull(error);

    }
}
