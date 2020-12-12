package bowling.domain.bowl;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Pin;
import bowling.dto.BowlDto;
import bowling.dto.FrameEnumsDto;
import bowling.dto.FramesDto;
import bowling.dto.ScoresDto;

import java.util.LinkedList;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Bowl {
    private final LinkedList<Frame> frames = new LinkedList<>();
    private BowlState state = new FirstBowlState();

    void setState(BowlState state) {
        this.state = state;
    }

    void addFrame(Frame frame) {
        frames.add(frame);
    }

    public void addPin(Pin pin) {
        state.addPin(pin, this);
    }

    public boolean isPlayable() {
        return state.isPlayable(this);
    }

    public int getFrameNumber() {
        return state.getFrameNumber();
    }

    private FrameEnumsDto exportFrameEnumsDto() {
        return frames.stream()
                .map(Frame::exportFrameDto)
                .collect(collectingAndThen(toList(), FrameEnumsDto::new));
    }

    private FramesDto exportFramesDto() {
        return new FramesDto(state.exportPinsDto(), exportFrameEnumsDto());
    }

    private ScoresDto exportScoresDto() {
        Scores scores = new Scores();
        frames.stream()
                .filter(Frame::hasScore)
                .map(Frame::getCountOfPins)
                .forEach(scores::accumulate);
        return scores.exportScoresDto();
    }

    public BowlDto exportBowlDto() {
        return new BowlDto(exportFramesDto(), exportScoresDto());
    }
}
