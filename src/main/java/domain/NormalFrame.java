package domain;

import utils.StringUtils;

public class NormalFrame extends Frame {
    private static int currentFrame = 1;
    private int chance = 2;
    public NormalFrame() {
    }

    public NormalFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    @Override
    public Frame nextFrame() {
        currentFrame++;
        if(isFinal()){
            return new FinalFrame();
        }
        return new NormalFrame();
    }

    @Override
    public void addPin(Pin pin) {
        useChance();
        pins.addPin(pin);
    }

    private void useChance() {
        chance--;
    }

    @Override
    public String toString() {
        String str = pins.toString();

        if(pins.isSpare()){
            if(!pins.isStrike()){
                str = StringUtils.makeSpareStr(str);
            }
        }
        return str;
    }

    @Override
    public boolean isStrikePass() {
        return pins.isStrike();
    }

    @Override
    public boolean isFinal() {
        return currentFrame == Frame.MAX_FRAME;
    }

    @Override
    public boolean isEmptyChane() {
        return chance == 0;
    }

}
