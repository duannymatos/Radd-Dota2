import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.radd.models.UnwiredApplicationUser;

public class TestConstants
{
	public static final String URL="http://18.191.221.150:8085/";
	public String webToken;
	
	public UnwiredApplicationUser getAdminUser()
	{
		UnwiredApplicationUser user =new UnwiredApplicationUser();
		user.setPassword("test");
		user.setUsername("User");
		return user;
	}
	
	//returns a token for admin
	public String getToken()
	{
		HttpHeaders headers = new HttpHeaders();
		TestRestTemplate restTemplate = new TestRestTemplate();
		UnwiredApplicationUser user= getAdminUser();
		HttpEntity<UnwiredApplicationUser> entity = new HttpEntity<>(user,headers);
		ResponseEntity<String> response = restTemplate.exchange(URL+"/user", HttpMethod.POST, entity,
			String.class);
		return response.getBody();
	}
	
	public String getUsableToken()
	{
		HttpHeaders headers = new HttpHeaders();
		TestRestTemplate restTemplate = new TestRestTemplate();
		UnwiredApplicationUser user= getAdminUser();
		HttpEntity<UnwiredApplicationUser> entity = new HttpEntity<>(user,headers);
		ResponseEntity<String> response = restTemplate.exchange(URL+"/user", HttpMethod.POST, entity,
			String.class);
		
		String token=response.getBody();

		//get rid of the "bad" parts of the token
		token=token.substring(10);
		token=token.substring(0,token.length()-2);
		return token;
	}
}
