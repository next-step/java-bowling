package bowling.domain.frames;

import bowling.domain.KnockDownPins;
import bowling.dto.Frame2Dto;
import bowling.dto.Frames2Dto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Frames2 {
    public static final int MAX_FRAME_SIZE = 10;
    private LinkedList<Frame2> value;

    private Frames2() {
        value = new LinkedList<>();
        value.add(Frame2.getFirstFrame());
    }

    public static Frames2 init() {
        return new Frames2();
    }

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        Frame2 currentFrame = value.getLast();
        currentFrame.setKnockDownPins(knockDownPins);

        if (currentFrame.isEnd()) {
            Frame2 nextFrame = currentFrame.getNextFrame();
            value.add(nextFrame);
        }
    }

    public boolean isEnd() {
        return value.size()  == MAX_FRAME_SIZE;
    }

    public int getCurrentFrameIndex() {
        return value.size();
    }

    public Frames2Dto convertToDto() {
        List<Frame2Dto> frame2Dtos = new ArrayList<>();
        Integer previousTotalScore = 0;
        for (Frame2 frame2 : value) {
            Frame2Dto frame2Dto = frame2.convertToFrameDto(previousTotalScore);
            frame2Dtos.add(frame2Dto);
            previousTotalScore = frame2Dto.getTotalScore();
        }
        return Frames2Dto.of(frame2Dtos);
    }
}
