package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    private final Answers answers = new Answers();

    @BeforeEach
    void setUp() {
        answers.add(new Answer(UserTest.JAVAJIGI, new Question(), ""));
    }

    @Test
    @DisplayName("Answer 추가 테스트")
    void addTest() {
        answers.add(new Answer(UserTest.JAVAJIGI, new Question(), ""));
        answers.add(new Answer(UserTest.SANJIGI, new Question(), ""));

        assertThat(3).isEqualTo(answers.answerCount());
    }

    @Test
    @DisplayName("Answers 삭제 테스트")
    void delete() throws CannotDeleteException {
        List<DeleteHistory> delete = answers.delete(UserTest.JAVAJIGI);

        assertThat(1).isEqualTo(delete.size());
    }

    @Test
    @DisplayName("다른 사람이 쓴 답글이 있을때 Answers 삭제 실패 테스트")
    void when_other_write_answer() {
        answers.add(new Answer(UserTest.SANJIGI, new Question(), ""));

        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}