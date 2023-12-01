package com.aliments.hateosswaggerrest.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class OrkutUserDAO {
    static List<OrkutUser> users = new ArrayList<>();
    static int userCount = 0;
    static {
        users.add(new OrkutUser(++userCount,"Ali", LocalDate.now().minusYears(30)));
        users.add(new OrkutUser(++userCount,"Shaheer", LocalDate.now().minusYears(20)));
        users.add(new OrkutUser(++userCount,"Ibrahim", LocalDate.now().minusYears(10)));
    }

    public List<OrkutUser> findAll() {
        return users;
    }
    public OrkutUser findOne(int id) {
        Predicate<? super OrkutUser> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public OrkutUser save(OrkutUser orkutUser) {
        orkutUser.setId(++userCount);
        users.add(orkutUser);
        return orkutUser;
    }
    public void deleteById(int id) {
        Predicate<? super OrkutUser> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
