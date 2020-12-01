package bowling.domain;

import bowling.dto.FrameNoDto;
import bowling.dto.PlayerStatusDto;

import java.util.function.Function;

class PlayerStatus {
    private final RollSubject subject;
    private final Board board;

    private PlayerStatus(RollSubject subject, Board board) {
        this.subject = subject;
        this.board = board;
    }

    static PlayerStatus of(Function<FrameNoDto, Roll> rollGenerator) {
        Board board = new Board();
        RollSubject subject = new RollSubject(() ->
                rollGenerator.apply(new FrameNoDto(board.getFrameNo())));
        subject.register(new BoardObserver(board));
        return new PlayerStatus(
                subject,
                board
        );
    }

    void register(Observer<Rolls> observer) {
        subject.register(observer);
    }

    void play() {
        subject.execute();
        if (!board.isStrike()) {
            subject.execute();
        }
        if (board.isGameOver()) {
            playBonus();
        }
    }

    private void playBonus() {
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
