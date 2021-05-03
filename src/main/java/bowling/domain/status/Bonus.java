package bowling.domain.status;

import bowling.domain.BonusPitch;
import bowling.domain.Pitch;

public class Bonus implements Status {
    private static final int BONUS_PITCH_COUNT = 1;
    private static final int ALREADY_USED_PITCH_COUNT = 1;

    private final Pitch current;
    private final BonusPitch bonusPitch;

    public Bonus(Pitch current, int addedBonusCount, Status status) {
        if (addedBonusCount <= 0) {
            throw new IllegalArgumentException("보너스 투구는 보너스 투구 횟수가 존재해야 합니다.");
        }
        this.current = current;
        this.bonusPitch = new BonusPitch(addedBonusCount, status);
    }

    @Override
    public Status roll(int fallenPins) {
        Pitch pitch = new Pitch(fallenPins);
        if (pitch.isStrike()) {
            return new Bonus(pitch, BONUS_PITCH_COUNT, new Strike());
        }

        if (current.isSpare(fallenPins)) {
            return new Bonus(pitch, BONUS_PITCH_COUNT, new Spare());
        }

        return new Bonus(pitch, BONUS_PITCH_COUNT, new Hold(pitch));
    }

    @Override
    public boolean isEnd() {
        return bonusPitch.addedBonusCount() - ALREADY_USED_PITCH_COUNT <= 0;
    }

    @Override
    public boolean hasBonusPitch() {
        return false;
    }

    @Override
    public int bonusPitchCount() {
        return bonusPitch.addedBonusCount() - ALREADY_USED_PITCH_COUNT;
    }

    @Override
    public String display() {
        return bonusPitch.display();
    }
}
