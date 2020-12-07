package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;
import qna.domain.mock.TestQuestion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void delete_성공() throws CannotDeleteException {
        TestQuestion testQuestion = new TestQuestion("title1", "contents1");
        testQuestion.writeBy(UserTest.JAVAJIGI);

        assertAll(
                () -> assertThatCode(() -> testQuestion.delete(UserTest.JAVAJIGI))
                        .doesNotThrowAnyException(),
                () -> assertThat(testQuestion.isDeleted()).isTrue()
        );
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws CannotDeleteException {
        User loginUser = UserTest.JAVAJIGI;
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(loginUser))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws CannotDeleteException {
        TestQuestion testQuestion = new TestQuestion("title1", "contents1");
        testQuestion.writeBy(UserTest.JAVAJIGI);
        testQuestion.addAnswer(AnswerTest.A1);

        assertAll(
                () -> assertThatCode(() -> testQuestion.delete(UserTest.JAVAJIGI))
                        .doesNotThrowAnyException(),
                () -> assertThat(testQuestion.isDeleted()).isTrue()
        );
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws CannotDeleteException {
        User invalidLoginUser = UserTest.SANJIGI;
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(invalidLoginUser))
                .withMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    public void deleteHistories() {
        User loginUser = UserTest.JAVAJIGI;
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A3);

        DeleteHistories deleteHistories = Q1.delete(loginUser);
        List<DeleteHistory> collect = StreamSupport.stream(deleteHistories.spliterator(), false)
                .collect(Collectors.toList());
        assertThat(collect).contains(new DeleteHistory(Q1, LocalDateTime.now()));
    }
}
