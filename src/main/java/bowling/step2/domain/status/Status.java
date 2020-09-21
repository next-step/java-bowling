package bowling.step2.domain.status;

public interface Status {
	Status bowling(int pin);
	boolean isFinished();
	String getMark();
}
