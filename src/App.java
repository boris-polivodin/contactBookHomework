import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class App {
    public static void main(String[] args) throws Exception {

        String file = "input.txt";
        Phonebook phonebook = new App().new Phonebook();
        try {
            String text = phonebook.readFile(file);

            HashMap<String, ArrayList<String>> map = phonebook.getContactMap(text);
            System.out.println("Unsorted list");
            map.entrySet().forEach(System.out::println);
            System.out.println();
            LinkedHashMap<String, ArrayList<String>> sortedMap = phonebook.getSortedMap(map);
            System.out.println("Sorted list");
            sortedMap.entrySet().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        
        
    }

    class Phonebook {


        LinkedHashMap<String, ArrayList<String>> getSortedMap(HashMap<String, ArrayList<String>> map) {

            LinkedHashMap<String, ArrayList<String>> linkedMap = new LinkedHashMap<String, ArrayList<String>>();

            HashMap<String, Integer> temp = new HashMap<>();
            map.entrySet().forEach(n -> temp.put(n.getKey(), n.getValue().size()));
;
            temp.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .forEach(n -> linkedMap.put(n.getKey(), map.get(n.getKey())));

            return linkedMap;
        }

        HashMap<String, ArrayList<String>> getContactMap(String text) {

            HashMap<String, ArrayList<String>> phoneNumber = new HashMap<String, ArrayList<String>>();

            String[] contacts = text.split("\n");
            for (String contact : contacts) {
                String[] notes = contact.strip().split("; ");
                String key = notes[0];
                String value = notes[1];
                if (phoneNumber.containsKey(key)) {
                    phoneNumber.get(key).add(value);
                } else {
                    ArrayList<String> arrayList = new ArrayList<String>(1);
                    arrayList.add(value);
                    phoneNumber.put(key, arrayList);
                }
            }
           
            
            return phoneNumber;
        }

        String readFile(String fileName) throws IOException {

            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }        
            fr.close();

            return sb.toString();
        }

    }
}
