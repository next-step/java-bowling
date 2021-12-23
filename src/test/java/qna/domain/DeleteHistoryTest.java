package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeleteHistoryTest {
    public static final LocalDateTime DEFAULT_TIME = LocalDateTime.parse("2021-12-23T22:32:02.620");
    public static final DeleteHistory D10 = new DeleteHistory(ContentType.QUESTION, QuestionTest.Q1.getId(), UserTest.JAVAJIGI, DEFAULT_TIME);
    public static final DeleteHistory D11 = new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), UserTest.JAVAJIGI, DEFAULT_TIME);
    public static final DeleteHistory D12 = new DeleteHistory(ContentType.ANSWER, AnswerTest.A2.getId(), UserTest.SANJIGI, DEFAULT_TIME);

    @Test
    public void 생성자_질문_비교() {
        assertThat(DeleteHistory.createQuestionDeleteHistory(QuestionTest.Q1, DEFAULT_TIME)).isEqualTo(D10);
    }

    @Test
    public void 생성자_답변_비교() {
        assertThat(DeleteHistory.createAnswerDeleteHistory(AnswerTest.A1, DEFAULT_TIME)).isEqualTo(D11);
    }
}