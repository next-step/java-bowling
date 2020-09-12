package bowling.score;

public interface Score {
	int getScore();

	default int getNextPitchingCountToReflect() {
		return 0;
	}

	void addAdditionalScore(Score nextScore);

	boolean reflectPreviousScore(Score previousScore);

	default boolean isItTimeToReflectNextScores(int advancedPitchingCount) {
		return false;
	}

	boolean isDoneCalculates();

	void reflectToNextScore();

	boolean isLastPitchingOfCurrentFrame();

	boolean reflectableNextScore();
}
