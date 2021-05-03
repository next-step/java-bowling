package bowling.domain;

import java.util.List;

public interface Frame {
    Frame roll(Pinfall pinfall);

    Frame roll(Pinfall pinfall, FrameFatory frameFatory);

    boolean isDone();

    FrameNumber frameNumber();

    PointSymbols pointSymbols();

    Score score();

    List<Pinfall> bonusPinfalls(int bonusPinfallCount);

    boolean hasNext();

    Frame next() ;

}
