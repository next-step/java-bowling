package bowling.domain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultScoreSheet implements ScoreSheet {

    private static final int NUM_OF_FRAMES = 10;

    private final Player player;
    private List<Frame> frames = new ArrayList<>();

    public DefaultScoreSheet(Player player) {
        this.player = player;
    }

    @Override
    public boolean isAllMarked() {
        return frames.size() == NUM_OF_FRAMES;
    }

    @Override
    public Frame nextFrame() {
        if (frames.size() == 0) {
            return addFrame(FrameFactory.createFirstFrame());
        }

        if (isCurrentFrameNotEnd()) {
            throw new IllegalStateException(MessageFormat.format("현재 {0} 번째 프래임 진행중입니다. 현재 프래임이 완료된 후 다음 프레임을 진행 할 수 있습니다", getCurrentFrame().getFrameNo()));
        }

        return addFrame(getCurrentFrame().createNext());
    }

    private boolean isCurrentFrameNotEnd() {
        return !getCurrentFrame().isEnd();
    }

    private Frame getCurrentFrame(){
        return frames.get(frames.size()-1);
    }

    @Override
    public String getPlayerName() {
        return player.getName();
    }

    @Override
    public List<FrameInfo> getFrameInfos() {
        return frames.stream()
                .map(Frame::toFrameInfo)
                .collect(Collectors.toList());
    }

    @Override
    public ScoreSheetReader getReader() {
        return new DefaultScoreSheetReader(this);
    }

    private Frame addFrame(Frame frame) {
        frames.add(frame);
        return frame;
    }


}
