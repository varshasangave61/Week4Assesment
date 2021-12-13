package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
//import org.junit.Test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.CustomerProducer.CustomerProducerApplication;
import com.example.CustomerProducer.Repository.CustomerRepository;
import com.example.CustomerProducer.controller.ProducerController;
import com.example.CustomerProducer.model.Customer_Bank;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@ContextConfiguration
@SpringBootTest(classes = ProducerController.class)
@RunWith(SpringRunner.class)
@WebMvcTest(value =ProducerController.class)
class ProducerControllerTest {

	@Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerRepository customerrepository;
	
	
	@Test
	 void testGetCustomers() throws Exception{
        String URI = "/bank/customers";
        Customer_Bank customer1 = new Customer_Bank();
        customer1.setId(1);
        customer1.setName("Suresh");
        customer1.setAge(20);
        customer1.setAddressId(234);
        customer1.setTypeofAccount("savings");

        Customer_Bank customer2 = new Customer_Bank();
        customer2.setId(3);
        customer2.setName("ramesh");
        customer2.setAge(20);
        customer2.setAddressId(12);
        customer2.setTypeofAccount("salary");

        List<Customer_Bank> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        String jsonInput = this.converttoJson(customerList);

        Mockito.when(customerrepository.findAll()).thenReturn(customerList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	
	
	
	@Test
     void testPostCustomer() throws Exception{
		String URI = "/bank/customers";
        Customer_Bank customer = new Customer_Bank();
        customer.setId(2);
        customer.setName("ramu");
        customer.setAge(50);
        customer.setAddressId(1095);
        customer.setTypeofAccount("current");
        String jsonInput = this.converttoJson(customer);

        Mockito.when(customerrepository.save(Mockito.any(Customer_Bank.class))).thenReturn(customer);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
    }
	
	
	@Test
    void testDeleteCust() throws Exception{
		String URI = "/bank/customers/{id}";
       Customer_Bank customer = new Customer_Bank();
       customer.setId(2);
       customer.setName("ramu");
       customer.setAge(50);
       customer.setAddressId(120);
       customer.setTypeofAccount("current");
      // Mockito.when(customerRepository.findById(2)).thenReturn(customer);
      // Mockito.when(customerRepository.delete(Mockito.any())).thenReturn("deleted");
       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 2).accept(MediaType.
       		APPLICATION_JSON)).andReturn();
       MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
      Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

   }

	
   @Test
    void testUpdateCustomer() throws Exception{

       String URI = "/bank/customers/{id}";
       Customer_Bank customer2 = new Customer_Bank();
       customer2.setId(3);
       customer2.setName("krish");
       customer2.setAge(20);
       customer2.setAddressId(123);
       customer2.setTypeofAccount("salary");
       String jsonInput = this.converttoJson(customer2);
 //      Mockito.when(customerRepository.findById(3)).thenReturn("find");
       Mockito.when(customerrepository.save(Mockito.any(Customer_Bank.class))).thenReturn(customer2);
       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,3).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
               .andReturn();
       MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
       String jsonOutput = mockHttpServletResponse.getContentAsString();
       assertThat(jsonInput).isEqualTo(jsonOutput);
   }

    @SuppressWarnings("unchecked")
	@Test
	 void testGetDataFallBack() throws Exception{
        String URI = "/bank/customers";
        Customer_Bank customer = new Customer_Bank();
        customer.setName("Suresh");
        customer.setAge(20);
        customer.setAddressId(1234);
        customer.setTypeofAccount("savings");
        List<Customer_Bank> customerList = new ArrayList<>();
        customerList.add(customer);

        String jsonInput = this.converttoJson(customerList);

        Mockito.when(customerrepository.findAll()).thenReturn(customerList );
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
 
        assertThat(jsonInput).isEqualTo(jsonOutput);
	}
        
	    private String converttoJson(Object customer) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(customer);
    }



}
