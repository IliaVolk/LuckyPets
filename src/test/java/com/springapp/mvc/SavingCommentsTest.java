package com.springapp.mvc;

import com.luckypets.config.DataSourceConfig;
import com.luckypets.config.HibernateConfig;
import com.luckypets.config.Initializer;
import com.luckypets.config.WebAppConfig;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Initializer.class,
        WebAppConfig.class, HibernateConfig.class, DataSourceConfig.class})
public class SavingCommentsTest {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;


    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testSavingAdvertComment() throws Exception {
        class Text {
            String text;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
        Text text = new Text();
        text.setText("qwerty-");
        byte[] bytes = TestUtil.convertObjectToJsonBytes(text);
        System.out.println(new String(bytes));


        mockMvc.perform(post("/ajax/adverts/1/comments").
                content(bytes).contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());

    }


    // @Test
    public void testSavingClinicComment() throws Exception {

        class PartialComment {
            String text;
            int mark;

            public PartialComment(String text, int mark) {
                this.text = text;
                this.mark = mark;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public int getMark() {
                return mark;
            }

            public void setMark(int mark) {
                this.mark = mark;
            }
        }
        byte[] bytes = TestUtil.convertObjectToJsonBytes(new PartialComment("qwe", 2));
        System.out.println(bytes);

        mockMvc.perform(post("/ajax/clinics/1/comments").
                content(bytes).
                contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print()).
                andExpect(status().isOk());
    }
}
