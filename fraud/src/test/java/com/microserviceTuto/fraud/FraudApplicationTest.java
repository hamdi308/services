package com.microserviceTuto.fraud;

import com.microserviceTuto.fraud.dao.FraudCheckRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microserviceTuto.fraud.repository.FraudCheckRepository;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.net.Socket;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Slf4j
@RequiredArgsConstructor
public class FraudApplicationTest {
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:5"));
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private FraudCheckRepository fraudCheckRepository;
    @BeforeAll
    static void initAll(){
        mongoDBContainer.start();
    }
    @Test
    void containerStartAndPublicPortIsAvailable(){
        assertThatPortIsAvailable(mongoDBContainer);
    }

    private void assertThatPortIsAvailable(MongoDBContainer mongoDBContainer) {
        try{
            new Socket(mongoDBContainer.getContainerIpAddress(),mongoDBContainer.getFirstMappedPort());
        }catch(IOException e){
            throw new AssertionError("The expected port "+ mongoDBContainer.getFirstMappedPort() +" is not available");
        }
    }

    @DynamicPropertySource
    static void setProperties(@NotNull DynamicPropertyRegistry dpr){
        dpr.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
        mongoDBContainer.start();
    }
    @Test
    void shouldCreateFraud() throws Exception {
        FraudCheckRequest fraudCheckRequest= getFraudCheckRequest();
        String fraudCheckRequestString=objectMapper.writeValueAsString(fraudCheckRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/api/v1/fraud-check/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fraudCheckRequestString))
                .andExpect(status().isCreated());
        assertEquals(1,fraudCheckRepository.findAll().size());
    }
    public FraudCheckRequest getFraudCheckRequest() {
        return FraudCheckRequest.builder()
                .customerId(320)
                .isFraudster(true)
                .build();
    }

}
