package bowling.domain.scoreType;

public interface StatusProvider {
    boolean isFinished();

    boolean isClear();

    int getBonusCount();
}
