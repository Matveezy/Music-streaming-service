package com.racers.euphmusic.handler;

import com.racers.euphmusic.dto.PersonLoggedDto;
import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.repository.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final PersonRepo personRepo;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        HttpSession session = request.getSession();
        Person person;
        String username = ((User) authentication.getPrincipal()).getUsername();
        person = personRepo.findByUsername(username).get();
        PersonLoggedDto personLoggedDto = PersonLoggedDto.builder()
                .username(person.getUsername())
                .image(person.getImage())
                .build();
        session.setAttribute("loggedPerson", personLoggedDto);
        redirectStrategy.sendRedirect(request, response, "/");
    }

}
