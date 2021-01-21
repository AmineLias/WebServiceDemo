import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Main {
    public static List<String> breedsList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        int i;
        do {
            String response = new Service().makeRequest("https://api.thecatapi.com/v1/breeds");
            System.out.println(ParseData1(response));
            Scanner scanner = new Scanner(System.in);
            i = scanner.nextInt();
            response = new Service().makeRequest("https://api.thecatapi.com/v1/images/search?breed_id=" + breedsList.get(i));
            System.out.println(ParseData2(response));
            System.out.println("\nenter 100 to continue, 99 to quit");
            i = scanner.nextInt();
        }while(i!=99);



    }

    public static String ParseData1(String Data){
        StringBuilder sBuilder = new StringBuilder();
        JSONArray globalArray = new JSONArray(Data);
        for (int i=0; i<globalArray.length(); i++){
            JSONObject o = globalArray.getJSONObject(i);
            breedsList.add(o.getString("id"));
            sBuilder.append(i + ".").append(" " + o.getString("name")).append("\n");
        }
        return sBuilder.toString();
    }

    public static String ParseData2(String Data){
        StringBuilder sBuilder = new StringBuilder();
        JSONArray globalArray = new JSONArray(Data);
        JSONObject o = globalArray.getJSONObject(0);
        JSONArray breedArray = o.getJSONArray("breeds");
        JSONObject o1 = breedArray.getJSONObject(0);

        sBuilder.append("Name: ").append(o1.getString("name")).append("\n").
                append("Origin: ").append(o1.getString("origin")).append("\n").
                    append("Lifespan: ").append(o1.getString("life_span")).append(" years").append(("\n")).
                        append("Temper: ").append(o1.getString("temperament")).append("\n");
        return sBuilder.toString();
    }
}