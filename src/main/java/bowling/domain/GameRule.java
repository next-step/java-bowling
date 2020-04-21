package bowling.domain;

public class GameRule {
    private static final int FIRST_INDEX = 0;
    private static final int ONE = 1;

    private int playerCount;
    private int turn;

    public GameRule(int playerCount) {
        this.playerCount = playerCount;
        turn = FIRST_INDEX;
    }

    public int getTurn() {
        return turn;
    }

    private int nextIndex() {
        return (turn + ONE) % playerCount;
    }

    public void changeTurn() {
        turn = nextIndex();
    }
}
