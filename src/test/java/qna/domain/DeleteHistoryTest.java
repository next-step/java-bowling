package qna.domain;

import java.time.LocalDateTime;

public class DeleteHistoryTest {
    public static final DeleteHistory DELETE_QUESTION_HISTORY_FROM_JAVAJIGI = new DeleteHistory(ContentType.QUESTION, 1L, UserTest.JAVAJIGI, LocalDateTime.now());
}
