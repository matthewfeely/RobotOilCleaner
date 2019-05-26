package robot.domain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
 	
public class Message {
	
	@JsonProperty("areaSize")
	private List<Integer> areaSize;

	@JsonProperty("startingPosition")
	private List<Integer> startingPosition;
	
	@JsonProperty("oilPatches")
	private List<List<Integer>> oilPatches;
	
	@JsonProperty("navigationInstructions")
	private String navigationInstructions;
	
	public Message() {
		
	}
	
	public List<Point> convertToListPoints(List<List<Integer>> sites){
		List<Point> newSites = new ArrayList<Point>();
		
		for(List<Integer> s : sites) {
			newSites.add(convertToPoint(s));
		}
		
		return newSites;
	}
	
	public Point convertToPoint(List<Integer> site){
		//validate size of the site
		Point p = new Point(site.get(0), site.get(1));				
		return p;
	}

	public List<Integer> getAreaSize() {
		return areaSize;
	}

	public void setAreaSize(List<Integer> areaSize) {
		this.areaSize = areaSize;
	}

	public List<Integer> getStartingPosition() {
		return startingPosition;
	}

	public void setStartingPosition(List<Integer> startingPosition) {
		this.startingPosition = startingPosition;
	}

	public List<List<Integer>> getOilPatches() {
		return oilPatches;
	}

	public void setOilPatches(List<List<Integer>> oilPatches) {
		this.oilPatches = oilPatches;
	}

	public String getNavigationInstructions() {
		return navigationInstructions;
	}

	public void setNavigationInstructions(String navigationInstructions) {
		this.navigationInstructions = navigationInstructions;
	}
	
}
