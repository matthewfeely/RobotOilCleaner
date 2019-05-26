package robot.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import robot.domain.Message;
import robot.domain.RobotResults;
import robot.service.RobotServiceImpl;

@RestController
@RequestMapping("rest/robot")
public class RobotController {
	
	private final static Logger LOGGER = Logger.getLogger(RobotController.class);
	
	@Autowired
	private RobotServiceImpl robotService;
	
	@PostMapping(path = "clean")
	public ResponseEntity<?> robotClean(@RequestBody Message message) {
		// TODO validate message format, assume nice format until complete

		LOGGER.info("Message received.");
		
		RobotResults result = robotService.processMessage(message);
		
		if(result == null) {
			return new ResponseEntity<RobotResults>(HttpStatus.BAD_REQUEST); 
		}
		
		return new ResponseEntity<RobotResults>(result, HttpStatus.OK);		
	}

}