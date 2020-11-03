// ============================================================================
//
// Copyright (C) 2006-2020 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package com.talend.excel.xssf.event;

import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.text.DateFormat;
import java.util.Map;

/**
 * created by wwang on 2012-9-27 Detailled comment
 *
 */
public class TalendXSSFSheetXMLHandler extends XSSFSheetXMLHandler {

    private final TalendSheetContentsHandler output;
    private final Map<String, DateFormat> customDateFormats;
    private final StylesTable stylesTable;
    private final DataFormatter formatter;

    /**
     * maybe replaced with stack if start and end element for xss has recursive call
     */
    private String lastChangedFormatString;

    public TalendXSSFSheetXMLHandler(StylesTable styles, ReadOnlySharedStringsTable strings,
            TalendSheetContentsHandler sheetContentsHandler, DataFormatter dataFormatter, boolean formulasNotResults,
            Map<String, DateFormat> customDateFormats) {
        super(styles, strings, sheetContentsHandler, dataFormatter, formulasNotResults);
        this.output = sheetContentsHandler;
        this.stylesTable = styles;
        this.formatter = dataFormatter;
        this.customDateFormats = customDateFormats;
    }

    public interface TalendSheetContentsHandler extends SheetContentsHandler {

        public void endSheet();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if (uri == null || uri.equals("http://schemas.openxmlformats.org/spreadsheetml/2006/main")) {
            if ("c".equals(localName)) {
                swapExcelDateFormatOnTalendIfNeed(attributes);
            }
        }
    }

    private void swapExcelDateFormatOnTalendIfNeed(Attributes attributes) {
        if (stylesTable == null) {
            return;
        }

        String columnName = attributes.getValue("r").replaceAll("[0-9]", "");
        String cellStyleStr = attributes.getValue("s");
        String cellType = attributes.getValue("t");

        XSSFCellStyle style = null;
        if (cellStyleStr != null) {
            int styleIndex = Integer.parseInt(cellStyleStr);
            style = this.stylesTable.getStyleAt(styleIndex);
        } else if (this.stylesTable.getNumCellStyles() > 0) {
            style = this.stylesTable.getStyleAt(0);
        }

        if (style != null) {
            String formatString = style.getDataFormatString();
            short formatIndex = style.getDataFormat();
            if (formatString == null) {
                formatString = BuiltinFormats.getBuiltinFormat(formatIndex);
            }

            DateFormat format = customDateFormats.get(columnName);
            if (formatString != null && (cellType == null)
                    && DateUtil.isADateFormat(formatIndex, formatString) && format != null) {
                lastChangedFormatString = formatString;
                formatter.addFormat(formatString, format);
            }
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            super.endElement(uri, localName, qName);
        } finally {
            // clean up
            if (lastChangedFormatString != null) {
                formatter.addFormat(lastChangedFormatString, null);
                lastChangedFormatString = null;
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        this.output.endSheet();
    }

}
