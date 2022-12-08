package qna.domain;

import java.time.LocalDateTime;

/**
 * Created by seungwoo.song on 2022-11-08
 */
class DeleteHistoryTest {

    public static final DeleteHistory ANSWER_DELETEHISTORY = new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now());
}
