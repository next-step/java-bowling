package bowling.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bowling.views.FinalFrameString;

public class FinalFrame implements Frame {
	private final List<Ward> wards;

	public FinalFrame(List<Ward> wards) {
		this.wards = wards;
	}

	public FinalFrame() {
		this(new ArrayList<>());
	}

	@Override
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

	@Override
	public String getScoreString() {
		return FinalFrameString.getString(wards);
	}

	@Override
	public boolean isLast() {
		return !isKeepGoing();
	}

	@Override
	public int getScore() {
		return this.wards
			.stream()
			.mapToInt(Ward::getScore)
			.sum();
	}

	@Override
	public int getRemainderCount() {
		return 0;
	}

	public void add(int score) {
		wards.add(
			FinalFrameWardsFactory.getInstance(wards.size())
				.getFunc()
				.apply(score, wards)
		);
	}

	@Override
	public List<Ward> getWards() {
		return wards;
	}

}
