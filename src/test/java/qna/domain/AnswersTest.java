package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    @Test
    @DisplayName("모든 질문의 작성자가 삭제한 경우 테스트")
    void deleteAllBy_owner_of_all_answers() {
        User owner = UserTest.JAVAJIGI;
        LocalDateTime deleteDate = LocalDateTime.now();
        Answer answer1 = new Answer(1L, owner, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = new Answer(2L, owner, QuestionTest.Q1, "Answers Contents2");
        DeleteHistory expectedAnswerDeleteHistory1 = new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(), deleteDate);
        DeleteHistory expectedAnswerDeleteHistory2 = new DeleteHistory(ContentType.ANSWER, answer2.getId(), answer2.getWriter(), deleteDate);
        Answers answers = new Answers(Arrays.asList(answer1, answer2));

        DeleteHistories deleteHistories = answers.deleteAllBy(owner, deleteDate);
        List<DeleteHistory> actualDeleteHistoryList = deleteHistories.getDeleteHistories();

        List<Answer> actualAnswers = answers.getAnswers();
        actualAnswers.forEach(answer -> assertThat(answer.isDeleted()).isTrue());
        assertThat(actualDeleteHistoryList).containsExactlyInAnyOrder(expectedAnswerDeleteHistory1,expectedAnswerDeleteHistory2);
    }

    @Test
    @DisplayName("모든 질문의 작성자가 아닌 경우 삭제 테스트")
    void deleteAllBy_not_a_owner_of_all_answers() {
        Answer answer1 = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = new Answer(2L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        Answers answers = new Answers(Arrays.asList(answer1, answer2));

        assertThatThrownBy(() -> answers.deleteAllBy(UserTest.JAVAJIGI, LocalDateTime.now()))
                .isInstanceOf(CannotDeleteException.class);

        assertThatThrownBy(() -> answers.deleteAllBy(UserTest.SANJIGI, LocalDateTime.now()))
                .isInstanceOf(CannotDeleteException.class);
    }
}
