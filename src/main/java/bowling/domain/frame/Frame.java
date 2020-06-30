package bowling.domain.frame;

public interface Frame {
	Frame addNextFrame();

	boolean canPlayMore();
}
