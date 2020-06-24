package qna.domain;

import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private User loginUser;

    @BeforeClass
    void setUp() {
        loginUser = UserTest.JAVAJIGI;
    }

    @Test
    @DisplayName("답변 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태만 변경한다.")
    void delete_성공() {
        assertThat(A1.isDeleted()).isFalse();
        A1.deleteBy(loginUser);
        assertThat(A1.isDeleted()).isTrue();
    }
}
