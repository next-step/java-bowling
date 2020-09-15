package bowling;

public class ScoreBoard {

    private static final int START_FRAME = 1;
    private final Player player;
    private Frame currentFrame;

    private ScoreBoard(Player player, Frame frame) {
        this.player = player;
        this.currentFrame = frame;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getCurrentFrame() {
        return currentFrame.getFrame();
    }

    public void bowl(int falledPins) {
        currentFrame.bowl(falledPins);
        if (currentFrame.isFinish()) {
            currentFrame = currentFrame.getNextFrame();
        }
    }

    public static ScoreBoard of(Player player, int totalFrames) {
        return new ScoreBoard(player, Frame.of(START_FRAME, totalFrames));
    }
}
