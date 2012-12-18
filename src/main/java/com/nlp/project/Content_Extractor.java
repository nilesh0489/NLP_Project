package com.nlp.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.gravity.goose.Article;
import com.gravity.goose.Configuration;
import com.gravity.goose.Goose;

public class Content_Extractor {
	
	public Content_Extractor() {
		
	}
	
	public void extract(String filename, String mode) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		int file_number = 1;
		String s;		
		while ((s = br.readLine()) != null){
			PrintWriter out = new PrintWriter(new File("tech-400/" + mode + "/" +  file_number + ".txt"));
			Goose goose = new Goose(new Configuration());
			try {				
				Article article = goose.extractContent(s);
				out.println(article.cleanedArticleText());
			} catch (Exception e) {
				//Bypass the error
			}
			out.close();
			file_number++;
		}
		br.close();
	}
	
	public static void main(String args[]) throws IOException {
		Content_Extractor ce = new Content_Extractor();
		ce.extract("tech-400/url-top-400.txt", "top");
		ce.extract("tech-400/url-bottom-400.txt", "bottom");
	}
}
