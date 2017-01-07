package com.rzodkiewicz.michal.util

import com.rzodkiewicz.michal.domain.Emission
import com.rzodkiewicz.michal.util.enums.XssfDataType
import org.apache.poi.xssf.model.SharedStringsTable
import org.apache.poi.xssf.usermodel.XSSFRichTextString
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler


class ExcelSaxParser  extends DefaultHandler{

    private static final Logger LOG = LoggerFactory.getLogger(ExcelSaxParser.class)

    private static final String CELL_TAG_NAME = 'c'
    private static final String STRING_CELL_TYPE_SST_TAG_NAME = 's'
    private static final String INDEX_SST_ATTRIBUTE_VALUE = 't'
    private static final String CELL_REFERENCE_ATTRIBUTE = 'r'
    private static final String CELL_CONTENT_TAG = 'v'
    private static final String ROW_TAG_NAME = 'row'
    private String columnIndicator
    private XssfDataType nextDataType

    private SharedStringsTable sharedStringsTable
    private String lastContent
    private boolean nextIsString
    private int rowCounter = 0
    private Emission emission = new Emission()
    List<Emission> emissionList = []


    ExcelSaxParser(SharedStringsTable sharedStringsTable){
        this.sharedStringsTable = sharedStringsTable
    }

    void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException{
        if(name.equals(CELL_TAG_NAME)){
            nextDataType = XssfDataType.NUMBER
            LOG.info("cell reference: ${-> attributes.getValue(CELL_REFERENCE_ATTRIBUTE)}")
            columnIndicator = attributes.getValue(CELL_REFERENCE_ATTRIBUTE)
            String cellType = attributes.getValue(INDEX_SST_ATTRIBUTE_VALUE)
            if(cellType?.equals(STRING_CELL_TYPE_SST_TAG_NAME)){
                nextDataType = XssfDataType.SST_STRING
                nextIsString = true
            }else{
                nextIsString = false
            }
        }
        lastContent = ''
    }



    void endElement(String uri, String localName, String name) throws SAXException {

        if(nextIsString){
            if(lastContent != ''){
                int index = Integer.parseInt(lastContent)
                String cellValue = new XSSFRichTextString(sharedStringsTable.getEntryAt(index).toString())
                lastContent = cellValue.substring(cellValue.indexOf('>') + 1, cellValue.lastIndexOf('<'))
                nextIsString = false
            }

        }
        if(name.equals(CELL_CONTENT_TAG)){
            LOG.info("CELL CONETENT: ${-> lastContent}")
            if(rowCounter > 0){
                fillEmissionAttribute(emission)
            }
        }else if(name.equals(ROW_TAG_NAME)){
            if(emission.countryCode != null){
                emissionList.add(emission)
            }
            emission = new Emission()
            rowCounter++
        }

    }

    private void fillEmissionAttribute(Emission emission){
        switch(columnIndicator.charAt(0)){
            case 'A':
                emission.countryCode = lastContent
                break
            case 'B':
                emission.countryName = lastContent
                break
            case 'C':
                break
            case 'G':
                emission.sector = lastContent
                break
            case 'H':
                emission.year = Integer.parseInt(lastContent)
                break
            case 'I':
                emission.emission = Double.parseDouble(lastContent)
                break
            default:
                break
        }
    }

    public void characters(char[] ch, int start, int length)
            throws org.xml.sax.SAXException {
        lastContent += new String(ch, start, length);
    }
}



