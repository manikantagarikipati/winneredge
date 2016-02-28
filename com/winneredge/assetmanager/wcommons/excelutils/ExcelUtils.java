package com.winneredge.assetmanager.wcommons.excelutils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.winneredge.assetmanager.wcommons.database.DBConstants;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manikanta on 2/28/2016.
 */
public class ExcelUtils {

    public static boolean saveExcelFile(Context context, String fileName,String sheetName) {

        // check if available and not read only
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Log.e("Excel Utils", "Storage not available or read only");
            return false;
        }

        boolean success = false;

        //New Workbook
        Workbook wb = new HSSFWorkbook();

        Cell c = null;

        //Cell style for header row
        CellStyle cs = wb.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.LIME.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet(sheetName);

        List<String> columnNames = getColumnNames();
        for(int i=0;i<columnNames.size();i++){
            Row row = sheet1.createRow(i);

            c = row.createCell(i);
            c.setCellValue(columnNames.get(i));
            c.setCellStyle(cs);
            sheet1.setColumnWidth(i, (15 * 500));
        }

        // Create a path where we will place our List of objects on external storage
        File file = new File(context.getExternalFilesDir(null), fileName);
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(file);
            wb.write(os);
            Log.w("FileUtils", "Writing file" + file);
            success = true;
        } catch (IOException e) {
            Log.w("FileUtils", "Error writing " + file, e);
        } catch (Exception e) {
            Log.w("FileUtils", "Failed to save file", e);
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
            }
        }
        return success;
    }

    private static List<String> getColumnNames() {
        List<String> columnNames = new ArrayList<>();
        columnNames.add(DBConstants.ASSET_BARCODE);
        columnNames.add(DBConstants.ASSET_NAME);
        columnNames.add(DBConstants.ASSET_COMMENTS);
        return columnNames;
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState));
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(extStorageState);
    }

}
