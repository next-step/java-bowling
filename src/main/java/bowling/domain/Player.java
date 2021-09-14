package bowling.domain;

public class Player {
    private final PlayerName playerName;
    private Frames frames = new Frames();

    public Player(String playerName) {
        this.playerName = new PlayerName(playerName);
    }

    public String getName() {
        return playerName.getValue();
    }

    public int getFrameIndex() {
        return frames.size();
    }

    public Frame startFrame() {
        return frames.getFirstFrame();
    }

    public Frame getFrame(int frameIndex) {
        return frames.getFrame(frameIndex);
    }

    public void addFrame(Frame frame) {
        frames.addFrame(frame);
    }
}
