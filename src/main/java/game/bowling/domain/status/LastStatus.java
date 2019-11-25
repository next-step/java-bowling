package game.bowling.domain.status;

/**
 * Created by yusik on 2019/11/25.
 */
public interface LastStatus extends Status {
    @Override
    default boolean isFinal() {
        return true;
    }
}
