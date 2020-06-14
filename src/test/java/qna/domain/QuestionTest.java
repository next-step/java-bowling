package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("삭제 히스토리를 생성할 수 있다.")
    @Test
    void canCreateDeleteHistory() {
        assertThat(Q1.createDeleteHistory()).isInstanceOf(DeleteHistory.class);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("답변 리스트를 생성할 수 있다.")
    @Test
    void canCreateAnswers() {
        assertThat(Q1.createAnswers()).isInstanceOf(Answers.class);
    }
}
