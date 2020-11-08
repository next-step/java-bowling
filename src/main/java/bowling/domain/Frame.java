package bowling.domain;

public interface Frame {
	void pitch(int count);
	boolean isEnd();
	Frame next();
}
