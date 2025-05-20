### SearchController

```java
package com.example.belajar_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.belajar_spring.model.Kontak;
import com.example.belajar_spring.repository.KontakRepository;

@Controller
public class SearchController {

    @Autowired
    private KontakRepository kontakRepository;

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Kontak> semua = kontakRepository.findAll();
        System.out.println("=== Semua Data Kontak ===");
        for (Kontak k : semua) {
            System.out.println("Nama: " + k.getNama());
        }

        List<Kontak> hasil = kontakRepository.searchKontak(keyword);
        model.addAttribute("hasil", hasil);
        return "hasil_search";
    }
}
```

### Usermodel

```java
package com.example.belajar_spring.model;

public class User {
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
```

###UserService

```java
package com.example.belajar_spring.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, String> users = new HashMap<>();

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Username already exists
        }
        users.put(username, password);
        return true;
    }

    public boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
```
