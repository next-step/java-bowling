package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @Test
    void test_answers_delete_all_success() throws CannotDeleteException {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, null);
        Answer answer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, null);

        Answers answers = Answers.of(Arrays.asList(answer1, answer2));

        List<DeleteHistory> deleteHistoryList = answers.delete(UserTest.JAVAJIGI);

        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
        assertThat(deleteHistoryList).hasSize(2);
    }

    @Test
    void test_answers_delete_all_throw_exception() throws CannotDeleteException {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, null);
        Answer answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, null);

        Answers answers = Answers.of(Arrays.asList(answer1, answer2));

        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }


}
