package bowling.constant;

public enum ScoringStatus {

    SPARE("/", GameState.FINISH_CURRENT_FRAME),
    STRIKE("X", GameState.FINISH_CURRENT_FRAME),
    EMPTY("", GameState.PLAYING),
    PLAYING("", GameState.PLAYING),
    DONE("", GameState.FINISH_CURRENT_FRAME);

    private String symbol;
    private GameState state;

    ScoringStatus(String symbol, GameState gameState) {
        this.symbol = symbol;
        this.state = gameState;
    }

    public String getSymbol() {
        return symbol;
    }

    public GameState getState() {
        return state;
    }
}
