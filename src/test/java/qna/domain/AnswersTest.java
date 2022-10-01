package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    private Answers answers;
    
    @BeforeEach
    void setUp() {
        answers = new Answers();
    }
    
    @Test
    @DisplayName("답변 삭제 성공")
    void delete_success() throws CannotDeleteException {
        answers.add(AnswerTest.A1);
        List<DeleteHistory> delete = answers.delete(UserTest.JAVAJIGI);
        assertThat(delete).hasSize(1);
    }
    
    @Test
    @DisplayName("답변 작성자가 다를 시 삭제 실패")
    void delete_fail_different_writer() throws CannotDeleteException {
        answers.add(AnswerTest.A2);
        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
