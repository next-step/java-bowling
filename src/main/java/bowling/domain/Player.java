package bowling.domain;

public class Player {
	private final static int MAX_SIZE = 3;
	private final static String PLAYER_INVALID_MESSAGE = "Player 이름은 1~3자로 구성 돼야 합니다.";
	private final String playerName;

	public Player(String playerName) {
		validatePlayerName(playerName);
		this.playerName = playerName;
	}

	private void validatePlayerName(String playerName) {
		if (playerName == null || playerName.trim().isEmpty() || playerName.length() > MAX_SIZE) {
			throw new IllegalArgumentException(PLAYER_INVALID_MESSAGE);
		}
	}

	@Override
	public String toString() {
		return playerName;
	}
}
