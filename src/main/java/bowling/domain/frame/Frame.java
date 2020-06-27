package bowling.domain.frame;

public interface Frame {
    Frame createNext(boolean isNextLast);

    boolean canAddMoreScore();
}
