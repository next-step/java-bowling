package bowling.domain.frame;

public interface Frame {

    Frame bowling(int count);

    Frame next(int count);

    int totalCount();

    boolean isFinal();
}
