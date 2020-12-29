package bowling.domain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class DefaultScoreSheet implements ScoreSheet {

    private final int NUM_OF_FRAMES = 10;
    private final Player player;
    private List<Frame> frames = new ArrayList<>();
    private Frame currentFrame;

    public DefaultScoreSheet(Player player) {
        this.player = player;
    }

    @Override
    public boolean isAllMarked() {
        return frames.size() == NUM_OF_FRAMES;
    }

    @Override
    public Frame nextFrame() {
        if (currentFrame == null) {
            return addFrame(Frame.first());
        }

        if (!currentFrame.isEnd()) {
            throw new IllegalStateException(MessageFormat.format("현재 {0} 번째 프래임 진행중입니다. 현재 프래임이 완료된 후 다음 프레임을 진행 할 수 있습니다", currentFrame.getFrameNo()));
        }

        return addFrame(currentFrame.nextFrame());
    }

    @Override
    public ScoreSheetReader getReader() {
        return new DefaultScoreSheetReader(player, frames);
    }

    private Frame addFrame(Frame frame) {
        frames.add(frame);
        currentFrame = frame;
        return frame;
    }


}
