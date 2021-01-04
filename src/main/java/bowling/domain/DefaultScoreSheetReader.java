package bowling.domain;

import java.util.List;

public class DefaultScoreSheetReader implements ScoreSheetReader {

    private static final int LAST_FRAME_NO = 10;

    private final String playerName;
    private final List<FrameInfo> frameInfos;
    private int nextIdx = 0;

    public DefaultScoreSheetReader(ScoreSheet scoreSheet) {
        this.playerName = scoreSheet.getPlayerName();
        this.frameInfos = scoreSheet.getFrameInfos();
    }

    @Override
    public boolean isEOF() {
        return nextIdx == LAST_FRAME_NO;
    }

    @Override
    public String readPlayName() {
        return playerName;
    }

    @Override
    public FrameInfo readFrameInfo() {
        if (isEOF()) return null;
        if (nextIdx >= frameInfos.size()) return FrameInfo.blank(++nextIdx);
        return frameInfos.get(nextIdx++);
    }

}
