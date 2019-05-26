package robot.domain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

public class RobotCleaner {

	private final static Logger LOGGER = Logger.getLogger(RobotCleaner.class);
	
	// robot should move and clean and have a position
	Point position;
	Point areaSize;
	List<Point> patchesCleaned;
	
	public RobotCleaner(Point position, Point areaSize) throws IllegalArgumentException {
		this.position = position;
		this.areaSize = areaSize;
		this.patchesCleaned = new ArrayList<Point>();
		
		if(outOfBounds()) {
			LOGGER.error("Robot starting point is out of bounds.");
			throw new IllegalArgumentException("Robot coordinates cannot be outside of the grid.");
		}
	}
	
	public RobotCleaner() {

	}

	/**
	 * Navigates the robot cleaner through the directions and stores the oil spill sites.
	 * Throws an exception if the cleaner is navigated out of bounds.
	 *
	 * @param robot  the robot object that will do the cleaning 
	 * @param directions  the navigation instructions for the robot cleaner 
	 * @param oilSites  the oilSites in the area
	 *              
	 */
	public void clean(String directions, List<Point> oilSites) throws IllegalArgumentException {
		
		for(Point p : oilSites) {
			LOGGER.debug("Oilsite at: " + p.getLocation());
		}
		
		for(int i = 0; i < directions.length(); i++) {
			char direction = directions.charAt(i);
			
			// if the robot starts on a site to clean
			if(i == 0) {
				checkOilSpills(oilSites);
			}
			
			// navigate robot through instructions
			navigate(direction);
			LOGGER.debug("Robot moved in direction " + direction + " to point: " + getPosition());
			
			checkOilSpills(oilSites);
		}		
		
	}
	
	/**
	 * Checks if the robot is at the oil spill sites and if the site has already been cleaned or not
	 * 
	 * @param oilSites  list of oil sites  
	 *              
	 */
	public void checkOilSpills(List<Point> oilSites) {
		// check if the position is at an oil spill
		// check if the oil spill has been cleaned already
		if(oilSites.contains(getPosition()) &&
				!getPatchesCleaned().contains(getPosition())) {
			
			// aliasing messing things up here without .getLocation()
			getPatchesCleaned().add(getPosition().getLocation());
			LOGGER.debug("Point cleaned: " + getPosition());
		}
	}
	
	/**
	 * Navigates RobotCleaner object one direction
	 * Throws an exception if the direction results in the robot going out of bounds
	 *
	 * @param direction  the direction to navigate the robot 
	 */
	public void navigate(char direction) throws IllegalArgumentException {
		
		switch (direction) {
			case 'N' :
				position.translate(0, 1);
				break;
			case 'S' :
				position.translate(0, -1);
				break;
			case 'E' :
				position.translate(1, 0);
				break;
			case 'W' :
				position.translate(-1, 0);
				break;
		}
		
		if(outOfBounds()) {
			LOGGER.error("Instructions navigate the robot out of bounds. Please enter valid instructions.");
			
			// go back in bounds
			switch (direction) {
			case 'N' :
				position.translate(0, -1);
				break;
			case 'S' :
				position.translate(0, 1);
				break;
			case 'E' :
				position.translate(-1, 0);
				break;
			case 'W' :
				position.translate(1, 0);
				break;
		}
			
			throw new IllegalArgumentException("Robot coordinates cannot be outside of the grid.");
		}		
	}
	
	/**
	 * Returns true if the robot object is out of bounds
	 */
	public boolean outOfBounds() {
		if(position.getX() < 0 || position.getY() < 0
				|| position.getX() > areaSize.getX()
				|| position.getY() > areaSize.getY()) {
			return true;
		}
		return false;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Point getAreaSize() {
		return areaSize;
	}

	public void setAreaSize(Point areaSize) {
		this.areaSize = areaSize;
	}
	
	public List<Point> getPatchesCleaned() {
		return patchesCleaned;
	}

	public void setPatchesCleaned(List<Point> patchesCleaned) {
		this.patchesCleaned = patchesCleaned;
	}

}
