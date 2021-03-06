package com.nlp.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class BottomWords {
	
	public BottomWords() {
		
	}
	
	public void createMap() throws IOException {
		Map<String, Double> intersectMap = new HashMap<String, Double>();
		ArrayList<Map<String, Double>> mapList = new ArrayList<Map<String, Double>>();
		for(int combination = 1; combination <= 1; combination++) {					
			String fileName = "iphone-500/bottom_words.txt";	
			try{
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				String str;
				while((str = br.readLine()) != null) {
					if(!(str.isEmpty() || str.equals("### END ###"))) {
						Map<String, Double> m = new HashMap<String, Double>();													
						InputStream modelIn = new FileInputStream("se-sent.bin");
						SentenceModel model = null;
						try {
						  model = new SentenceModel(modelIn);
						}
						catch (IOException e) {
						  e.printStackTrace();
						}
						finally {
						  if (modelIn != null) {
							  try {
								  modelIn.close();
							  }
							  catch (IOException e) {
								  e.printStackTrace();
							  }
						  }
						}						
							
						SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);		
						String lines[] = sentenceDetector.sentDetect(str);
												
						InputStream token = new FileInputStream("en-token.bin");
						TokenizerModel tokenModel = null;
						try {
						  tokenModel = new TokenizerModel(token);
						}
						catch (IOException e) {
						  e.printStackTrace();
						}
						finally {
						  if (modelIn != null) {
						    try {
						      modelIn.close();
						    }
						    catch (IOException e) {
						    	e.printStackTrace();
						    }
						  }
						}
										
						TokenizerME t = new TokenizerME(tokenModel);
											
						for(String oneLine : lines) {
							oneLine = oneLine.replaceAll("[^0-9a-zA-Z\\s\\.]", " ");
							oneLine = oneLine.toLowerCase();
							Pattern stopWords = Pattern.compile("\\b(?:u|my|a|an|and|are|as|at|be|by|for|from|has|he|in|it|is|its|of|on|that|the|to|was|where|will|with|this|have|has|you|we|i|their|our|they|what|any|had|them)\\b\\s*", Pattern.CASE_INSENSITIVE);
							Matcher matcher = stopWords.matcher(oneLine);
							oneLine = matcher.replaceAll(" ");
							String words[] = t.tokenize(oneLine);				
							for(int j = 0; j < words.length; j++) {
								String word = words[j];					
								if(!m.containsKey(word)) {
									m.put(word, 1.0);
								}
								else {
									m.put(word, m.get(word) + 1.0);
								}
							}
						}
						mapList.add(m);
					}						
				}
			}catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		intersectMap = Intersection.findCommonElements(mapList, true);
		System.out.println("Size: " + intersectMap.size());
		Set<String> set = intersectMap.keySet();
		Iterator<String> itr = set.iterator();
		
		File file = new File("iphone-500/bottom.txt");
		if(file.createNewFile()) {
		}
		FileWriter fstream = new FileWriter("iphone-500/bottom.txt");
		BufferedWriter out = new BufferedWriter(fstream);
		
		while(itr.hasNext()) {
			out.write(itr.next());
			out.write("\n");
		}
		
		out.close();
	}
	
	public static void main(String args[]) throws IOException {
		BottomWords uc = new BottomWords();
		uc.createMap();
	}
}