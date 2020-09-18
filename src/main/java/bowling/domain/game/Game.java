package bowling.domain.game;

import bowling.domain.DownedPinCount;
import bowling.domain.frame.Frame;
import bowling.domain.player.Player;
import bowling.domain.state.State;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {
	private static final int START_INDEX = 1;
	private static final int MAX_FRAMES_PER_GAME = 10;

	private final Player player;
	private final LinkedList<Frame> frames;

	public Game(Player player) {
		this.player = player;
		this.frames = IntStream.of(START_INDEX)
						.mapToObj(Frame::new)
						.collect(Collectors.toCollection(LinkedList::new));
	}

	public String getPlayersName() {
		return player.getName();
	}

	public int getCurrentFrameSequence() {
		return frames.getLast()
					.getFrameSequence();
	}

	public Map<Integer, State> getStateGroupedBy() {
		Map<Integer, State> grouped = frames.stream()
										.collect(Collectors.toMap(Frame::getFrameSequence, Frame::getState, (frame1, frame2) -> frame1, LinkedHashMap::new));
		return Collections.unmodifiableMap(grouped);
	}

	public boolean isGameFinished() {
		return isFullFrame() && isAllFrameDone();
	}

	public void play(DownedPinCount currentFramePitch) {
		Frame last = frames.getLast();
		Frame frame = last.roll(currentFramePitch);
		if(!isFullFrame() && !last.equals(frame)) {
			frames.add(frame);
		}
	}

	private boolean isFullFrame() {
		return frames.size() == MAX_FRAMES_PER_GAME;
	}

	private boolean isAllFrameDone() {
		return frames.stream()
				.allMatch(Frame::isFrameFinished);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Game game = (Game) o;
		return player.equals(game.player) &&
				frames.equals(game.frames);
	}

	@Override
	public int hashCode() {
		return Objects.hash(player, frames);
	}
}
