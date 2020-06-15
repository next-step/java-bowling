package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문 삭제 시 삭제 상태를 true로 변경한다.")
    @Test
    public void deleteQuestionTest() {
        assertThat(Q1.isDeleted()).isFalse();

        Q1.delete();
        
        assertThat(Q1.isDeleted()).isTrue();
    }
}
