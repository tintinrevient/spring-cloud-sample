package com.hncy.demo.reviewservice;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.RestPactRunner;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.target.MockMvcTarget;
import org.junit.runner.RunWith;

@RunWith(RestPactRunner.class)
@Provider("review_service")
@PactFolder("target/pacts")
public class ReviewContractTests {

}
