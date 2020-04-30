package bowling.domain.frameScore;

public interface HasBonusFrameScore extends FrameScore {
    void addBonus(int shot);
}
