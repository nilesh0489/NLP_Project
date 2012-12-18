package com.nlp.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Temp {
	
	public Temp() {
		
	}

	public HashMap<String, Double> createMap(String mode) {
		POSModel model1 = new POSModelLoader().load(new File("en-pos-maxent.bin"));
	    POSTaggerME tagger = new POSTaggerME(model1);
	    HashMap<String, Double> globalMap = new HashMap<String, Double>();
		for(int combination = 1; combination <= 1; combination++) {			
//			ArrayList<HashMap<String, Double>> mapList = new ArrayList<HashMap<String, Double>>();											   
			String fileName = "iphone-500/" + mode + "_words.txt";	
			String training_file = "iphone-500/posbi_"+mode+".txt";
			File file = new File(training_file);
			FileWriter fstream = null;
			
			BufferedWriter out = null;
			try{
				file.createNewFile();
				fstream = new FileWriter(training_file);
				out = new BufferedWriter(fstream);
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				String str;
				while((str = br.readLine()) != null) {
					if(!(str.isEmpty() || str.equals("### END ###"))) {
//						HashMap<String, Double> m = new HashMap<String, Double>();
						str = str.replaceAll("\\W", " ");
						Pattern stopWords = Pattern.compile("\\b(?:a|an|and|are|as|at|be|by|for|from|has|he|in|it|is|its|of|on|that|the|to|was|where|will|with)\\b\\s*", Pattern.CASE_INSENSITIVE);
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
							String word;
							for(int x = 0; x < tags.length - 1; x++){
								hasSpecialChar = p.matcher(tags[x]).find();
								if(!hasSpecialChar && !p.matcher(tags[x + 1]).find()) {
									word = tags[x] + " " + tags[x + 1];
									if(!globalMap.containsKey(word)) {
										globalMap.put(word, 1.0);
										out.write(word);
										out.write("\n");
									}									
								}									
							}						
						}
					}					
				}
				out.close();
			}catch (Exception e) { 
				e.printStackTrace();
			}
		}
		
		return globalMap;
	}

	public static void main(String args[]) {
		Temp p = new Temp();
		HashMap<String, Double> m1 = new HashMap<String, Double>();
		HashMap<String, Double> m2 = new HashMap<String, Double>();
		m1 = p.createMap("top");
		m2 = p.createMap("bottom");
		
		Intersection.findCommonElements(m1, m2);
	}

}
