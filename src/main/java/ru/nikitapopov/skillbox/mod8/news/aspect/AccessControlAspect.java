package ru.nikitapopov.skillbox.mod8.news.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccessControlAspect {

    @Before("execution(* ru.nikitapopov.skillbox.mod8.news.controller.UserController.deleteUser(..)) && args(userId)")
    public void checkUserAccess(String userId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserId = userDetails.getUsername(); // Получаем ID текущего пользователя

        if (!currentUserId.equals(userId) && !userDetails.getAuthorities().contains("ROLE_ADMIN")) {
            throw new SecurityException("Access Denied");
        }
    }
}