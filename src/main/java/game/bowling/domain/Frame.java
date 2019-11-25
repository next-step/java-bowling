package game.bowling.domain;

/**
 * Created by yusik on 2019/11/20.
 */
public interface Frame {

    int getFrameNo();

    void bowl(int score);

    boolean isFinish();

    Score getScore();
}
