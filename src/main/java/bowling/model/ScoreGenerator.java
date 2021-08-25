package bowling.model;

public class ScoreGenerator {

	private static final int MAX_PIN = 10;
	private static final int MIN_PIN = 0;

	public static String scoreGenerator(int firstPin) {
		if (firstPin == MAX_PIN) {
			return "X";
		}
		if (firstPin == MIN_PIN) {
			return "-";
		}
		return String.valueOf(firstPin);
	}

	public static String scoreGenerator(int firstPin, int secondPin) {
		if (firstPin == MAX_PIN) {
			return "X";
		}
		if (firstPin + secondPin == MAX_PIN) {
			return firstPin + "|" + "/";
		}
		return scoreGenerator(firstPin) + "|" + scoreGenerator(secondPin);
	}

	public static String scoreGenerator(int firstPin, int secondPin, int bonusPin) {
		if (firstPin == MAX_PIN && secondPin == MAX_PIN && bonusPin == MAX_PIN) {
			return "X|X|X";
		}
		if (firstPin == MAX_PIN && secondPin == MAX_PIN) {
			return "X|X|" + scoreGenerator(bonusPin);
		}
		if (firstPin == MAX_PIN && secondPin == MIN_PIN) {
			return "X|-|" + scoreGenerator(bonusPin);
		}
		if (firstPin == MAX_PIN) {
			return "X|" + scoreGenerator(secondPin, bonusPin);
		}
		return scoreGenerator(firstPin, secondPin) + "|" + scoreGenerator(bonusPin);
	}
}
