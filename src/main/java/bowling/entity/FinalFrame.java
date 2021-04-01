package bowling.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FinalFrame {
	private final List<Ward> wards;

	public FinalFrame(List<Ward> wards) {
		this.wards = wards;
	}

	public FinalFrame() {
		this(new ArrayList<>());
	}

	public boolean isKeepGoing() {
		if (wards.isEmpty()) {
			return true;
		}

		return Optional.ofNullable(wards.get(wards.size() - 1))
			.orElseGet(() -> new Ward(0) {
				@Override
				boolean hasNext() {
					return false;
				}
			})
			.hasNext();
	}

	public void add(int score) {
		if (wards.size() == 0) {
			wards.add(new Ward(score) {
				@Override
				boolean hasNext() {
					return true;
				}
			});
			return;
		}

		if (wards.size() == 1) {
			wards.add(new Ward(score) {
				@Override
				boolean hasNext() {
					return wards.get(0).getScore() == 10 || score == 10 || wards.get(0).getScore() + score == 10;
				}
			});
			return;
		}

		if (wards.size() == 2) {
			wards.add(new Ward(score) {
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
