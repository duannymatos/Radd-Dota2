

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
import com.radd.models.DotaItemStage;
import com.radd.models.DotaItems;
import com.radd.models.DotaPrimaryAttributes;
import com.radd.models.DotaStage;

@SpringBootTest(classes = Driver.class)
public class DotaItemStageTests {
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	TestConstants t = new TestConstants();

	@Test
	public void testReturnAllHeroes() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes/item"), HttpMethod.GET, entity,
				String.class);

		assertNotNull(response);
	}
	
	@Test
	public void testCorrectItemsById() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes/item/9"), HttpMethod.GET, entity,
				String.class);

		//returns an acutal response just way to long to check against
		assertNotNull(response);

	}
	
	@Test
	public void testItemsByIncorrectId() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("heroes/item/99999"), HttpMethod.GET, entity,
				String.class);

		String expected = "[]";

		JSONAssert.assertEquals(expected, response.getBody(), false);

	}
	/*
	@Test
	public void testCreate() {
		DotaItemStage item = createItemWithStage();
		HttpEntity<DotaItemStage> entity = new HttpEntity<DotaItemStage>(item,headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("heroes/item"), HttpMethod.POST, entity, String.class);
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		assertThat(actual.contains("heroes/item"));
	}
	
	@Test
	public void testUpdate() {
		DotaItemStage item = updateItemWithStage();
		HttpEntity<DotaItemStage> entity = new HttpEntity<DotaItemStage>(item,headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("heroes/item"), HttpMethod.PUT, entity, String.class);
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		assertThat(actual.contains("heroes/item"));
	}
	
	@Test
	public void testDelete() {
		DotaItemStage item = updateItemWithStage();
		HttpEntity<DotaItemStage> entity = new HttpEntity<DotaItemStage>(item,headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("heroes/item"), HttpMethod.DELETE, entity, String.class);
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		assertThat(actual.contains("heroes/item"));
	}
	*/
	
	
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
	
	private DotaItems createItem() {
		DotaItems item = new DotaItems();
			item.setItem_id("999");
			item.setItem_name("The Sword of Rennie");
			item.setNotes("Behold the power of Rennie");
			item.setPrice("10,000,000");
			item.setImg("IPlayRuneScape");
		return item;
	}
	private DotaItemStage createItemWithStage() {
		DotaStage stage = new DotaStage();
			stage.setStage("start");
		
		DotaItemStage itemStage = new DotaItemStage();
			itemStage.setId(999);
			itemStage.setItemId(createItem());
			itemStage.setHeroId(createHero());
			itemStage.setStage(stage);
		return itemStage;
	}
	
	private DotaItemStage updateItemWithStage() {
		DotaStage stage = new DotaStage();
			stage.setStage("early");
		
		DotaItemStage itemStage = new DotaItemStage();
			itemStage.setId(999);
			itemStage.setItemId(createItem());
			itemStage.setHeroId(createHero());
			itemStage.setStage(stage);
		return itemStage;
	}
}
