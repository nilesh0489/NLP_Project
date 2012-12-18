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

import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class POS_TOP {
    
    public POS_TOP() {
        
    }

    public void createMap() {
        POSModel model1 = new POSModelLoader().load(new File("en-pos-maxent.bin"));
        POSTaggerME tagger = new POSTaggerME(model1);
        for(int combination = 1; combination <= 1; combination++) {         
            HashMap<String, Double> globalMap = new HashMap<String, Double>();
            ArrayList<HashMap<String, Double>> mapList = new ArrayList<HashMap<String, Double>>();                                                     
            String fileName = "iphone-500/top_words.txt";             
            try{
                
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String str;
                while((str = br.readLine()) != null) {
                    if(!(str.isEmpty() || str.equals("### END ###"))) {
                        HashMap<String, Double> m = new HashMap<String, Double>();
                        str = str.replaceAll("\\W", " ");
                        Pattern stopWords = Pattern.compile("\\b(?:u|my|a|an|and|are|as|at|be|by|for|from|has|he|in|it|is|its|of|on|that|the|to|was|where|will|with|this|have|has|you|we|i|their|our|they|what|any|had|them)\\b\\s*", Pattern.CASE_INSENSITIVE);
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
                            oneLine = oneLine.toLowerCase();
                            
                            Pattern p = Pattern.compile("[^a-zA-Z0-9]");
                            boolean hasSpecialChar;
                            String word;
                            for(int x = 0; x < tags.length - 1; x++){
                                hasSpecialChar = p.matcher(tags[x]).find();
                                if(!hasSpecialChar && !p.matcher(tags[x]).find()) {
                                    word = tags[x] + tags[x+1];
                                    if(!m.containsKey(word)) {
                                        m.put(word, 1.0);
                                    }
                                    else
                                    {
                                        m.put(word,m.get(word) + 1);
                                    }
                                    
                                    if(!globalMap.containsKey(word)) {
                                        globalMap.put(word, 1.0);
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
                      
            String training_file = "iphone-500/pos_bi_top.txt";
            try{
                File file = new File(training_file);
                file.createNewFile();
                FileWriter fstream = new FileWriter(training_file);
                BufferedWriter out = new BufferedWriter(fstream);                                         
                                                
                //Create Attribute List
                Set<String> set = globalMap.keySet();
                Iterator<String> its = set.iterator();
                //Create Attribute List and calculate the IDF value for the attributes in the global Map
                double globalCount = 0;
                while(its.hasNext()) {              
                    String key = its.next();                                    
                    double count = 0;
                    for(HashMap<String, Double> gm : mapList) {
                        if(gm.containsKey(key)) {
                            count+=gm.get(key);
                        }
                    }
                    globalCount = globalCount + count;
                    globalMap.put(key, count);
                }
                
                Map<String, Double> result = MapUtil.sortByValue(globalMap);
                Iterator<String> it_gmap = result.keySet().iterator();                            
                
                while(it_gmap.hasNext()){
                    String key = it_gmap.next();
                    double normalized = result.get(key) / globalCount;
                    System.out.println(key + ":" + normalized);
                    out.write(key + " " + normalized);
                    out.write("\n");
                }
                out.close();
            }catch (Exception e) {
            	e.printStackTrace();
			}
        }
    }
        
    

    public static void main(String args[]) {
        POS_TOP p = new POS_TOP();
        p.createMap();
    }

}