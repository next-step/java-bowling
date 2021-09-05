package bowling.step2.domain;

import bowling.step2.domain.pitchresult.PitchResultGroup;
import bowling.step2.domain.visitor.FrameVisitor;

import java.util.List;

public interface Frame {
    void pitch(int count);

    Frame nextFrame(FrameVisitor frameVisitor);

    boolean finished();

    List<Integer> current();

    PitchResultGroup createResult();
}
