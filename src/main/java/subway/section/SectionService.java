package subway.section;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import subway.common.exception.ResourceNotFoundException;
import subway.line.Line;
import subway.line.LineRepository;
import subway.station.Station;
import subway.station.StationRepository;

@Service
@Transactional
public class SectionService {

    private final LineRepository lineRepository;
    private final StationRepository stationRepository;

    public SectionService(final LineRepository lineRepository, final StationRepository stationRepository) {
        this.lineRepository = lineRepository;
        this.stationRepository = stationRepository;
    }

    public SectionResponse createSection(final Long lineId, final SectionRequest request) {
        Line line = findLineById(lineId);
        Station upStation = findStationById(request.getUpStationId());
        Station downStation = findStationById(request.getDownStationId());

        line.addSection(upStation, downStation, request.getDistance());

        return new SectionResponse(line.getLastSection());
    }

    public void deleteSection(final Long lineId, final Long stationId) {
        Line line = findLineById(lineId);

        line.deleteSection(findStationById(stationId));
    }

    private Line findLineById(final Long id) {
        return lineRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(Line.class, id));
    }

    private Station findStationById(final Long id) {
        return stationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(Station.class, id));
    }

}