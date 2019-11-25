package game.bowling.domain;

/**
 * Created by yusik on 2019/11/20.
 */
public interface Frame {

    int getFrameNo();

    String getStatus();

    void bowl(int score);

    boolean isFinish();
}
