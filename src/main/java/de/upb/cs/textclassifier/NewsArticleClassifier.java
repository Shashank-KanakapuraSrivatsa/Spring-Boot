package de.upb.cs.textclassifier;

import org.springframework.boot.SpringApplication;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.upb.cs.textclassifier.jsonhandler.CustomJsonParser;
import de.upb.cs.textclassifier.preprocessing.SanitizeInputData;

@SpringBootApplication
@ComponentScan(basePackages={"de.upb.cs.textclassifier"})
public class NewsArticleClassifier {
	public static void main(String[] args) {

		CustomJsonParser customJsonParser = new CustomJsonParser();
		
		// Stage 1 : Reading the input file
		SpringApplication.run(NewsArticleClassifier.class, args);
		List<InputJsonObject> jsonFileOutput = customJsonParser.jsonhandler("News_Category_Dataset_v2_30records.json");
		//jsonFileOutput.forEach(System.out::println);
		//List<InputJsonObject> inputFile = new ArrayList<InputJsonObject>();
		
		// Stage 2 : Preprocessing
		// Step 1 : Update categories
		SanitizeInputData sanitizeInputData = new SanitizeInputData();
        List<InputJsonObject> replacedCategoryValues = sanitizeInputData.replaceCategoryValues(jsonFileOutput);
		replacedCategoryValues.forEach(System.out::println);
        
        List<InputJsonObject> inputDataWithStopWordsRemoved = sanitizeInputData.processStopWords(replacedCategoryValues);
        
        //inputDataWithStopWordsRemoved.forEach(System.out::println);
        
        List<InputJsonObject> annotatedText = sanitizeInputData.performAnnotation(inputDataWithStopWordsRemoved);
        annotatedText.forEach(System.out::println);
        
		

	}
}
