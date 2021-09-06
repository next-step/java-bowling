package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("Question 에 등록된 Answers 들의 Owner 가 존재하는지 테스트")
    @Test
    void isAnswersHaveOwner() {
        assertThat(Q1.isAnswerHaveOwner(UserTest.JAVAJIGI)).isTrue();
    }

    @DisplayName("Question 에 등록된 Answers 들의 Owner 가 존재하지 않을 때 테스트")
    @Test
    void isNotAnswersHaveOwner() {
        assertThat(Q2.isAnswerHaveOwner(UserTest.SANJIGI)).isFalse();
    }

    @DisplayName("Question 에 등록된 Answers 들이 Deleted 상태 인지 테스트")
    @Test
    void setDeleted() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);

        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @DisplayName("Question 에 등록된 Answers 들이 Deleted 상태 인지 테스트")
    @Test
    void delete() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);

        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @DisplayName("질문 삭제 권한 없는 사용자가 delete 시 exception 테스트")
    @Test
    void deleteException() {

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> {
                    Q1.delete(UserTest.SANJIGI);
                }).withMessageMatching("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문 삭제 권한 있는 사용자가 delete 시 정상 적으로 deleteHistory List 잘 가져 오는지 테스트")
    @Test
    void getDeleted() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);

        List<DeleteHistory> deleteHistories = Q1.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistories).isEqualTo(Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now())
                , new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now())
        ));
    }
}
