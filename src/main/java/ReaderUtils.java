import com.google.gson.Gson;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class ReaderUtils {
    private ReaderUtils(){

    }

    public static String readTextFromFile(String path){
        String text = "";
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            text = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static void writeTextToFile(String path, String text){
        File file = new File(path);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))){
            bw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomPerson() {
        String text = "";
        try {
            URL url = new URL("https://randus.org/api.php");
            URLConnection urlConnection = url.openConnection();
            long length = urlConnection.getContentLengthLong();
            if(length != 0) {
                try(BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    }
                    text = sb.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(text, Person.class).getPersonName();
    }
}
