package subway.section;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import subway.line.Line;
import subway.station.Station;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "line_id")
    private Line line;
    @ManyToOne
    @JoinColumn(name = "up_station_id")
    private Station upStation;
    @ManyToOne
    @JoinColumn(name = "down_station_id")
    private Station downStation;
    private int distance;
    private int sequence;

    public Section() {
    }

    public Section(Line line, Station upStation, Station downStation, int distance) {
        this(null, line, upStation, downStation, distance, 1);
    }

    public Section(Line line, Station upStation, Station downStation, int distance, int sequence) {
        this(null, line, upStation, downStation, distance, sequence);
    }

    public Section(Long id, Line line, Station upStation, Station downStation, int distance, int sequence) {
        this.id = id;
        this.line = line;
        this.upStation = upStation;
        this.downStation = downStation;
        this.distance = distance;
        this.sequence = sequence;
    }

    public Long getId() {
        return id;
    }

    public Line getLine() {
        return line;
    }

    public Station getUpStation() {
        return upStation;
    }

    public Station getDownStation() {
        return downStation;
    }

    public int getDistance() {
        return distance;
    }

    public int getSequence() {
        return sequence;
    }
}
