import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class App {
    public static void main(String[] args) throws Exception {

        String file = "input.txt";
        Phonebook phonebook = new Phonebook();
        try {
            String text = phonebook.readFile(file);

            System.out.println();
            System.out.println("Data");
            HashMap<String, ArrayList<String>> data = phonebook.getAllContactList(text);
            data.entrySet().forEach(System.out::println);

            System.out.println();
            HashMap<String, HashSet<String>> map = phonebook.getContactMap(text);
            System.out.println("Unsorted list");
            map.entrySet().forEach(System.out::println);

            System.out.println();
            LinkedHashMap<String, HashSet<String>> sortedMap = phonebook.getSortedMap(map);
            System.out.println("Sorted list");
            sortedMap.entrySet().forEach(System.out::println);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
         
    }

}

class Phonebook {


        LinkedHashMap<String, HashSet<String>> getSortedMap(HashMap<String, HashSet<String>> map) {

            LinkedHashMap<String, HashSet<String>> linkedMap = new LinkedHashMap<String, HashSet<String>>();

            HashMap<String, Integer> temp = new HashMap<>();
            map.entrySet().forEach(n -> temp.put(n.getKey(), n.getValue().size()));
;
            temp.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .forEach(n -> linkedMap.put(n.getKey(), map.get(n.getKey())));

            return linkedMap;
        }

        HashMap<String, HashSet<String>> getContactMap(String text) {

            HashMap<String, HashSet<String>> phoneNumber = new HashMap<String, HashSet<String>>();

            String[] contacts = text.split("\n");
            for (String contact : contacts) {
                String[] notes = contact.strip().split("; ");
                String key = notes[0];
                String value = notes[1];
                if (phoneNumber.containsKey(key)) {
                    phoneNumber.get(key).add(value);
                } else {
                    HashSet<String> set = new HashSet<String>(1);
                    set.add(value);
                    phoneNumber.put(key, set);
                }
            }
           
            
            return phoneNumber;
        }

        HashMap<String, ArrayList<String>> getAllContactList(String text) {

            HashMap<String, ArrayList<String>> phoneNumber = new HashMap<String, ArrayList<String>>();

            String[] contacts = text.split("\n");
            for (String contact : contacts) {
                String[] notes = contact.strip().split("; ");
                String key = notes[0];
                String value = notes[1];
                if (phoneNumber.containsKey(key)) {
                    phoneNumber.get(key).add(value);
                } else {
                    ArrayList<String> set = new ArrayList<String>(1);
                    set.add(value);
                    phoneNumber.put(key, set);
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

