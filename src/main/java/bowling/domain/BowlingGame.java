package bowling.domain;

import java.util.List;

import bowling.domain.player.Bowler;
import bowling.domain.player.Bowlers;

public class BowlingGame {

	private final Bowlers bowlers;
	private Bowler current;

	public BowlingGame(final List<String> names) {
		bowlers = Bowlers.of(names);
		current = bowlers.first();
	}

	public static BowlingGame start(final List<String> names) {
		return new BowlingGame(names);
	}

	public void play(final int pins) {
		current.pitch(pins);
		if (current.needBowlerChange()) {
			current = bowlers.changeBowler(current);
		}
	}

	public boolean isFinish() {
		return bowlers.isAllFinish();
	}

	public List<Bowler> getBowlers() {
		return bowlers.getBowlers();
	}

	public Bowler getCurrentBowler() {
		return current;
	}
}
