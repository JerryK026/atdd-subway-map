package nextstep.subway.domain.station;

import nextstep.subway.applicaion.station.dto.StationResponse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Station() {
    }

    public Station(final String name) {
        this.name = name;
    }

    public Station(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public StationResponse toResponse() {
        return new StationResponse(id, name);
    }
}