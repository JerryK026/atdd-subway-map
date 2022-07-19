package nextstep.subway.applicaion;

import lombok.RequiredArgsConstructor;
import nextstep.subway.applicaion.dto.SectionRequest;
import nextstep.subway.applicaion.dto.SectionResponse;
import nextstep.subway.applicaion.mapper.domain.SectionMapper;
import nextstep.subway.applicaion.mapper.response.SectionResponseMapper;
import nextstep.subway.applicaion.validator.SectionValidator;
import nextstep.subway.domain.Line;
import nextstep.subway.domain.Section;
import nextstep.subway.domain.SectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final SectionValidator sectionValidator;
    private final SectionResponseMapper sectionResponseMapper;

    public Section createSection(Line line, SectionRequest sectionRequest) {
        sectionValidator.createValidate(line, sectionRequest);
        return sectionMapper.map(sectionRequest);
    }

    @Transactional
    public void deleteSection(Line line, Long stationId) {
        sectionValidator.deleteValidate(line, stationId);
        line.findSectionByDownStationId(stationId).setLine(null);
    }

    @Transactional(readOnly = true)
    public List<SectionResponse> findSectionsByLineId(Long lineId) {
        return sectionRepository.findAllByLineId(lineId).stream()
                .map(sectionResponseMapper::map)
                .collect(Collectors.toList());
    }
}
