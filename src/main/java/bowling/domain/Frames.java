package bowling.domain;

import java.util.Stack;

public class Frames {
    private final Stack<Frame> frames;

    public static Frames init() {
        Stack<Frame> frames = new Stack<>();
        frames.add(Frame.init());
        return new Frames(frames);
    }

    private Frames(Stack<Frame> frames) {
        this.frames = frames;
    }

    public Inning currentInning() {
        Frame frame = frames.peek();
        return frame.inning();
    }

    public void recodePitch(Pitch pitch) {
        Frame currentFrame = currentFrame();
        Frame recodeFrame = currentFrame.recode(pitch);

        if (currentFrame.equals(recodeFrame)) {//같은 이닝일때는 새로 기록된 이닝을 넣는다
            frames.add(recodeFrame);
            return;
        }
        //새로운 이닝일때는 이전 기록을 넣고 추가로 넣는다
        frames.add(currentFrame);
        frames.add(recodeFrame);

    }

    private Frame currentFrame() {
        return frames.pop();
    }

    public boolean nonOver() {
        if (frames.size() > 10) {
            return false;
        }
        if (frames.size() == 10 && frames.peek().finish()) {
            return false;
        }
        return true;
    }

    public Stack<Frame> getFrames() {
        return frames;
    }
}
