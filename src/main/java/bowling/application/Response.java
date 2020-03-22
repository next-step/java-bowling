package bowling.application;

import bowling.domain.Bowling;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

public class Response {

    private Bowling bowling;

    public Response(Bowling bowling) {
        this.bowling = bowling;
    }

    public String view() {
        StringBuilder frames = new StringBuilder();
        for (Frame defaultFrame : bowling.getDefaultFrames()) {
            NormalFrame normalFrame = (NormalFrame) defaultFrame;
            frames.append(normalFrame.getFrameStatus().display());
        }
        return frames.toString();
    }

}
