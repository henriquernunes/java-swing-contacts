

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AccessContacts {
    private String path = "list.txt";

    public List<Contact> list() {
        List<Contact> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(";");
                list.add(new Contact(fields[0], fields[1], fields[2]));

                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (Contact c : list) {
            System.out.println(c);
        }

        return list;
    }
}