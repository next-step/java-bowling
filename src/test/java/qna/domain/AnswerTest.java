package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 답변삭제_상태값_변경() throws CannotDeleteException {

        final User 질문_답변자 = new User(1L, "pongdang", "password", "pd", "pongdang@gmail.com");
        final Question 질문 = new Question("title1", "contents1").writeBy(질문_답변자);
        final Answer 답변 = new Answer(질문_답변자, 질문, "Answers Contents1");
        질문.addAnswer(답변);

        질문.delete(질문_답변자);

        assertThat(답변.isDeleted()).isTrue();
    }
}
