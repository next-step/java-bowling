package bowling.state.end;

/**
 * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
 */
public class Miss implements EndState {

    @Override
    public String getPrintMark() {
        return "-";
    }
}
