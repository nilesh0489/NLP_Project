import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class FoldGenerator
{

    /**
     * @param args
     */
    
    public static void generateFolds() {
        
    try {
        
        for(int j = 1; j <= 5; j++)
        {
            FileWriter fop = new FileWriter("train/train-fold-"+ j +".txt");
            PrintWriter out = new PrintWriter(fop);
            FileWriter fopTest = new FileWriter("test/test-fold-"+ j +".txt");
            PrintWriter outTest = new PrintWriter(fopTest);
            
            for(int i = 1; i <= 150; i++)
            {
                FileInputStream fstream = new FileInputStream("bottom/" + i + ".txt");
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                
                FileInputStream fstreamTop = new FileInputStream("top/" + i + ".txt");
                DataInputStream inTop = new DataInputStream(fstreamTop);
                BufferedReader brTop = new BufferedReader(new InputStreamReader(inTop));
                
                String line;

                while((line = br.readLine()) != null)
                {
                        if(i > j*30 || i < (j-1)*30)
                        {
                            out.write(line);
                        }
                        else
                            outTest.write(line);
                }
              
                if(i > j*30 || i < (j-1)*30)
                {
                    out.println();
                    out.println("### END ###");
                }
                else {
                    outTest.println();
                    outTest.println("### END ###");
                }
                
                while((line = brTop.readLine()) != null)
                {
                        if(i > j*30 || i < (j-1)*30)
                        {
                            out.write(line);
                        }
                        else
                            outTest.write(line);
                }
                
                if(i > j*30 || i < (j-1)*30)
                {
                    out.println();
                    out.println("### END ###");
                }
                else {
                    outTest.println();
                    outTest.println("### END ###");
                }
                
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
