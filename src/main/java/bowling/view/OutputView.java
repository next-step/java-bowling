package bowling.view;

import static java.lang.System.*;

import bowling.frame.Frames;
import bowling.player.Player;

public class OutputView {

	public void showResult(Player player, Frames frames) {
		show("");
	}

	private void show(String text) {
		out.println(text);
	}
}
