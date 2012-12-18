import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Set;

public class POS_Unique {

	public static void main(String args[]) {
		try {
			FileInputStream fstream = new FileInputStream("POS/pos_bi_top.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			FileInputStream fstreamB = new FileInputStream("POS/pos_bi_bottom.txt");
			DataInputStream inB = new DataInputStream(fstreamB);
			BufferedReader brB = new BufferedReader(new InputStreamReader(inB));
			
			HashMap<String, Integer> uniqTag = new HashMap<String, Integer>();
			
			String line;
			
			while((line = br.readLine()) != null)
			{
				
				if(uniqTag.get(line) == null)
				{
					uniqTag.put(line, 1);
				}
			}
			
			
			while((line = brB.readLine()) != null)
			{
				
				if(uniqTag.get(line) != null)
				{
					uniqTag.remove(line);
				}
			}
			
			System.out.println(uniqTag.size());
			
			Set<String> keySet = uniqTag.keySet();
			Iterator<String> it = keySet.iterator();
			
			while(it.hasNext())
			{
				String key = it.next();
				System.out.println(key);
			}
			
			br.close();
			brB.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
