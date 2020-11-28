package bowling.domain;

import bowling.dto.BoardDto;

class Board {
    private final Frames frames = new Frames();
    private final Scores scores = new Scores();

    int frameNo() {
        return frames.frameNo();
    }

    void update(Rolls rolls) {
        frames.update(rolls);
        scores.update(rolls, frames);
    }

    boolean isFrameFinished() {
        return frames.isLastFinished();
    }

    boolean isBonus() {
        return frames.isBonus();
    }

    BoardDto exportBoardDto() {
        return new BoardDto(
                frames.exportFramesDto(),
                scores.exportScoresDto()
        );
    }
}
