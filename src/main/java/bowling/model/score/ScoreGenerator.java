package bowling.model.score;

import bowling.model.Pin;

public class ScoreGenerator {

	public static String scoreGenerator(Pin firstPin) {
		if (firstPin.isMaxPin()) {
			return "X";
		}
		if (firstPin.isMinPin()) {
			return "-";
		}
		return String.valueOf(firstPin.getPin());
	}

	public static String scoreGenerator(Pin firstPin, Pin secondPin) {
		if (firstPin.isMaxPin() && secondPin.isMaxPin()) {
			return "X|X";
		}
		if (firstPin.isMaxPin() && !secondPin.isMaxPin()) {
			return "X|" + scoreGenerator(secondPin);
		}
		if (firstPin.add(secondPin).isMaxPin()) {
			return scoreGenerator(firstPin) + "|" + "/";
		}
		return scoreGenerator(firstPin) + "|" + scoreGenerator(secondPin);
	}

	public static String scoreGenerator(Pin firstPin, Pin secondPin, Pin bonusPin) {
		if (firstPin.isMaxPin() && secondPin.isMaxPin() && bonusPin.isMaxPin()) {
			return "X|X|X";
		}
		if (firstPin.isMaxPin() && secondPin.isMaxPin()) {
			return "X|X|" + scoreGenerator(bonusPin);
		}
		if (firstPin.isMaxPin() && secondPin.isMinPin()) {
			return "X|-|" + scoreGenerator(bonusPin);
		}
		if (firstPin.isMaxPin()) {
			return "X|" + scoreGenerator(secondPin, bonusPin);
		}
		return scoreGenerator(firstPin, secondPin) + "|" + scoreGenerator(bonusPin);
	}
}
