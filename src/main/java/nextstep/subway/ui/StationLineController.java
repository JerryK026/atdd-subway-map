package nextstep.subway.ui;

import lombok.RequiredArgsConstructor;
import nextstep.subway.applicaion.StationLineService;
import nextstep.subway.applicaion.dto.SectionRequest;
import nextstep.subway.applicaion.dto.SectionResponse;
import nextstep.subway.applicaion.dto.StationLineResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static nextstep.subway.applicaion.dto.StationLineRequest.*;

@RestController
@RequestMapping("/lines")
@RequiredArgsConstructor
public class StationLineController {

    private final StationLineService stationLineService;

    @PostMapping
    public ResponseEntity<StationLineResponse> createLine(@Valid  @RequestBody PostRequest request) {
        StationLineResponse response = stationLineService.save(request.toEntity());
        return ResponseEntity.created(URI.create("/lines/" + response.getId())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StationLineResponse>> findAll() {
        List<StationLineResponse> response = stationLineService.findAllStationLines();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationLineResponse> findById(@PathVariable Long id) {
        return  ResponseEntity.ok(stationLineService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putLine(
            @PathVariable Long id, @RequestBody PutRequest putRequest) {
        stationLineService.update(putRequest.toEntity(id));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLine(@PathVariable Long id) {
        stationLineService.deleteLineById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/sections")
    public ResponseEntity<SectionResponse> createSection(@PathVariable Long id,
                                                         @Valid @RequestBody SectionRequest.PostRequest request) {
        SectionResponse response = stationLineService.addSection(id, request);
        return ResponseEntity.created(URI.create("/lines/" +id)).body(response);
    }

    @DeleteMapping("/{id}/sections")
    public ResponseEntity<Void> createSection(@PathVariable Long id,
                                              @Valid @RequestParam Long stationId) {
        stationLineService.deleteSection(id, stationId);
        return ResponseEntity.noContent().build();
    }

}