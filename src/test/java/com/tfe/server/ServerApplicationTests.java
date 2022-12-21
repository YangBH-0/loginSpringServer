package com.tfe.server;

import com.google.gson.Gson;
import com.tfe.server.controller.appController;
import com.tfe.server.domain.app.LoginRequest;
import com.tfe.server.domain.app.LoginResponse;
import com.tfe.server.service.DBservice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ServerApplicationTests {
	@Autowired
	private MockMvc mockMvc;


	@MockBean
	private DBservice service;

	@Autowired
	private com.tfe.server.controller.appController ac;

	@Test
	public void contextLoads() throws Exception{
		assertThat(ac).isNotNull();
	}
	@Test
	public void greetingShouldReturnMessageFromService() throws Exception {
		when(service.login(new LoginRequest("test","test"))).thenReturn(new LoginResponse());
		Gson gson=new Gson();
		String jsonString = gson.toJson(new LoginRequest("test","test"));
		System.out.println(jsonString);


		/*this.mockMvc.perform(post("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, Mock")));*/
		this.mockMvc.perform(post("/app/login")
						/*.contentType("application/json")*/
						.content(jsonString))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
