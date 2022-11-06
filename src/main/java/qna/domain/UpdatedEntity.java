package qna.domain;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class UpdatedEntity extends CreatedEntity {
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public UpdatedEntity() {
    }

    public UpdatedEntity(Long id) {
        super(id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (getId() ^ (getId() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UpdatedEntity{" +
                "updatedAt=" + updatedAt +
                '}';
    }
}
