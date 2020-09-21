package bowling.step2.domain.status;

public class Gutter extends Finished {

	@Override
	public String getMark() {
		// 0 or |0
		return "0";
	}
}
