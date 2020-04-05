package bowling.domain.state;

import bowling.domain.frame.Score;

public class Bonus extends Finished {
    private static final int MAX_FALLEN_PINS = 10;
    private static final int MIN_FALLEN_PINS = 0;

    private Pin firstFallenPins;
    private Pin secondFallenPins;
    private Pin bonus;

    public Bonus(Pin firstFallenPins, Pin secondFallenPins, Pin bonus) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.bonus = bonus;
    }

    private String convert(int firstFallenPins, int secondFallenPins) {
        String first = String.valueOf(firstFallenPins);
        String second;
        if (firstFallenPins == MAX_FALLEN_PINS) {
            first = convertByPins(firstFallenPins);
            second = convertByPins(secondFallenPins);
            return String.format("%s|%s", first, second);
        }
        second = convertBySpare(firstFallenPins, secondFallenPins);
        return String.format("%s|%s", first, second);
    }

    private String convertByPins(int pins) {
        if (pins == MAX_FALLEN_PINS) {
            return "X";
        }
        return String.valueOf(pins);
    }

    private String convertBySpare(int firstFallenPins, int secondFallenPins) {
        if (firstFallenPins + secondFallenPins == MAX_FALLEN_PINS) {
            return "/";
        }
        return String.valueOf(secondFallenPins);
    }

    private String convert(int bonus) {
        if (bonus == MAX_FALLEN_PINS) {
            return "X";
        }
        return convertByMiss(bonus);
    }

    private String convertByMiss(int bonus) {
        if (bonus == MIN_FALLEN_PINS) {
            return "-";
        }
        return String.valueOf(bonus);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String display() {
        return convert(firstFallenPins.getFallenPins(), secondFallenPins.getFallenPins()) + "|" + convert(bonus.getFallenPins());
    }

    @Override
    public Score calculateByBeforeScore(Score before) {
        before = before.bowl(bonus.getFallenPins());
        if (before.isCalculation()) {
            return before;
        }
        return before;
    }
}
