package bowling.domain;

import java.util.List;

public interface Frame {

    Frame hit(int count);

    int getNumber();

    boolean isFinish();

    List<String> toResults();

    Score getScore();

    Score additionalScore(Score beforeScore);
}