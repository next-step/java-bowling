package bowling.domain.frame;

public abstract class Frame {

    public abstract Frame record(int downedPin);

    public abstract boolean isEnd();

    public abstract String getDescriptionForm();

}
