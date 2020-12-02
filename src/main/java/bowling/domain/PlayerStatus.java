package bowling.domain;

import bowling.dto.PlayerStatusDto;

import java.util.function.Supplier;

import static bowling.asset.Const.MAX_FRAME_NO;
import static bowling.asset.Const.PIN_NUM;

class PlayerStatus {
    private final RollSubject subject;
    private final Board board;

    private PlayerStatus(RollSubject subject, Board board) {
        this.subject = subject;
        this.board = board;
    }

    static PlayerStatus of(Supplier<Roll> rollGenerator) {
        Board board = new Board();
        RollSubject subject = new RollSubject(() -> rollGenerator.get());
        subject.register(new BoardObserver(board));
        return new PlayerStatus(
                subject,
                board
        );
    }

    void register(Runnable runnable) {
        subject.register(runnable);
    }

    void play(int frameNo) {
        subject.execute();
        Rolls rolls = subject.get();
        if (rolls.sum(rolls.size() - 1, 1) != PIN_NUM) {
            subject.execute();
        }
        if (frameNo == MAX_FRAME_NO) {
            playBonus();
        }
    }

    private void playBonus() {
        Rolls rolls = subject.get();
        if (rolls.sum(rolls.size() - 1, 1) == PIN_NUM) {
            subject.execute();
            subject.execute();
        }
        if (rolls.sum(rolls.size() - 2, 2) == PIN_NUM) {
            subject.execute();
        }
    }

    PlayerStatusDto exportPlayerStatusDto() {
        return new PlayerStatusDto(subject.exportRollsDto(), board.exportBoardDto());
    }
}
