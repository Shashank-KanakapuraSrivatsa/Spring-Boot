package de.upb.cs.textclassifier.jsonhandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.upb.cs.textclassifier.InputJsonObject;

public class CustomJsonParser {
	public List<InputJsonObject> jsonhandler(String file) {

		List<InputJsonObject> dataJsonList = new ArrayList<InputJsonObject>();

		// read json file data to String
		try {

			List<String> newsItems = Files.readAllLines(Paths.get(file));

			// newsItems.forEach(System.out::println);

			ObjectMapper objectMapper = new ObjectMapper();

			// TODO : To implement the same using Streams
			for (String newsElements : newsItems) {
				InputJsonObject inputjsonobj = new InputJsonObject();
				inputjsonobj = objectMapper.readValue(newsElements, InputJsonObject.class);

				dataJsonList.add(inputjsonobj);

			}

			// System.out.println("News Categories Object\n" + inputjsonobj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataJsonList;
	}

}