package bowling.ui;

import bowling.domain.frame.Rolling;

@FunctionalInterface
public interface NormalFrameOperator {

    boolean display(Rolling first, Rolling second);

}
