package robot.service;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import robot.domain.Message;
import robot.domain.RobotCleaner;
import robot.domain.RobotResults;

/**
 * @author wehtt
 *
 */
@Service
public class RobotServiceImpl implements RobotService {
	
	/**
	 * Returns a RobotResults object that contains the results of the cleaning process
	 * or null if an exception is thrown during the clean.
	 *
	 * @param Message  json payload received through the api 
	 * @return RobotResults results of the cleaning operation
	 */
	public RobotResults processMessage(Message message) {
		List<Integer> areaSize = message.getAreaSize();
		List<Integer> startingPosition = message.getStartingPosition();
		List<List<Integer>> oilPatches = message.getOilPatches();
		String directions = message.getNavigationInstructions();
				
		// convert lists to points
		Point gridSize = message.convertToPoint(areaSize);
		Point position = message.convertToPoint(startingPosition);
		List<Point> oilSites = message.convertToListPoints(oilPatches);

		// initialise the robot cleaner
		// an exception is thrown when the robot has an out of bounds starting point
		RobotCleaner robot = new RobotCleaner();
		try {
			robot = new RobotCleaner(position, gridSize);
		} catch (Exception e1) {
			return null;
		}
		
		// attempt a clean, an exception is thrown when the robot is navigated out of bounds
		try {
			robot.clean(directions, oilSites);
		} catch (IllegalArgumentException e) {
			return null;
		}
		
		return generateResults(robot);
	}		
	
	/**
	 * Returns a RobotResults object generated from the RobotCleaner object after cleaning
	 * (i.e the clean method above has been run on it)
	 *
	 * @param robot  robot that has already cleaned 
	 * @return RobotResults results of the cleaning operation by robot
	 */
	public RobotResults generateResults(RobotCleaner robot) {
		List<Integer> finalPosition = new ArrayList<Integer>();
		
		// TODO cast to int isn't that nice
		finalPosition.add((int) robot.getPosition().getX());
		finalPosition.add((int) robot.getPosition().getY());
		
		RobotResults result = new RobotResults(finalPosition, robot.getPatchesCleaned().size());
		
		return result;
	}
}
