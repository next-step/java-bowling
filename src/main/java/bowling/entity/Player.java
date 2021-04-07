package bowling.entity;

import java.util.List;

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

	public int currentPlayerFrameIndex() {

		if (playerFrameScore.isAdd()) {
			return this.playerFrameScore.getFrames().size() + 1;
		}
		return this.playerFrameScore.getFrames().size();
	}

	public void addPlayerFrameScore(int score) {
		List<FrameScore> scores = playerFrameScore.addScore(score);

		if (!playerFrameScore.isAdd()) {
			Frame frame = getFrame(score);
			this.playerFrameScore = playerFrameScore.setFrameScore(frame, scores);
			return;
		}

		Frame frame = getFrame(score);
		this.playerFrameScore = playerFrameScore.addFrameScore(frame, scores);
	}

	private Frame getFrame(int score) {
		if (playerFrameScore.isFinalFrame(currentPlayerFrameIndex()) && playerFrameScore.isAdd()) {
			FinalFrame finalFrame = new FinalFrame();
			finalFrame.add(score);
			return finalFrame;
		}

		if (playerFrameScore.isFinalFrame(currentPlayerFrameIndex()) && playerFrameScore.getFrames().size() == 10) {
			FinalFrame finalFrame = (FinalFrame) playerFrameScore.getFrames().get(playerFrameScore.getFrames().size() - 1);
			finalFrame.add(score);
			return finalFrame;
		}

		if (playerFrameScore.isAdd()) {
			return NormalFrame.ofNext(score, null);
		}
		return NormalFrame.ofNext(score, playerFrameScore.getFrames().get(playerFrameScore.lastIndex()));
	}

	public boolean isEnd() {
		return currentPlayerFrameIndex() < 11;
	}

	public boolean isKeepGoing(int turn) {
		return turn != currentPlayerFrameIndex();
	}

	public PlayerFrameScore getPlayerFrameScore() {
		return playerFrameScore;
	}
}
