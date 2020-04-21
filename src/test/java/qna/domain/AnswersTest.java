package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    @Test
    public void deleteAnswersWhenUserIsOwner() throws CannotDeleteException {
        Answer answer1 = AnswerTest.ofUser(11L, UserTest.JAVAJIGI);
        Answers answers = new Answers(Arrays.asList(answer1));

        List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);

        assertThat(answer1.isDeleted()).isTrue();
        assertThat(deleteHistories).hasSize(1);
    }

    @Test
    public void deleteAnswersWhenUserIsNotOwner() {
        Answer answer1 = AnswerTest.ofUser(11L, UserTest.JAVAJIGI);
        Answer answer2 = AnswerTest.ofUser(12L, UserTest.SANJIGI);
        Answers answers = new Answers(Arrays.asList(answer1, answer2));

        assertThatThrownBy(() -> {
            answers.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
