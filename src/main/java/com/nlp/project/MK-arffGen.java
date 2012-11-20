package nlp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;



public class Part1 {
	public static void main(String args[]) {
		
		try {
			//Get 5 fold files
			
			//fold 1
			
		/*	

			BufferedReader fp1 = new BufferedReader(new FileReader("E:\\nlp-assn-data\\hotel-reviews\\hotel_truthful"));
			BufferedReader fp2 = new BufferedReader(new FileReader("E:\\nlp-assn-data\\hotel-reviews\\hotel_deceptive"));
			PrintWriter out = new PrintWriter(new File("E:\\nlp-assn-data\\hotel-reviews\\fold1.txt"));
			String s1,s2;
			int count_rev=0;
			while((s1 = fp1.readLine()) != null && count_rev <80) {
				count_rev++;
				out.println(s1);
			
				}
			
			count_rev=0;
			while((s2 = fp2.readLine()) != null && count_rev <80) {
				count_rev++;
				out.println(s2);
				
			}
			fp1.close();
			fp2.close();
			out.close();
			System.out.println("Done fold1");
			
			//fold 2 
			BufferedReader fp3 = new BufferedReader(new FileReader("E:\\nlp-assn-data\\hotel-reviews\\hotel_truthful"));
			BufferedReader fp4 = new BufferedReader(new FileReader("E:\\nlp-assn-data\\hotel-reviews\\hotel_deceptive"));
			PrintWriter out2 = new PrintWriter(new File("E:\\nlp-assn-data\\hotel-reviews\\fold2.txt"));
			String s3,s4;
			int count_rev2=0;
			for(int i=0;i<80;i++)fp3.readLine();
			while((s3 = fp3.readLine()) != null && count_rev2 <80) {
				count_rev2++;
				out2.println(s3);
			
			}
			
			count_rev2=0;
			for(int i=0;i<80;i++)fp4.readLine();
			while((s4 = fp4.readLine()) != null && count_rev2 <80) {
				count_rev2++;
				out2.println(s4);
			
			}
			fp3.close();
			fp4.close();
			out2.close();
			System.out.println("Done fold2");
			
			//fold 3
			BufferedReader fp5 = new BufferedReader(new FileReader("E:\\nlp-assn-data\\hotel-reviews\\hotel_truthful"));
			BufferedReader fp6 = new BufferedReader(new FileReader("E:\\nlp-assn-data\\hotel-reviews\\hotel_deceptive"));
			PrintWriter out3 = new PrintWriter(new File("E:\\nlp-assn-data\\hotel-reviews\\fold3.txt"));
			String s5,s6;
			int count_rev3=0;
			for(int i=0;i<160;i++)fp5.readLine();
			while((s5 = fp5.readLine()) != null && count_rev3<80) {
				count_rev3++;
				out3.println(s5);
			
			}
			
			count_rev3=0;
			for(int i=0;i<160;i++)fp6.readLine();
			while((s6 = fp6.readLine()) != null && count_rev3<80) {
				count_rev3++;
				out3.println(s6);
			
			}
			fp5.close();
			fp6.close();
			out3.close();
			System.out.println("Done fold3");
			
			//fold 4
			BufferedReader fp7 = new BufferedReader(new FileReader("E:\\nlp-assn-data\\hotel-reviews\\hotel_truthful"));
			BufferedReader fp8 = new BufferedReader(new FileReader("E:\\nlp-assn-data\\hotel-reviews\\hotel_deceptive"));
			PrintWriter out4 = new PrintWriter(new File("E:\\nlp-assn-data\\hotel-reviews\\fold4.txt"));
			String s7,s8;
			int count_rev4=0;
			for(int i=0;i<240;i++)fp7.readLine();
			while((s7 = fp7.readLine()) != null && count_rev4<80) {
				count_rev4++;
				out4.println(s7);
			
			}
			
			count_rev4=0;
			for(int i=0;i<240;i++)fp8.readLine();
			while((s8 = fp8.readLine()) != null && count_rev4<80) {
				count_rev4++;
				out4.println(s8);
		
			}
			fp7.close();
			fp8.close();
			out4.close();
			System.out.println("Done fold4");
			
			//fold 5
			BufferedReader fp9 = new BufferedReader(new FileReader("E:\\nlp-assn-data\\hotel-reviews\\hotel_truthful"));
			BufferedReader fp10 = new BufferedReader(new FileReader("E:\\nlp-assn-data\\hotel-reviews\\hotel_deceptive"));
			PrintWriter out5 = new PrintWriter(new File("E:\\nlp-assn-data\\hotel-reviews\\fold5.txt"));
			String s9,s10;
			int count_rev5=0;
			for(int i=0;i<320;i++)fp9.readLine();
			while((s9 = fp9.readLine()) != null && count_rev5<80) {
				count_rev5++;
				out5.println(s9);
			
			}
			
			count_rev5=0;
			for(int i=0;i<320;i++)fp10.readLine();
			while((s10 = fp10.readLine()) != null && count_rev5<80) {
				count_rev5++;
				out5.println(s10);
			
			}
		
			fp9.close();
			fp10.close();
			out5.close();
			System.out.println("Done fold5");
		*/
			/*String[] folds = {"fold1.txt", "fold2.txt", "fold3.txt", "fold4.txt", "fold5.txt"};
			String[] uni = {"uni1.txt", "uni2.txt", "uni3.txt", "uni4.txt", "uni5.txt"};
			String[] bi = {"bi1.txt", "bi2.txt", "bi3.txt", "bi4.txt", "bi5.txt"};
			String[] tri = {"tri1.txt", "tri2.txt", "tri3.txt", "tri4.txt", "tri5.txt"};
			String[] uniarff = {"uni1.arff", "uni2.arff", "uni3.arff", "uni4.arff", "uni5.arff"};
			String[] biarff = {"bi1.arff", "bi2.arff", "bi3.arff", "bi4.arff", "bi5.arff"};
			String[] triarff = {"tri1.arff", "tri2.arff", "tri3.arff", "tri4.arff", "tri5.arff"};
			String[] unitestarff = {"uni1-test.arff", "uni2-test.arff", "uni3-test.arff", "uni4-test.arff", "uni5-test.arff"};
			String[] bitestarff = {"bi1-test.arff", "bi2-test.arff", "bi3-test.arff", "b14-test.arff", "bi5-test.arff"};
			String[] tritestarff = {"tri1-test.arff", "tri2-test.arff", "tri3-test.arff", "tri4-test.arff", "tri5-test.arff"};
			String[] posarff = {"pos1.arff", "pos2.arff", "pos3.arff", "pos4.arff", "pos5.arff"};
			String[] posarfft = {"pos1-test.arff", "pos2-test.arff", "pos3-test.arff", "pos4-test.arff", "pos5-test.arff"};
			int m=0;
			
			
		
			//Use 4 folds as training data - unigrams done, bigrams done, trigrams done
			
		//while(m < 5){
			/*RandomAccessFile f1 = new RandomAccessFile("E:\\nlp-assn-data\\hotel-reviews\\"+folds[m%5],"r");
			RandomAccessFile f2 = new RandomAccessFile("E:\\nlp-assn-data\\hotel-reviews\\"+folds[(m+1)%5],"r");
			RandomAccessFile f3 = new RandomAccessFile("E:\\nlp-assn-data\\hotel-reviews\\"+folds[(m+2)%5],"r");*/
			RandomAccessFile f4 = new RandomAccessFile("C:\\Users\\kamal\\NLP_Project\\train\\train-fold-1.txt","r");
			RandomAccessFile f5 = new RandomAccessFile("C:\\Users\\kamal\\NLP_Project\\test\\test-fold-1.txt","r");
			
			StringBuilder sb1  = new StringBuilder();
			StringBuilder sb2  = new StringBuilder();
			String st1,st2,st3,st4,st5;
			while((st4 = f4.readLine()) != null ){//&& ((st2 = f2.readLine()) != null) && ((st3 = f3.readLine()) != null) && ((st4 = f4.readLine()) != null) ) {
				//sb1.append(st1.toLowerCase() + "\n");
				//sb1.append(st2.toLowerCase() + "\n");
				sb1.append(st4.toLowerCase() + "\n");
				//sb1.append(st4.toLowerCase() + "\n");
			}
		
			while((st5 = f5.readLine()) != null){
				sb2.append(st5.toLowerCase() + "\n");
			}
		
			
			// f1.close();
			 //f2.close();
			 //f3.close();
			 f4.close();
			 f5.close();
		    
			 //bigram
			 
			 String reviews[] = sb1.toString().split("### end ###");
			 String reviewstest[] = sb2.toString().split("### end ###");
			 List<HashMap<String, Integer>> bigramtfMap = new ArrayList<HashMap<String, Integer>>();
			 Map<String,Integer>  bi_fre=new HashMap<String,Integer>();
			 HashMap<String, Integer> dfMap = new HashMap<String, Integer>();
			 Set<Entry<String, Integer>> e = bi_fre.entrySet();
			 for (String rev: reviews) {
				 
				 HashMap<String, Integer> revMap = new HashMap<String, Integer>();
				 
				 String sentences1[] = rev.split("\\.");
				 for (String sen: sentences1){
						
						String sen1=sen.replaceAll("[^A-Za-z0-9']", " ").replaceAll("\\s+", " "),k1="",k2="";
					 
						int i=0;
						StringTokenizer token = new StringTokenizer(sen1, " ");
						int n=token.countTokens();
						if(token.hasMoreTokens())
							k1=token.nextToken();
						else
							continue;
						if(token.hasMoreTokens())
							k2=token.nextToken();
						else
							continue;
								do
									{
									String key= k1+ " " +k2;
						
									if (key.matches("\\s+")==false){
										bi_fre.put(key,0);}
									if(revMap.containsKey(key)) {
										 int count = revMap.get(key);
										 
										 count++;
										 revMap.put(key, count);
									 }
									else {revMap.put(key, 1);}
								k1=k2;
								i++;
								if(token.hasMoreTokens())
									k2 = token.nextToken();
								else 
									break;
							}while(i < n);
								
						}
				 		bigramtfMap.add(revMap);
	
			 }
			 
			 List<HashMap<String, Integer>> bigramtftestMap = new ArrayList<HashMap<String, Integer>>();
			 Map<String,Integer>  bi_fretest=new HashMap<String,Integer>();
			 for (String rev: reviewstest) {
				 
				 HashMap<String, Integer> revMap = new HashMap<String, Integer>();
				 
				 String sentences1[] = rev.split("\\.");
				 for (String sen: sentences1){
						
						String sen1=sen.replaceAll("[^A-Za-z0-9']", " ").replaceAll("\\s+", " "),k1="",k2="";
					 
						int i=0;
						StringTokenizer token = new StringTokenizer(sen1, " ");
						int n=token.countTokens();
						if(token.hasMoreTokens())
							k1=token.nextToken();
						else
							continue;
						if(token.hasMoreTokens())
							k2=token.nextToken();
						else
							continue;
								do
									{
									String key= k1+ " " +k2;
							
									if (key.matches("\\s+")==false){
										bi_fretest.put(key,0);}
									if(revMap.containsKey(key)) {
										 int count = revMap.get(key);
										 
										 count++;
										 revMap.put(key, count);
									 }
									else {revMap.put(key, 1);}
								k1=k2;
								i++;
								if(token.hasMoreTokens())
									k2 = token.nextToken();
								else 
									break;
							}while(i < n);
								
						}
				 		bigramtftestMap.add(revMap);
			 }

		 		for (Entry<String,Integer> f: e)
		 		{
		 			for(HashMap<String, Integer> mapReview: bigramtfMap)
		 			{
		 			if(mapReview.get(f.getKey()) != null){
		 				Integer df = dfMap.get(f.getKey());
		 				if(df!=null)
		 					dfMap.put(f.getKey(), df+1);
		 				else 
		 					dfMap.put(f.getKey(), 1);	
		 			}
		 			}
		 		}
		 
		 

			 PrintWriter bi1 = new PrintWriter(new File("C:\\Users\\kamal\\NLP_Project\\output\\bi1.txt"));
				FileWriter fstream11 = new FileWriter("C:\\Users\\kamal\\NLP_Project\\output\\biarff1.arff");
				BufferedWriter bi1_arff = new BufferedWriter(fstream11);
				FileWriter fstream11test = new FileWriter("C:\\Users\\kamal\\NLP_Project\\output\\bitestarff1.arff");
				BufferedWriter bi1test_arff = new BufferedWriter(fstream11test);
				bi1_arff.write("@RELATION assignment1-bi1" + "\n");
				bi1test_arff.write("@RELATION assignment1-test-bi1" + "\n");
				  for (Entry<String, Integer> entry : e) {
					  if(entry.getKey().matches("\\s+") == false && entry.getKey()!=null){
						  bi1.println(entry.getKey());
						  bi1_arff.write("@ATTRIBUTE" + " " + "\"" +entry.getKey() + "\"" + " " + "NUMERIC\n");
						  bi1test_arff.write("@ATTRIBUTE" + " " + "\"" +entry.getKey() + "\"" + " " + "NUMERIC\n");
					  }
					  
			        }
				  bi1_arff.write("@ATTRIBUTE" + " " + "class" + " " + "{bottom, top}\n");
				  bi1test_arff.write("@ATTRIBUTE" + " " + "class" + " " + "{bottom, top}\n");
			        bi1_arff.write("@DATA\n");
			        bi1test_arff.write("@DATA\n");
			        
			        int h =0 ;
			        for (HashMap<String, Integer> revMap : bigramtfMap){
			        	h++;
			        	for (Entry<String,Integer> f: e){
			        		
			        		if (revMap.containsKey(f.getKey()) && f.getKey()!=null){
			        			double val = ( revMap.get(f.getKey()) * Math.log(640/dfMap.get(f.getKey()))); 
								bi1_arff.write(val+ ",");
								}
			        		else
			        			
								 bi1_arff.write( "0"+ ",");
			        		}
			        	if((h%2)!=0)
			        		bi1_arff.write("bottom\n");
			        	else
			        		bi1_arff.write("top\n");
			        			
			        	}
			        
			        int h1 =0 ;
			        for (HashMap<String, Integer> revMap : bigramtftestMap){
			        	h1++;
			        	for (Entry<String,Integer> f: e){
			        		
			        		if (revMap.containsKey(f.getKey())){
			        			double val = ( revMap.get(f.getKey()) * Math.log(640/dfMap.get(f.getKey()))); 
								bi1test_arff.write(val+ ",");
								}
			        		else
			        			
								 bi1test_arff.write( "0"+ ",");
			        		}
			        	if((h1%2)!=0)
			        		bi1test_arff.write("bottom\n");
			        	else
			        		bi1test_arff.write("top\n");
			        			
			        	}
			        
			        
			        
		/*	        
			        //trigram
			        
			        List<HashMap<String, Integer>> trigramtfMap = new ArrayList<HashMap<String, Integer>>();
					 Map<String,Integer>  tri_fre=new HashMap<String,Integer>();
					 HashMap<String, Integer> dfMap1 = new HashMap<String, Integer>();
					 Set<Entry<String, Integer>> e1 = tri_fre.entrySet();
			        
					 for (String rev: reviews) {
						 
						 HashMap<String, Integer> revMap = new HashMap<String, Integer>();
						 
						 String sentences1[] = rev.split("\\.");
						 for (String sen: sentences1){
							 String sen1=sen.replaceAll("[^A-Za-z0-9']", " ").replaceAll("\\s+", " "),k1="",k2="",k3="";
								int i=0;
								StringTokenizer token = new StringTokenizer(sen1, " ");
								int n= token.countTokens();
								if(token.hasMoreTokens())
									k1=token.nextToken();
									else
									continue;
								if(token.hasMoreTokens())
									k2=token.nextToken();
									else continue;
								if(token.hasMoreTokens())
									k3=token.nextToken();
									else continue;
										do
											{
											String key= k1+ " " +k2+ " "+k3;
											
											if(key.matches("\\s+")==false)
												tri_fre.put(key,0);
											if(revMap.containsKey(key)) {
											int count = revMap.get(key);
											 
											 count++;
											 revMap.put(key, count);
											
											 }
											else {revMap.put(key, 1);}
										k1=k2;
										k2=k3;
										i++;
										if(token.hasMoreTokens())
											k3 = token.nextToken();
										else 
											break;
									}while(i < n);	
							}	
						 trigramtfMap.add(revMap);
					 }
						 
					 List<HashMap<String, Integer>> trigramtftestMap = new ArrayList<HashMap<String, Integer>>();
					 Map<String,Integer>  tri_fretest=new HashMap<String,Integer>();
			        
					 for (String rev: reviewstest) {
						 
						 HashMap<String, Integer> revMap = new HashMap<String, Integer>();
						 
						 String sentences1[] = rev.split("\\.");
						 for (String sen: sentences1){
							 String sen1=sen.replaceAll("[^A-Za-z0-9']", " ").replaceAll("\\s+", " "),k1="",k2="",k3="";
								int i=0;
								StringTokenizer token = new StringTokenizer(sen1, " ");
								int n= token.countTokens();
								if(token.hasMoreTokens())
									k1=token.nextToken();
									else
									continue;
								if(token.hasMoreTokens())
									k2=token.nextToken();
									else continue;
								if(token.hasMoreTokens())
									k3=token.nextToken();
									else continue;
										do
											{
											String key= k1+ " " +k2+ " "+k3;
											
											if(key.matches("\\s+")==false)
												tri_fretest.put(key,0);
											if(revMap.containsKey(key)) {
											int count = revMap.get(key);
											 
											 count++;
											 revMap.put(key, count);
											
											 }
											else {revMap.put(key, 1);}
										k1=k2;
										k2=k3;
										i++;
										if(token.hasMoreTokens())
											k3 = token.nextToken();
										else 
											break;
									}while(i < n);	
							}	
						 trigramtftestMap.add(revMap);
					 }
						 
						 for (Entry<String,Integer> f: e1)
					 		{
					 			for(HashMap<String, Integer> mapReview: trigramtfMap)
					 			{
					 			if(mapReview.get(f.getKey()) != null){
					 				Integer df = dfMap1.get(f.getKey());
					 				if(df!=null)
					 					dfMap1.put(f.getKey(), df+1);
					 				else 
					 					dfMap1.put(f.getKey(), 1);	
					 			}
					 			}
					 		}
						 
						 
						PrintWriter tri1 = new PrintWriter(new File("E:\\nlp-assn-data\\hotel-reviews\\"+tri[m%5]));
						FileWriter fstream = new FileWriter("E:\\nlp-assn-data\\hotel-reviews\\"+triarff[m%5]);
						BufferedWriter tri1_arff = new BufferedWriter(fstream);
						tri1_arff.write("@RELATION assignment1-"+tri[m%5]+"\n");
						FileWriter fstreamtest = new FileWriter("E:\\nlp-assn-data\\hotel-reviews\\"+tritestarff[m%5]);
						BufferedWriter tri1test_arff = new BufferedWriter(fstreamtest);
						tri1test_arff.write("@RELATION assignment1-test"+tri[m%5]+"\n");
						  for (Entry<String, Integer> entry : e1) {
							  if(entry.getKey().matches("\\s+") == false){
							    	tri1.println(entry.getKey());
							    	tri1_arff.write("@ATTRIBUTE" + " " + "\"" +entry.getKey() + "\"" + " " + "NUMERIC\n");
							    	tri1test_arff.write("@ATTRIBUTE" + " " + "\"" +entry.getKey() + "\"" + " " + "NUMERIC\n");
					       }
						  }
						  tri1_arff.write("@ATTRIBUTE" + " " + "class" + " " + "{true, deceptive}\n");
					      tri1_arff.write("@DATA\n");
					      tri1test_arff.write("@ATTRIBUTE" + " " + "class" + " " + "{true, deceptive}\n");
					      tri1test_arff.write("@DATA\n");
			        
					      int l =0 ;
					        for (HashMap<String, Integer> revMap : trigramtfMap){
					        	l++;
					        	for (Entry<String,Integer> f: e1){
					        		
					        		if (revMap.containsKey(f.getKey())){
					        			double val = ( revMap.get(f.getKey()) * Math.log(640/dfMap1.get(f.getKey()))); 
										tri1_arff.write(val+ ",");
										}
					        		else
					        			
										 tri1_arff.write( "0"+ ",");
					        		}
					        	if(l<=320)
					        		tri1_arff.write("true\n");
					        	else
					        		tri1_arff.write("deceptive\n");
					        			
					        	}
					        
					        int l1 =0 ;
					        for (HashMap<String, Integer> revMap : trigramtftestMap){
					        	l1++;
					        	for (Entry<String,Integer> f: e1){
					        		
					        		if (revMap.containsKey(f.getKey())){
					        			double val = ( revMap.get(f.getKey()) * Math.log(640/dfMap1.get(f.getKey()))); 
										tri1test_arff.write(val+ ",");
										}
					        		else
					        			
										 tri1test_arff.write( "0"+ ",");
					        		}
					        	if(l1<=80)
					        		tri1test_arff.write("true\n");
					        	else
					        		tri1test_arff.write("deceptive\n");
					        			
					        	}
			        
					        //unigram
					        InputStream file = new FileInputStream("en-sent.bin");
					        SentenceModel fileModel = new SentenceModel(file);
					        SentenceDetectorME filesd = new SentenceDetectorME(fileModel);
					        TokenizerModel tokenmodel = new TokenizerModel(new FileInputStream("en-token.bin"));
					        TokenizerME tokenizer = new TokenizerME(tokenmodel);
					        List<HashMap<String, Integer>> unigramtfMap = new ArrayList<HashMap<String, Integer>>();
							 Map<String,Integer>  uni_fre=new HashMap<String,Integer>();
							 Set<Entry<String, Integer>> e2 = uni_fre.entrySet();
							 for (String rev: reviews) {
								 
								 HashMap<String, Integer> revMap = new HashMap<String, Integer>();
								 
								 String sentence=rev.replaceAll("\\.", ". ").replaceAll("[^A-Za-z0-9\\.\\s]", " ");//.replaceAll("\\s+", " ");
								
								 String[] sens = filesd.sentDetect(sentence);
								 
								for( String sen: sens){
									String[] words = tokenizer.tokenize(sen.replaceAll("\\."," "));
									
									for (String w: words){
										
									
											uni_fre.put(w,1);
									 if(revMap.containsKey(w)) {
										 int count = revMap.get(w);
										 
										 count++;
										 revMap.put(w, count);
									 }
										else {revMap.put(w, 1);}
									 }

								}
								 unigramtfMap.add(revMap);
							 }
							 
							 InputStream filet = new FileInputStream("en-sent.bin");
						        SentenceModel fileModelt = new SentenceModel(filet);
						        SentenceDetectorME filesdt = new SentenceDetectorME(fileModelt);
							 List<HashMap<String, Integer>> unigramtfMaptest = new ArrayList<HashMap<String, Integer>>();
							 Map<String,Integer>  uni_fretest=new HashMap<String,Integer>();
							 for (String rev: reviewstest) {
								 
								 HashMap<String, Integer> revMap = new HashMap<String, Integer>();
								 
								 String sentencet=rev.replaceAll("\\.", ". ").replaceAll("[^A-Za-z0-9'\\.\\s]", " ");//.replaceAll("\\s+", " ");
								 
								 String[] sens = filesdt.sentDetect(sentencet);
								 for( String sen: sens){
									 String[] words = tokenizer.tokenize(sen.replaceAll("\\."," "));
										
									 for (String w: words){
										 if(uni_fretest.get(w) == null)
											 uni_fretest.put(w,1);
									 if(revMap.containsKey(w)) {
										 int count = revMap.get(w);
										 
										 count++;
										 revMap.put(w, count);
									 }
										else {
											revMap.put(w, 1);
											}
									 }
								 }
								 //}
								 unigramtfMaptest.add(revMap);
							 }
							 
							 
							 e2 = uni_fre.entrySet();
							 for (Entry<String,Integer> f: e2)
						 		{
						 			for(HashMap<String, Integer> mapReview: unigramtfMap)
						 			{
						 			if(mapReview.get(f.getKey()) != null) {
						 				Integer df = uni_fre.get(f.getKey());
						 					uni_fre.put(f.getKey(), df+1);
						 				
						 			}
						 			}
						 		}
							 
							 PrintWriter uni1 = new PrintWriter(new File("E:\\nlp-assn-data\\hotel-reviews\\"+uni[m%5]));
								FileWriter fstream111 = new FileWriter("E:\\nlp-assn-data\\hotel-reviews\\"+uniarff[m%5]);
								FileWriter fstreamtest111 = new FileWriter("E:\\nlp-assn-data\\hotel-reviews\\"+unitestarff[m%5]);
								BufferedWriter uni1_arff = new BufferedWriter(fstream111);
								uni1_arff.write("@RELATION assignment1-"+uni[m%5]+"\n");
								BufferedWriter uni1test_arff = new BufferedWriter(fstreamtest111);
								uni1test_arff.write("@RELATION assignment1-test"+uni[m%5]+"\n");
								e2 = uni_fre.entrySet();
								  for (Entry<String, Integer> entry : e2) {
							            uni1.println(entry.getKey() +"=>"+ entry.getValue());
							            uni1_arff.write("@ATTRIBUTE" + " "  +entry.getKey()  + " " + "NUMERIC\n");
							            uni1test_arff.write("@ATTRIBUTE" + " "  +entry.getKey() +" " + "NUMERIC\n");
							        }
								  uni1_arff.write("@ATTRIBUTE" + " " + "CLASS" + " " + "{true, deceptive}\n");
							      uni1_arff.write("@DATA\n");
							      uni1test_arff.write("@ATTRIBUTE" + " " + "CLASS" + " " + "{true, deceptive}\n");
							      uni1test_arff.write("@DATA\n");
							 
							      int g =0 ;
							        for (HashMap<String, Integer> revMap : unigramtfMap){
							        	g++;
							        	for (Entry<String,Integer> f: e2){
							        		
							        		if (revMap.containsKey(f.getKey())) { 
							        			double val = ( (double) revMap.get(f.getKey()) *  Math.log((double)640/f.getValue()));
												uni1_arff.write(val+ ",");
												}
							        		else
							        			
												 uni1_arff.write( "0" + ",");
							        		}
							        	
							        	if(g<=320)
							        		uni1_arff.write("true\n");
							        	else
							        		uni1_arff.write("deceptive\n");
							        			
							        	}
							        
							        int g1 =0 ;
							        for (HashMap<String, Integer> revMap : unigramtfMaptest){
							        	g1++;
							        	for (Entry<String,Integer> f: e2){
							        		
							        		if (revMap.containsKey(f.getKey())){
							        			
							        			double val = ( (double) revMap.get(f.getKey()) * Math.log((double)640/f.getValue()));
												uni1test_arff.write(val+ ",");
												}
							        		else
							        			
												 uni1test_arff.write( "0"+ ",");
							        		}
							        	if(g1<=80)
							        		uni1test_arff.write("true\n");
							        	else
							        		uni1test_arff.write("deceptive\n");
							        			
							        	}
							 
							        //POS
							        InputStream modelIn = null;
							        modelIn = new FileInputStream("en-pos-maxent.bin");
							        POSModel posModel = new POSModel(modelIn);
							        modelIn.close();
							        POSTaggerME _posTagger = new POSTaggerME(posModel);
							        List<HashMap<String, Integer>> postfMap = new ArrayList<HashMap<String, Integer>>();
									 Map<String,Integer>  pos_fre=new HashMap<String,Integer>();
									 for (String rev: reviews) {
										 
										 HashMap<String, Integer> revMap = new HashMap<String, Integer>();
										 
										 String sentence=rev.replaceAll("\\.", ". ").replaceAll("[^A-Za-z0-9'\\.\\s]", " ");//.replaceAll("\\s+", " ");
										 
										 String[] sens = filesdt.sentDetect(sentence);
										 for( String sen: sens){
											 String[] words = tokenizer.tokenize(sen.replaceAll("\\."," "));
											
											 for (String w: words){
												 String dt=_posTagger.tag(w);
												String[] dt1 = dt.split("/");												
												String word = dt1[1];
												 if(pos_fre.get(word) == null)
													 pos_fre.put(word,1);
											 if(revMap.containsKey(word)) {
												 int count = revMap.get(word);
												 
												 count++;
												 revMap.put(word, count);
											 }
												else {
													revMap.put(word, 1);
													}
											 }
										 }
										 postfMap.add(revMap);
									 }
									 
									 Set<Entry<String, Integer>> pos = pos_fre.entrySet();
									 for (Entry<String,Integer> f: pos)
								 		{
								 			for(HashMap<String, Integer> mapReview: postfMap)
								 			{
								 			if(mapReview.get(f.getKey()) != null) {
								 				Integer df = pos_fre.get(f.getKey());
								 					pos_fre.put(f.getKey(), df+1);
								 			}
								 			}
								 		}
									 
										FileWriter posStream = new FileWriter("E:\\nlp-assn-data\\hotel-reviews\\"+posarff[m%5]);
										BufferedWriter pos_arff = new BufferedWriter(posStream);
										pos_arff.write("@RELATION assignment1-"+posarff[m%5]+"\n");
										FileWriter posStreamt = new FileWriter("E:\\nlp-assn-data\\hotel-reviews\\"+posarfft[m%5]);
										BufferedWriter pos_arfft = new BufferedWriter(posStreamt);
										pos_arfft.write("@RELATION assignment1-test"+posarfft[m%5]+"\n");
										pos = pos_fre.entrySet();
										  for (Entry<String, Integer> entry : pos) {
									            pos_arff.write("@ATTRIBUTE" + " "  +entry.getKey()  + " " + "NUMERIC\n");
									            pos_arfft.write("@ATTRIBUTE" + " "  +entry.getKey()  + " " + "NUMERIC\n");

									        }
										  pos_arff.write("@ATTRIBUTE" + " " + "CLASS" + " " + "{true, deceptive}\n");
									      pos_arff.write("@DATA\n");
									      pos_arfft.write("@ATTRIBUTE" + " " + "CLASS" + " " + "{true, deceptive}\n");
									      pos_arfft.write("@DATA\n");
									 
									      int p =0 ;
									        for (HashMap<String, Integer> revMap : postfMap){
									        	p++;
									        	for (Entry<String,Integer> f: pos){
									        		
									        		if (revMap.containsKey(f.getKey())) { 
									        			double val = ( (double) revMap.get(f.getKey()) *  Math.log((double)640/f.getValue()));
														pos_arff.write(val+ ",");
														}
									        		else
									        			
														 pos_arff.write( "0" + ",");
									        		}
									        	if(p<=320)
									        		pos_arff.write("true\n");
									        	else
									        		pos_arff.write("deceptive\n");
									        			
									        	}
									 
									        InputStream modelInt = null;
									        modelInt = new FileInputStream("en-pos-maxent.bin");
									        POSModel posModelt = new POSModel(modelInt);
									        modelInt.close();
									        POSTaggerME _posTaggert = new POSTaggerME(posModelt);
									        List<HashMap<String, Integer>> postfMapt = new ArrayList<HashMap<String, Integer>>();
											 Map<String,Integer>  pos_fret=new HashMap<String,Integer>();
											 for (String rev: reviewstest) {
												 
												 HashMap<String, Integer> revMap = new HashMap<String, Integer>();
												 
												 String sentence=rev.replaceAll("\\.", ". ").replaceAll("[^A-Za-z0-9'\\.\\s]", " ");//.replaceAll("\\s+", " ");
												 
												 String[] sens = filesdt.sentDetect(sentence);
												 for( String sen: sens){
													 String[] words = tokenizer.tokenize(sen.replaceAll("\\."," "));
													
													 for (String w: words){
														 String dt=_posTaggert.tag(w);
														String[] dt1 = dt.split("/");												
														String word = dt1[1];
														 if(pos_fret.get(word) == null)
															 pos_fret.put(word,1);
													 if(revMap.containsKey(word)) {
														 int count = revMap.get(word);
														 
														 count++;
														 revMap.put(word, count);
													 }
														else {
															revMap.put(word, 1);
															}
													 }
												 }
												 postfMapt.add(revMap);
											 }
											  
											      int p1 =0 ;
											        for (HashMap<String, Integer> revMap : postfMapt){
											        	p1++;
											        	for (Entry<String,Integer> f: pos){
											        		
											        		if (revMap.containsKey(f.getKey())) { 
											        			double val = ( (double) revMap.get(f.getKey()) *  Math.log((double)640/f.getValue()));
																pos_arfft.write(val+ ",");
																}
											        		else
											        			
																 pos_arfft.write( "0" + ",");
											        		}
											        	if(p1<=80)
											        		pos_arfft.write("true\n");
											        	else
											        		pos_arfft.write("deceptive\n");
											        			
											        	}
									 
			   
					tri1_arff.close();
					tri1.close();
					tri1test_arff.close();*/
					bi1_arff.close();
					bi1.close();
					bi1test_arff.close();
				/*	uni1_arff.close();
					uni1.close();
					pos_arff.close();
					pos_arfft.close();
					uni1test_arff.close();
					System.out.println("Done tri,bi and uni for "+folds[m%5]);*/
					System.out.println("Done");
			//		m++;
	//	}
	}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
