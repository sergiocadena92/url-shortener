package com.upwork.url.shortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upwork.url.shortener.exception.UrlNotFoundException;
import com.upwork.url.shortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UrlController.class)
class UrlControllerTest {

    private static final String SHORT_URL = "shortUrl123";
    private static final String ORIGINAL_URL = "http://example.com";
    private static final String INVALID_SHORT_URL = "invalidShortUrl";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlService urlService;


    @Test
    void testShortenUrlCreated() throws Exception {
        UrlRequest urlRequest = new UrlRequest();
        urlRequest.setOriginalUrl(ORIGINAL_URL);
        when(urlService.shortenUrl(anyString())).thenReturn(SHORT_URL);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(urlRequest))
                )
                .andExpect(status().isCreated());

        verify(urlService, times(1)).shortenUrl(anyString());
    }

    @Test
    void testShortenUrlBadRequest() throws Exception {
        when(urlService.shortenUrl(anyString())).thenReturn(SHORT_URL);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/url")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(new UrlRequest()))
                )
                .andExpect(status().isBadRequest());

        verify(urlService, never()).shortenUrl(anyString());
    }

    @Test
    void testShortenUrlInternalError() throws Exception {
        UrlRequest urlRequest = new UrlRequest();
        urlRequest.setOriginalUrl(ORIGINAL_URL);
        when(urlService.shortenUrl(anyString())).thenThrow(RuntimeException.class);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/url")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(urlRequest))
                )
                .andExpect(status().isInternalServerError());

        verify(urlService, times(1)).shortenUrl(anyString());
    }

    @Test
    void testRedirectToOriginalUrl() throws Exception {
        when(urlService.getOriginalUrl(SHORT_URL)).thenReturn(ORIGINAL_URL);

        mockMvc.perform(MockMvcRequestBuilders.get("/".concat(SHORT_URL)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(ORIGINAL_URL));

        verify(urlService, times(1)).getOriginalUrl(SHORT_URL);
    }

    @Test
    public void testRedirectToOriginalUrlNotFoundException() throws Exception {
        when(urlService.getOriginalUrl(INVALID_SHORT_URL)).thenThrow(new UrlNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/".concat(INVALID_SHORT_URL)))
                .andExpect(status().isNotFound());

        verify(urlService, times(1)).getOriginalUrl(INVALID_SHORT_URL);
    }
}