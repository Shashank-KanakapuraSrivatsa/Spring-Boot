package de.upb.cs.textclassifier.preprocessing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;

import de.upb.cs.textclassifier.InputJsonObject;

public class SanitizeInputData {
	public List<InputJsonObject> replaceCategoryValues(List<InputJsonObject> dataJsonList) {

		List<InputJsonObject> updatedCategoriesList = new ArrayList<InputJsonObject>();
		List<String> retainCategoriesList = Arrays.asList("POLITICS", "WELLNESS", "ENTERTAINMENT", "TRAVEL",
				"STYLE & BEAUTY");
		for (InputJsonObject dataElement : dataJsonList) {
			// System.out.println("Current category" + dataElement.getCategory());
			if (!updatedCategoriesList.contains(dataElement.getCategory())) {
				String replaceCategory = "OTHER";
				InputJsonObject updateCategories = dataElement;
				updateCategories.setCategory(replaceCategory);
				updatedCategoriesList.add(updateCategories);
				// System.out.println(" Updated category" + updateCategories.getCategory());

			} else {
				updatedCategoriesList.add(dataElement);
			}

		}

		return updatedCategoriesList;
	}

	public List<InputJsonObject> processStopWords(List<InputJsonObject> dataWithUpdatedCategroriesList) {

		List<InputJsonObject> stopWordsProcessedList = new ArrayList<InputJsonObject>();
		System.out.println("Comes here");
		try {

			List<String> stopWords = Files.readAllLines(Paths.get("english_stopwords.txt"));

			for (InputJsonObject inputData : dataWithUpdatedCategroriesList) {

				InputJsonObject stopWordsProcessedElement = inputData;
				String stopWordsProcessedHeadlines = removeStopwords(stopWords, inputData.getHeadline());
				String stopWordsProcessedShortDescription = removeStopwords(stopWords,
						inputData.getshort_description());

				stopWordsProcessedElement.setHeadline(stopWordsProcessedHeadlines);
				stopWordsProcessedElement.setshort_description(stopWordsProcessedShortDescription);

				stopWordsProcessedList.add(stopWordsProcessedElement);

//				System.out.println("Headlines before stop words removal : " + inputData.getHeadline());
//				System.out.println("Headlines after stop words removal : " + stopWordsProcessedElement.getHeadline());
//				
//				System.out.println("ShortDescription before stop words removal : " + inputData.getshort_description());
//				System.out.println("ShortDescription after stop words removal : " + stopWordsProcessedElement.getshort_description());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return stopWordsProcessedList;
	}

	public String removeStopwords(List<String> stopWords, String text) {

		String[] words = text.replaceAll("[^\\x00-\\x7F\"']", "").replaceAll("\\\\n", "").replaceAll("\\\\r", "")
				.toLowerCase().split("\\s+");

		// List<String> outputList = new ArrayList<String>();
		String output = "";
		for (String word : words) {
			if (word.isEmpty())
				continue;
			if (isStopword(stopWords, word))
				continue;
			output += (word + " ");
		}

		return output;
	}

	private boolean isStopword(List<String> stopWords, String word) {
		if (stopWords.contains(word))
			return true;
		else
			return false;

	}

	public String getLemmasList(String text) {
		List<String> lemmas = new ArrayList<String>();
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		String[] textArray = text.split(" ");
				
		for (String annotateText : textArray) {
			Annotation doc = new Annotation(annotateText);
			pipeline.annotate(doc);
			
			
			List<CoreMap> sentences = doc.get(SentencesAnnotation.class);
			for(CoreMap sentence: sentences) {
				for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
					lemmas.add(token.get(LemmaAnnotation.class));
				}
					
			}
			
		}
		
		return String.join(" ", lemmas);
	}
   
	public List<InputJsonObject> performAnnotation(List<InputJsonObject> inputDataWithStopWordsRemoved) {
		
		List<InputJsonObject> processedLemmaList = new ArrayList<InputJsonObject>();
		
		for(InputJsonObject removedStopWords: inputDataWithStopWordsRemoved) {
			
			InputJsonObject annotatedElements = removedStopWords;
			String annotatedHeadlines = getLemmasList(removedStopWords.getHeadline());
			String annotatedShortDescription = getLemmasList(removedStopWords.getshort_description());
			
			annotatedElements.setHeadline(annotatedHeadlines);
			annotatedElements.setshort_description(annotatedShortDescription);
			
			processedLemmaList.add(annotatedElements);
			
		}
		return processedLemmaList;
	}
	
	
}
