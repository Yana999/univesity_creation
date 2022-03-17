package ru.abdramanova.university_platform.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "invoice")   // проверить, как вляиет invoice
@EqualsAndHashCode(exclude = "invoice")
public class SubInGroupId implements Serializable {

    private int group;
    private int subject;

    public SubInGroupId(int groupId, int subjectId) {
        this.group = groupId;
        this.subject = subjectId;
    }
}
