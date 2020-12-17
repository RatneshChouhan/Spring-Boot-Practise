package io.rc.springsecurity.JwtImpl;

import io.rc.springsecurity.JwtImpl.model.AuthenticationRequest;
import io.rc.springsecurity.JwtImpl.model.AuthenticationResponse;
import io.rc.springsecurity.JwtImpl.service.MyUserDetailsService;
import io.rc.springsecurity.JwtImpl.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeResource {

    @Autowired
    AuthenticationManager authenticationManager ;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    JwtUtil jwtTokenUtil;

    @GetMapping("/home")
    public String home() {
        return ("<h2> Welcome </h2>");
    }

    @RequestMapping(value="/authenticate", method= RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authenticationRequest.getUserename(),authenticationRequest.getPassword())
                );
        }catch(BadCredentialsException e){
            throw new Exception("Invalid Credenials ", e);
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUserename());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
