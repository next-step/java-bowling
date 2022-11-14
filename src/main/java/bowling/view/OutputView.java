package bowling.view;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bowling.domain.Constants;
import bowling.domain.PitchType;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class OutputView {

	public void printFrame(String name, Frames frames) {
		System.out.print("| Name |  ");
		IntStream.range(1, Constants.FINAL_FRAME + 1).forEach(index -> System.out.print(String.format("%02d", index) + "  |  "));
		System.out.println();

		System.out.print("|  " + name + " |");

		frames.getFrames()
			.stream().filter(frames::isNormalFrame)
			.forEach(frame -> printScoreFrame(joinNormalFrame(frame)));

		frames.getFrames()
			.stream().filter(frames::isFinalFrame)
			.forEach(frame -> printScoreFrame(createNormalFrame(frame)));

		IntStream.range(0, Constants.FINAL_FRAME - frames.size())
				.forEach(index -> printScoreFrame(" "));

		System.out.println();
	}

	private void printScoreFrame(String score) {
		score = "  " + score;
		System.out.print(String.format("%-6s", score) + "|");
	}

	private String joinNormalFrame(Frame normalFrame) {
		List<String> str = IntStream.range(0, normalFrame.size())
			.mapToObj(i -> convertScoreToPitchType(i, normalFrame))
			.collect(Collectors.toList());
		return String.join("|", str);
	}

	private String createNormalFrame(Frame finalFrame) {
		List<String> str = IntStream.range(0, finalFrame.size())
			.mapToObj(i -> convertScoreToPitchType(i, finalFrame))
			.collect(Collectors.toList());
		return String.join("|", str);
	}

	private String convertScoreToPitchType(int index, Frame finalFrame) {
		if(finalFrame.getPitchType(index).equals(PitchType.MISS))
			return finalFrame.get(index).toString();
		return PitchType.getSign(finalFrame.getPitchType(index));
	}
}
