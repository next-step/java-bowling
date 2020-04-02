package bowling.domain.state;

import bowling.domain.frame.Score;

public class Bonus extends Finished {
    private static final int MAX_FALLEN_PINS = 10;
    private static final int MIN_FALLEN_PINS = 0;

    private int firstFallenPins;
    private int secondFallenPins;
    private int bonus;

    public Bonus(int firstFallenPins, int secondFallenPins, int bonus) {
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
    public State bowl(int pins) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String display() {
        return convert(firstFallenPins, secondFallenPins) + "|" + convert(bonus);
    }

    @Override
    public Score getScore() {
        return new Score(this.firstFallenPins + this.secondFallenPins + bonus, 0);
    }

    @Override
    public Score calculateByBeforeScore(Score before) {
        before = before.bowl(this.firstFallenPins);
        if (before.isCalculation()) {
            return before;
        }
        return before;
    }
}
