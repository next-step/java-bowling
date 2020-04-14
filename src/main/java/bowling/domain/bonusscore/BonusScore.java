package bowling.domain.bonusscore;

/**
 * 스트라이크, 스페어 일때 보너스 점수를 계산하기 위한 인터페이스
 */
public interface BonusScore {
    void add(int score);

    boolean isAddable();

    int getBonusPoint(int frameIndex);
}
