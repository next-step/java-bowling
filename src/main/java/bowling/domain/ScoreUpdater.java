package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.LinkedList;
import java.util.Queue;

public class ScoreUpdater {

    private final Queue<Frame> queue;

    public ScoreUpdater() {
        queue = new LinkedList<>();
    }

    public void checkFrameNeedUpdate(Frame frame) {

        if (isNotNormalFrame(frame)) {
            return;
        }

        NormalFrame normalFrame = (NormalFrame) frame;

        if (normalFrame.needUpdate()) {
            queue.add(frame);
        }
    }

    private boolean isNotNormalFrame(Frame frame) {
        return !(frame instanceof NormalFrame);
    }

    public void update(int score) {

        int updateFrameCount = queue.size();

        while(updateFrameCount > 0 && !queue.isEmpty()) {
            updateFrameCount--;

            NormalFrame frame = (NormalFrame) queue.poll();
            frame.updateScore(score);

            if (frame.needUpdate()) {
                queue.add(frame);
            }
        }
    }
}
