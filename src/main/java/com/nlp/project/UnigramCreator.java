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

public class UnigramCreator {
	
	public UnigramCreator() {
		
	}
	
	public void createMap() {
		Pattern stopWords = Pattern.compile("\\b(?:a|able|about|across|after|all|almost|also|am|among|an|and|any|are|as|at|be|because|been|but|by|can|cannot|could|dear|did|do|does|either|else|ever|every|for|from|get|got|had|has|have|he|her|hers|him|his|how|however|i|if|in|into|is|it|its|just|least|let|like|likely|may|me|might|most|must|my|neither|no|nor|not|of|off|often|on|only|or|other|our|own|rather|said|say|says|she|should|since|so|some|than|that|the|their|them|then|there|these|they|this|tis|to|too|twas|us|wants|was|we|were|what|when|where|which|while|who|whom|why|will|with|would|yet|you|your)\\b\\s*", Pattern.CASE_INSENSITIVE);
		for(int combination = 1; combination <= 5; combination++) {		
			Map<String, Double> globalMap = new HashMap<String, Double>();
			ArrayList<HashMap<String, Double>> mapList = new ArrayList<HashMap<String, Double>>();
			ArrayList<HashMap<String, Double>> testMapList = new ArrayList<HashMap<String, Double>>();
			
			ArrayList<Double> trainUniCount = new ArrayList<Double>();
			ArrayList<Double> testUniCount = new ArrayList<Double>();
			
			
			String fileName = "iphone-500/new-train/train-fold-" + combination + ".txt";	
			String testFile = "iphone-500/new-test/test-fold-" + combination + ".txt";
			try{
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				String str;
				while((str = br.readLine()) != null) {
					double unigramCount = 0.0;
					if(!(str.isEmpty() || str.equals("### END ###"))) {
						HashMap<String, Double> m = new HashMap<String, Double>();													
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
			//			LemmaTest lt = new LemmaTest();					
						for(String oneLine : lines) {
							oneLine = oneLine.replaceAll("[^0-9a-zA-Z\\s\\.]", " ");
							oneLine = oneLine.replaceAll("[0-9]+", " ");
							oneLine = oneLine.replace(".", "");
							oneLine = oneLine.toLowerCase();							
							Matcher matcher = stopWords.matcher(oneLine);
							oneLine = matcher.replaceAll(" ");
						//	List<String> words = new ArrayList<String>();
						//	words = lt.lemmatize(oneLine);
							String words[] = t.tokenize(oneLine);				
							for(int j = 0; j < words.length; j++) {
								String word = words[j];									
								if(!m.containsKey(word)) {
									m.put(word, 1.0);
								}
								else{
									m.put(word, m.get(word) + 1.0);
								}
								if(!globalMap.containsKey(word)) {
									globalMap.put(word, 1.0);
								}								
							}
							unigramCount += words.length;
						}
												
						mapList.add(m);
						trainUniCount.add(unigramCount);
					}						
				}
			}catch (Exception e) {
				e.printStackTrace();
			}			
			
			try{
				BufferedReader br = new BufferedReader(new FileReader(testFile));
				String str;
				while((str = br.readLine()) != null) {
					Double testUnigramCount = 0.0;
					if(!(str.isEmpty() || str.equals("### END ###"))) {
						HashMap<String, Double> m = new HashMap<String, Double>();													
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
			//			LemmaTest lt = new LemmaTest();					
						for(String oneLine : lines) {
							oneLine = oneLine.replaceAll("[^0-9a-zA-Z\\s\\.]", " ");
							oneLine = oneLine.replaceAll("[0-9]+", " ");
							oneLine = oneLine.replace(".", "");
							oneLine = oneLine.toLowerCase();							
							Matcher matcher = stopWords.matcher(oneLine);
							oneLine = matcher.replaceAll(" ");
							String words[] = t.tokenize(oneLine);
						//	List<String> words = new ArrayList<String>();
						//	words = lt.lemmatize(oneLine);
							for(int j = 0; j < words.length; j++) {
								String word = words[j];									
								if(!m.containsKey(word)) {
									m.put(word, 1.0);
								}
								else {
									m.put(word, m.get(word) + 1.0);
								}
							}							
							testUnigramCount += words.length;
						}												
						testUniCount.add(testUnigramCount);
						testMapList.add(m);
					}										
				}				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(globalMap.size());
			System.out.println(mapList.size());
			System.out.println(testMapList.size());
			
			String training_file = "iphone-500/training-uni-" + combination + ".arff";
			String test_file = "iphone-500/test-uni-" + combination + ".arff";
			try{
				File file = new File(training_file);
				file.createNewFile();
				FileWriter fstream = new FileWriter(training_file);
				BufferedWriter out = new BufferedWriter(fstream);
				
				File file1 = new File(test_file);
				file1.createNewFile();
				FileWriter fstream1 = new FileWriter(test_file);
				BufferedWriter out1 = new BufferedWriter(fstream1);
				
				out.write("@RELATION blog-posts");
				out.write("\n");
				out.write("\n");
				
				out1.write("@RELATION blog-posts");
				out1.write("\n");
				out1.write("\n");

				Set<String> set = globalMap.keySet();
				Iterator<String> its = set.iterator();

				while(its.hasNext()) {				
					String key = its.next();				
					out.write("@ATTRIBUTE " + key + " NUMERIC");
					out.write("\n");
					out1.write("@ATTRIBUTE " + key + " NUMERIC");
					out1.write("\n");
					double count = 0;
					for(HashMap<String, Double> gm : mapList) {
						if(gm.containsKey(key)) {
							count++;
						}
					}
					globalMap.put(key, count);
				}
				
				globalMap = MapUtil.sortByValue(globalMap);
				out.write("@ATTRIBUTE post-popular {True, False}");
				out.write("\n");				

				out.write("@DATA");
				out.write("\n");
				boolean flag = true;
				
				Set<String> checkSet = globalMap.keySet();
				int countMaps = 0;
				for(HashMap<String, Double> gm : mapList) {
					Iterator<String> it = checkSet.iterator();
					StringBuffer strbuf = new StringBuffer();
					while(it.hasNext()) {
						String key = it.next();						
						double DF = globalMap.get(key);
						double TF = 0.0;
						if(gm.containsKey(key)) {
							TF = gm.get(key);
						}						
						double TFIDF = (TF/trainUniCount.get(countMaps)) * (mapList.size()/DF);
					//	double TFIDF = TF;
					//	if(TFIDF == 0) {
					//		TFIDF = 0.05;
					//	}                            
						strbuf.append(TFIDF + ",");
					}					
					if(flag == true) {					
						strbuf.append("False");
						flag = false;				
					}
					else {				
						strbuf.append("True");
						flag = true;
					}
					out.write(strbuf.toString());
					out.write("\n");
					countMaps++;
				}
				
				out.close();
				out1.write("@ATTRIBUTE post-popular {True, False}");
				out1.write("\n");				

				out1.write("@DATA");
				out1.write("\n");
				flag = true;
				
				countMaps = 0;
				for(HashMap<String, Double> test : testMapList) {
					Iterator<String> it = checkSet.iterator();
					StringBuffer strbuf = new StringBuffer();
					while(it.hasNext()) {
						String key = it.next();
						double DF = globalMap.get(key);
						double TF = 0.0;
						if(test.containsKey(key)) {
							TF = test.get(key);
						}
						double TFIDF = (TF/testUniCount.get(countMaps)) * (testMapList.size()/DF);
					//	double TFIDF = TF;
					//	if(TFIDF == 0) {
					//		TFIDF = 0.05;
					//	}
						strbuf.append(TFIDF + ",");
					}
					if(flag == true) {
						strbuf.append("False");
						flag = false;				
					}
					else {
						strbuf.append("True");
						flag = true;
					}
					out1.write(strbuf.toString());
					out1.write("\n");
				}	
				out1.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) {
		UnigramCreator uc = new UnigramCreator();
		uc.createMap();
	}
}
