package bowling.entity;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame {
	private final List<Ward> wards;

	public FinalFrame() {
		this.wards = new ArrayList<>();
	}

	public boolean isKeepGoing(int index) {
		return wards.get(index).hasNext();
	}

	public void add(int index, int score) {
		if (wards.size() == 0) {
			wards.add(index, new Ward(score) {
				@Override
				boolean hasNext() {
					return true;
				}
			});
			return;
		}

		if (wards.size() == 1) {
			wards.add(index, new Ward(score) {
				@Override
				boolean hasNext() {
					return wards.get(0).getScore() == 10 || score == 10 || wards.get(0).getScore() + score == 10;
				}
			});
			return;
		}

		if (wards.size() == 2) {
			wards.add(index, new Ward(score) {
				@Override
				boolean hasNext() {
					return false;
				}
			});
		}
	}

	public List<Ward> getWards() {
		return wards;
	}
}
