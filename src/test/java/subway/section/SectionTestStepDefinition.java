package subway.section;


import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import subway.section.SectionRequest;
import subway.section.SectionResponse;

public class SectionTestStepDefinition {
    public static SectionResponse 지하철_구간_생성_요청(Long lineId, Long upStationId, Long downStationId, Integer distance) {
        SectionRequest sectionRequest = new SectionRequest(upStationId, downStationId, distance);

        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(sectionRequest)
            .when().post("/lines/" + lineId + "/sections")
            .then().log().all()
            .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        return response.as(SectionResponse.class);
    }

    public static int 지하철_구간_생성_요청_상태_코드_반환(Long lineId, Long upStationId, Long downStationId, Integer distance) {
        SectionRequest sectionRequest = new SectionRequest(upStationId, downStationId, distance);

        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(sectionRequest)
            .when().post("/lines/" + lineId + "/sections")
            .then().log().all()
            .extract();

        return response.statusCode();
    }

    public static void 지하철_구간_제거_요청(Long lineId, Long stationId) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .params("stationId", stationId)
            .when().delete("/lines/" + lineId + "/sections")
            .then().log().all()
            .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    public static int 지하철_구간_제거_요청_상태_코드_반환(Long lineId, Long stationId) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .params("stationId", stationId)
            .when().delete("/lines/" + lineId + "/sections")
            .then().log().all()
            .extract();

        return response.statusCode();
    }
}