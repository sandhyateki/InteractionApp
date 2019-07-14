package com.sample.interaction;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InteractionApplicationTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.port = port;
    }

    @Test
    public void appInfoForFileSuccessTest() throws Exception {

        String containResultSet = "{\"ApplicationDetail\":[{\"otaviewable\":\"Y\",\"otadownloadable\":\"Y\",\"SubType\":\"Application1\",\"Values\":[\"SV\",\"DE\",\"DA\",\"FR-FR\",\"TR\",\"PT-PT\",\"RU\",\"CS\",\"PL\"],\"ApplicationPartDetail\":{\"mgmfile\":{\"romsizeKB\":\"1664508640\",\"Protocol\":[{\"NetworkProtocol\":\"YES\",\"NetworkWireless\":\"OTA\"},{\"NetworkProtocol\":\"NO\",\"NetworkWireless\":\"USB\"}],\"FileFormat\":\"TAR.GZ\",\"Checksum\":\"0xC7C1DA2F\",\"FileFingerprint\":\"0x61192B821A9EA47D72DD360175C7A8EE\",\"Size\":\"1664508640\",\"FileDateModified\":\"2016-03-22T01:20:40.000+0000\"},\"FilePN\":\"SA-11-TT\",\"FrozenStatus\":\"True\",\"SuccessorPartNumber\":\"SA-11-TS\",\"Version\":\"1.0\",\"RunTimeSize\":\"1664508640\"},\"ApplicationDescription\":\"Test Application to run certain features in the vehicle\",\"Serviceable\":\"Y\",\"Testable\":\"Y\",\"CompatibleAssemblies\":[{\"AssyPN\":\"SA-12-FAA\"},{\"AssyPN\":\"SA-12-AAC\"},{\"AssyPN\":\"SA-12-FAB\"},{\"AssyPN\":\"SA-12-AAB\"},{\"AssyPN\":\"SA-12-FAC\"},{\"AssyPN\":\"SA-12-AAA\"}],\"FunctionalTypeValues\":{\"FunctionalType\":\"CUSTOM APPLICATION\"},\"Sellable\":\"N\",\"ResidentOnVehicle\":\"Y\",\"DigitallySigned\":\"Generically Signed\",\"ConsumerViewable\":\"Y\",\"ConsumerDownloadable\":\"Y\",\"DealerDownloadable\":\"Y\"}]}";
        URI uri = new URI("/interaction/application/getDetails?fileName=SA-11-TT&fileType=Application1");
        String baseUrl = "http://localhost:" + port + uri;

        Response response =
                given()
                        .when()
                        .get(baseUrl)
                        .then()
                        .extract().response();
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(containResultSet, response.body().asString());
    }


    @Test
    public void getEmptyResponseForApp() throws Exception {

        URI uri = new URI("/interaction/application/getDetails?fileName=SA-11-TT&fileType=Application9");
        String baseUrl = "http://localhost:" + port + uri;

        Response response =
                        given()
                        .when()
                        .get(baseUrl)
                        .then()
                        .extract().response();
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue("{\"ApplicationDetail\":null}".equals(response.getBody().asString()));
    }

    @Test
    public void getAppInfoNotFoundTest() throws Exception {

        URI uri = new URI("/interaction/appliction/getDetails?fileName=SA-11-TT&fileType=Application1");
        String baseUrl = "http://localhost:" + port + uri;

        Response response =
                        given()
                        .when()
                        .get(baseUrl)
                        .then()
                        .extract().response();
        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertTrue(response.getBody().asString().contains("Not Found"));
    }
    @Test
    public void getAppInfoBadRequestTest() throws Exception {

        URI uri = new URI("/interaction/application/getDetails?fileName=SA-11-TT");
        String baseUrl = "http://localhost:" + port + uri;

        Response response =
                        given()
                        .when()
                        .get(baseUrl)
                        .then()
                        .extract().response();
        Assert.assertEquals(400, response.getStatusCode());
        Assert.assertTrue(response.getBody().asString().contains("Bad Request"));
    }
}
