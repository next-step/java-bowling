package bowling.domain;

public class BoardObserver implements Observer<Rolls> {
    private final Board board;

    BoardObserver(Board board) {
        this.board = board;
    }

    @Override
    public void update(Subject<Rolls> subject) {
        Rolls rolls = subject.get();
        board.update(rolls);
    }
}
