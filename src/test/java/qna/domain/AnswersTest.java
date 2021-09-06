package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswersTest {
    @DisplayName("생성 테스트")
    @Test
    void create() {
        Answers answers = Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        assertThat(answers).isEqualTo(Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A2)));
    }

    @DisplayName("add 메소드 테스트")
    @Test
    void add() {
        Answers answers = Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        Answers addAnswers = new Answers();
        addAnswers.add(AnswerTest.A1);
        addAnswers.add(AnswerTest.A2);

        assertThat(answers).isEqualTo(addAnswers);
    }

    @DisplayName("Owner 가지고 있는지 테스트")
    @Test
    void isOwnerTest() {
        Answers answers = Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A2));


        assertThat(answers.isOwner(UserTest.JAVAJIGI)).isTrue();
        assertThat(answers.isOwner(UserTest.SANJIGI)).isTrue();
    }

    @DisplayName("Owner 가지고 있지 않을 때 테스트")
    @Test
    void isNotOwnerTest() {
        Answers answers = Answers.of(Arrays.asList(AnswerTest.A1));

        assertThat(answers.isOwner(UserTest.SANJIGI)).isFalse();
    }

    @DisplayName("delete 테스트")
    @Test
    void delete() throws CannotDeleteException {
        Answers answers = Answers.of(Arrays.asList(AnswerTest.A1));

        List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistories).isEqualTo(Arrays.asList(
                new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now())));
    }

    @DisplayName("deleteException 테스트")
    @Test
    void deleteException() {
        Answers answers = Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> {
                    answers.delete(UserTest.SANJIGI);
                }).withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

    }
}
