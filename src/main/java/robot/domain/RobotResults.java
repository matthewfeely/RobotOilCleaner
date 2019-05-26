package robot.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RobotResults {

	@JsonProperty("finalPostion")
	private List<Integer> finalPosition;
	
	@JsonProperty("oilPatchesCleaned")
	private Integer oilPatchesCleaned;
	
	public RobotResults(List<Integer> finalPosition, Integer oilPatchesCleaned){
		this.finalPosition = finalPosition;
		this.oilPatchesCleaned = oilPatchesCleaned;
	}

	public RobotResults(List<Integer> finalPosition, int oilPatchesCleaned) {
		this.finalPosition = finalPosition;
		this.oilPatchesCleaned = oilPatchesCleaned;
	}

	public List<Integer> getFinalPosition() {
		return finalPosition;
	}

	public void setFinalPosition(List<Integer> point) {
		this.finalPosition = point;
	}

	public Integer getOilPatchesCleaned() {
		return oilPatchesCleaned;
	}

	public void setOilPatchesCleaned(Integer oilPatchesCleaned) {
		this.oilPatchesCleaned = oilPatchesCleaned;
	}
}
