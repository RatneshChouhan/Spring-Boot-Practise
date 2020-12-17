package io.rc.springsecurity.JwtImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtImplApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtImplApplication.class, args);
	}

}
/*
@RestController
class HomeResource {

	@Autowired
	AuthenticationManager authenticationManager ;
	@Autowired
	MyUserDetailsService myUserDetailsService;
	@Autowired
	JwtUtil jwtTokenUtil;

	@GetMapping("/hello")
	public String home() {
		return ("<h2> Welcome </h2>");
	}

@RequestMapping(value="/authenticate", method = RequestMethod.POST)
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

@EnableWebSecurity
class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserDetailsService myUserDetailsService;

	//@Override
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests().antMatchers("/authenticate").permitAll()
				.anyRequest().authenticated();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
}*/

