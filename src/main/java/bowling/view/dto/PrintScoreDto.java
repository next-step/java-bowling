package bowling.view.dto;

import java.util.List;
import java.util.stream.Collectors;

import bowling.domain.score.Score;

public class PrintScoreDto {

	private final int score;

	private PrintScoreDto(final int score) {
		this.score = score;
	}

	public static PrintScoreDto of(final Score score) {
		return new PrintScoreDto(score.getValue());
	}

	public static List<PrintScoreDto> of(final List<Score> scores) {
		return scores.stream()
			.map(PrintScoreDto::of)
			.collect(Collectors.toList());
	}

	public int getValue() {
		return score;
	}
}
