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

public class BagOfWords {
	
	public BagOfWords() {
		
	}
	
	public void createMap() {
		for(int combination = 1; combination <= 5; combination++) {		
			Map<String, Double> globalMap = new HashMap<String, Double>();
			ArrayList<HashMap<String, Double>> mapList = new ArrayList<HashMap<String, Double>>();
			ArrayList<HashMap<String, Double>> testMapList = new ArrayList<HashMap<String, Double>>();
			String fileName = "iphone-500/top-train/train-fold-" + combination + ".txt";	
			String testFile = "iphone-500/top-test/test-fold-" + combination + ".txt";
			try{
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				String str;
				while((str = br.readLine()) != null) {
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
								if(!globalMap.containsKey(word)) {
									globalMap.put(word, 1.0);
								}								
							}
						}
						mapList.add(m);
					}						
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			try{
				BufferedReader br = new BufferedReader(new FileReader(testFile));
				String str;
				while((str = br.readLine()) != null) {
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
											
						for(String oneLine : lines) {
							oneLine = oneLine.replaceAll("[^0-9a-zA-Z\\s\\.]", " ");
							Pattern stopWords = Pattern.compile("\\b(?:u|my|a|an|and|are|as|at|be|by|for|from|has|he|in|it|is|its|of|on|that|the|to|was|where|will|with|this|have|has|you|we|i|their|our|they|what|any|had|them)\\b\\s*", Pattern.CASE_INSENSITIVE);
							Matcher matcher = stopWords.matcher(oneLine);
							oneLine = matcher.replaceAll(" ");
							String words[] = t.tokenize(oneLine);
				
							for(int j = 0; j < words.length; j++) {
								String word = words[j];									
								if(!m.containsKey(word)) {
									m.put(word, 1.0);
								}							
							}
						}
						testMapList.add(m);
					}										
				}				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(globalMap.size());
			System.out.println(mapList.size());
			System.out.println(testMapList.size());
			
			String training_file = "iphone-500/training-bogtop-" + combination + ".arff";
			String test_file = "iphone-500/test-bogtop-" + combination + ".arff";
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
				double count = 0;
				String key = null;
				
				while(its.hasNext()) {
					count = 0;
					key = its.next();
					for(HashMap<String, Double> gm : mapList) {						
						if(gm.containsKey(key)) {
							count++;
						}
					}
					globalMap.put(key, count);
				}				
				
				globalMap = MapUtil.sortByValue(globalMap);
				
				int mapCount = 1;
				its = set.iterator();
				while(its.hasNext() && mapCount <= 200) {
					key = its.next();
					out.write("@ATTRIBUTE " + key + " NUMERIC");
					out.write("\n");
					out1.write("@ATTRIBUTE " + key + " NUMERIC");
					out1.write("\n");
					
					mapCount++;
				}							
				
				out.write("@ATTRIBUTE post-popular {True, False}");
				out.write("\n");				

				out.write("@DATA");
				out.write("\n");
				boolean flag = true;
				
				Set<String> checkSet = globalMap.keySet();
				
				for(HashMap<String, Double> gm : mapList) {
					mapCount = 1;
					Iterator<String> it = checkSet.iterator();
					StringBuffer strbuf = new StringBuffer();
					while(it.hasNext() && mapCount <= 200) {
						key = it.next();						
						double tfidf = 0.0;
						if(gm.containsKey(key)) {
							tfidf = 1;
						}						                            
						strbuf.append(tfidf + ",");
						mapCount++;
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
				}
				
				out.close();
				out1.write("@ATTRIBUTE post-popular {True, False}");
				out1.write("\n");				

				out1.write("@DATA");
				out1.write("\n");
				flag = true;
				
				for(HashMap<String, Double> test : testMapList) {
					mapCount = 1;
					Iterator<String> it = checkSet.iterator();
					StringBuffer strbuf = new StringBuffer();
					while(it.hasNext() && mapCount <= 200) {
						key = it.next();
						double TF = 0.0;
						if(test.containsKey(key)) {
							TF = 1;
						}
						strbuf.append(TF + ",");
						mapCount++;
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
		BagOfWords uc = new BagOfWords();
		uc.createMap();
	}
}