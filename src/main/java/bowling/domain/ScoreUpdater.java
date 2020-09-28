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

        enqueue(frame);
    }

    private boolean isNotNormalFrame(Frame frame) {
        return !(frame instanceof NormalFrame);
    }

    private void enqueue(Frame frame) {
        if (((NormalFrame) frame).needUpdate()) {
            queue.add(frame);
        }
    }

    public void update(int score) {

        int updateFrameCount = queue.size();

        while(updateFrameCount > 0 && !queue.isEmpty()) {
            updateFrameCount--;

            NormalFrame frame = (NormalFrame) queue.poll();
            frame.updateScore(score);

            checkNeedMoreUpdateFrame(frame);
        }
    }

    private void checkNeedMoreUpdateFrame(NormalFrame frame) {
        if (frame.needUpdate()) {
            queue.add(frame);
        }
    }
}
