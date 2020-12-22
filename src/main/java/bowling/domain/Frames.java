package bowling.domain;

import bowling.dto.FrameDto;
import bowling.dto.FramesDto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Frames {
    public static final int MAX_FRAME_SIZE = 10;
    private final LinkedList<Frame> value;

    private Frames() {
        value = new LinkedList<>();
        value.add(Frame.getFirstFrame());
    }

    public static Frames init() {
        return new Frames();
    }

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        Frame currentFrame = value.getLast();
        currentFrame.setKnockDownPins(knockDownPins);

        if (currentFrame.isEnd() && value.size() < MAX_FRAME_SIZE) {
            int currentFrameNo = getCurrentFrameNo();
            Frame nextFrame = currentFrame.getNextFrame(currentFrameNo + 1);
            value.add(nextFrame);
        }
    }

    public boolean isEnd() {
        Frame last = value.getLast();
        return value.size() == MAX_FRAME_SIZE && last.isEnd();
    }

    public int getCurrentFrameNo() {
        return value.size();
    }

    public FramesDto convertToDto() {
        List<FrameDto> frameDtos = new ArrayList<>();
        Integer previousTotalScore = 0;
        for (Frame frame : value) {
            FrameDto frameDto = frame.convertToFrameDto(previousTotalScore);
            frameDtos.add(frameDto);
            previousTotalScore = frameDto.getTotalScore();
        }
        return FramesDto.of(frameDtos);
    }

    @Override
    public String toString() {
        return "Frames{" +
                "value=" + value +
                '}';
    }
}
