package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultScoreSheetReader implements ScoreSheetReader {

    private static final int LAST_FRAME_NO = 10;
    private final Player player;
    private final List<FrameData> frameData;
    private int nextIdx = 0;

    public DefaultScoreSheetReader(Player player, List<Frame> frames) {
        this.player = player;
        this.frameData = frames.stream()
                .map(Frame::toFrameData)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isEOF() {
        return nextIdx == LAST_FRAME_NO;
    }

    @Override
    public String readPlayName() {
        return player.getName();
    }

    @Override
    public FrameData readFrameData() {
        if (isEOF()) return null;
        if (nextIdx >= frameData.size()) return FrameData.blank(nextIdx++ + 1);
        return frameData.get(nextIdx++);
    }

}
