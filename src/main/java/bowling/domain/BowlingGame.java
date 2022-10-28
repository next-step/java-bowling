package bowling.domain;

public class BowlingGame {
    private final Player player;
    private final Records records;

    public Records getRecords() {
        return records;
    }

    public Player getPlayer() {
        return player;
    }

    public BowlingGame(Player player) {
        this.frameHistory = new FrameHistory();
        this.player = player;
    }

    public void doGame() {
        Frame frame = new Frame();
        for(int index = 0; index < RuleConfig.NUMBER_OF_FRAME - 1; index++) {
            frame.doFrame(frameHistory, player);
        }

        Frame finalFrame = new FinalFrame();
        finalFrame.doFrame(frameHistory, player);
    }

}