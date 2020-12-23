package bowling.domain;

public interface Frame {
    void add(Pitch pitch);

    int size();

    int getFirstOfKnockDown();

    int getSecondOfKnockDown();

    boolean isFirst();
}
