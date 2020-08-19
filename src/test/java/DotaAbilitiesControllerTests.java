

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.radd.Driver;
import com.radd.models.DotaHero;
import com.radd.models.DotaHeroAbility;
import com.radd.models.DotaPrimaryAttributes;

@SpringBootTest(classes = Driver.class)
public class DotaAbilitiesControllerTests {

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	TestConstants  t = new TestConstants();

	@Test
	void testReturnAllHeroes() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes/abilities"), HttpMethod.GET,
				entity, String.class);

		assertNotNull(response);
	}

	@Test
	public void testCorrectAbilitiesByHeroId() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes/abilities/8"), HttpMethod.GET,
				entity, String.class);

		String expected = "[{\"ability_name\":\"Blade Dance\",\"img\":\"http://cdn.dota2.com/apps/dota2/images/abilities/juggernaut_blade_dance_md.png\"},{\"ability_name\":\"Blade Fury\",\"img\":\"http://cdn.dota2.com/apps/dota2/images/abilities/juggernaut_blade_fury_md.png\"},{\"ability_name\":\"Healing Ward\",\"img\":\"http://cdn.dota2.com/apps/dota2/images/abilities/juggernaut_healing_ward_md.png\"},{\"ability_name\":\"Omnislash\",\"img\":\"http://cdn.dota2.com/apps/dota2/images/abilities/juggernaut_omni_slash_md.png\"}]";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void testAbilitiesByIncorrectHeroId() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes/abilities/99999"),
				HttpMethod.GET, entity, String.class);

		// will be an error code
		assertNotNull(response);
	}

	
	  @Test public void addAbility() 
	  { 
		  DotaHeroAbility ability = createAbility();
		  HttpEntity<DotaHeroAbility> entity = new HttpEntity<DotaHeroAbility>(ability,
		  headers); ResponseEntity<String> response =
		  restTemplate.exchange(t.URL + ("/abilities"), HttpMethod.POST,
		  entity, String.class); String actual =
		  response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		  assertThat(actual.contains("abilites"));
	  }
	  
	   @Test public void deleteAbility() 
	   { 
		  DotaHeroAbility ability = updateAbility();
		  HttpEntity<DotaHeroAbility> entity = new HttpEntity<DotaHeroAbility>(ability,
		  headers); ResponseEntity<String> response =
		  restTemplate.exchange(t.URL + ("/abilities"), HttpMethod.DELETE,
		  entity, String.class); String actual =
		  response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		  assertThat(actual.contains("abilites")); 
	  }
//	  
//	  @Test public  void updateAbility() { DotaHeroAbility ability = updateAbility();
//	  HttpEntity<DotaHeroAbility> entity = new HttpEntity<DotaHeroAbility>(ability,
//	  headers); ResponseEntity<String> response =
//	  restTemplate.exchange(createURLWithPort("/abilities"), HttpMethod.PUT,
//	  entity, String.class); String actual =
//	  response.getHeaders().get(HttpHeaders.LOCATION).get(0);
//	  assertThat(actual.contains("abilites")); }
	  

	 
	

	public DotaHeroAbility createAbility() {
		DotaHeroAbility ability = new DotaHeroAbility();
		ability.setAbility_name("Reeeeee");
		ability.setHero_id(createHero());
		ability.setImg("hehehehe");
		return ability;

	}
	
	public DotaHeroAbility updateAbility() {
		DotaHeroAbility ability = new DotaHeroAbility();
		ability.setAbility_name("Nub");
		ability.setHero_id(createHero());
		ability.setImg("Employee of Duanny");
		return ability;

	}

	private DotaHero createHero() {
		DotaPrimaryAttributes atri = new DotaPrimaryAttributes();
		atri.setPrimary_atri("str");
		DotaHero hero = new DotaHero();
		hero.setHero_id("999");
		hero.setHero_name("Rennie The Conquerer");
		hero.setImg("rennie's pc");
		hero.setAttacktype("melee");
		hero.setPrimary_atri(atri);
		return hero;
	}
}
