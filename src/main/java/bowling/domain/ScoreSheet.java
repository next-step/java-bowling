package bowling.domain;

public interface ScoreSheet {

    /**
     * 모든 프래임에 핀이 기입되었느지 여부
     *
     * @return
     */
    boolean isAllMarked();


    /**
     * 다음 프레임을 가져온다
     *
     * @return
     * @throws IllegalStateException 현재 프레임이 완료되지 않은상태인 경우 발생한다
     */
    Frame nextFrame();

    ScoreSheetReader getReader();
}


