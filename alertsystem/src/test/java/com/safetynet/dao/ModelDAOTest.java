package com.safetynet.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.safetynet.alertsystem.constants.URIDataConstants;
import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.dao.NetworkDAO;
import com.safetynet.alertsystem.model.PersonalInformation;

class ModelDAOTest {
	
	static ArrayList<PersonalInformation> personalList;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ModelDAO modelDao = new ModelDAO();
		
		personalList = modelDao.fetchPersonalInformation(NetworkDAO.request(URIDataConstants.LINK_JASON_DATA));
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	// @TODO: Ask to Nick what is causing the error, the test seems to work when the key is not matching. 
	@DisplayName("Tests if the first name of the first array is the right one")
	@Test
	void fetchPersonalInformationTest1() throws ClientProtocolException, IOException {
//		// ARRANGE
//		ModelDAO modelDao = new ModelDAO();
//		
//		// ACT
//		ArrayList<PersonalInformation> personalList = 
//				modelDao.fetchPersonalInformation(NetworkDAO.request(URIDataConstants.LINK_JASON_DATA));
		
		//ASSERT
		assertThat( personalList, contains(hasProperty("firstName", is("John"))));
	}
	
	@DisplayName("Tests if the last name of the first array is the right one")
	@Test
	void fetchPersonalInformationTest2() throws ClientProtocolException, IOException {
//		// ARRANGE
//		ModelDAO modelDao = new ModelDAO();
//		
//		// ACT
//		ArrayList<PersonalInformation> personalList = 
//				modelDao.fetchPersonalInformation(NetworkDAO.request(URIDataConstants.LINK_JASON_DATA));
		
		//ASSERT
		assertThat( personalList, contains(hasProperty("lastName", is("Boyd"))));
	}
	
	@Test
	void test() throws ClientProtocolException, IOException {
		// ARRANGE
		ModelDAO modelDao = new ModelDAO();
		
		// ACT
		ArrayList<PersonalInformation> personalList = 
				modelDao.fetchPersonalInformation(NetworkDAO.request(URIDataConstants.LINK_JASON_DATA));
		
		//ASSERT
		assertThat(personalList, contains(hasProperty("firstName", is("John")), hasProperty("lastName", is("Boyd"))));
	}
	
	@DisplayName("Tests if the size of the ArrayList is bigger than 5")
	@Test
	void fetchPersonalInformationTest3() throws ClientProtocolException, IOException {
		
		assert(personalList.size() > 5);
	}

}
