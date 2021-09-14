package bowling.domain.score;

public interface Score {

	boolean isComputeAble();

	Score add(Score score);

	int getValue();
}
