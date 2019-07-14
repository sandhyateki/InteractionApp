package com.sample.interaction;

import com.sample.interaction.entity.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DataSetUpUtil {

        public static Optional<ApplicationDetailEntity> getApplicationDetailEntity(Boolean fileNotFound, Boolean appPartDetailEmpty) {
            ApplicationDetailEntity applicationDetails = new ApplicationDetailEntity();
            if(!fileNotFound){
            ApplicationPartDetailEntity applicationPartDetail = new ApplicationPartDetailEntity();

            applicationDetails.setSubType("Application1");
            Set<ValuesEntity> valluesEntitySet = new HashSet<>();

            ValuesEntity valuesEntity1 = new ValuesEntity(1, "Application1", "CS");
            ValuesEntity valuesEntity2 = new ValuesEntity(1, "Application1", "DA");

            valluesEntitySet.add(valuesEntity1);
            valluesEntitySet.add(valuesEntity2);

            applicationDetails.setValuesEntity(valluesEntitySet);

            applicationPartDetail.setFilePN("SA-11-TT");
            applicationPartDetail.setFrozenStatus("true");
            applicationPartDetail.setSuccessorPartNumber("SA-11-TS");

            Set<ProtocolEntity> protocols = new HashSet<>();

            ProtocolPK protocolPk = new ProtocolPK();
            protocolPk.setNetworkProtocol("USB");
            protocols.add(new ProtocolEntity(protocolPk, "yes"));

            applicationPartDetail.setMGMFile(new MGMFileEntity("SA-11-TT", protocols, "TAR.GZ", "0xC7C1DA2F", "0x61192B821A9EA47D72DD360175C7A8EE", "1664508640", "1664508640", Timestamp.valueOf(LocalDateTime.now())));

            applicationPartDetail.setVersion("1.0");
            applicationPartDetail.setRunTimeSize("1664508640");
            if(!appPartDetailEmpty) {
                applicationDetails.setApplicationPartDetail(applicationPartDetail);
            }
            applicationDetails.setApplicationDescription("Test Application to run certain features in the vehicle");
            applicationDetails.setServiceable("Y");
            applicationDetails.setTestable("Y");

            HashSet compatibleAssembliesSet = new HashSet();
            compatibleAssembliesSet.add(new CompatibleAssembliesEntity(1, "SA-12-AAA", "Application1"));
            applicationDetails.setCompatibleAssemblies(compatibleAssembliesSet);

            applicationDetails.setFunctionalTypeValues(new FunctionalTypeValuesEntity("Application1", "Custom Application"));

            applicationDetails.setSellable("Y");
            applicationDetails.setResidentOnVehicle("Y");
            applicationDetails.setDigitallySigned("Generically Signed");
            applicationDetails.setConsumerViewable("Y");
            applicationDetails.setConsumerDownloadable("Y");
            applicationDetails.setDealerDownloadable("Y");
            applicationDetails.setOTAViewable("N");
            applicationDetails.setOTADownloadable("Y");
        }
            return Optional.of(applicationDetails);
        }


    public static ApplicationDetailEntity getApplicationData(boolean isEmptyMgmFile, boolean isEmptyProtocol, boolean isEmptyCompatAssm){
        ApplicationDetailEntity applicationDetails = new ApplicationDetailEntity();
        ApplicationPartDetailEntity applicationPartDetail = new ApplicationPartDetailEntity();

        applicationDetails.setSubType("Application1");
        Set<ValuesEntity> valluesEntitySet = new HashSet<>();

        ValuesEntity valuesEntity1 = new ValuesEntity(1,"Application1","CS");
        ValuesEntity valuesEntity2 = new ValuesEntity(1,"Application1","DA");

        valluesEntitySet.add(valuesEntity1);
        valluesEntitySet.add(valuesEntity2);

        applicationDetails.setValuesEntity(valluesEntitySet);

        applicationPartDetail.setFilePN("SA-11-TT");
        applicationPartDetail.setFrozenStatus("true");
        applicationPartDetail.setSuccessorPartNumber("SA-11-TS");

        Set<ProtocolEntity> protocols = new HashSet<>();

        ProtocolPK  protocolPk = new ProtocolPK();
        protocolPk.setNetworkProtocol("USB");
        protocols.add(new ProtocolEntity(protocolPk,"yes"));


        applicationPartDetail.setVersion("1.0");
        applicationPartDetail.setRunTimeSize("1664508640");

        applicationDetails.setApplicationPartDetail(applicationPartDetail);

        applicationDetails.setApplicationDescription("Test Application to run certain features in the vehicle");
        applicationDetails.setServiceable("Y");
        applicationDetails.setTestable("Y");

        if(!isEmptyMgmFile) {
            if (!isEmptyProtocol) {
                applicationPartDetail.setMGMFile(new MGMFileEntity("SA-11-TT", protocols, "TAR.GZ", "0xC7C1DA2F", "0x61192B821A9EA47D72DD360175C7A8EE", "1664508640", "1664508640", Timestamp.valueOf(LocalDateTime.now())));
            }else{
                applicationPartDetail.setMGMFile(new MGMFileEntity("SA-11-TT", new HashSet<>(), "TAR.GZ", "0xC7C1DA2F", "0x61192B821A9EA47D72DD360175C7A8EE", "1664508640", "1664508640", Timestamp.valueOf(LocalDateTime.now())));
            }
        }
        if(!isEmptyCompatAssm){
            HashSet compatibleAssembliesSet = new HashSet();
            compatibleAssembliesSet.add(new CompatibleAssembliesEntity(1,"SA-12-AAA","Application1"));
            applicationDetails.setCompatibleAssemblies(compatibleAssembliesSet);
        }

        applicationDetails.setFunctionalTypeValues(new FunctionalTypeValuesEntity("Application1","Custom Application"));

        applicationDetails.setSellable("Y");
        applicationDetails.setResidentOnVehicle("Y");
        applicationDetails.setDigitallySigned("Generically Signed");
        applicationDetails.setConsumerViewable("Y");
        applicationDetails.setConsumerDownloadable("Y");
        applicationDetails.setDealerDownloadable("Y");
        applicationDetails.setOTAViewable("N");
        applicationDetails.setOTADownloadable("Y");

        return applicationDetails;
    }
}
