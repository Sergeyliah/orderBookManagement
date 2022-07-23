import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/output.txt", false);
        String str;
        Map<Integer, Integer> bidMap = new HashMap<>();
        Map<Integer, Integer> askMap = new HashMap<>();
        while ((str = reader.readLine()) != null) {
            String[] arr = str.toLowerCase().trim().replace(',', ' ').split(" ");
            if (arr[0].equals("u") && arr[arr.length - 1].equals("bid")) {
                bidMap.put(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
            } else if (arr[0].equals("u") && arr[arr.length - 1].equals("ask")) {
                askMap.put(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
            } else if (arr[0].equals("o") && arr[1].equals("sell")) {
                bidMap.put(Collections.max(bidMap.keySet()), bidMap.get(Collections.max(bidMap.keySet())) - Integer.parseInt(arr[2]));
            } else if (arr[0].equals("q") && arr[arr.length - 1].equals("best_bid")) {
                fileOutputStream.write((Collections.max(bidMap.keySet())+","+bidMap.get(Collections.max(bidMap.keySet()))+"\n").getBytes());
            } else if (arr[0].equals("q") && arr[arr.length - 1].equals("best_ask")) {
                fileOutputStream.write((Collections.max(askMap.keySet())+","+askMap.get(Collections.max(askMap.keySet()))+"\n").getBytes());
            } else if (arr[0].equals("q") && arr[1].equals("size")) {
                if (bidMap.keySet().contains(Integer.parseInt(arr[2]))){
                    fileOutputStream.write((bidMap.get(Collections.max(bidMap.keySet()))+"\n").getBytes());
                } else if (askMap.keySet().contains(Integer.parseInt(arr[2]))){
                    fileOutputStream.write((askMap.get(Collections.max(askMap.keySet()))+"\n").getBytes());
                }
            }
        }
        reader.close();
        fileOutputStream.close();
    }
}
