package qna.domain;

import java.time.LocalDateTime;

public class DeleteHistoryTest {
    public static final DeleteHistory DELETE_HISTORY_QUESTION_JAVAJIGI = new DeleteHistory(ContentType.QUESTION, 1L, UserTest.JAVAJIGI, LocalDateTime.now());
    public static final DeleteHistory DELETE_HISTORY_ANSWER_JAVAJIGI = new DeleteHistory(ContentType.ANSWER, 1L, UserTest.JAVAJIGI, LocalDateTime.now());
    public static final DeleteHistory DELETE_HISTORY_QUESTION_SANGJIGI = new DeleteHistory(ContentType.QUESTION, 2L, UserTest.SANJIGI, LocalDateTime.now());
    public static final DeleteHistory DELETE_HISTORY_ANSWER_SANGJIGI = new DeleteHistory(ContentType.ANSWER, 2L, UserTest.SANJIGI, LocalDateTime.now());
}
