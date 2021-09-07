package bowling.domain.frame;

public interface Frame {

    int INIT_NUMBER = 1;
    int LAST_NORMAL_NUMBER = 9;
    int FINAL_NUMBER = 10;

    boolean isEnd();

    Frame next();

    int number();

    Frame roll(int fallenPin);

    Rollings rollings();

}
