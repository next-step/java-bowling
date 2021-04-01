package bowling.entity;

import java.util.Optional;

public class Player {
	private final Name name;
	private PlayerFrameScore playerFrameScore;

	public Player(Name name) {
		this.name = name;
		this.playerFrameScore = new PlayerFrameScore();
	}

	public Player(String name) {
		this(new Name(name));
	}

	public String getName() {
		return name.getName();
	}

	public void addPlayerFrameScore(int key, int score) {
		NormalFrame normalFrame = this.playerFrameScore.getNormalFrames()
			.get(key);

		if (normalFrame == null) {
			NormalFrame first = NormalFrame.ofNext(key, score, null);
			addPlayerNormalFrameScore(first);
			return;
		}

		NormalFrame second = NormalFrame.ofNext(key, score, normalFrame);
		addPlayerNormalFrameScore(second);
	}

	public void addFinalFrame(int key) {
		FinalFrame finalFrame = this.playerFrameScore.getFinalFrame();

		finalFrame.add(key);
		addPlayerFinalFrameScore(finalFrame);
	}

	private void addPlayerNormalFrameScore(NormalFrame normalFrame) {
		this.playerFrameScore = Optional.ofNullable(playerFrameScore)
			.orElseGet(PlayerFrameScore::new)
			.addFrameScore(normalFrame);
	}

	private void addPlayerFinalFrameScore(FinalFrame finalFrame) {
		this.playerFrameScore = Optional.ofNullable(playerFrameScore)
			.orElseGet(PlayerFrameScore::new)
			.addFrameScore(finalFrame);
	}

	public boolean isKeepGoing(int key) {
		return this.playerFrameScore.getNormalFrames()
			.get(key)
			.isKeepGoing();
	}

	public boolean isFinalFrameKeepGoing() {
		return this.getPlayerFrameScore()
			.getFinalFrame()
			.isKeepGoing();
	}

	public PlayerFrameScore getPlayerFrameScore() {
		return playerFrameScore;
	}
}
