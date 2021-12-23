package qna.domain;

import java.time.LocalDateTime;

import static qna.domain.AnswerTest.A1;
import static qna.domain.QuestionTest.Q1;

public class DeleteHistoryTest {
    public static final DeleteHistory DH1 = new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now());
    public static final DeleteHistory DH2 = new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now());
}
