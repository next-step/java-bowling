package bowling.domain;

import java.util.Arrays;
import java.util.List;

public interface FrameData {

    int getFrameNo();

    List<PinMark> getPinMarks();

    int getFrameScore();

    static FrameData of(Frame frame) {
        return new FrameData() {
            @Override
            public int getFrameNo() {
                return frame.getFrameNo();
            }

            @Override
            public List<PinMark> getPinMarks() {
                return frame.getPinMarks();
            }

            @Override
            public int getFrameScore() {
                return 0;
            }
        };
    }

    static FrameData empty(int frameNo) {
        return new FrameData() {
            @Override
            public int getFrameNo() {
                return frameNo;
            }

            @Override
            public List<PinMark> getPinMarks() {
                return Arrays.asList();
            }

            @Override
            public int getFrameScore() {
                return 0;
            }
        };
    }

}
