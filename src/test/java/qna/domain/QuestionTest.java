package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문 생성")
    @Test
    public void create() {
        Question actual = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        assertThat(actual).isEqualTo(Q1);
    }

    @DisplayName("제목, 컨텐츠, 작성자로 초기화")
    @Test
    public void getMembers() {
        assertThat(Q1.getTitle()).isEqualTo("title1");
        assertThat(Q1.getContents()).isEqualTo("contents1");
        assertThat(Q1.getWriter()).isEqualTo(UserTest.JAVAJIGI);
    }

    @DisplayName("답변 조회")
    @Test
    public void getAnswer() {
        Answer actual = new Answer(UserTest.SANJIGI, Q1, "contents");
        Q1.addAnswer(actual);
        assertThat(Q1.getAnswers().contains(actual)).isTrue();
    }

    @DisplayName("작성자 조회")
    @Test
    public void owner() {
        assertThat(Q1.isOwner(UserTest.JAVAJIGI)).isTrue();
    }


    @DisplayName("질문자=사용자, 답변 없음: 삭제 가능")
    @Test
    public void delete_user_is_writer() {
        User loginUser = UserTest.JAVAJIGI;
        Q1.delete(loginUser);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문자!=사용자, 답변 없음: 삭제 불가")
    @Test
    public void delete_user_is_not_writer() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문자=사용자, 타 사용자 답변 존재: 삭제 불가")
    @Test
    public void delete_answer_exist_from_other_user() {
        Q1.addAnswer(new Answer(UserTest.SANJIGI, Q1, "co"));
        Q1.addAnswer(new Answer(UserTest.JAVAJIGI, Q1, "co"));
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문자=사용자, 질문자=답변자: 삭제 가능")
    @Test
    public void delete_answer_made_by_oneself() {
        User loginUser = UserTest.JAVAJIGI;
        Q1.addAnswer(new Answer(UserTest.JAVAJIGI, Q1, "co"));
        Q1.delete(loginUser);
        assertThat(Q1.isDeleted()).isTrue();
    }
}
