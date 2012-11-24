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

import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class POS {
	
	public POS() {
		
	}

	public void createMap() {
		POSModel model1 = new POSModelLoader().load(new File("en-pos-maxent.bin"));
	    POSTaggerME tagger = new POSTaggerME(model1);
		for(int combination = 1; combination <= 5; combination++) {			
			HashMap<String, Double> globalMap = new HashMap<String, Double>();
			ArrayList<HashMap<String, Double>> mapList = new ArrayList<HashMap<String, Double>>();
			ArrayList<HashMap<String, Double>> testMapList = new ArrayList<HashMap<String, Double>>();											   
			String fileName = "tech-150/train/train-fold-" + combination + ".txt";	
			String testFile = "tech-150/test/test-fold-" + combination + ".txt";
			
			try{
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				String str;
				while((str = br.readLine()) != null) {
					if(!(str.isEmpty() || str.equals("### END ###"))) {
						HashMap<String, Double> m = new HashMap<String, Double>();
						str = str.replaceAll("\\W", " ");
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
						    }
						  }
						}
										
						TokenizerME t = new TokenizerME(tokenModel);
											
						for(String oneLine : lines) {
							String words[] = t.tokenize(oneLine);
							String tags[] = tagger.tag(words);
							Pattern p = Pattern.compile("[^a-zA-Z0-9]");
							boolean hasSpecialChar;
							for(String oneTag : tags) {
								hasSpecialChar = p.matcher(oneTag).find();
								if(!hasSpecialChar) {
									if(!m.containsKey(oneTag)) {
										m.put(oneTag, 1.0);
									}
									if(!globalMap.containsKey(oneTag)) {
										globalMap.put(oneTag, 1.0);
									}
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
						str = str.replaceAll("\\W", " ");
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
						    }
						  }
						}
										
						TokenizerME t = new TokenizerME(tokenModel);
											
						for(String oneLine : lines) {
							String words[] = t.tokenize(oneLine);
							String tags[] = tagger.tag(words);
							Pattern p = Pattern.compile("[^a-zA-Z0-9]");
							boolean hasSpecialChar;
							for(String oneTag : tags) {
								hasSpecialChar = p.matcher(oneTag).find();
								if(!hasSpecialChar) {
									if(!m.containsKey(oneTag)) {
										m.put(oneTag, 1.0);
									}									
								}									
							}						
						}
						testMapList.add(m);
					}					
				}
			}catch (Exception e) { 
				e.printStackTrace();
			}
			
			String training_file = "tech-150/training-pos-" + combination + ".arff";
			String test_file = "tech-150/test-pos-" + combination + ".arff";
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
				//Create Attribute List
				Set<String> set = globalMap.keySet();
				Iterator<String> its = set.iterator();
				//Create Attribute List and calculate the IDF value for the attributes in the global Map
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
				boolean flag = false;
				for(HashMap<String, Double> gm : mapList) {
					Set<String> checkSet = globalMap.keySet();
					Iterator<String> it = checkSet.iterator();
					String data = "";
					while(it.hasNext()) {
						String key = it.next();
						double DF = globalMap.get(key);
						double TF = 0.0;
						if(gm.containsKey(key)) {
							TF = gm.get(key);
						}
						double TFIDF = TF / DF;
						data = data + TFIDF + ",";
					}
					if(flag == false) {
						data = data + "False";
						flag = true;				
					}
					else {
						data = data + "True";
						flag = false;
					}
					out.write(data);
					out.write("\n");
				}								
				out1.write("@ATTRIBUTE post-popular {True, False}");
				out1.write("\n");				
				out1.write("@DATA");
				out1.write("\n");
				flag = false;
				for(HashMap<String, Double> gm : testMapList) {
					Set<String> checkSet = globalMap.keySet();
					Iterator<String> it = checkSet.iterator();
					String data = "";
					while(it.hasNext()) {
						String key = it.next();
						double DF = globalMap.get(key);
						double TF = 0.0;
						if(gm.containsKey(key)) {
							TF = gm.get(key);
						}
						double TFIDF = TF / DF;
						data = data + TFIDF + ",";
					}
					if(flag == false) {
						data = data + "False";
						flag = true;				
					}
					else {
						data = data + "True";
						flag = false;
					}
					out1.write(data);
					out1.write("\n");
				}
				out.close();
				out1.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		POS p = new POS();
		p.createMap();
	}

}
