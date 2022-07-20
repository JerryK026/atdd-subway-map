package nextstep.subway.applicaion.dto;

public class UpdateLineRequest {
    private String name;
    private String color;

    public UpdateLineRequest() {
    }

    public UpdateLineRequest(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}