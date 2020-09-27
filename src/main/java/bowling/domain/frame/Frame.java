package bowling.domain.frame;

public interface Frame {

    void swing(int score);

    boolean isEndedFrame();

    int getScore();

    String swingRecordsToString();
}
