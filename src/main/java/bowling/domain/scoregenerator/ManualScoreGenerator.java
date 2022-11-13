package bowling.domain.scoregenerator;

public class ManualScoreGenerator implements ScoreGenerator {

	private int score;

	public ManualScoreGenerator(int score) {
		this.score = score;
	}

	@Override
	public int generate() {
		return this.score;
	}
}
