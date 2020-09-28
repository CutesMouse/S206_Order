package com.cutesmouse.s206Order;

import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class DataWrite {

    private WritableWorkbook wwbCopy;
    private WritableSheet shSheet;
    private String strSheetName;

    public void createExcel(String path) {
        try {
            wwbCopy = Workbook.createWorkbook(new File(path));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    public void createSheet(String strSheetName, int number) {
        this.strSheetName = strSheetName;
        shSheet = wwbCopy.createSheet(strSheetName, number);
    }

    public void setValueIntoCell(int iColumnNumber, int iRowNumber, Object data) throws WriteException {
        WritableSheet wshTemp = wwbCopy.getSheet(strSheetName);

        if (data instanceof String) {
            Label labTemp = new Label(iColumnNumber, iRowNumber, (String) data);
            wshTemp.addCell(labTemp);
        } else if (data instanceof Double) {
            jxl.write.Number number
                    = new jxl.write.Number(iColumnNumber, iRowNumber, (double) data);
            wshTemp.addCell(number);
        } else if (data instanceof Integer) {
            jxl.write.Number number
                    = new jxl.write.Number(iColumnNumber, iRowNumber, Integer.parseInt(data.toString()));
            wshTemp.addCell(number);
        }

    }

    public void closeFile() {
        try {
            wwbCopy.write();
            wwbCopy.close();
        } catch (IOException | WriteException ex) {
            System.out.println(ex);
        }

    }
}