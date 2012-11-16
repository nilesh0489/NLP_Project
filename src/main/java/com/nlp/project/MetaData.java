import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;



public class Metadata {
	public static void main(String args[]) {
		try {
				BufferedReader fp = new BufferedReader(new FileReader("E:\\nlp_proj_data\\metadata-bottom-tech-reddit-week-400.txt"));
				PrintWriter out = new PrintWriter(new File("E:\\nlp_proj_data\\filtered-metadata-bottom-400.txt"));
				String s;
				int i=1;
				while ((s = fp.readLine()) != null){
					String[] posts = s.split("}},");
					for (String post : posts){
						String[] fields = post.split(", \"");
							out.println(i++ + ". ");
						for( String field : fields) {
							if (field.startsWith("score") || field.startsWith("title") || field.startsWith("created") || field.startsWith("created_utc") || field.startsWith("data\": {\"domain\":") || field.startsWith("permalink") || field.startsWith("url") )
								if (field.startsWith("data\": {\"domain\": \"youtube.com"))
									out.println("DOMAIN NOT APPLICABLE\n");
								else if (field.startsWith("created") || field.startsWith("created_utc")) {
									String[] field_parts = field.split(" ");
									String[] field_subparts = field_parts[1].split("\\.");
									long dateLong = Long.parseLong(field_subparts[0]);
									dateLong = dateLong * 1000;
									String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date(dateLong));
									out.println(field_parts[0].replaceAll("\":", ": ") + date ); 
								}
								else
									out.println(field.replaceAll("data\": \\{\"", "").replaceAll("\": ", ": "));
						}
						out.println("\n");
					}
				}
				fp.close();
				out.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
