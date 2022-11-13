package bowling.domain.scoregenerator;

import java.util.Random;

public class RandomScoreGenerator implements ScoreGenerator {

	private static Random RANDOM = new Random();

	private static int MAX_SCORE = 10;

	@Override
	public int generate() {
		return generateRandomScore();
	}

	private int generateRandomScore() {
		return RANDOM.nextInt(MAX_SCORE);
	}
}
