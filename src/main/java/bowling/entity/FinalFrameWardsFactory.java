package bowling.entity;

import static bowling.exception.UserExceptionMesssage.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import bowling.exception.UserException;

public enum FinalFrameWardsFactory {
	ONE(0, (score, wards) -> new FinalFirstWard(score)),
	TWO(1, (score, wards) -> new FinalSecondWard(score, wards.get(0).getScore())),
	THREE(2, (score, wards) -> new FinalThirdWard(score));
	private final int size;
	private final BiFunction<Integer, List<Ward>, Ward> func;

	FinalFrameWardsFactory(int size, BiFunction<Integer, List<Ward>, Ward> func) {
		this.size = size;
		this.func = func;
	}

	public static FinalFrameWardsFactory getInstance(int size) {
		return Arrays.stream(values())
			.filter(ward -> ward.size == size)
			.findFirst()
			.orElseThrow(() -> new UserException(FINAL_FRAME_MAX_THREE));
	}

	public BiFunction<Integer, List<Ward>, Ward> getFunc() {
		return func;
	}
}
