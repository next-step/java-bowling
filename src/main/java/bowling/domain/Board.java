package bowling.domain;

import bowling.dto.BoardDto;

import static java.util.stream.Collectors.toList;

class Board {
    private final Frames frames = new Frames();
    private final Scores scores = new Scores();

    void update(Rolls rolls) {
        frames.update(rolls);
        scores.accumulate(frames.subList(scores.size(), frames.size())
                .stream()
                .map(frame -> frame.score(rolls))
                .filter(score -> score >= 0)
                .collect(toList()));
    }

    BoardDto exportBoardDto() {
        return new BoardDto(
                frames.exportFramesDto(),
                scores.exportScoresDto()
        );
    }
}
