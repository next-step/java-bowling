package bowling.ui;

import bowling.domain.frame.Rolling;

@FunctionalInterface
public interface FinalFrameOperator {

    boolean display(Rolling first, Rolling second, Rolling third);

}
