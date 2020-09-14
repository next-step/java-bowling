package bowling.frame;

import java.util.List;

import bowling.exception.FrameException;
import bowling.pin.Pins;
import bowling.pitching.PitchingResults;
import bowling.pitching.status.PitchingResult;
import bowling.score.NormalScore;
import bowling.score.Score;

public class NormalFrame implements Frame {
	private static final int LAST_NO_OF_NORMAL_FRAME = 9;

	private final int frameNo;
	private PitchingResults pitchingResults;

	private NormalFrame(int frameNo) {
		if (frameNo > LAST_NO_OF_NORMAL_FRAME) {
			throw new FrameException(String.format("일반 프레임의 번호는 %d를 넘을 수 없습니다.", LAST_NO_OF_NORMAL_FRAME));
		}

		this.pitchingResults = PitchingResults.newInstance();
		this.frameNo = frameNo;
	}

	public static Frame of(int frameNo) {
		return new NormalFrame(frameNo);
	}

	@Override
	public Score reflect(Pins knockingDownPins) {
		PitchingResult pitchingResult = pitchingResults.reflectPitching(knockingDownPins);

		return NormalScore.of(pitchingResult, frameNo);
	}

	@Override
	public boolean finish() {
		return pitchingResults.isDonePitchingOfCurrentFrame();
	}

	@Override
	public List<String> getKnockingDownPinsSigns() {
		return pitchingResults.getKnockingDownPinsSigns();
	}
}
