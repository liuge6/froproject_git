package com.fandrproject.frpro.data.util;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.*;

/**
 * Created by sml
 * 2020/09/19 23:39
 */
public class ExcelErrorUtil {
    static Map<String, CellStyle> styleMap = new HashMap();

    ExcelErrorUtil() {
    }

    public static void initStyle(Workbook targetWorkbook) {
        styleMap.clear();
        CellStyle _style = targetWorkbook.createCellStyle();
        setCellStyle(_style);
        styleMap.put("normal", _style);
        _style = targetWorkbook.createCellStyle();
        setErrorCellStyle(targetWorkbook, _style);
        styleMap.put("error", _style);
        _style = targetWorkbook.createCellStyle();
        setHeadStyle(_style);
        styleMap.put("head", _style);
    }

    public static void copyRow(Workbook workbook, Sheet sheet, Row fromRow, Row toRow, int moveCellNum, HzExcelErrorMsg cellErrorMsg, boolean head, boolean copyValueFlag) {
        Iterator<Cell> cellIt = fromRow.cellIterator();

        for(int _i = 0; cellIt.hasNext(); ++_i) {
            Cell _oldCell = (Cell)cellIt.next();
            Cell _newCell = toRow.createCell(_oldCell.getColumnIndex() + moveCellNum);
            if (cellErrorMsg != null && cellErrorMsg.getCellIndex() >= 0 && cellErrorMsg.getCellIndex() == _i) {
                copyCell(workbook, sheet, _oldCell, _newCell, head, cellErrorMsg, copyValueFlag);
            } else {
                copyCell(workbook, sheet, _oldCell, _newCell, head, (HzExcelErrorMsg)null, copyValueFlag);
            }

            _oldCell = null;
            _newCell = null;
        }

    }

    public static void copyCell(Workbook workbook, Sheet sheet, Cell fromCell, Cell toCell, boolean head, HzExcelErrorMsg cellErrorMsg, boolean copyValueFlag) {
        if (head) {
            toCell.setCellStyle((CellStyle)styleMap.get("head"));
        } else if (cellErrorMsg != null) {
            toCell.setCellStyle((CellStyle)styleMap.get("error"));
        } else {
            toCell.setCellStyle((CellStyle)styleMap.get("normal"));
        }

        if (fromCell.getCellComment() != null) {
            toCell.setCellComment(fromCell.getCellComment());
        } else if (cellErrorMsg != null) {
            addComment(workbook, sheet, toCell, cellErrorMsg.getCellExpMsg());
        }

        toCell.setCellType(fromCell.getCellTypeEnum());
        if (copyValueFlag) {
            switch(fromCell.getCellTypeEnum()) {
                case STRING:
                    toCell.setCellValue(fromCell.getRichStringCellValue());
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(fromCell)) {
                        toCell.setCellValue(fromCell.getDateCellValue());
                    } else {
                        toCell.setCellValue(fromCell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    toCell.setCellValue(fromCell.getBooleanCellValue());
                    break;
                case BLANK:
                    toCell.setCellValue("");
                    break;
                case ERROR:
                    toCell.setCellValue((double)fromCell.getErrorCellValue());
                    break;
                case FORMULA:
                    toCell.setCellValue(fromCell.getCellFormula());
            }
        }

    }

    public static void copyCellStyle(CellStyle fromStyle, CellStyle toStyle) {
        toStyle.setAlignment(fromStyle.getAlignmentEnum());
        toStyle.setBorderBottom(fromStyle.getBorderBottomEnum());
        toStyle.setBorderLeft(fromStyle.getBorderLeftEnum());
        toStyle.setBorderRight(fromStyle.getBorderRightEnum());
        toStyle.setBorderTop(fromStyle.getBorderTopEnum());
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());
        toStyle.setDataFormat(fromStyle.getDataFormat());
        toStyle.setFillPattern(fromStyle.getFillPatternEnum());
        toStyle.setHidden(fromStyle.getHidden());
        toStyle.setIndention(fromStyle.getIndention());
        toStyle.setLocked(fromStyle.getLocked());
        toStyle.setRotation(fromStyle.getRotation());
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignmentEnum());
        toStyle.setWrapText(fromStyle.getWrapText());
    }

    public static void setHeadStyle(CellStyle toStyle) {
        toStyle.setAlignment(HorizontalAlignment.CENTER);
        toStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        toStyle.setBorderBottom(BorderStyle.THIN);
        toStyle.setBorderLeft(BorderStyle.THIN);
        toStyle.setBorderRight(BorderStyle.THIN);
        toStyle.setBorderTop(BorderStyle.THIN);
        toStyle.setTopBorderColor(IndexedColors.BLACK.index);
        toStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        toStyle.setRightBorderColor(IndexedColors.BLACK.index);
        toStyle.setLeftBorderColor(IndexedColors.BLACK.index);
        toStyle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.index);
        toStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        toStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        toStyle.setWrapText(true);
    }

    public static void setCellStyle(CellStyle toStyle) {
        toStyle.setAlignment(HorizontalAlignment.CENTER);
        toStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        toStyle.setBorderBottom(BorderStyle.THIN);
        toStyle.setBorderLeft(BorderStyle.THIN);
        toStyle.setBorderRight(BorderStyle.THIN);
        toStyle.setBorderTop(BorderStyle.THIN);
        toStyle.setTopBorderColor(IndexedColors.BLACK.index);
        toStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        toStyle.setRightBorderColor(IndexedColors.BLACK.index);
        toStyle.setLeftBorderColor(IndexedColors.BLACK.index);
        toStyle.setFillBackgroundColor(IndexedColors.WHITE.index);
        toStyle.setFillForegroundColor(IndexedColors.WHITE.index);
        toStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        toStyle.setWrapText(true);
    }

    public static void setErrorCellStyle(Workbook workbook, CellStyle toStyle) {
        toStyle.setAlignment(HorizontalAlignment.CENTER);
        toStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        toStyle.setBorderBottom(BorderStyle.THIN);
        toStyle.setBorderLeft(BorderStyle.THIN);
        toStyle.setBorderRight(BorderStyle.THIN);
        toStyle.setBorderTop(BorderStyle.THIN);
        toStyle.setTopBorderColor(IndexedColors.BLACK.index);
        toStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        toStyle.setRightBorderColor(IndexedColors.BLACK.index);
        toStyle.setLeftBorderColor(IndexedColors.BLACK.index);
        toStyle.setFillBackgroundColor(IndexedColors.RED.index);
        toStyle.setFillForegroundColor(IndexedColors.RED.index);
        toStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        toStyle.setWrapText(true);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.BLACK.index);
        toStyle.setFont(font);
    }

    public static void addComment(Workbook workbook, Sheet sheet, Cell cell, String comment) {
        Drawing<?> drw = sheet.createDrawingPatriarch();
        Comment cmt;
        if (workbook instanceof XSSFWorkbook) {
            cmt = drw.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, 3, 3, 5, 6));
            cmt.setVisible(false);
            cmt.setString(new XSSFRichTextString(comment));
            cell.setCellComment(cmt);
        } else {
            cmt = drw.createCellComment(new HSSFClientAnchor(0, 0, 0, 0, (short)3, 3, (short)5, 6));
            cmt.setVisible(false);
            cmt.setString(new HSSFRichTextString(comment));
            cell.setCellComment(cmt);
        }

        drw = null;
    }

    public static void insertCell(Workbook targetWorkbook, Sheet targetSheet, int rowIndex, int cellIndex, String cellValue, boolean head, int mergedRowNum) {
        Row row = targetSheet.getRow(rowIndex);
        Cell _newCell = row.createCell(cellIndex);
        _newCell.setCellValue(cellValue);
        if (head) {
            _newCell.setCellStyle((CellStyle)styleMap.get("head"));
        } else {
            _newCell.setCellStyle((CellStyle)styleMap.get("normal"));
        }

        if (mergedRowNum > 0 && targetSheet != null) {
            CellRangeAddress _range = new CellRangeAddress(row.getRowNum(), row.getRowNum() + mergedRowNum - 1, cellIndex, cellIndex);
            targetSheet.addMergedRegion(_range);
        }

    }

    public static void mergedRows(Sheet targetSheet, Sheet sourceSheet, int targetFirstRowNum, int targetEndRowNum, int sourceFirstRowNum, int sourceEndRowNum, int cellMoveNum) {
        int _firstSub = sourceFirstRowNum - targetFirstRowNum;
        int _endSub = sourceEndRowNum - targetEndRowNum;

        for(List valids = sourceSheet.getDataValidations(); targetFirstRowNum < targetEndRowNum; ++targetFirstRowNum) {
            Row _row = sourceSheet.getRow(sourceFirstRowNum);
            Iterator<Cell> cellIt = _row.cellIterator();

            for(int _i = 0; cellIt.hasNext(); ++_i) {
                Cell cell = (Cell)cellIt.next();
                if (!isMergedRegion(targetSheet, targetFirstRowNum, _i + cellMoveNum)) {
                    CellRangeAddress _sourceRange = getRangeAddress(sourceSheet, sourceFirstRowNum, _i);
                    if (_sourceRange != null) {
                        CellRangeAddress _targetRange = new CellRangeAddress(_sourceRange.getFirstRow() - _firstSub, _sourceRange.getLastRow() - _endSub, _sourceRange.getFirstColumn() + cellMoveNum, _sourceRange.getLastColumn() + cellMoveNum);
                        Map<String, Object> _descMap = _getCellDescData(_targetRange, _sourceRange, targetSheet, sourceSheet, valids);
                        targetSheet.addMergedRegion(_targetRange);
                        if (_descMap != null && _descMap.size() > 0) {
                            Cell _cell = targetSheet.getRow(_targetRange.getFirstRow()).getCell(_targetRange.getFirstColumn());
                            if (_descMap.containsKey("c")) {
                                _cell.setCellComment((Comment)_descMap.get("c"));
                            }

                            if (_descMap.containsKey("s")) {
                                _cell.setCellStyle((CellStyle)_descMap.get("s"));
                            }

                            if (_descMap.containsKey("v")) {
                                setCellDownList(targetSheet, _targetRange.getFirstRow(), _targetRange.getLastRow(), _targetRange.getFirstColumn(), _targetRange.getLastColumn(), (String[])((String[])_descMap.get("v")));
                            }
                        }

                        _targetRange = null;
                    }

                    _sourceRange = null;
                }
            }

            ++sourceFirstRowNum;
        }

    }

    private static Map<String, Object> _getCellDescData(CellRangeAddress targetRange, CellRangeAddress sourceRange, Sheet targetSheet, Sheet sourceSheet, List<? extends DataValidation> valids) {
        Map<String, Object> obj = new HashMap();
        Comment cmt = null;

        int r;
        Cell _cell;
        for(r = targetRange.getFirstRow(); r <= targetRange.getLastRow(); ++r) {
            _cell = targetSheet.getRow(r).getCell(targetRange.getFirstColumn());
            cmt = _cell.getCellComment();
            if (cmt != null) {
                obj.put("c", cmt);
            }

            if (_cell.getCellStyle() != null) {
                obj.put("s", _cell.getCellStyle());
            }
        }

        for(r = sourceRange.getFirstRow(); r <= sourceRange.getLastRow(); ++r) {
            _cell = sourceSheet.getRow(r).getCell(sourceRange.getFirstColumn());
            Iterator var9 = valids.iterator();

            while(var9.hasNext()) {
                DataValidation validation = (DataValidation)var9.next();
                CellRangeAddressList addressList = validation.getRegions();
                if (null != addressList && addressList.getSize() != 0) {
                    CellRangeAddress[] addresses = addressList.getCellRangeAddresses();
                    CellRangeAddress[] var13 = addresses;
                    int var14 = addresses.length;

                    for(int var15 = 0; var15 < var14; ++var15) {
                        CellRangeAddress _range = var13[var15];
                        if (_range.getFirstRow() <= r && r <= _range.getLastRow() && _range.getFirstColumn() <= sourceRange.getFirstColumn() && sourceRange.getFirstColumn() <= _range.getLastColumn()) {
                            obj.put("v", validation.getValidationConstraint().getExplicitListValues());
                            break;
                        }
                    }

                    if (obj.containsKey("v")) {
                        break;
                    }
                }
            }

            if (obj.containsKey("v")) {
                break;
            }
        }

        return obj.size() == 0 ? null : obj;
    }

    public static boolean isMergedRegion(Sheet sheet, int rowIdx, int cellIdx) {
        int _sheetRegionNum = sheet.getNumMergedRegions();

        for(int i = 0; i < _sheetRegionNum; ++i) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            if (rowIdx >= range.getFirstRow() && rowIdx <= range.getLastRow() && cellIdx >= range.getFirstColumn() && cellIdx <= range.getLastColumn()) {
                return true;
            }
        }

        return false;
    }

    public static void setCellDownList(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol, String[] dataList) {
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        DataValidationHelper helper = sheet.getDataValidationHelper();
        DataValidationConstraint validCont = helper.createExplicitListConstraint(dataList);
        DataValidation dataValidation = helper.createValidation(validCont, addressList);
        if (dataValidation instanceof XSSFDataValidation) {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        } else {
            dataValidation.setSuppressDropDownArrow(false);
        }

        sheet.addValidationData(dataValidation);
    }

    public static CellRangeAddress getRangeAddress(Sheet sheet, int rowIdx, int cellIdx) {
        int _sheetRegionNum = sheet.getNumMergedRegions();

        for(int i = 0; i < _sheetRegionNum; ++i) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            if (rowIdx >= range.getFirstRow() && rowIdx <= range.getLastRow() && cellIdx >= range.getFirstColumn() && cellIdx <= range.getLastColumn()) {
                return range;
            }
        }

        return null;
    }

    public static Map<Integer, HzExcelErrorMsg> sortMap(Map<Integer, HzExcelErrorMsg> map) {
        if (map != null && map.size() != 0) {
            Map<Integer, HzExcelErrorMsg> _sortmap = new TreeMap((Map) new MapKeyComparator());
            _sortmap.putAll(map);
            return _sortmap;
        } else {
            return null;
        }
    }
}
