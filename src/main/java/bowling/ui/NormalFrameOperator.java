package bowling.ui;

import bowling.domain.rolling.Rolling;

@FunctionalInterface
public interface NormalFrameOperator {

    boolean display(Rolling first, Rolling second);

}
