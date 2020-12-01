package bowling.domain;

import bowling.dto.PlayerStatusDto;

import java.util.function.Function;

class PlayerStatus {
    private final RollSubject subject;
    private final Board board;

    private PlayerStatus(RollSubject subject, Board board) {
        this.subject = subject;
        this.board = board;
    }

    static PlayerStatus of(Function<String, Roll> rollGenerator) {
        Board board = new Board();
        RollSubject subject = new RollSubject(() -> {
            String prefix = Integer.toString(board.frameNo());
            return rollGenerator.apply(prefix);
        });
        subject.register(new BoardObserver(board));
        return new PlayerStatus(
                subject,
                board
        );
    }

    void register(Observer<Rolls> observer) {
        subject.register(observer);
    }

    void playFrame() {
        subject.execute();
        if (!board.isFrameFinished()) {
            subject.execute();
        }
    }

    void playBonus() {
        if (board.isStrike()) {
            subject.execute();
            subject.execute();
        }
        if (board.isSpare()) {
            subject.execute();
        }
    }

    PlayerStatusDto exportPlayerStatusDto() {
        return new PlayerStatusDto(subject.exportRollsDto(), board.exportBoardDto());
    }
}
