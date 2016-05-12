package com.springapp.mvc;

import com.luckypets.config.DataSourceConfig;
import com.luckypets.config.HibernateConfig;
import com.luckypets.config.Initializer;
import com.luckypets.config.WebAppConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Initializer.class,
        WebAppConfig.class, HibernateConfig.class, DataSourceConfig.class})
public class TestRequests {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    //@Test
    public void testRadiusClinicsRequest() throws Exception {
        mockMvc.perform(get("/articles/clinics/around").
                param("beginIndex", "0").param("count", "5").
                param("animalType", "DOG").param("lat", "50").
                param("lng", "30").param("radius", "200"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("articles/clinics"));
        /*
        Test for this:
        RequestMapping(value = "/clinics/around", method = RequestMethod.GET)
    public String getClinics(//TODO: better make object "ClinicRequest"
                             //TODO: or make LatLng separated
                             //TODO: and what with enum mapping?
                             @RequestParam("beginIndex") int beginIndex,
                             @RequestParam("count") int count,
                             @RequestParam("animalType") AnimalType animalType,
                             //@RequestParam("latLnd") LatLng latLng,
                             @RequestParam("lat") double lat,
                             @RequestParam("lng") double lng,
                             //LatLng latLng,
                             @RequestParam("radius") double radiusInKilometers,
                             Model model) {
        LatLng latLng = new LatLng(lat, lng);
        model.addAttribute("clinics", clinicDao.getClinics(
                latLng, radiusInKilometers, animalType, beginIndex, count
        ));
        return "articles/clinics";
    }
         */
    }

    //@Test
    public void testEnumRequest() throws Exception {
        //Test for this
        /*
        @RequestMapping(value = "/clinics", method = RequestMethod.GET)
        public String getClinics(@RequestParam("beginIndex") int beginIndex,
                             @RequestParam("count") int count,
                             @RequestParam("animalType") AnimalType animalType,
                             Model model) {
            model.addAttribute("clinics", clinicDao.getClinics(beginIndex, count, animalType));
            return "articles/clinics";
        }
         */
        mockMvc.perform(get("/articles/clinics").
                param("beginIndex", "0").param("count", "5").
                param("animalType", "DOG"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("articles/clinics"));
    }
}