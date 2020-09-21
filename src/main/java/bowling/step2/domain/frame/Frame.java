package bowling.step2.domain.frame;

public interface Frame {
	Frame bowling(int pin);
	boolean isGameOver();
	void addFrameResult(FrameBoard board);
	FrameBoard makeBoard();
	int getFrameNo();
}
