package bowling.strategy;

import bowling.utils.RandomIntNumberUtil;

public class RandomPitchNumberStrategy implements PitchNumberStrategy {
    private static final int INCLUDE_BOUND_SIZE = 1;

    @Override
    public int generate(int bound) {
        return RandomIntNumberUtil.nextInt(bound + INCLUDE_BOUND_SIZE);
    }
}
