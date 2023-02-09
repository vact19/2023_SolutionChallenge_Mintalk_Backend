package xyz.hugme.hugmebackend.domain.counselor;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Counselor {
    @Id
    private Long id;
}
