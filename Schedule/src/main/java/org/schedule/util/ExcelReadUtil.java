package org.schedule.util;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelReadUtil {

     private boolean isMergedRegion(Sheet sheet, int row, int column) {
             int sheetMergeCount = sheet.getNumMergedRegions();
                  for (int i = 0; i < sheetMergeCount; i++) {
                        CellRangeAddress range = sheet.getMergedRegion(i);
                        int firstColumn = range.getFirstColumn();
                        int lastColumn = range.getLastColumn();
                        int firstRow = range.getFirstRow();
                        int lastRow = range.getLastRow();
                        if (row >= firstRow && row <= lastRow) {
                               if (column >= firstColumn && column <= lastColumn) {
					return true;
                               }
                        }
                  }
                  return false;
     }

     public String getMergedRegionValue(Sheet sheet, int row, int column) {
             int sheetMergeCount = sheet.getNumMergedRegions();

                  for (int i = 0; i < sheetMergeCount; i++) {
                        CellRangeAddress ca = sheet.getMergedRegion(i);
                        int firstColumn = ca.getFirstColumn();
                        int lastColumn = ca.getLastColumn();
                        int firstRow = ca.getFirstRow();
                        int lastRow = ca.getLastRow();

                        if (row >= firstRow && row <= lastRow) {

                               if (column >= firstColumn && column <= lastColumn) {
                                      Row fRow = sheet.getRow(firstRow);
                                      Cell fCell = fRow.getCell(firstColumn);
                                      return getCell(fCell);
                               }
                        }
                  }

                  return null;
     }

     public Date getMergedRegionValueDate(Sheet sheet, int row, int column) {
             int sheetMergeCount = sheet.getNumMergedRegions();

                  for (int i = 0; i < sheetMergeCount; i++) {
                        CellRangeAddress ca = sheet.getMergedRegion(i);
                        int firstColumn = ca.getFirstColumn();
                        int lastColumn = ca.getLastColumn();
                        int firstRow = ca.getFirstRow();
                        int lastRow = ca.getLastRow();

                        if (row >= firstRow && row <= lastRow) {

                               if (column >= firstColumn && column <= lastColumn) {
                                      Row fRow = sheet.getRow(firstRow);
                                      Cell fCell = fRow.getCell(firstColumn);
                                      return fCell.getDateCellValue();
                               }
                        }
                  }

                  return null;
     }

     public String getCell(org.apache.poi.ss.usermodel.Cell cell) {

                  if (cell == null)
                        return "";
                  switch (cell.getCellType()) {
                  case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
                        return cell.getNumericCellValue() + "";
                  case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
                        return cell.getStringCellValue();
                  case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA:
                        return cell.getCellFormula();
                  case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK:
                        return "";
                  case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
                        return cell.getBooleanCellValue() + "";
                  case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR:
                        return cell.getErrorCellValue() + "";
                  }
                  return "";
     }

     public String getMergeOr(Sheet sheet, int i, Cell c, Row row) {
             boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());
                  // 判断是否具有合并单元格
                  if (isMerge) {
                        String rs = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
                        return rs;
                  } else {
                        return getCell(c);
                  }
     }

     public Date getMergeOrDate(Sheet sheet, int i, Cell c, Row row) {
             boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());
                  // 判断是否具有合并单元格
                  if (isMerge) {
                        Date rs = getMergedRegionValueDate(sheet, row.getRowNum(), c.getColumnIndex());
                        return rs;
                  } else {
                        return c.getDateCellValue();
                  }
     }
}
