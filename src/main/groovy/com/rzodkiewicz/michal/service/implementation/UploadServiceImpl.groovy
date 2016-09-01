package com.rzodkiewicz.michal.service.implementation

import com.rzodkiewicz.michal.domain.Emission
import com.rzodkiewicz.michal.repository.EmissionRepository
import com.rzodkiewicz.michal.service.UploadService
import com.rzodkiewicz.michal.util.ExcelSaxParser
import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.eventusermodel.XSSFReader
import org.apache.poi.xssf.model.SharedStringsTable
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.xml.sax.InputSource
import org.xml.sax.SAXException
import org.xml.sax.XMLReader
import org.xml.sax.helpers.XMLReaderFactory

@Service
class UploadServiceImpl implements UploadService{

    @Value('${unfccc.sheet-number}')
    private final int DATASET_SHEET_INDEX
    private final EmissionRepository emissionRepository
    private List<Emission> emissions = []
    private ExcelSaxParser handler

    @Autowired
    UploadServiceImpl(EmissionRepository emissionRepository){
        this.emissionRepository = emissionRepository
    }

    @Override
    List<Emission> uploadUnfccDataset(MultipartFile file) throws IOException {
        OPCPackage opcPackage = OPCPackage.open(file.inputStream)
        XSSFReader reader = new XSSFReader(opcPackage)
        SharedStringsTable sharedStringsTable = reader.getSharedStringsTable()
        XMLReader parser = fetchSheetParser(sharedStringsTable)
        InputStream sheet = reader.getSheet("rId2")
        InputSource inputSource = new InputSource(sheet)
        parser.parse(inputSource)
        sheet.close()
        emissions = handler.emissionList
        emissionRepository.save(emissions)
        emissions

    }

    private XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
        XMLReader parser =
                XMLReaderFactory.createXMLReader(
                        "org.apache.xerces.parsers.SAXParser"
                )
        handler = new ExcelSaxParser(sst)
        parser.contentHandler = handler
        return parser
    }

    private List<Emission> parseDataToEntityList(XSSFSheet sheet){
        List<Emission> emissions = []
        for(int i = 1; i < sheet.getLastRowNum(); i++){
            Row currentRow = sheet.getRow(i)
            Emission emission = new Emission()
            emission.countryCode = currentRow.getCell(0).stringCellValue
            emission.countryName = currentRow.getCell(1).stringCellValue
            emission.sector = currentRow.getCell(6).stringCellValue
            emission.year = currentRow.getCell(7).numericCellValue
            emission.emission = currentRow.getCell(8).numericCellValue
            emissions.add(emission)
        }
    }



}
