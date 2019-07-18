package com.hncy.demo.dataservice.controller;

import com.hncy.demo.dataservice.domain.Review;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataControllerTest {

    @Autowired
    private RestTemplate testRestTemplate;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(6061);

    @Test
    public void aggregate() {
        stubFor(get(urlEqualTo("/review/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"id\":1,\"subject\":\"cup review\",\"comment\":\"a good cup\",\"user\":1,\"product\":1,\"ignore\":true}")));

        Review expectedReview = new Review();
        expectedReview.setId(1l);
        expectedReview.setSubject("cup review");
        expectedReview.setComment("a good cup");
        expectedReview.setUser(1l);
        expectedReview.setProduct(1l);

        assertThat(testRestTemplate.getForEntity("http://localhost:6061/review/1", Review.class).getBody()).isEqualTo(expectedReview);
    }
}
