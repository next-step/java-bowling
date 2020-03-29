package bowling.domain.state;

import bowling.domain.frame.Score;

public class Bonus implements State {

    private int firstFallenPins;
    private int secondFallenPins;
    private int bonus;
    private boolean finish;
    private String display;

    public Bonus(int firstFallenPins, int secondFallenPins) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.finish = false;
        this.display = convert(firstFallenPins, secondFallenPins)+"  ";
    }

    public Bonus(int firstFallenPins, int secondFallenPins, int bonus) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.finish = true;
        this.bonus = bonus;
        this.display = convert(firstFallenPins, secondFallenPins)+"|"+convert(bonus);
    }

    private String convert(int firstFallenPins, int secondFallenPins) {
        String first = String.valueOf(firstFallenPins);
        String second;
        if (firstFallenPins == 10) {
            first = convertByPins(firstFallenPins);
            second = convertByPins(secondFallenPins);
            return String.format("%s|%s", first, second);
        }
        second = convertBySpare(firstFallenPins, secondFallenPins);
        return String.format("%s|%s", first, second);
    }

    private String convertByPins(int pins) {
        if (pins == 10) {
            return "X";
        }
        return String.valueOf(pins);
    }

    private String convertBySpare(int firstFallenPins, int secondFallenPins) {
        if (firstFallenPins + secondFallenPins == 10) {
            return  "/";
        }
        return String.valueOf(secondFallenPins);
    }

    private String convert(int bonus) {
        if (bonus == 10) {
            return "X";
        }
        return convertByMiss(bonus);
    }

    private String convertByMiss(int bonus) {
        if (bonus == 0) {
            return "-";
        }
        return String.valueOf(bonus);
    }

    @Override
    public State bowl(int pins) {
        return new Bonus(firstFallenPins, secondFallenPins, pins);
    }

    @Override
    public boolean isFinish() {
        return this.finish;
    }

    @Override
    public String display() {
        return this.display;
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

    @Override
    public void renewScore(Score score) {

    }
}
