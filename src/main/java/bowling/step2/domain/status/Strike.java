package bowling.step2.domain.status;

public class Strike extends Finished {

	@Override
	public String getMark() {
		return "X";
	}
}
