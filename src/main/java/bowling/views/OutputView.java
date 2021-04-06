package bowling.views;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bowling.entity.Frame;
import bowling.entity.Player;

public class OutputView extends View {
	public void showFrames() {
		out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
	}

	public void showPlayerFrames(Player player) {
		String userNameFormat = String.format("|  %3s | ", player.getName());

		String playing = " " + player.getPlayerFrameScore()
			.getFrames()
			.stream()
			.map(Frame::getScoreString)
			.map((str) -> String.format(" %-3s | ", str))
			.collect(Collectors.joining());

		out.println(userNameFormat + playing + notPlaying(player.getPlayerFrameScore().getFrames().size()));
	}

	public void showPlayerFrameScore(Player player) {
		String playing = IntStream.range(0, (int) player.getPlayerFrameScore().scoreLastSize())
			.mapToObj((index) -> player.getPlayerFrameScore().sum(index))
			.map(sum -> String.format("  %3d |", sum))
			.collect(Collectors.joining());

		out.println("|      | " + playing + notPlaying((int) player.getPlayerFrameScore().scoreLastSize()));
	}


	private String notPlaying(int size) {
		return IntStream.rangeClosed(size, 10)
			.mapToObj((i) -> "     ")
			.collect(Collectors.joining("|"));
	}
}
