package bowling.domain;

import bowling.domain.scores.Scores;
import bowling.dto.BoardDto;

import static java.util.stream.Collectors.toList;

class Board implements Observer<Rolls> {
    private final Frames frames = new Frames();
    private final Scores scores = new Scores();

    @Override
    public void update(Subject<Rolls> subject) {
        Rolls rolls = subject.get();
        update(rolls);
    }

    private void update(Rolls rolls) {
        frames.update(rolls);
        scores.accumulate(frames.subList(scores.size(), frames.size())
                .stream()
                .filter(frame -> frame.hasScore(rolls))
                .map(frame -> frame.getScore(rolls))
                .collect(toList()));
    }

    BoardDto exportBoardDto() {
        return new BoardDto(
                frames.exportFramesDto(),
                scores.exportScoresDto()
        );
    }
}
