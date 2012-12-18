package com.nlp.project;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class FoldClass
{

    /**
     * @param args
     */
    
    public static void generateFolds() {
        
    try {
        
        for(int j = 1; j <= 1; j++)
        {
            FileWriter fop = new FileWriter("iphone-500/new-top.txt");
            PrintWriter out = new PrintWriter(fop);
                        
            
            for(int i = 1; i <= 215; i++)
            {
                FileInputStream fstream = new FileInputStream("iphone-500/new-top/a1 ("+ i + ").txt");
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                                            
                String line;

                while((line = br.readLine()) != null)
                {                       
                            out.write(line);                            
                }                                           
                out.write("\n");
                out.write("### END ###");
                out.write("\n");
                br.close();
                
            }            
            out.close();
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args)
    {
       generateFolds();
    }

}