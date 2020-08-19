
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.AssertTrue;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.radd.Driver;
import com.radd.models.DotaHero;
import com.radd.models.DotaPrimaryAttributes;
import com.radd.repositories.DotaHeroRepository;
import com.radd.services.DotaHeroService;

@SpringBootTest(classes = Driver.class)
public class DotaHeroControllerTests {

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	TestConstants t = new TestConstants();
	String token;

	@Test
	public void testReturnAllHeroes() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes"), HttpMethod.GET, entity,
				String.class);

		assertNotNull(response);
	}

	@Test
	public void testCorrectId() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes/2"), HttpMethod.GET, entity,
				String.class);

		String expected = "{\"img\":\"http://cdn.dota2.com/apps/dota2/images/heroes/axe_full.png?\",\"attacktype\":\"Melee\",\"hero_id\":\"2\",\"hero_name\":\"Axe\",\"primary_atri\":{\"primary_atri\":\"str\"}}";

		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	@Test
	public void testIncorrectId() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes/-3"), HttpMethod.GET, entity,
				String.class);

		String expected = null;
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void testCorrectAttribute() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes/atri/str"), HttpMethod.GET, entity,
				String.class);

		assertNotNull(response);
	}

	@Test
	public void testIncorrectAttribute() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes/atri/strength"), HttpMethod.GET,
				entity, String.class);

		String expected = "[]";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void testCorrectCounter() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes/counter/33"), HttpMethod.GET, entity,
				String.class);

		String expected = "{\"best1\":{\"img\":\"http://cdn.dota2.com/apps/dota2/images/heroes/pudge_full.png?\",\"attacktype\":\"Melee\",\"hero_id\":\"14\",\"hero_name\":\"Pudge\",\"primary_atri\":{\"primary_atri\":\"str\"}},\"best2\":{\"img\":\"http://cdn.dota2.com/apps/dota2/images/heroes/weaver_full.png?\",\"attacktype\":\"Ranged\",\"hero_id\":\"63\",\"hero_name\":\"Weaver\",\"primary_atri\":{\"primary_atri\":\"agi\"}},\"best3\":{\"img\":\"http://cdn.dota2.com/apps/dota2/images/heroes/abyssal_underlord_full.png?\",\"attacktype\":\"Melee\",\"hero_id\":\"108\",\"hero_name\":\"Underlord\",\"primary_atri\":{\"primary_atri\":\"str\"}},\"worst1\":{\"img\":\"http://cdn.dota2.com/apps/dota2/images/heroes/night_stalker_full.png?\",\"attacktype\":\"Melee\",\"hero_id\":\"60\",\"hero_name\":\"Night Stalker\",\"primary_atri\":{\"primary_atri\":\"str\"}},\"worst2\":{\"img\":\"http://cdn.dota2.com/apps/dota2/images/heroes/spectre_full.png?\",\"attacktype\":\"Melee\",\"hero_id\":\"67\",\"hero_name\":\"Spectre\",\"primary_atri\":{\"primary_atri\":\"agi\"}},\"worst3\":{\"img\":\"http://cdn.dota2.com/apps/dota2/images/heroes/terrorblade_full.png?\",\"attacktype\":\"Melee\",\"hero_id\":\"109\",\"hero_name\":\"Terrorblade\",\"primary_atri\":{\"primary_atri\":\"agi\"}}}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void testIncorrectCounter() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes/counter/-33"), HttpMethod.GET, entity,
				String.class);

		assertNotNull(response);
	}

	@Test
	public void testCorrectName() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes/names/Axe"), HttpMethod.GET, entity,
				String.class);

		String expected = "{\"img\":\"http://cdn.dota2.com/apps/dota2/images/heroes/axe_full.png?\",\"attacktype\":\"Melee\",\"hero_id\":\"2\",\"hero_name\":\"Axe\",\"primary_atri\":{\"primary_atri\":\"str\"}}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void testIncorrectName() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes/names/xxx"), HttpMethod.GET, entity,
				String.class);

		assertNotNull(response);
	}

	@Test
	public void updateHero() {
		DotaHero hero = createHero();
		token = t.getUsableToken();
		headers.add("authorization", token);
		HttpEntity<DotaHero> entity = new HttpEntity<DotaHero>(hero, headers);
		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes"), HttpMethod.PUT, entity,
				String.class);
		assertEquals(202, response.getStatusCodeValue());
	}

	private DotaHero createHero() {
		DotaPrimaryAttributes atri = new DotaPrimaryAttributes();
		atri.setPrimary_atri("str");
		DotaHero hero = new DotaHero();
		hero.setHero_id("1");
		hero.setHero_name("Rennie The Conquerer");
		hero.setImg("rennie's pc");
		hero.setAttacktype("melee");
		hero.setPrimary_atri(atri);
		return hero;
	}
}
