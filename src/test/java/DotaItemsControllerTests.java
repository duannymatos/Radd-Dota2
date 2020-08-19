
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.radd.models.DotaItems;

@SpringBootTest(classes = Driver.class)
public class DotaItemsControllerTests {

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	TestConstants t = new TestConstants();
	String token;

	@Test
	public void testReturnAllItems() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("items"), HttpMethod.GET, entity,
				String.class);

		assertNotNull(response);
	}

	@Test
	public void getCorrectItemById() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("items/6"), HttpMethod.GET, entity,
				String.class);

		String expected = "{\"img\":\"http://cdn.dota2.com/apps/dota2/images/items/helm_of_iron_will_lg.png?t=1592658656576\",\"notes\":\"The helmet of a legendary warrior who fell in battle.\",\"price\":\"925\",\"item_id\":\"6\",\"item_name\":\"Helm of Iron Will\"}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void geIncorrectItemById() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("items/9999"), HttpMethod.GET, entity,
				String.class);

		String expected = null;
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void getCorrectItemByName() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("items/name/Reaver"), HttpMethod.GET, entity,
				String.class);
		String expected = "{\"img\":\"http://cdn.dota2.com/apps/dota2/images/items/reaver_lg.png?t=1592658656576\",\"notes\":\"A massive axe capable of tearing whole mountains down.\",\"price\":\"3000\",\"item_name\":\"Reaver\",\"item_id\":\"53\"}";
		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	@Test
	public void getItemByIncorrectName() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("items/names/xxx"), HttpMethod.GET, entity,
				String.class);
		assertNotNull(response);

	}

	@Test
	public void testCreateItem() {
		DotaItems item = createItem();
		token = t.getUsableToken();
		headers.add("authorization", token);
		HttpEntity<DotaItems> entity = new HttpEntity<DotaItems>(item, headers);
		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("items"), HttpMethod.POST, entity,
				String.class);
		assertEquals(200, response.getStatusCodeValue());
		;
	}

	@Test
	public void testUpdateItem() {
		DotaItems item = updateItem();
		token = t.getUsableToken();
		headers.add("authorization", token);
		HttpEntity<DotaItems> entity = new HttpEntity<DotaItems>(item, headers);
		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("items"), HttpMethod.PUT, entity,
				String.class);
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void testDeleteItem() {
		DotaItems item = updateItem();
		token = t.getUsableToken();
		headers.add("authorization", token);
		HttpEntity<DotaItems> entity = new HttpEntity<DotaItems>(item, headers);
		ResponseEntity<String> response = restTemplate.exchange(t.URL + ("items"), HttpMethod.DELETE, entity,
				String.class);
		assertEquals(200, response.getStatusCodeValue());
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

	private DotaItems updateItem() {
		DotaItems item = new DotaItems();
		item.setItem_id("999");
		item.setItem_name("The Spear of Rennie");
		item.setNotes("Lacks the power of Rennie");
		item.setPrice("100");
		item.setImg("IPlayRuneScape");
		return item;
	}
}
