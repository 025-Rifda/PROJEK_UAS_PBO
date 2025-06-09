package com.example.belajar_spring.controller;

import com.example.belajar_spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request,
            Model model) {
        if (userService.login(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", username);
            return "redirect:/dashboard"; // Redirect ke halaman utama (kontak)
        }
        model.addAttribute("error", "Username atau password salah!");
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate(); // Menghapus semua data sesi
        return "redirect:/auth/login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
            @RequestParam String password,
            Model model) {
        if (userService.register(username, password)) {
            return "redirect:/auth/login";
        }
        model.addAttribute("error", "Username sudah ada!");
        return "auth/register";
    }

    @Controller
    public class DashboardController {

        @GetMapping("/dashboard")
        public String dashboard(Model model) {
            model.addAttribute("pageTitle", "Dashboard");
            model.addAttribute("activePage", "dashboard");
            return "dashboard"; // mengarah ke src/main/resources/templates/dashboard.html
        }
    }

}
