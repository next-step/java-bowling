package bowling.application;

import bowling.domain.Bowling;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.framestatus.FrameStatus;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private Bowling bowling;

    public Response(Bowling bowling) {
        this.bowling = bowling;
    }

    public List<FrameStatus> getFrameStatuses() {
        List<FrameStatus> frameStatuses = new ArrayList<>();
        for (Frame defaultFrame : bowling.getDefaultFrames()) {
            NormalFrame normalFrame = (NormalFrame) defaultFrame;
            frameStatuses.add(normalFrame.getFrameStatus());
        }
        return frameStatuses;
    }

    public String displayPlayer() {
        return bowling.displayPlayer();
    }

}
