package com.example.belajar_spring.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String uri = request.getRequestURI();

        // Izinkan akses ke halaman login, register, dan resource statis (CSS, JS, dll)
        if (uri.startsWith("/auth") ||
                uri.startsWith("/static") ||
                uri.matches(".*\\.(css|js|png|jpg|jpeg|gif|woff|woff2|ttf|svg)$")) {
            return true; // lanjutkan
        }

        // Cek apakah user sudah login
        Object userSession = request.getSession().getAttribute("loggedInUser");
        if (userSession == null) {
            response.sendRedirect("/auth/login");
            return false;
        }

        return true; // user sudah login → izinkan akses
    }
}
