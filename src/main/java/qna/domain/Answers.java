package qna.domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> value;

    public Answers(List<Answer> answers) {
        this.value = answers;
    }

    public boolean hasOtherOwnerDifferentFrom(User loginUser) {
        return value.stream()
                .anyMatch(ans -> !ans.isOwner(loginUser));
    }

    public List<DeleteHistory> deleteAll() {
        return value.stream()
                .peek(ans -> ans.setDeleted(true))
                .map(ans -> new DeleteHistory(ContentType.ANSWER, ans.getId(), ans.getWriter(), LocalDateTime.now()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }
}
