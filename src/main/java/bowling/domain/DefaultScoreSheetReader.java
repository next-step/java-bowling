package bowling.domain;

import java.util.List;

public class DefaultScoreSheetReader implements ScoreSheetReader {

    private final Player player;
    private final List<Frame> frames;
    private int nextFrameIdx = 0;

    public DefaultScoreSheetReader(Player player, List<Frame> frames) {
        this.player = player;
        this.frames = frames;
    }

    @Override
    public boolean isEOF() {
        return frames.size() <= nextFrameIdx;
    }

    @Override
    public String readPlayName() {
        return player.getName();
    }

    @Override
    public FrameData readFrameData() {
        if (isEOF()) return null;

        return FrameData.of(frames.get(nextFrameIdx++));
    }

}
