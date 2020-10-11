package openshift.cop;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import java.util.UUID;

@QuarkusTest
public class GreetingControllerTest {

	@Test
	public void testGreetingV1() throws Exception {
        given().when().get("/v1/greeting").then().statusCode(200).body(is("Hello, World!"));
    }
    
    @Test
	public void testGreetingV1WithParam() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().queryParam("name", uuid).when().get("/v1/greeting").then().statusCode(200).body(startsWith("hello " + uuid));
	}
}
