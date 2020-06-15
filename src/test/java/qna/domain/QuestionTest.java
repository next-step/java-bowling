package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    @DisplayName("질문 삭제 시 삭제 상태를 true로 변경한다.")
    @Test
    public void deleteQuestionTest() throws CannotDeleteException {
        assertThat(Q1.isDeleted()).isFalse();

        Q1.delete(JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("로그인한 사용자와 질문자가 다른 상태에서 삭제 시도 시 예외 발생 - CannotDeleteException")
    @Test
    public void deleteQuestionsAuthTest() {
        assertThatThrownBy(() -> Q1.delete(SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
