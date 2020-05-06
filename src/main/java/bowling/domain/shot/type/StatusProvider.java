package bowling.domain.shot.type;

public interface StatusProvider {
    boolean isFinished();

    boolean isClear();

    int getBonusCount();
}
