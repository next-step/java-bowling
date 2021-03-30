package bowling.views;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bowling.entity.FinalFrameString;
import bowling.entity.NormalFrame;
import bowling.entity.Player;

public class OutputView extends View {
	public void showFrames() {
		out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
	}

	public void showPlayerFrames(Player player) {
		String userNameFormat = String.format("|  %3s | ", player.getName());

		String playing = " " + player.getPlayerFrameScore().getNormalFrames()
			.keySet()
			.stream()
			.map((key) -> player.getPlayerFrameScore().getNormalFrames().get(key))
			.map(NormalFrame::getScoreString)
			.map((str) -> String.format("%5s", str))
			.collect(Collectors.joining("| "));

		String notPlaying = IntStream.range(player.getPlayerFrameScore().getNormalFrames().size(), 9)
			.mapToObj((i) -> "   |  ")
			.collect(Collectors.joining());

		String finalFrame =
			String.format(" | %5s | ", FinalFrameString.getString(player.getPlayerFrameScore()
				.getFinalFrame()
				.getWards())
			);

		out.println(userNameFormat + playing + notPlaying + finalFrame);
	}
}
