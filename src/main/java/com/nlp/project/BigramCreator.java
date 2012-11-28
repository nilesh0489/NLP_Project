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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class BigramCreator {
	
	public BigramCreator() {
		
	}
	
	public void createMap() {
		for(int combination = 1; combination <= 5; combination++) {		
			HashMap<String, Double> globalMap = new HashMap<String, Double>();
			ArrayList<HashMap<String, Double>> mapList = new ArrayList<HashMap<String, Double>>();
			ArrayList<HashMap<String, Double>> testMapList = new ArrayList<HashMap<String, Double>>();
			String fileName = "iphone-500/train/train-fold-" + combination + ".txt";	
			String testFile = "iphone-500/test/test-fold-" + combination + ".txt";
			try{
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				String str;
				while((str = br.readLine()) != null) {
					if(!(str.isEmpty() || str.equals("### END ###"))) {
						HashMap<String, Double> m = new HashMap<String, Double>();							
//						str = str.replaceAll("[^0-9a-zA-Z\\s\\.]", " ");
//						Pattern stopWords = Pattern.compile("\\b(?:|a|an|and|are|as|at|be|by|for|from|has|he|in|it|is|its|of|on|that|the|to|was|where|will|with)\\b\\s*", Pattern.CASE_INSENSITIVE);
//						Matcher matcher = stopWords.matcher(str);
//						str = matcher.replaceAll(" ");
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
							oneLine = oneLine.replaceAll("[^A-Za-z0-9']", " ").replaceAll("\\s+", " ");
							Pattern stopWords = Pattern.compile("\\b(?:a|an|and|are|as|at|be|by|for|from|has|he|in|it|is|its|of|on|that|the|to|was|where|will|with)\\b\\s*", Pattern.CASE_INSENSITIVE);
			                Matcher matcher = stopWords.matcher(oneLine);
			                oneLine = matcher.replaceAll(" "); 
							String words[] = t.tokenize(oneLine);				
							for(int j = 0; j < words.length - 1; j++) {
								String word = words[j] + words[j + 1];									
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
						str = str.replaceAll("[^0-9a-zA-Z\\s\\.]", " ");
						Pattern stopWords = Pattern.compile("\\b(?:|a|an|and|are|as|at|be|by|for|from|has|he|in|it|is|its|of|on|that|the|to|was|where|will|with)\\b\\s*", Pattern.CASE_INSENSITIVE);
						Matcher matcher = stopWords.matcher(str);
						str = matcher.replaceAll(" ");
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
							String words[] = t.tokenize(oneLine);
				
							for(int j = 0; j < words.length - 1; j++) {
								String word = words[j] + words[j + 1];									
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
			
			String training_file = "iphone-500/training-bi-" + combination + ".arff";
			String test_file = "iphone-500/test-bi-" + combination + ".arff";
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
				out.write("@ATTRIBUTE post-popular {True, False}");
				out.write("\n");				

				out.write("@DATA");
				out.write("\n");
				boolean flag = true;
				
				Set<String> checkSet = globalMap.keySet();
				
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
						double TFIDF = TF/gm.size() * 165/DF;
						if(TFIDF == 0) {
							TFIDF = 0.05;
						}
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
				}
				
				out.close();
				out1.write("@ATTRIBUTE post-popular {True, False}");
				out1.write("\n");				

				out1.write("@DATA");
				out1.write("\n");
				flag = true;
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
						double TFIDF = TF/test.size() * 165/DF;
						if(TFIDF == 0) {
							TFIDF = 0.05;
						}
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
		BigramCreator bc = new BigramCreator();
		bc.createMap();
	}
}
