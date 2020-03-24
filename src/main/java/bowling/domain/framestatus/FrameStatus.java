package bowling.domain.framestatus;

public interface FrameStatus {

    String display();

    int getPreScore();

    int getCurrentScore();

    boolean isBonus();

}
