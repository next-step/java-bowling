package bowling.domain.state;

import bowling.domain.*;

public interface State {

    void run(Pitch pitch, Frame frame);

    boolean progressing();

    boolean retryable();
}
