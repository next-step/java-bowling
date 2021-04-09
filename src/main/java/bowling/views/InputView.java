package bowling.views;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bowling.entity.Player;

public class InputView extends View {

	private final Scanner scanner;

	public InputView() {
		this.scanner = new Scanner(System.in);
	}

	public String getUserName() {
		out.println("플레이어 이름은(3 english letters)?:");
		return scanner.nextLine();
	}

	public int getFrameScore(Player player) {
		out.println(player.getName() +  " 의 " + player.currentPlayerFrameIndex() + "프레임 투구 : ");
		return Integer.parseInt(scanner.nextLine());
	}

	public int getPeopleCount() {
		System.out.println("How many people?");
		return Integer.parseInt(scanner.nextLine());
	}

	public List<String> getPlayers(int count) {
		return IntStream.range(0, count)
			.mapToObj((index) -> {
				String input = String.format("플레이어 %d의 이름은?(3 english letters):", index + 1);
				out.println(input);
				return scanner.nextLine();
			})
			.collect(Collectors.toList());
	}
}
