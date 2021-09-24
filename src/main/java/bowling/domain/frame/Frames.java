package bowling.domain.frame;

import java.util.Stack;

public class Frames {
    private final Stack<Frame> frames;

    private Frames(Stack<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        Stack<Frame> frames = new Stack<>();
        return new Frames(frames);
    }

    public Inning currentInning() {
        Frame frame = frames.peek();
        return frame.inning();
    }

    public void recodePitch(Pitch pitch) {
        if (frames.isEmpty()) {
            frames.add(Frame.of(Inning.first(), pitch));
            return;
        }
        Frame currentFrame = currentFrame();
        Frame recodeFrame = currentFrame.recode(pitch);

        if (currentFrame.equals(recodeFrame)) {//같은 이닝일때는 새로 기록된 프레임을 같은 이닝에 넣는다
            frames.add(recodeFrame);
            return;
        }

        if (frames.size() == 10) {//보너스 프레임인 경우 넣지 않는다.
            frames.add(currentFrame);
            return;
        }
        //새로운 이닝일때는 이전 프레임과 새 프레임을 넣는다
        frames.add(currentFrame);
        frames.add(recodeFrame);
    }

    private Frame currentFrame() {
        return frames.pop();
    }

    public boolean over() {
        if (frames.size() > 10) {
            return true;
        }
        if (frames.size() == 10 && frames.peek().finish()) {
            return true;
        }
        return false;
    }

    public boolean isLastFrame() {
        return frames.size() == 10;
    }

    public Stack<Frame> getFrames() {
        return frames;
    }

}
