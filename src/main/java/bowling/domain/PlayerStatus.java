package bowling.domain;

import bowling.domain.play.PlayContext;
import bowling.dto.PlayerStatusDto;

import java.util.function.Supplier;

class PlayerStatus {
    private final PlayContext playContext;
    private final Board board;

    private PlayerStatus(PlayContext playContext, Board board) {
        this.playContext = playContext;
        this.board = board;
    }

    static PlayerStatus of(Supplier<Roll> rollGenerator) {
        Board board = new Board();
        RollSubject subject = new RollSubject(rollGenerator);
        subject.register(board);
        return new PlayerStatus(
                new PlayContext(subject),
                board
        );
    }

    void register(Runnable runnable) {
        playContext.register(runnable);
    }

    void play(int frameNo) {
        playContext.play(frameNo);
    }

    PlayerStatusDto exportPlayerStatusDto() {
        return new PlayerStatusDto(playContext.exportRollsDto(), board.exportBoardDto());
    }
}
