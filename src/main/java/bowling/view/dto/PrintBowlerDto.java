package bowling.view.dto;

import java.util.List;
import java.util.stream.Collectors;

import bowling.domain.player.Bowler;
import bowling.domain.state.PitchStates;

public class PrintBowlerDto {

	private final String name;
	private final int currentFrameCount;
	private final List<PrintPitchStatesDto> states;
	private final List<PrintScoreDto> scores;

	private PrintBowlerDto(final Bowler bowler) {
		this.name = bowler.getName();
		this.states = bowler.getAllPitchStates().stream()
			.map(PitchStates::getValues)
			.map(PrintPitchStatesDto::of)
			.collect(Collectors.toList());
		this.scores = PrintScoreDto.of(bowler.getComputeAbleScores());
		this.currentFrameCount = states.size();
	}

	public static PrintBowlerDto of(final Bowler bowler) {
		return new PrintBowlerDto(bowler);
	}

	public static List<PrintBowlerDto> of(final List<Bowler> bowlers) {
		return bowlers.stream()
			.map(PrintBowlerDto::of)
			.collect(Collectors.toList());
	}

	public String getBowlerName() {
		return name;
	}

	public int getCurrentFrameCount() {
		return currentFrameCount;
	}

	public List<PrintPitchStatesDto> getStates() {
		return states;
	}

	public List<PrintScoreDto> getScores() {
		return scores;
	}
}
