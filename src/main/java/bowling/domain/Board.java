package bowling.domain;

import bowling.dto.BoardDto;

import static java.util.stream.Collectors.toList;

class Board {
    private final Frames frames = new Frames();
    private final Scores scores = new Scores();

    void update(Rolls rolls) {
        frames.update(rolls);
        scores.accumulateOnlyValid(frames.subList(scores.size(), frames.size())
                .stream()
                .map(frame -> frame.score(rolls))
                .collect(toList()));
    }

    int getFrameNo() {
        return frames.getFrameNo();
    }

    boolean isStrike() {
        return frames.isStrike();
    }

    boolean isSpare() {
        return frames.isSpare();
    }

    boolean isGameOver() {
        return frames.isGameOver();
    }

    BoardDto exportBoardDto() {
        return new BoardDto(
                frames.exportFramesDto(),
                scores.exportScoresDto()
        );
    }
}
