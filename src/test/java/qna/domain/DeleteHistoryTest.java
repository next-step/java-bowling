package qna.domain;

import java.time.LocalDateTime;

public class DeleteHistoryTest {

    public static final DeleteHistory D1_Q = DeleteHistory.of(ContentType.QUESTION, 1L, UserTest.SANJIGI, LocalDateTime.now());
    public static final DeleteHistory D2_A = DeleteHistory.of(ContentType.ANSWER, 2L, UserTest.JAVAJIGI, LocalDateTime.now());

}
