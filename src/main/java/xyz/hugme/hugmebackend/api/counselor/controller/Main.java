package xyz.hugme.hugmebackend.api.counselor.controller;

import javax.servlet.http.Cookie;

public class Main {
    public static void main(String[] args) {
        Cookie[] cookies = new Cookie[0];
        for (Cookie cookie : cookies) {
                if (cookie.getName().equals("session_id")) {
                    System.out.println(cookie.getValue());
                }
        }
    }
}
