package bowling.frame;

import java.util.List;

import bowling.pin.Pins;
import bowling.pitching.PitchingResults;
import bowling.pitching.status.PitchingResult;
import bowling.score.FinalScore;
import bowling.score.Score;

public class FinalFrame implements Frame {

	private static final int DEFAULT_PITCH_COUNT = 2;
	private static final int MAX_PITCH_COUNT = 3;

	private PitchingResults pitchingResults;
	private boolean oneMorePitching;


	private FinalFrame() {
		this.pitchingResults = PitchingResults.newInstance();
	}

	public static Frame newInstance() {
		return new FinalFrame();
	}

	@Override
	public Score reflect(Pins knockingDownPins) {
		PitchingResult pitchingResult = pitchingResults.reflectPitching(knockingDownPins);
		if (pitchingResult.isStrikeOrSpare()) {
			oneMorePitching = Boolean.TRUE;
		}

		return FinalScore.of(knockingDownPins, finish());
	}

	@Override
	public boolean finish() {
		int currentPitchingCount = pitchingResults.getPitchingCountToDate();
		return oneMorePitching ?
				currentPitchingCount == MAX_PITCH_COUNT :
				currentPitchingCount == DEFAULT_PITCH_COUNT;
	}

	@Override
	public List<String> getKnockingDownPinsSigns() {
		return pitchingResults.getKnockingDownPinsSigns();
	}

}
