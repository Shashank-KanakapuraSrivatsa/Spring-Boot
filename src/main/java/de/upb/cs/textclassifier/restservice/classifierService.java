package de.upb.cs.textclassifier.restservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class classifierService {
	restServiceWrapper restServiceWrapper = new restServiceWrapper();

	public HashMap<String, ArrayList<String>> constructOutput(ArrayList<String> finalList) {
		HashMap<String, ArrayList<String>> outputHashMap = new HashMap<String, ArrayList<String>>();

		outputHashMap.put("category", finalList);

		return outputHashMap;
	}
}
