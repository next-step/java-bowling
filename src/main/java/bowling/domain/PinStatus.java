package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class PinStatus {
	private final List<Integer> hitSize = new ArrayList<>();

	public void add(int hitSize) {
		this.hitSize.add(hitSize);
	}

	public int totalPin() {
		return this.hitSize.stream().mapToInt(Integer::intValue).sum();
	}

	public int firstPin() {
		return hitSize.get(0);
	}

	public int secondPin() {
		return hitSize.get(1);
	}

	public int bonusPin() {
		return hitSize.get(2);
	}

	public int pinSize(int playCount) {
		return hitSize.get(playCount);
	}

}
