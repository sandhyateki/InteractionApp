package com.sample.interaction;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.interaction.Processor.InteractionProcessor;
import com.sample.interaction.controller.InteractionController;
import com.sample.interaction.model.AppDtlsResponseTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InteractionController.class)
public class InteractionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InteractionProcessor interactionProcessor;


    @Test
    public void getAppDtlsResponseTest() throws Exception{

        String result =  "{\"ApplicationDetail\":[{\"otaviewable\":\"Y\",\"otadownloadable\":\"Y\",\"SubType\":\"Application1\",\"Values\":[\"SV\",\"DE\",\"DA\",\"FR-FR\",\"TR\",\"PT-PT\",\"RU\",\"CS\",\"PL\"],\"ApplicationPartDetail\":{\"mgmfile\":{\"romsizeKB\":\"1664508640\",\"Protocol\":[{\"NetworkProtocol\":\"YES\",\"NetworkWireless\":\"OTA\"},{\"NetworkProtocol\":\"NO\",\"NetworkWireless\":\"USB\"}],\"FileFormat\":\"TAR.GZ\",\"Checksum\":\"0xC7C1DA2F\",\"FileFingerprint\":\"0x61192B821A9EA47D72DD360175C7A8EE\",\"Size\":\"1664508640\",\"FileDateModified\":\"2016-03-22T01:20:40.000+0000\"},\"FilePN\":\"SA-11-TT\",\"FrozenStatus\":\"True\",\"SuccessorPartNumber\":\"SA-11-TS\",\"Version\":\"1.0\",\"RunTimeSize\":\"1664508640\"},\"ApplicationDescription\":\"Test Application to run certain features in the vehicle\",\"Serviceable\":\"Y\",\"Testable\":\"Y\",\"CompatibleAssemblies\":[{\"AssyPN\":\"SA-12-FAA\"},{\"AssyPN\":\"SA-12-AAC\"},{\"AssyPN\":\"SA-12-FAB\"},{\"AssyPN\":\"SA-12-AAB\"},{\"AssyPN\":\"SA-12-FAC\"},{\"AssyPN\":\"SA-12-AAA\"}],\"FunctionalTypeValues\":{\"FunctionalType\":\"CUSTOM APPLICATION\"},\"Sellable\":\"N\",\"ResidentOnVehicle\":\"Y\",\"DigitallySigned\":\"Generically Signed\",\"ConsumerViewable\":\"Y\",\"ConsumerDownloadable\":\"Y\",\"DealerDownloadable\":\"Y\"}]}";

        ObjectMapper objectMapper = new ObjectMapper();
        AppDtlsResponseTO responseTo = objectMapper.readValue(result, AppDtlsResponseTO.class);
        when(interactionProcessor.getResponse("SA-11-TT", "Application1")).thenReturn(responseTo);
        mockMvc.perform( MockMvcRequestBuilders
                .get("/interaction/application/getDetails?fileName=SA-11-TT&fileType=Application1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ApplicationDetail").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ApplicationDetail[*]").isNotEmpty());


    }
    @Test
    public void getAppInfoExceptionTest() throws Exception {
        when(interactionProcessor.getResponse("SA-11-TT", "Application1"))
                .thenThrow(new NullPointerException("Exception"));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/interaction/application/getDetails?fileName=SA-11-TT&fileType=Application1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("{\"error\":{\"message\":\"Exception\",\"error\":\"INTERNAL_SERVER_ERROR\",\"path\":\"/interaction/application/getDetails\",\"status\":500,\"exception\":\"java.lang.NullPointerException\"}}"));
    }


}
