package bowling.domain.frame;

import bowling.domain.pin.NormalPins;

public class NormalFrame extends Frame{
    private int score;
    private NormalPins normalPins;

    public NormalFrame(){
        score = INIT_SCORE;
        normalPins = new NormalPins();
    }

    @Override
    public void bowl(int pin) {
        normalPins.bowl(pin);
    }

    @Override
    public boolean isEnd() {
        return normalPins.isEnd();
    }

    @Override
    public int bonusAmount() {
        if(normalPins.isStrike()){
            return STRIKE_BONUS;
        }
        if(normalPins.isSpare()){
            return SPARE_BONUS;
        }
        return NO_BONUS;
    }

}
