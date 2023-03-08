package sg.edu.nus.iss.app.day13workshop.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.day13workshop.User;

@Repository
public class AppRepository {

    public void createUser(User user, File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        String data = String.format("%s\n%s\n%s\n%s\n%d\n%s", user.getName() , user.getEmail() , user.getPhoneNumber(), user.getDateOfBirth().toString(), user.getAge(), user.getId());
        fw.write(data);
        fw.close();
    }

    public User getUserFromID(String id, File directory) throws IOException {
        
        for (File file: directory.listFiles()) {

            if (file.getName().contains(id)){
                InputStream is = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                String line;
                List<String> attributes = new ArrayList<>();
                while ((line = br.readLine()) != null) { // continue reading the existing file line by line till the end
                    attributes.add(line);
                }
                User user = new User(attributes.get(0), attributes.get(1), attributes.get(2), LocalDate.parse(attributes.get(3)), attributes.get(5));
                br.close();
                isr.close();
                is.close();
                return user;
            }
        }
        return null;
    }

    public String[] getAllContactIDs(File directory) {
        return directory.list();
    }
}
