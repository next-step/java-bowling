package bowling.domain.frame.status;

public interface Status {

    String getDescription();

    Status record(int downedPin);

    boolean isEnd();

    int calculateScore();

}
