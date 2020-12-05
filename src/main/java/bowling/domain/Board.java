package bowling.domain;

import bowling.domain.frames.Frames;
import bowling.domain.scores.Scores;
import bowling.dto.BoardDto;

import static java.util.stream.Collectors.toList;

class Board {
    private final Frames frames = new Frames();
    private final Scores scores = new Scores();

    void update(Pins pins) {
        frames.update(pins);
        scores.accumulate(frames.subList(scores.size(), frames.size())
                .stream()
                .filter(frame -> frame.hasScore(pins))
                .map(frame -> frame.getScore(pins))
                .collect(toList()));
    }

    BoardDto exportBoardDto() {
        return new BoardDto(
                frames.exportFramesDto(),
                scores.exportScoresDto()
        );
    }
}
