package sg.edu.nus.iss.app.day13workshop.service;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.day13workshop.User;
import sg.edu.nus.iss.app.day13workshop.repository.AppRepository;

@Service
public class AppService {

    @Autowired
    AppRepository appRepository;

    public void createUser(User user, File directory) throws IOException {

        File file = new File(directory.getAbsolutePath() + File.separator + user.getId() + ".txt");
        if (!file.exists()) {
            appRepository.createUser(user, file);
        }
        
    }

    public User getUserFromID(String id, File directory) throws IOException {
        User user = appRepository.getUserFromID(id, directory);
        return user;
    }

    public String[] getAllContactIDs(File directory) {
        String[] contactIDs = appRepository.getAllContactIDs(directory);
        return contactIDs;
    }
}
