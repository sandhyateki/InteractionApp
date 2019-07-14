package com.sample.interaction.Processor;

import com.sample.interaction.entity.ApplicationDetailEntity;
import com.sample.interaction.entity.ValuesEntity;
import com.sample.interaction.model.*;
import com.sample.interaction.repository.ApplicationDetailRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InteractionProcessor {

    @Autowired
    ApplicationDetailRespository applicationDetailRespository;

    public AppDtlsResponseTO getResponse(String fileName, String fileType) {

        AppDtlsResponseTO appResponse = new AppDtlsResponseTO();
        List<ApplicationDetailTO> appDetails = null;

        Optional<ApplicationDetailEntity> appInfo = applicationDetailRespository.findBySubType(fileType);
        if(null != appInfo
                && appInfo.isPresent()
                && null != appInfo.get().getApplicationPartDetail()
                && fileName.equalsIgnoreCase(appInfo.get().getApplicationPartDetail().getFilePN())) {
            appDetails = new ArrayList<>();
            appDetails.add(getApplicationDetailsResponse(appInfo.get()));
        }
        appResponse.setApplicationDetail(appDetails);
        return appResponse;
    }

    private ApplicationDetailTO getApplicationDetailsResponse(ApplicationDetailEntity applicationDetailEntity) {
        ApplicationDetailTO applicationDetails = new ApplicationDetailTO();

        applicationDetails.setSubType(applicationDetailEntity.getSubType());
        applicationDetails.setValues(applicationDetailEntity.getValuesEntity().stream().map(ValuesEntity::getValue).collect(Collectors.toList()));
        if(null != applicationDetailEntity.getApplicationPartDetail()) {
            setApplicationPartDetails(applicationDetails, applicationDetailEntity);
        }
        applicationDetails.setApplicationDescription(applicationDetailEntity.getApplicationDescription());
        applicationDetails.setServiceable(applicationDetailEntity.getServiceable());
        applicationDetails.setTestable(applicationDetailEntity.getTestable());

        setCompatibleAssemblies(applicationDetailEntity, applicationDetails);

        FunctionalTypeValuesTO funcTypVal = new FunctionalTypeValuesTO();
        funcTypVal.setFunctionalType( !ObjectUtils.isEmpty(applicationDetailEntity.getFunctionalTypeValues()) ? applicationDetailEntity.getFunctionalTypeValues().getFunctionalType() : null );
        applicationDetails.setFunctionalTypeValues(funcTypVal);

        applicationDetails.setSellable(applicationDetailEntity.getSellable());
        applicationDetails.setResidentOnVehicle(applicationDetailEntity.getResidentOnVehicle());
        applicationDetails.setDigitallySigned(applicationDetailEntity.getDigitallySigned());
        applicationDetails.setConsumerViewable(applicationDetailEntity.getConsumerViewable());
        applicationDetails.setConsumerDownloadable(applicationDetailEntity.getConsumerDownloadable());
        applicationDetails.setDealerDownloadable(applicationDetailEntity.getDealerDownloadable());
        applicationDetails.setOTAViewable(applicationDetailEntity.getOTAViewable());
        applicationDetails.setOTADownloadable(applicationDetailEntity.getOTADownloadable());

        return applicationDetails;
    }

    private void setCompatibleAssemblies(ApplicationDetailEntity applicationDetailEntity, ApplicationDetailTO applicationDetails) {
        List<CompatibleAssembliesTO> compAssemList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(applicationDetailEntity.getCompatibleAssemblies())) {
            applicationDetailEntity.getCompatibleAssemblies().forEach(val -> {
                CompatibleAssembliesTO compAssem = new CompatibleAssembliesTO();
                compAssem.setAssyPN(val.getAssyPN());
                compAssemList.add(compAssem);
            });
        }
        applicationDetails.setCompatibleAssemblies(compAssemList);
    }

    private void setApplicationPartDetails(ApplicationDetailTO applicationDetails, ApplicationDetailEntity applicationDetailEntity) {
        ApplicationPartDetailTO applicationPartDetail = new ApplicationPartDetailTO();

        applicationPartDetail.setFilePN(applicationDetailEntity.getApplicationPartDetail().getFilePN());
        applicationPartDetail.setFrozenStatus(applicationDetailEntity.getApplicationPartDetail().getFrozenStatus());
        applicationPartDetail.setSuccessorPartNumber(applicationDetailEntity.getApplicationPartDetail().getSuccessorPartNumber());

        applicationPartDetail.setMGMFile(setMgmFileDtls(applicationDetailEntity));

        applicationPartDetail.setVersion(applicationDetailEntity.getApplicationPartDetail().getVersion());
        applicationPartDetail.setRunTimeSize(applicationDetailEntity.getApplicationPartDetail().getRunTimeSize());

        applicationDetails.setApplicationPartDetail(applicationPartDetail);
    }


    private MGMFileTO setMgmFileDtls(ApplicationDetailEntity applicationDetailEntity) {
        MGMFileTO mgmFile = new MGMFileTO();
        if(null != applicationDetailEntity.getApplicationPartDetail()
                && null != applicationDetailEntity.getApplicationPartDetail().getMGMFile()) {
                    {
                        setProtocol(applicationDetailEntity, mgmFile);
                    }

            mgmFile.setFileFormat(applicationDetailEntity.getApplicationPartDetail().getMGMFile().getFileFormat());
            mgmFile.setChecksum(applicationDetailEntity.getApplicationPartDetail().getMGMFile().getChecksum());
            mgmFile.setFileFingerprint(applicationDetailEntity.getApplicationPartDetail().getMGMFile().getFileFingerprint());
            mgmFile.setROMSizeKB(applicationDetailEntity.getApplicationPartDetail().getMGMFile().getROMSizeKB());
            mgmFile.setSize(applicationDetailEntity.getApplicationPartDetail().getMGMFile().getSize());
            mgmFile.setFileDateModified(applicationDetailEntity.getApplicationPartDetail().getMGMFile().getFileDateModified());
        }
        return mgmFile;

    }

    private void setProtocol(ApplicationDetailEntity applicationDetailEntity, MGMFileTO mgmFile) {
        Set<ProtocolTO> protocols = new HashSet<>();
        if (null != applicationDetailEntity.getApplicationPartDetail().getMGMFile() && !CollectionUtils.isEmpty(applicationDetailEntity.getApplicationPartDetail().getMGMFile().getProtocol())){
            applicationDetailEntity.getApplicationPartDetail().getMGMFile().getProtocol().forEach(val -> {
                ProtocolTO protocol = new ProtocolTO();
                protocol.setNetworkWireless(val.getNetworkWireless());
                protocol.setNetworkProtocol(val.getEmbeddedId().getNetworkProtocol());
                protocols.add(protocol);
            });
            mgmFile.setProtocol(protocols);

        }
    }
}
