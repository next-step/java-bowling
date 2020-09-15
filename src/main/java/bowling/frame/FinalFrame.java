package bowling.frame;

import java.util.List;

import bowling.pin.Pins;
import bowling.pitching.PitchingResults;
import bowling.pitching.status.PitchingResult;
import bowling.score.FinalScore;
import bowling.score.Score;

public class FinalFrame implements Frame {
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
		PitchingResult pitchingResult = pitchingResults.makeResultUsing(knockingDownPins);
		if (pitchingResult.isStrikeOrSpare()) {
			oneMorePitching = Boolean.TRUE;
		}

		return FinalScore.of(knockingDownPins, finish());
	}

	@Override
	public boolean finish() {
		return pitchingResults.finishFinalFrame(oneMorePitching);
	}

	@Override
	public List<String> getKnockingDownPinsSigns() {
		return pitchingResults.getKnockingDownPinsSigns();
	}

}
