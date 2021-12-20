package bowling.domain.state;

import bowling.domain.*;
import bowling.domain.frame.Frame;

public interface State {

    State run(Pitch pitch, Frame frame);

    boolean progressing();
}
