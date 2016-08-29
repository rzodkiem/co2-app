package com.rzodkiewicz.michal.service.implementation

import jdk.internal.org.xml.sax.SAXException
import org.apache.poi.xssf.model.SharedStringsTable
import org.apache.poi.xssf.usermodel.XSSFRichTextString
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler


class ExcelSaxParser  extends DefaultHandler{

    private static final Logger LOG = LoggerFactory.getLogger(ExcelSaxParser.class)

    private static final String CELL_TAG_NAME = 'c'
    private static final String STRING_CELL_TYPE_SST_TAG_NAME = 's'
    private static final String INDEX_SST_ATTRIBUTE_VALUE = 't'
    private static final String CELL_REFERENCE_ATTRIBUTE = 'r'
    private static final String CELL_CONTENT_TAG = 'v'

    private SharedStringsTable sharedStringsTable
    private String lastContent
    private boolean nextIsString


    ExcelSaxParser(SharedStringsTable sharedStringsTable){
        this.sharedStringsTable = sharedStringsTable
    }

    void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException{
        if(name.equals(CELL_TAG_NAME)){
            LOG.info("cell reference: ${-> attributes.getValue(CELL_REFERENCE_ATTRIBUTE)}")
            String cellType = attributes.getValue(INDEX_SST_ATTRIBUTE_VALUE)
            nextIsString = cellType?.equals(STRING_CELL_TYPE_SST_TAG_NAME)
        }
        lastContent = ''
    }



    void endElement(String uri, String localName, String name) throws SAXException {
        if(nextIsString){
            if(lastContent != ''){
                int index = Integer.parseInt(lastContent)
                lastContent = new XSSFRichTextString(sharedStringsTable.getEntryAt(index).toString())
                nextIsString = false
            }

        }
        if(name.equals(CELL_CONTENT_TAG)){
            LOG.info("CELL CONETENT: ${-> lastContent}")
        }

    }


}
