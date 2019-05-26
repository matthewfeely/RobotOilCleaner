package robot.service;

import robot.domain.Message;
import robot.domain.RobotCleaner;
import robot.domain.RobotResults;

public interface RobotService {
	
	public RobotResults processMessage(Message message);
		
	public RobotResults generateResults(RobotCleaner robot);

}
