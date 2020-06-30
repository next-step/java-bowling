package bowling.domain;

public class Scores {

	private final Score first;
	private Score second;

	private Scores(Score first) {
		this.first = first;
	}

	public static Scores ofScore(Score first) {
		return new Scores(first);
	}

	public void addSecondScore(Score second) {
		Score scoreTotal = first.add(second);
		this.second = second;
	}

	public Score getFirst() {
		return first;
	}

	public Score getSecond() {
		return second;
	}
}
