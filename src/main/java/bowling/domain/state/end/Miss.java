package bowling.domain.state.end;

/**
 * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
 */
public class Miss implements ResultState {

    private static final String MARKER = "-";

    public Miss() {
    }

    @Override
    public String getPrintMark() {
        return MARKER;
    }

    @Override
    public boolean isInstanceOf(Class<? extends ResultState> clazz) {
        return clazz.isInstance(this);
    }
}
