package robot;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import robot.domain.RobotCleaner;

class RobotCleanerTest {
	
	@Test
	void testValidDirections() throws IllegalArgumentException {
		RobotCleaner robot = new RobotCleaner(new Point(1,1), new Point(2,2));
		List<Point> oilSites = new ArrayList<Point>();
		
		robot.clean("N", oilSites);
		assertThat(robot.getPosition(),is(equalTo(new Point(1,2))));

		robot.clean("S", oilSites);
		assertThat(robot.getPosition(),is(equalTo(new Point(1,1))));
		
		robot.clean("E", oilSites);
		assertThat(robot.getPosition(),is(equalTo(new Point(2,1))));
		
		robot.clean("W", oilSites);
		assertThat(robot.getPosition(),is(equalTo(new Point(1,1))));
	}
	
	@Test
	void testInvalidDirections() throws IllegalArgumentException {
		RobotCleaner robot = new RobotCleaner(new Point(0,0), new Point(0,0));
		List<Point> oilSites = new ArrayList<Point>();
		
		assertThrows(IllegalArgumentException.class, () -> {
			robot.clean("N", oilSites);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			robot.clean("S", oilSites);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			robot.clean("E", oilSites);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			robot.clean("W", oilSites);
		});
	}
	
	@Test
	void testOutOfBoundsStartPosition() throws IllegalArgumentException {

		assertThrows(IllegalArgumentException.class, () -> {
			new RobotCleaner(new Point(-1,0), new Point(1,1));
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new RobotCleaner(new Point(2,0), new Point(1,1));
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new RobotCleaner(new Point(0,-1), new Point(1,1));
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new RobotCleaner(new Point(0,-2), new Point(1,1));
		});
	}
	
	@Test
	void testSingleOilPatchCleaning() throws IllegalArgumentException {
		RobotCleaner robot = new RobotCleaner(new Point(2,3), new Point(8,8));
		List<Point> oilSites = new ArrayList<Point>();
		
		oilSites.add(new Point(3,3));
		
		robot.clean("E", oilSites);
		assertThat(robot.getPatchesCleaned().size(), is(equalTo(1)));
		
		// go over same patch
		robot.clean("WE", oilSites);
		assertThat(robot.getPatchesCleaned().size(), is(equalTo(1)));
	}
	
	@Test
	void testOilPatchCleaningAtStartPosition() throws IllegalArgumentException {
		RobotCleaner robot = new RobotCleaner(new Point(2,3), new Point(8,8));
		List<Point> oilSites = new ArrayList<Point>();
		
		oilSites.add(new Point(2,3));
		
		robot.clean("E", oilSites);
		assertThat(robot.getPatchesCleaned().size(), is(equalTo(1)));
	}
	
	@Test
	void testMultipleOilPatchCleaning() throws IllegalArgumentException {
		RobotCleaner robot = new RobotCleaner(new Point(2,3), new Point(8,8));
		List<Point> oilSites = new ArrayList<Point>();
		
		oilSites.add(new Point(3,3));
		oilSites.add(new Point(3,3));
		
		robot.clean("E", oilSites);
		assertThat(robot.getPatchesCleaned().size(), is(equalTo(1)));
		
		// go over same patch
		robot.clean("WE", oilSites);
		assertThat(robot.getPatchesCleaned().size(), is(equalTo(1)));
	}

}
