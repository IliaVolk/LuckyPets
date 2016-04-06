package com.springapp.mvc;

import com.luckypets.config.DataSourceConfig;
import com.luckypets.config.HibernateConfig;
import com.luckypets.config.Initializer;
import com.luckypets.config.WebAppConfig;
import com.luckypets.entity.ajax.ClinicsInRadiusSearchCriteria;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    //@Test
    public void testSimpleAjax() throws Exception {
        String request = "request";
        mockMvc.perform(post("/ajax").contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(request))).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testClinicsInRadius() {
        try {
            ClinicsInRadiusSearchCriteria request = new ClinicsInRadiusSearchCriteria();
            request.setAnimalType(AnimalType.DOG).setBeginIndex(0).setCount(5)
                    .setLat(50).setLng(30).setRadiusInKilometers(200);

            System.out.println(request);
            System.out.println(new String(TestUtil.convertObjectToJsonBytes(request)));
            System.out.println(mockMvc.perform(post("/search/api/articles").
                            contentType(TestUtil.APPLICATION_JSON_UTF8).
                            content(TestUtil.convertObjectToJsonBytes(request)))
                            .andDo(MockMvcResultHandlers.print()).andReturn()
                    //.getAsyncResult().toString()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}