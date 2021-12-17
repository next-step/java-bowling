package bowling.domain.state;

import bowling.domain.*;

public interface State {

    default void run(Pitch pitch, Frame frame) {
        pitch.run();
        FrameInfo frameInfo = frame.info();
        frameInfo.addPitch(pitch);
        frame.changeState();
    }

    boolean progressing();

    boolean retryable();
}
