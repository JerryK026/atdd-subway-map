package subway.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String name;

    public Station() {
    }

    public Station(String name) {
        this.name = name;
    }

    public boolean isSameStation(Station other) {
        return isSameStation(other.getId());
    }

    public boolean isSameStation(Long stationId) {
        return Objects.equals(this.id, stationId);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}