package de.upb.cs.textclassifier.restservice;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class restServiceProvider {
	
	ArrayList<String> finalList = new ArrayList<String>();
	restServiceWrapper restServiceWrapper = new restServiceWrapper();
	
	@Autowired
	private classifierService classifierService;
	
	@RequestMapping(method=RequestMethod.POST, value="/classify", consumes = "application/json")
	@ResponseBody
	public HashMap<String,ArrayList<String>> sendResponse(ArrayList<String> finalList) {
		finalList.add("cat1");
		finalList.add("cat2");
		HashMap<String,ArrayList<String>> outputHashMap = new HashMap<String, ArrayList<String>>();
		outputHashMap = classifierService.constructOutput(finalList);
		return outputHashMap;
	}
}
