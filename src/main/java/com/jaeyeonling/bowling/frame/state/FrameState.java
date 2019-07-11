package com.jaeyeonling.bowling.frame.state;

import com.jaeyeonling.bowling.frame.KnockdownPins;
import com.jaeyeonling.bowling.view.StringVisualizable;

public interface FrameState extends StringVisualizable {

    boolean isFinished();
    FrameState bowl(final KnockdownPins knockdownPins);
}
