package domain;

import utils.StringUtils;

public class FinalFrame extends Frame {
    private final boolean isFinal = Boolean.TRUE;
    private int chance = 3;

    @Override
    public Frame nextFrame() {
        return null;
    }

    @Override
    public void addPin(Pin pin) {
        useChance();
        pins.addFinalPin(pin);
    }

    private void useChance() {
        if (chance < 0) {
            chance--;
        }
    }

    @Override
    public String toString() {
        if (pins.isSpare()) {
            return StringUtils.makeFinalSpare(pins.toString());
        }
        return pins.toString();
    }

    @Override
    public boolean isStrikePass() {
        return false;
    }

    @Override
    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public boolean isEmptyChane() {
        return chance == 0 || (chance == 1 && (pins.isNotSpare()));
    }
}
