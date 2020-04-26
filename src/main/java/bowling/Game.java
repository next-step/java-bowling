package bowling;

public class Game {
    private int[] rolls = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    public int getScore() {
        int score = 0;
        int firstRollInFrame = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isSpare(firstRollInFrame)) {
                score += 10 + rolls[firstRollInFrame + 2];
                firstRollInFrame += 2;
            } else if (isStrike(rolls[firstRollInFrame])){
                score += 10 + rolls[firstRollInFrame + 1] + rolls[firstRollInFrame + 2];
                firstRollInFrame += 1;
            }
            else {
                score += rolls[firstRollInFrame] + rolls[firstRollInFrame + 1];
                firstRollInFrame += 2;
            }
        }
        return score;
    }

    private boolean isStrike(int roll) {
        return roll == 10;
    }

    private boolean isSpare(int firstRollInFrame) {
        return rolls[firstRollInFrame] + rolls[firstRollInFrame + 1] == 10;
    }
}
