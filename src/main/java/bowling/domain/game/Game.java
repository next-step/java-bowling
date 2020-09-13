package bowling.domain.game;

import bowling.domain.DownedPinCount;
import bowling.domain.frame.Frame;
import bowling.domain.player.Player;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {
	private static final int START_INDEX = 0;
	private static final int MAX_FRAMES_PER_GAME = 10;

	private final Player player;
	private final List<Frame> frames;
	private int currentIndex = START_INDEX;


	public Game(Player player) {
		this.player = player;
		this.frames = IntStream.range(START_INDEX, MAX_FRAMES_PER_GAME)
						.mapToObj(Frame::new)
						.collect(Collectors.toList());
	}

	public String getPlayersName() {
		return player.getName();
	}

	public List<Frame> getFrames() {
		return frames;
	}

	public int getCurrentFrameSequence() {
		return frames.get(currentIndex).getFrameSequence();
	}

	public boolean isGameFinished() {
		return frames.stream()
				.allMatch(Frame::isFrameFinished);
	}

	public void pitchTheBall(DownedPinCount currentFramePitch) {
		Frame currentFrame = frames.get(currentIndex);
		currentFrame.record(currentFramePitch);
		if(currentFrame.isFrameFinished()) {
			currentIndex = currentIndex == MAX_FRAMES_PER_GAME -1 ? currentIndex : currentIndex + 1;
		}
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
