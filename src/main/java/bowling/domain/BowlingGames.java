package bowling.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingGames {
	private final List<BowlingGame> values;
	private Index index;

	private BowlingGames(List<BowlingGame> values) {
		this(values, Index.first());
	}

	private BowlingGames(List<BowlingGame> values, Index index) {
		this.values = new ArrayList<>(values);
		this.index = index;
	}

	public static BowlingGames create(List<Player> players) {
		return players.stream()
			.map(BowlingGame::create)
			.collect(collectingAndThen(toList(), BowlingGames::new));
	}

	public List<BowlingGame> getValues() {
		return Collections.unmodifiableList(values);
	}

	public boolean hasNextPitching() {
		return values.stream()
			.anyMatch(BowlingGame::hasNextPitching);
	}

	public Index getCurrentIndex() {
		return index;
	}

	public void increaseIndexIfFrameEnd() {
		boolean frameEnd = values.stream()
			.noneMatch(bowlingGame -> bowlingGame.isFrameRunning(index));

		if (frameEnd) {
			index = index.next();
		}
	}
}
