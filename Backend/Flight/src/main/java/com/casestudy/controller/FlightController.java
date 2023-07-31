package com.casestudy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.model.BookingModel;
import com.casestudy.model.FlightModel;
import com.casestudy.repository.FlightRepository;




@Transactional
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/flight")
public class FlightController {
	
	@Autowired
	FlightRepository flightrepo;
	
	 
	Logger logger = LoggerFactory.getLogger(FlightController.class);
	
	
	
	// to add flight details
	@PostMapping("/addflight")
	public String addflight(@RequestBody FlightModel flightmodel) {
		flightrepo.save(flightmodel);
		
		//logger implementation
        logger.info("[addflight] info message added");
        logger.debug("[addflight] debug message added");
        return "Flight Added Successfully";
	}
	
	
	// to get all flight details
	@GetMapping("/viewallflights")
	public List<FlightModel> getAllFlights(){
		
		
        logger.info("[viewallflights] info message added");
        logger.debug("[viewallflights] debug message added");
        
		return flightrepo.findAll();
	}
	

	// to get flight details by Id
	@GetMapping("/viewflightbyno/{flightNo}")
	public FlightModel getflights(@PathVariable("flightNo") String flightNo){
		
		//logger implementation
        logger.info("[viewflightbyno/flightId] info message added");
        logger.debug("[viewflightbyno/flightId] debug message added");
		
		return flightrepo.findByFlightNo(flightNo);		
	}
	
	// to get flight details by Name
	@GetMapping("/viewflightbyname/{flightName}")
	public List<FlightModel> getflightsbyname(@PathVariable("flightName") String flightName){
		
		
        logger.info("[viewflightbyname/flightName] info message added");
        logger.debug("[viewflightbyname/flightName] debug message added");
		
		return flightrepo.findByFlightName(flightName);		
	}
	
	
	// to update flight details by Id
	@RequestMapping(value="/updateflight/{flightNo}", method=RequestMethod.PUT)
	public String update(@PathVariable("flightNo") String flightNo, @RequestBody FlightModel flightmodel) {
		flightrepo.save(flightmodel);
		
		//logger implementation
        logger.info("[updateflight/flightId] info message added");
        logger.debug("[updateflight/flightId] debug message added");
        
		return "flight with no. "+flightNo+" have been updated successfully";
	}
	
	
	// to delete flight details by Id
	@DeleteMapping("/deleteflight/{flightNo}")
	public String delete(@PathVariable String flightNo){
		FlightModel obj = new FlightModel();
		obj=flightrepo.findByFlightNo(flightNo);
		flightrepo.delete(obj);
		

        logger.info("[deleteflight/flightId] info message added");
        logger.debug("[deleteflight/flightId] debug message added");
        return "Flight deleted successfully";
	}
	
	
	// to get flight details from a particular source to destination
	@GetMapping("/findbw/{flightFrom}/{flightTo}/{date}")
	public List<FlightModel> findByloc(@PathVariable("flightFrom") String flightFrom, @PathVariable("flightTo") String flightTo, @PathVariable("date") String date){
	
        logger.info("[findto/source/destination] info message added");
        logger.debug("[findto/source/destination] debug message added");
        
		return flightrepo.findByFlightFromAndFlightTo(flightFrom, flightTo, date);
	}
	
	
	// to get flight details from a particular source
	@GetMapping("/findfrom/{flightFrom}")
	public List<FlightModel> findByfrom(@PathVariable("flightFrom") String flightFrom){
		
	
        logger.info("[findfrom/destination] info message added");
        logger.debug("[findfrom/destination] debug message added");
        
		return flightrepo.findByFlightFrom(flightFrom);
	}
	
	
	// to get flight details to a particular destination
	@GetMapping("/findto/{flightTo}")
	public List<FlightModel> findByto(@PathVariable("flightTo") String flightTo){
		

        logger.info("[findto/source] info message added");
        logger.debug("[findto/source] debug message added");
        
		return flightrepo.findByFlightTo(flightTo);
	}
	
	
	// to get flight fare by flight Id 
	@GetMapping("/findfarebyno/{flightNo}")
	public int findfare(@PathVariable("flightNo") String flightNo){
		
	
        logger.info("[findfarebyno/flightId] info message added");
        logger.debug("[findfarebyno/flightId] debug message added");
        
		return flightrepo.findByFlightNo(flightNo).getFare();
	}
	
	
	//to decrease flight Seats by flight Id 
	@PostMapping("/decreaseseat/{flightNo}/{seats}")
	public void decreaseseats(@PathVariable("flightNo") String flightNo, @PathVariable("seats") int seats, @RequestBody BookingModel bookmodel){
		FlightModel obj = new FlightModel();
		obj=flightrepo.findByFlightNo(flightNo);
		int temp = obj.getSeats();
		obj.setSeats(temp-seats);
		flightrepo.save(obj);
	
        logger.info("[decreaseseat/flightId/seats] info message added");
        logger.debug("[decreaseseat/flightId/seats] debug message added");
	}
	
	
	// to increase flight Seats by flight Id 
	@PostMapping("/increaseseat/{flightNo}/{seats}")
	public void increaseseats(@PathVariable("flightNo") String flightNo, @PathVariable("seats") int seats){
		FlightModel obj = new FlightModel();
		obj=flightrepo.findByFlightNo(flightNo);
		int temp = obj.getSeats();
		obj.setSeats(temp+seats);
		flightrepo.save(obj);
		
	
        logger.info("[increaseseat/flightId/seats] info message added");
        logger.debug("[increaseseat/flightId/seats] debug message added");	
	}
	
	
	// to get flight Time by flight Id
	@GetMapping("/findtimebyno/{flightNo}")
	public String findtimebyno(@PathVariable("flightNo") String flightNo){
		
		//logger implementation
        logger.info("[findtimebyno/flightId] info message added");
        logger.debug("[findtimebyno/flightId] debug message added");
        
		return flightrepo.findByFlightNo(flightNo).getTime();
	}
	
	// to get flight Time by flight Name
	@GetMapping("/findtimebyname/{flightName}")
	public String findtimebyname(@PathVariable("flightName") String flightName){
		
		
        logger.info("[findtimebyname/flightName] info message added");
        logger.debug("[findtimebyname/flightName] debug message added");
        
		return flightrepo.findByFlightNo(flightName).getTime();
	}
	
}
