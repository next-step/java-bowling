package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Nested
    @DisplayName("답변이 없는 경우")
    class noAnswerQuestion {
        private Question question1;
        private Question question2;

        @BeforeEach
        void setUp() {
            question1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
            question2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
        }

        @DisplayName("로그인 사용자와 질문한 사용자가 같은 경우 삭제 정상 동작")
        @Test
        void delete() {
            assertThatCode(() -> question1.delete(UserTest.JAVAJIGI)).doesNotThrowAnyException();
            assertThatCode(() -> question2.delete(UserTest.SANJIGI)).doesNotThrowAnyException();
        }

        @DisplayName("로그인 사용자와 질문한 사용자가 디른 경우 삭제시 throws CannotDeleteException")
        @Test
        void deleteFailByOwner() {
            assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> question1.delete(UserTest.SANJIGI));
            assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> question2.delete(UserTest.JAVAJIGI));
        }

        @DisplayName("정상 삭제동작시 삭제 상태로 변경")
        @Test
        void deleteResult() throws CannotDeleteException {
            question1.delete(UserTest.JAVAJIGI);

            assertAll(
                    () -> assertThat(question1).isNotNull(),
                    () -> assertThat(question1.isDeleted()).isTrue()
            );
        }

        @DisplayName("정상 삭제동작시 삭제 이력 저장")
        @Test
        void deleteHistory() throws CannotDeleteException {
            List<DeleteHistory> deleteHistories = question1.delete(UserTest.JAVAJIGI);
            assertAll(
                    () -> assertThat(deleteHistories).hasSize(1),
                    () -> assertThat(deleteHistories).extracting("contentType").contains(ContentType.QUESTION)
            );
        }
    }

    @Nested
    @DisplayName("답변이 있는 경우")
    class hasAnswerQuestion {
        private Question question1;
        private Question question2;

        @BeforeEach
        void setUp() {
            question1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
            question2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
        }

        @DisplayName("로그인 사용자와 질문한 사람이 같고, 답변한 사람이 모두 같은 경우 삭제 정상 동작")
        @Test
        void delete() {
            addAnswerWriteByEqualUser();

            assertThatCode(() -> question1.delete(UserTest.JAVAJIGI)).doesNotThrowAnyException();
            assertThatCode(() -> question2.delete(UserTest.SANJIGI)).doesNotThrowAnyException();
        }

        @DisplayName("로그인 사용자와 질문한 사람이 같고, 답변한 사람이 다른 경우 삭제시 throws CannotDeleteException")
        @Test
        void deleteFailByAnswerUser() {
            addAnswerWriteByDiffUser();

            assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() ->question1.delete(UserTest.JAVAJIGI));
            assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() ->question2.delete(UserTest.SANJIGI));
        }

        @DisplayName("답변한 사람들이 같고, 로그인 사용자와 질문한 사용자가 디른 경우 삭제시 throws CannotDeleteException")
        @Test
        void deleteFailByOwner() {
            addAnswerWriteByEqualUser();

            assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> question1.delete(UserTest.SANJIGI));
            assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> question2.delete(UserTest.JAVAJIGI));
        }

        @DisplayName("정상 삭제동작시 질문과 답변 삭제 상태로 변경")
        @Test
        void deleteResult() throws CannotDeleteException {
            addAnswerWriteByEqualUser();
            question1.delete(UserTest.JAVAJIGI);

            assertAll(
                    () -> assertThat(question1).isNotNull(),
                    () -> assertThat(question1.isDeleted()).isTrue(),
                    () -> {
                        List<Answer> answers = question1.getAnswers().getAnswers();
                        for (Answer answer : answers) {
                            assertThat(answer).isNotNull();
                            assertThat(answer.isDeleted()).isTrue();
                        }
                    }
            );
        }

        @DisplayName("정상 삭제동작시 삭제 이력 저장")
        @Test
        void deleteHistory() throws CannotDeleteException {
            addAnswerWriteByEqualUser();
            List<DeleteHistory> deleteHistories = question1.delete(UserTest.JAVAJIGI);
            assertAll(
                    () -> assertThat(deleteHistories).hasSize(3),
                    () -> assertThat(deleteHistories).extracting("contentType").contains(ContentType.QUESTION),
                    () -> assertThat(deleteHistories).extracting("contentType").contains(ContentType.ANSWER)
            );

        }

        private void addAnswerWriteByDiffUser() {
            question1.addAnswer(AnswerTest.A1);
            question1.addAnswer(AnswerTest.A2);
            question2.addAnswer(AnswerTest.A1);
            question2.addAnswer(AnswerTest.A2);
        }

        private void addAnswerWriteByEqualUser() {
            question1.addAnswer(AnswerTest.A1);
            question1.addAnswer(AnswerTest.A1);
            question2.addAnswer(AnswerTest.A2);
            question2.addAnswer(AnswerTest.A2);
        }
    }
}
