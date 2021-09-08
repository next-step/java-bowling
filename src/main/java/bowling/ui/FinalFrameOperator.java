package bowling.ui;

import bowling.domain.frame.rolling.Rolling;

@FunctionalInterface
public interface FinalFrameOperator {

    boolean display(Rolling first, Rolling second, Rolling third);

}
