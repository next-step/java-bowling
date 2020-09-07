package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete_답변_삭제_상태_확인() {
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void delete_삭제시_삭제_기록_생성() {
        DeleteHistory deleteHistory = A1.delete();
        assertThat(deleteHistory).isNotNull();
    }

}
