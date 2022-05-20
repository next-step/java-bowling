package qna.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    /**
     * 다른 회원이 쓴 답변이 있다면 true, 그렇지 않다면 false를 리턴합니다.
     *
     * @param user 확인할 회원
     * @return 다른 회원이 쓴 답변이 있다면 true, 그렇지 않다면 false
     */
    public boolean hasWrittenByOthers(User user) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(user));
    }

    public void delete(List<DeleteHistory> deleteHistories) {
        answers.forEach(answer -> {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        });
    }
}
