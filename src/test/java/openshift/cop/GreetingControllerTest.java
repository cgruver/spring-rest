package openshift.cop;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import java.util.UUID;

@QuarkusTest
public class GreetingControllerTest {

	@Test
	public void testGreetingV1() {
        given().when().get("/v1/greeting").then().statusCode(200).body(containsString("Hello, World!"));
        //given().when().get("/v1/greeting").then().statusCode(200).body(instanceOf(Greeting.class));
    }
    // content().string(containsString("Hello, World!")
    @Test
	public void testGreetingV1WithParam() {
        String uuid = UUID.randomUUID().toString();
        //given().queryParam("name", uuid).when().get("/v1/greeting").then().statusCode(200).body(instanceOf(Greeting.class));
        given().queryParam("name", uuid).when().get("/v1/greeting").then().statusCode(200).body(containsString("Hello, " + uuid));
	}
}
