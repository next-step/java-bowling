package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("delete 테스트: 질문자와 사용자가 같은 경우 정상")
    void delete_질문자와_사용자가_같은_경우() {
        Question question = copy(Q1);

        question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("delete 테스트: 질문자와 사용자가 다른 경우 삭제 CannotDeleteException 발생")
    void delete_질문자와_사용자가_다른_경우() {
        Question question = copy(Q1);

        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    public static Question copy(Question question) {
        return new Question(question.getTitle(), question.getContents()).writeBy(question.getWriter());
    }

}
