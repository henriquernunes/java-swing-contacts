package contacts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
    return list;
  }

  public void saveContacts(List<Contact> contacts) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
      for (Contact c : contacts) {
        bw.write(c.getFirstName() + ";" + c.getLastName() + ";" + c.getPhone());
        bw.newLine();
      }
    } catch (Exception e) {
      System.out.println("Erro ao salvar contatos: " + e.getMessage());
    }
  }

  public List<Contact> searchName(String name) {
    List<Contact> result = new ArrayList<>();
    List<Contact> contacts = list();

    for (Contact c : contacts) {
      if (c.getFirstName().toLowerCase().contains(name.toLowerCase()) ||
          c.getLastName().toLowerCase().contains(name.toLowerCase())) {
        result.add(c);
      }
    }
    return result;
  }
}
