package bowling.entity;

import static bowling.exception.UserExceptionMesssage.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import bowling.exception.UserException;

public class PlayerFrameScore {
	private final Map<Integer, NormalFrame> normalFrames;
	private final FinalFrame finalFrame;

	public PlayerFrameScore() {
		this(new LinkedHashMap<>(), null);
	}

 	public PlayerFrameScore(Map<Integer, NormalFrame> frameScores, FinalFrame finalFrame) {
	    validate(frameScores);
		this.normalFrames = frameScores;
		this.finalFrame = finalFrame;
	}

	private void validate(Map<Integer, NormalFrame> frameScores) {
		if (frameScores.size() > 9) {
			throw new UserException(FRAME_MAXIMUM_NINE);
		}
	}

	public PlayerFrameScore addFrameScore(NormalFrame normalFrame) {
		this.normalFrames.put(normalFrame.getNo(), normalFrame);
		return new PlayerFrameScore(normalFrames, null);
	}

	public PlayerFrameScore addFrameScore(FinalFrame finalFrame) {
		return new PlayerFrameScore(normalFrames, finalFrame);
	}

	public Map<Integer, NormalFrame> getNormalFrames() {
		return normalFrames;
	}

	public FinalFrame getFinalFrame() {
		return Optional.ofNullable(finalFrame).orElseGet(FinalFrame::new);
	}
}
