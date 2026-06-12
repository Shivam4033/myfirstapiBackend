package com.example.myfirstapi;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
//import java.util.ArrayList;
@Component
public class JwtFillter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        final String authorizationHeader = request.getHeader("Authorization");
        System.out.println("-----------------FILTER Check: Authorization Header recived: [" + authorizationHeader +"] -----");
        String username = null;
        String jwtToken = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
        {
            jwtToken = authorizationHeader.substring(7).trim();
            try{
                username = jwtUtil.extractUsername(jwtToken);
                System.out.println("------------FILTER Check: Username extracted successfully " + username + " -------------");
            }catch (Exception e)
            {
                System.out.println("Error IN FILTER TOKEN PARSING : " +e.getMessage());
            }
        }//else {
            //System.out.println("WARNING: Header is either NULL or  does not start with 'Bearer` !");
        //}
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            String role = jwtUtil.extractRole(jwtToken);
            //System.out.println("-----------FILTER: Role extracted from token: " +role + " ---------");
            if(role == null)
            {
                role = "ADMIN";
            }
            if(!role.startsWith("ROLE_"))
            {
                role = "ROLE_" + role;
            }
            //System.out.println("-----------FILTER: final Role after prefix check: " + role + " -----------");
            java.util.List<org.springframework.security.core.GrantedAuthority> authorities =
                    java.util.Collections.singletonList(new org.springframework.security.core.authority.SimpleGrantedAuthority(role));
            System.out.println("------FILTER RE CHECK: Setting auth Contex for User: " + username + " with in  Role: " + role );
            //if(jwtUtil.validateToken(jwtToken, username)){
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            authenticationToken.setDetails(new  org.springframework.security.web.authentication.WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //}
        }
        filterChain.doFilter(request, response);

    }
}
