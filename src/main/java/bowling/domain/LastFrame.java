package bowling.domain;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class LastFrame implements Frame {
    private final LinkedList<Pins> fallenPins = new LinkedList<>();
    private int leftBonusCount;

    public LastFrame() {
        fallenPins.add(new Pins());
        this.leftBonusCount = 2;
    }

    @Override
    public Frame bowl(int fallenPinCount) {
        if (isBonusLeft()) {
            fallenPins.add(bonusBowl(fallenPinCount));
            return this;
        }

        Pins firstPins = fallenPins.getFirst();

        firstPins.bowl(fallenPinCount);
        leftBonusCount -= 1;

        if (firstPins.isFinish()) {
            leftBonusCount += addBonusCount(firstPins);
        }

        return this;
    }

    private Pins bonusBowl(int fallenPinCount) {
        Pins bonusPins = new Pins();
        bonusPins.bowl(fallenPinCount);
        leftBonusCount -= 1;

        return bonusPins;
    }

    private boolean isBonusLeft() {
        Pins firstPins = fallenPins.getFirst();
        if (firstPins.isFinish()) {
            return leftBonusCount > 0;
        }
        return false;
    }

    private int addBonusCount(Pins firstPins) {
        if (firstPins.isStrike()) {
            return 1;
        }
        if (firstPins.isSpare()) {
            return 1;
        }
        return 0;
    }

    @Override
    public int getNumber() {
        return 10;
    }

    @Override
    public String getRecord() {
        return fallenPins.stream()
                .map(Pins::getDescription)
                .collect(Collectors.joining("|"));
    }

    public boolean isGameEnd() {
        return leftBonusCount == 0;
    }
}
