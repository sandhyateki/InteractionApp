package com.sample.interaction;

import com.sample.interaction.Processor.InteractionProcessor;
import com.sample.interaction.model.AppDtlsResponseTO;
import com.sample.interaction.model.ApplicationDetailTO;
import com.sample.interaction.model.MGMFileTO;
import com.sample.interaction.repository.ApplicationDetailRespository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class InteractionProcessorTest {
    @InjectMocks
    @Autowired
    InteractionProcessor interactionProcessor;

    @Mock
    ApplicationDetailRespository applicationDetailRespository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void emptyMgmFileTest() throws Exception{
        MGMFileTO actualMgmFileTo = Whitebox.invokeMethod(interactionProcessor, "setMgmFileDtls", DataSetUpUtil.getApplicationData(true,false, false));
        MGMFileTO expectedMgmFileTo = new MGMFileTO();
        Assert.assertEquals(expectedMgmFileTo, actualMgmFileTo);
    }

    @Test
    public void validMgmFileTest() throws Exception{
        MGMFileTO actualMgmFileTo = Whitebox.invokeMethod(interactionProcessor, "setMgmFileDtls", DataSetUpUtil.getApplicationData(false,false, false));
        Assert.assertEquals( "TAR.GZ", actualMgmFileTo.getFileFormat());
        Assert.assertEquals("1664508640",actualMgmFileTo.getROMSizeKB());
        Assert.assertEquals("0xC7C1DA2F",actualMgmFileTo.getChecksum() );
        Assert.assertEquals("1664508640",actualMgmFileTo.getSize());
        Assert.assertTrue(!actualMgmFileTo.getProtocol().isEmpty());
        Assert.assertTrue(actualMgmFileTo.getProtocol().size() == 1);
    }

    @Test
    public void emptyProtocolTest() throws Exception {
        MGMFileTO mgmFileTO = new MGMFileTO();
        Whitebox.invokeMethod(interactionProcessor, "setProtocol", DataSetUpUtil.getApplicationData(true, true,false), mgmFileTO);
        Assert.assertTrue(CollectionUtils.isEmpty(mgmFileTO.getProtocol()));
    }

    @Test
    public void validProtocolTest() throws Exception {
        MGMFileTO mgmFileTO = new MGMFileTO();
        Whitebox.invokeMethod(interactionProcessor, "setProtocol", DataSetUpUtil.getApplicationData(false, false, false), mgmFileTO);
        Assert.assertTrue(!CollectionUtils.isEmpty(mgmFileTO.getProtocol()));
        Assert.assertTrue(mgmFileTO.getProtocol().size() == 1);
        Assert.assertTrue("USB".equalsIgnoreCase(mgmFileTO.getProtocol().iterator().next().getNetworkProtocol()));
        Assert.assertTrue("yes".equalsIgnoreCase(mgmFileTO.getProtocol().iterator().next().getNetworkWireless()));
    }

    @Test
    public void emptyCompatibleAssembliesTest() throws Exception {
        ApplicationDetailTO applicationDetailTO = new ApplicationDetailTO();
        Whitebox.invokeMethod(interactionProcessor, "setCompatibleAssemblies", DataSetUpUtil.getApplicationData(false, false,true), applicationDetailTO);
        Assert.assertTrue(CollectionUtils.isEmpty(applicationDetailTO.getCompatibleAssemblies()));
    }

    @Test
    public void validCompatibleAssembliesTest() throws Exception {
        ApplicationDetailTO applicationDetailTO = new ApplicationDetailTO();
        Whitebox.invokeMethod(interactionProcessor, "setCompatibleAssemblies", DataSetUpUtil.getApplicationData(false, false,false), applicationDetailTO);
        Assert.assertTrue(!CollectionUtils.isEmpty(applicationDetailTO.getCompatibleAssemblies()));
        Assert.assertEquals("SA-12-AAA",applicationDetailTO.getCompatibleAssemblies().get(0).getAssyPN());
    }

    @Test
    public void getAppDtlsResponseTest() {
        Mockito.when(applicationDetailRespository.findBySubType(Mockito.anyString())).thenReturn(DataSetUpUtil.getApplicationDetailEntity(false, false));
        AppDtlsResponseTO responseTo = interactionProcessor.getResponse("SA-11-TT", "Application1");
        assertEquals("Application1", responseTo.getApplicationDetail().get(0).getSubType());
        assertEquals("SA-11-TT", responseTo.getApplicationDetail().get(0).getApplicationPartDetail().getFilePN());
    }

    @Test
    public void getEmptyResponseTest() {
        Mockito.when(applicationDetailRespository.findBySubType(Mockito.anyString())).thenReturn(DataSetUpUtil.getApplicationDetailEntity(true,false));
        AppDtlsResponseTO responseTo = interactionProcessor.getResponse("SA-11-TT", "Application10");
        assertTrue(ObjectUtils.isEmpty(responseTo.getApplicationDetail()));
    }

    @Test
    public void testGetResponse_NullCheck_GetApplicationPartDetail() {
        Mockito.when(applicationDetailRespository.findBySubType(Mockito.anyString())).thenReturn(DataSetUpUtil.getApplicationDetailEntity(true,true));
        AppDtlsResponseTO responseTo = interactionProcessor.getResponse("SA-11-TT", "Application3");
        assertTrue(ObjectUtils.isEmpty(responseTo.getApplicationDetail()));
    }
}
