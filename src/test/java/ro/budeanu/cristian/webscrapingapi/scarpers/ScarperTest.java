package ro.budeanu.cristian.webscrapingapi.scarpers;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.budeanu.cristian.webscrapingapi.exceptions.ScarperException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScarperTest {
    @Mock
    private JsoupService jsoupService;
    private Scarper scarper;

    @BeforeEach
    void setup() {
        scarper = new Scarper("/local", jsoupService);
    }

    @Test
    void getScarpingDataThrowsIOException() throws IOException {
        //mock
        when(jsoupService.connect("/local/video")).thenThrow(new IOException("error getting data"));

        //verify
        ScarperException exception = assertThrows(ScarperException.class, () -> scarper.getScarpingData("video"));
        assertEquals("Unable to get video data with error: error getting data", exception.getMessage());
    }

    @Test
    void getScarpingDataReturnsNoData() throws IOException {
        //setup
        Document document = new Document("/local/video");

        //mock
        when(jsoupService.connect("/local/video")).thenReturn(document);

        //
        Elements elements = scarper.getScarpingData("video");
        assertNotNull(elements);
        assertEquals(0, elements.size());
    }

    @Test
    void getScarpingDataa() throws IOException {
        //setup
        Document document = new Document("/local/video");
        document.appendElement("item", "test");

        //mock
        when(jsoupService.connect("/local/video")).thenReturn(document);

        //
        Elements elements = scarper.getScarpingData("video");
        assertNotNull(elements);
        assertEquals(1, elements.size());
    }
}