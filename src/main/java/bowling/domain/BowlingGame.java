package bowling.domain;

public class BowlingGame {
    private final Player player;
    private final FrameHistory frameHistory;

    public BowlingGame(Player player, FrameHistory frameHistory) {
        this.frameHistory = frameHistory;
        this.player = player;
    }

    public void doGame() {
        Frame frame = new Frame();
        for(int index = 0; index < RuleConfig.NUMBER_OF_FRAME - 1; index++) {
            frame.doFrame(frameHistory, player);
        }

        Frame lastFrame = new LastFrame();
        lastFrame.doFrame(frameHistory, player);
    }

}