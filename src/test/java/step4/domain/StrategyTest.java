package step4.domain;

import step4.strategy.PitchesStrategy;

public class StrategyTest {
    public static final PitchesStrategy TEST_RETURN_STRATEGY = (value) -> value;
    public static final PitchesStrategy T10 = (value) -> 10;
    public static final PitchesStrategy T9 = (value) -> 9;
    public static final PitchesStrategy T8 = (value) -> 8;
    public static final PitchesStrategy T7 = (value) -> 7;
    public static final PitchesStrategy T6 = (value) -> 6;
    public static final PitchesStrategy T5 = (value) -> 5;
    public static final PitchesStrategy T4 = (value) -> 4;
    public static final PitchesStrategy T3 = (value) -> 3;
    public static final PitchesStrategy T2 = (value) -> 2;
    public static final PitchesStrategy T1 = (value) -> 1;
    public static final PitchesStrategy T0 = (value) -> 0;
}
