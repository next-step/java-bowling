package qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswersTest {

    @Test
    @DisplayName("정상 삭제")
    void deleteAllAfterDeletingQuestion() throws CannotDeleteException {
        Answers answers = new Answers();
        answers.add(new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"));

        List<DeleteHistory> deleteHistories = answers.deleteAllAfterDeletingQuestion(UserTest.JAVAJIGI);
        Assertions.assertThat(deleteHistories.size()).isEqualTo(answers.size());
    }

    @Test
    @DisplayName("권한 없는 답변이 존재")
    void delete_noPermission() {
        Answers answers = new Answers();
        answers.add(new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(1L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));

        assertThatThrownBy(() -> answers.deleteAllAfterDeletingQuestion(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
