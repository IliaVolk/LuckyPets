package com.springapp.mvc;

import com.luckypets.config.DataSourceConfig;
import com.luckypets.config.HibernateConfig;
import com.luckypets.config.Initializer;
import com.luckypets.config.WebAppConfig;
import com.luckypets.entity.ajax.AjaxAdvertByAnimalTypeAndTypeRequest;
import com.luckypets.entity.ajax.ClinicsInRadiusSearchCriteria;
import com.luckypets.entity.enums.AdvertType;
import com.luckypets.entity.enums.AnimalType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Initializer.class,
        WebAppConfig.class, HibernateConfig.class, DataSourceConfig.class})
public class TestAjaxRequests {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testAdvertCommentsRequest() throws Exception {
        mockMvc.perform(get("/ajax/adverts/2/comments")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void testAdvertRequest() throws Exception {
        AjaxAdvertByAnimalTypeAndTypeRequest request = new AjaxAdvertByAnimalTypeAndTypeRequest();
        request.setAnimalType(AnimalType.DOG);
        request.setAdvertType(AdvertType.GIVE);
        request.setBeginIndex(0);
        request.setCount(10);
        mockMvc.perform(get("/ajax/adverts/1/0/0/10"))
                .andDo(MockMvcResultHandlers.print()).
                andExpect(status().isOk());
    }

    @Test
    public void testClinicAnimalTypeRequest() throws Exception {
        mockMvc.perform(get("/ajax/animalList")).
                andDo(MockMvcResultHandlers.print()).
                andExpect(status().isOk());
    }

    @Test
    public void testDistrictListRequest() throws Exception {
        mockMvc.perform(get("/ajax/districtList")).
                andDo(MockMvcResultHandlers.print()).
                andExpect(status().isOk());
    }
    @Test
    public void testClinicComments() throws Exception {
        mockMvc.perform(get("/ajax/clinics/1/comments")).
                andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
    }

    @Test
    public void testClinicsByAnimalTypeAndDistrict() throws Exception {
        /*AjaxClinicByAnimalTypeAndDistrictRequest request = new AjaxClinicByAnimalTypeAndDistrictRequest();
        request.setBeginIndex(0);
        request.setCount(10);
        request.setAnimalType(AnimalType.DOG);
        request.setDistrict(District.SVIATOSHINSKI);
        System.out.println(new String(TestUtil.convertObjectToJsonBytes(request)));*/
        mockMvc.perform(
                get("/ajax/clinics/1/1/0/10"))
                //.content(TestUtil.convertObjectToJsonBytes(request)))
                .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
    }


    //@Test
    public void testSimpleAjax() throws Exception {
        String request = "request";
        mockMvc.perform(post("/ajax").contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(request))).
                andDo(MockMvcResultHandlers.print());
    }

    //@Test
    public void testClinicsInRadius() {
        try {
            ClinicsInRadiusSearchCriteria request = new ClinicsInRadiusSearchCriteria();
            request.setAnimalType(AnimalType.DOG).setBeginIndex(0).setCount(5)
                    .setLat(50).setLng(30).setRadiusInKilometers(200);

            System.out.println(request);
            System.out.println(new String(TestUtil.convertObjectToJsonBytes(request)));
            System.out.println(mockMvc.perform(post("/ajax/clinics").
                            contentType(TestUtil.APPLICATION_JSON_UTF8).
                            content(TestUtil.convertObjectToJsonBytes(request)))
                            .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
                            .andReturn()
                    //.getAsyncResult().toString()

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}