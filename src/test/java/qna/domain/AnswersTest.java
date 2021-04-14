package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A3;

class AnswersTest {

    @DisplayName("답변 게시물 삭제 권한 테스트")
    @Test
    void deleteAnswers_응답_삭제_여부테스트() {
        // given
        Answers answers = new Answers();
        // when
        answers.add(A1);
        // then
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answers.checkAuthorization(UserTest.SANJIGI));
    }

    @DisplayName("답변 게시물 삭제처리 리스트 확인 테스트")
    @Test
    void deleteAll_답변_게시물_삭제테스트() {
        Answers answers = new Answers();

        // when
        answers.add(A1);
        answers.add(A3);
        List<DeleteHistory> deleteHistories = answers.deleteAll();
        // then
        assertThat(deleteHistories.size()).isEqualTo(2);
    }

}
