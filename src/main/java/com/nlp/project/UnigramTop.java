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

public class UnigramTop {
	
	public UnigramTop() {
		
	}
	
	public Map<String, Double> createMap(String mode) throws IOException {
		Map<String, Double> intersectMap = new HashMap<String, Double>();
		ArrayList<Map<String, Double>> mapList = new ArrayList<Map<String, Double>>();
		for(int combination = 1; combination <= 1; combination++) {					
			String fileName = "iphone-500/" + mode +"_words.txt";	
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
							oneLine = oneLine.replace(".", "");
							oneLine = oneLine.toLowerCase();
							Pattern stopWords = Pattern.compile("\\b(?:your|they|not|if|u|my|a|an|and|are|as|at|be|by|for|from|has|he|in|it|is|its|of|on|that|the|to|was|where|will|with|this|have|has|you|we|i|their|our|they|what|any|had|them)\\b\\s*", Pattern.CASE_INSENSITIVE);
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
								
								if(intersectMap.containsKey(word)) {
									intersectMap.put(word, intersectMap.get(word) + 1.0);
								}
								else {
									intersectMap.put(word, 1.0);
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
	//	boolean flag = true;
		System.out.println(mapList.size());
	//	intersectMap = Intersection.findCommonElements(mapList, true);
		intersectMap = MapUtil.sortByValue(intersectMap);
		System.out.println("Size: " + intersectMap.size());
		Set<String> set = intersectMap.keySet();
		Iterator<String> itr = set.iterator();
		
		File file = new File("iphone-500/unigram-" + mode + ".txt");
		if(file.createNewFile()) {
		}
		FileWriter fstream = new FileWriter("iphone-500/unigram-" + mode + ".txt");
		BufferedWriter out = new BufferedWriter(fstream);
		int count = 0;
		while(itr.hasNext() && count < 100) {
			String key = itr.next();
			out.write(key + " : " + intersectMap.get(key));
			out.write("\n");
			count++;
		}
		
		out.close();
		
		return intersectMap;
	}
	
	public static void main(String args[]) throws IOException {
		UnigramTop uc = new UnigramTop();
		uc.createMap("top");
		uc.createMap("bottom");
	}
}