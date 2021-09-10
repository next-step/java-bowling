package bowling.domain.player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Bowlers {

	private final List<Bowler> bowlers;

	private Bowlers(final List<String> names) {
		bowlers = names.stream()
			.map(Bowler::of)
			.collect(Collectors.toList());
	}

	public static Bowlers of(final List<String> names) {
		return new Bowlers(names);
	}

	public Bowler first() {
		return bowlers.get(0);
	}

	public Bowler changeBowler(final Bowler currentPlayer) {
		final int current = bowlers.indexOf(currentPlayer);
		final int next = (current + 1) % bowlers.size();

		return bowlers.get(next);
	}

	public List<Bowler> getBowlers() {
		return Collections.unmodifiableList(bowlers);
	}

	public boolean isAllFinish() {
		return bowlers.stream()
			.allMatch(Bowler::isFinish);
	}
}
