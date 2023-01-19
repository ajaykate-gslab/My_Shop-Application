package com.shop.myshop.controller;

import com.shop.myshop.helper.JwtUtil;
import com.shop.myshop.model.JwtRequest;
import com.shop.myshop.model.JwtResponse;
import com.shop.myshop.service.ProductUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProductUserDetailsService productUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        System.out.println(jwtRequest);
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));


        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("User Not Found ");
        }
        catch (BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

        UserDetails userDetails = this.productUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token= this.jwtUtil.generateToken(userDetails);
        System.out.println("JWT :"+token);

        //{"token : value"}

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
