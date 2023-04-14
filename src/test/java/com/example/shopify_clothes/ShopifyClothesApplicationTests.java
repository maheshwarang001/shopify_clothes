package com.example.shopify_clothes;

import com.example.shopify_clothes.model.Catlog;
import com.example.shopify_clothes.service.CatlogServiceLayer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ShopifyClothesApplicationTests {

    @Autowired
    CatlogServiceLayer catlogServiceLayer;
    @Test
    public void testDisplayHome() {
        // Create mock model
        Model mockModel = Mockito.mock(Model.class);

        // Create mock list of Catlogs to be returned by service layer
        List<Catlog> mockCatlogList = new ArrayList<>();
        mockCatlogList.add(new Catlog("Catlog 1"));
        mockCatlogList.add(new Catlog("Catlog 2"));

        // Mock service layer behavior to return mock list
        when(catlogServiceLayer.allCatlog()).thenReturn(mockCatlogList);

        // Call the controller method
        String viewName = controller.displayHome(mockModel);

        // Verify that the correct view name is returned
        assertEquals("home.html", viewName);

        // Verify that the model attribute "list" is set correctly
        verify(mockModel).addAttribute("list", mockCatlogList);
    }

    @RunWith(SpringRunner.class)
    @WebMvcTest(YourController.class)
    public class YourControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private CatlogServiceLayer catlogServiceLayer;

        @Test
        public void testDisplayCategory() throws Exception {
            // arrange
            String category = "testCategory";
            List<Catlog> expectedList = Arrays.asList(new Catlog(), new Catlog());

            given(catlogServiceLayer.categoryLog(category)).willReturn(expectedList);

            // act
            MvcResult result = mockMvc.perform(post("/category")
                            .param("category", category))
                    .andExpect(status().isOk())
                    .andExpect(view().name("category.html"))
                    .andReturn();

            // assert
            List<Catlog> actualList = (List<Catlog>) result.getModelAndView().getModel().get("list");
            assertEquals(expectedList, actualList);
            verify(catlogServiceLayer, times(1)).categoryLog(category);
        }
    }

    @Test
    public void testDisplayProductDetail() {
        // create a mock Catlog object
        Catlog mockCatlog = new Catlog();
        mockCatlog.setId(1);
        mockCatlog.setName("Test Product");
        mockCatlog.setDescription("This is a test product.");
        mockCatlog.setPrice(9.99);

        // create a mock Model object
        Model mockModel = new ConcurrentModel();

        // mock the CatlogServiceLayer
        CatlogServiceLayer mockService = Mockito.mock(CatlogServiceLayer.class);
        when(mockService.findById(1)).thenReturn(mockCatlog);

        // create an instance of the controller and call the method
        CatalogController controller = new CatalogController();
        controller.setCatlogServiceLayer(mockService);
        String result = controller.displayProductDetail(1, mockModel);

        // verify that the correct view was returned
        assertEquals("productdetail.html", result);

        // verify that the Catlog object was added to the model
        assertEquals(mockCatlog, mockModel.getAttribute("p"));
    }




}
