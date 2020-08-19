import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.radd.Driver;
import com.radd.models.UnwiredApplicationUser;

@SpringBootTest(classes = Driver.class)
public class JwtAuthControllerTest {
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	TestConstants  t = new TestConstants();
	String token;
	
	@Test
	public void testAdminLogin() throws Exception
	{
		//check to see if we get a token back from our test assist class
//		
//		token = t.getToken();
//		
//		assertEquals(token.substring(0, 7),"{\"token");
	}
	
	//Test creating a user
	@Test
	public void testCreatingAUser() throws Exception
	{
		token=t.getUsableToken();
		headers.add("authorization", token);
		HttpEntity<UnwiredApplicationUser> entity = new HttpEntity<>(t.getAdminUser(),headers);
		ResponseEntity<String> response = restTemplate.exchange(t.URL+"/user/role", HttpMethod.POST, entity,
			String.class);
	 
		String res=response.getBody();
		System.out.println(res);
		int a =5;
		a =5;
	}
	
}
