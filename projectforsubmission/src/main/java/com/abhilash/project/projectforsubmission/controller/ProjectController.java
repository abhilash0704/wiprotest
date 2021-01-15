package com.abhilash.project.projectforsubmission.controller;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.json.JSONException;
//import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhilash.project.projectforsubmission.exception.ResourceNotFoundException;
import com.abhilash.project.projectforsubmission.model.InputRequest;
import com.abhilash.project.projectforsubmission.model.OutputResponse;
import com.abhilash.project.projectforsubmission.repository.IncomingRepository;
import com.abhilash.project.projectforsubmission.service.SequenceGeneratorService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProjectController {
	
	@Autowired
	private IncomingRepository incomingRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	
	
	@PostMapping("/incomingdata")
	public OutputResponse getoutputResponse(@Valid @RequestBody InputRequest inputRequest) throws ResourceNotFoundException {
		
		
		System.out.println(inputRequest);
		
		JSONObject jsonObj = new JSONObject(inputRequest);
		
		jsonObj.toString();
		
		//System.out.println(jsonObj.toString());
		
		OutputResponse  outputResponse = new OutputResponse();
		outputResponse.setId(sequenceGeneratorService.generateSequence(outputResponse.SEQUENCE_NAME));
		
		outputResponse.setResponsedata(jsonObj.toString());
		
		if (inputRequest.getId()!= (int)inputRequest.getId()) {
			
			throw new ResourceNotFoundException("Integer Not Found");
			
		}
		
		return incomingRepository.save(outputResponse );
	}
	
	
	
	
	
	
	@GetMapping("/outgoing_largestnumber/{stringdata}")
	public OutputResponse getoutputLargestResponse(@Valid @PathVariable(value = "stringdata") String stringdata) throws ResourceNotFoundException {
		//System.out.println(stringdata);
		
		//========================== DATA FOR REQUEST ===================
		
		final int ASCII_SIZE = 256; 
	        int count[] = new int[ASCII_SIZE]; 
	       
	        int len = stringdata.length(); 
	        for (int i=0; i<len; i++) 
	            count[stringdata.charAt(i)]++; 
	       
	        int max = -1;  // Initialize max count 
	        char result = ' ';   // Initialize result 
	       
	        
	        for (int i = 0; i < len; i++) { 
	            if (max < count[stringdata.charAt(i)]) { 
	                max = count[stringdata.charAt(i)]; 
	                result = stringdata.charAt(i); 
	            } 
	        } 
	        
	       // System.out.println(result);
	        
	        
	        //================ SAVE REQUESTS TO DB ======================
		      JSONObject obj = new JSONObject();
		      try {
		    	  
		    	  obj.put("Request", stringdata);
		    	  obj.put("Result", result);
		         
		      } catch(JSONException e) {
		         e.printStackTrace();
		      }
		     // System.out.println(obj.toString());
		      
		      OutputResponse  outputResponse = new OutputResponse();
		      outputResponse.setId(sequenceGeneratorService.generateSequence(outputResponse.SEQUENCE_NAME));
	        
		      outputResponse.setResponsedata(obj.toString());
	
				
				return incomingRepository.save(outputResponse );
	        	        
	        
//	        ---------------------SAVE REQUESTS TO DB ==========================
	   
		
	}
	
	
	@GetMapping("/outgoing_duplicate/{stringdata}")
	public OutputResponse getoutputDuplicateResponse(@Valid @PathVariable(value = "stringdata") String stringdata) throws ResourceNotFoundException {
	    
	    IntStream intStream = stringdata.chars();
	    
        Stream<Character> charsStream = intStream.mapToObj(ch -> (char) ch);

        Map<Character, Long> output = charsStream.collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));

       // System.out.println(output);
        
        //================ SAVE REQUESTS TO DB ======================
	      JSONObject obj = new JSONObject();
	      try {
	    	  
	    	  obj.put("Request", stringdata);
	    	  obj.put("Result", output);
	         
	      } catch(JSONException e) {
	         e.printStackTrace();
	      }
	     // System.out.println(obj.toString());
	      
	      OutputResponse  outputResponse = new OutputResponse();
	      outputResponse.setId(sequenceGeneratorService.generateSequence(outputResponse.SEQUENCE_NAME));
      
	      outputResponse.setResponsedata(obj.toString());

			
			return incomingRepository.save(outputResponse );
      	        
      
//      ---------------------SAVE REQUESTS TO DB ==========================

		
     
		
	}
	
	
	@GetMapping("/outgoing_romove")
	public OutputResponse getoutputRemoveResponse() throws ResourceNotFoundException {
		//System.out.println(stringdata);

		      
		      String str1 = "whiteSpacesGalore";
		        String newStr = "";
		        int startFrom = 11, endBefore = 17;// test startFrom and endBefore indices
		        for (int i = startFrom; i < endBefore; i++)
		            newStr += String.valueOf(str1.charAt(i));
		        System.out.println(newStr);
		        
		        
  //================ SAVE REQUESTS TO DB ======================
JSONObject obj = new JSONObject();
try {
	  
	  obj.put("Request", str1);
	  obj.put("Result", newStr);
   
} catch(JSONException e) {
   e.printStackTrace();
}
// System.out.println(obj.toString());

OutputResponse  outputResponse = new OutputResponse();
outputResponse.setId(sequenceGeneratorService.generateSequence(outputResponse.SEQUENCE_NAME));

outputResponse.setResponsedata(obj.toString());

	
	return incomingRepository.save(outputResponse );
        

//---------------------SAVE REQUESTS TO DB ========================== 
	   
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
