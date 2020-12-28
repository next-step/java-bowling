package bowling.domain;

import java.util.List;

public class DefaultScoreSheetReader implements ScoreSheetReader {

    private static final int LAST_FRAME_NO = 10;
    private final Player player;
    private final List<Frame> frames;
    private int nextFrameIdx = 0;

    public DefaultScoreSheetReader(Player player, List<Frame> frames) {
        this.player = player;
        this.frames = frames;
    }

    @Override
    public boolean isEOF() {
        return nextFrameIdx == LAST_FRAME_NO;
    }

    @Override
    public String readPlayName() {
        return player.getName();
    }

    @Override
    public FrameData readFrameData() {
        if (isEOF()) return null;
        if (nextFrameIdx >= frames.size()) return FrameData.empty(nextFrameIdx++ +1);
        return FrameData.of(frames.get(nextFrameIdx++));
    }

}
