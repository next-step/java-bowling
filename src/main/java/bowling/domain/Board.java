package bowling.domain;

import bowling.dto.BoardDto;

import static java.util.stream.Collectors.toList;

class Board {
    private final Frames frames = new Frames();
    private final Scores scores = new Scores();

    int frameNo() {
        return frames.frameNo();
    }

    void update(Rolls rolls) {
        frames.update(rolls);
        scores.addValidOnly(frames.subList(scores.size(), frames.size())
                .stream()
                .map(frame -> frame.score(rolls))
                .map(Score::new)
                .collect(toList()));
    }

    boolean isFrameFinished() {
        return frames.isLastFinished();
    }

    boolean isStrike() {
        return frames.isStrike();
    }

    boolean isSpare() {
        return frames.isSpare();
    }

    BoardDto exportBoardDto() {
        return new BoardDto(
                frames.exportFramesDto(),
                scores.exportScoresDto()
        );
    }
}
