package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    public static final List<DeleteHistory> DELETE_HISTORIES = new ArrayList<>();

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제")
    void delete_ok() {
        assertThatCode(() -> Q1.delete(UserTest.JAVAJIGI, DELETE_HISTORIES))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("다른 사람이 쓴 글 삭제시 오류 ")
    void delete_fail() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI, DELETE_HISTORIES))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("delete_성공_질문자_답변자_같음")
    void delete_QnA() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(UserTest.JAVAJIGI, DELETE_HISTORIES);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제 완료시 deleteHistory를 추가한다.")
    void check_deleteHistory() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(UserTest.JAVAJIGI, DELETE_HISTORIES);
        assertThat(DELETE_HISTORIES.size()).isEqualTo(2);
    }

}
