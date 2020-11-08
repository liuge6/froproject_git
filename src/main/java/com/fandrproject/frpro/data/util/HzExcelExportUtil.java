package com.fandrproject.frpro.data.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMergeCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMergeCells;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

public class HzExcelExportUtil {
    private Workbook _workbook = null;
    private OutputStream _outputStream;
    private Sheet _sheet;
    private CellStyle _oddBodyStyle;
    private CellStyle _evenBodyStyle;
    private int _titleRowIndex = 0;
    private int _dataRowIndex = 0;
    private int _rowColorIndex = 0;
    private Field[] _fields;
    private boolean _merged = false;
    private Drawing _patriarch;
    private boolean _XSSF = false;

    public HzExcelExportUtil(OutputStream outputStream) {
        this._outputStream = outputStream;
        this._workbook = new HSSFWorkbook();
    }

    public HzExcelExportUtil(OutputStream outputStream, boolean XSSF) {
        this._outputStream = outputStream;
        if (XSSF) {
            this._workbook = new XSSFWorkbook();
            this._XSSF = true;
        } else {
            this._workbook = new HSSFWorkbook();
        }

    }

    public Workbook getWorkbook() {
        return this._workbook;
    }

    public Sheet getSheet() {
        return this._sheet;
    }

    public HzExcelExportUtil creatSheet() {
        return this.creatSheet("Sheet1", this._createOddBodyStyle(this._workbook), this._createEvenBodyStyle(this._workbook));
    }

    public HzExcelExportUtil creatSheet(String sheetName) {
        return this.creatSheet(sheetName, (CellStyle)null, (CellStyle)null);
    }

    public HzExcelExportUtil creatSheet(String sheetName, CellStyle oddBodyStyle, CellStyle evenBodyStyle) {
        return this.creatSheet(sheetName, oddBodyStyle, evenBodyStyle, 20, (short)15);
    }

    public HzExcelExportUtil creatSheet(String sheetName, int defaultColWidth, short defaultRowHeight) {
        return this.creatSheet(sheetName, (CellStyle)null, (CellStyle)null, defaultColWidth, defaultRowHeight);
    }

    public HzExcelExportUtil creatSheet(String sheetName, CellStyle oddBodyStyle, CellStyle evenBodyStyle, int defaultColWidth, short defaultRowHeight) {
        this._sheet = this._workbook.createSheet(sheetName);
        this._sheet.setDefaultColumnWidth(defaultColWidth);
        this._sheet.setDefaultRowHeight((short)(defaultRowHeight * 20));
        if (oddBodyStyle == null) {
            oddBodyStyle = this._createOddBodyStyle(this._workbook);
        }

        if (evenBodyStyle == null) {
            evenBodyStyle = this._createEvenBodyStyle(this._workbook);
        }

        this._oddBodyStyle = oddBodyStyle;
        this._evenBodyStyle = evenBodyStyle;
        this._patriarch = this._sheet.createDrawingPatriarch();
        this._titleRowIndex = 0;
        this._dataRowIndex = 0;
        this._rowColorIndex = 0;
        return this;
    }

    public HzExcelExportUtil setColumnWidth(String fieldName, int colWidth) {
        int _colIndex = 0;
        Field[] var4 = this._fields;
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field f = var4[var6];
            if (f.getName().equals(fieldName)) {
                break;
            }

            ++_colIndex;
        }

        this._sheet.setColumnWidth(_colIndex, colWidth * 256);
        return this;
    }

    public HzExcelExportUtil setColumnWidthAuto(int columnIndex) {
        this._sheet.autoSizeColumn(columnIndex);
        return this;
    }

    public HzExcelExportUtil setColumnWidthAuto(String fieldName) {
        int _colIndex = 0;
        Field[] var3 = this._fields;
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Field f = var3[var5];
            if (f.getName().equals(fieldName)) {
                break;
            }

            ++_colIndex;
        }

        this._sheet.autoSizeColumn(_colIndex);
        return this;
    }

    public HzExcelExportUtil setRowHeight(int firstRow, int lastRow, short rowHeight) {
        for(Row row = null; firstRow <= lastRow; ++firstRow) {
            row = this._sheet.getRow(firstRow);
            if (row != null) {
                row.setHeight((short)(rowHeight * 20));
            }
        }

        return this;
    }

    public HzExcelExportUtil setRowHeight(int firstRow, short rowHeight) {
        return this.setRowHeight(firstRow, this._dataRowIndex, rowHeight);
    }

    public HzExcelExportUtil setPassWord(String passWord) {
        this._sheet.protectSheet(passWord);
        return this;
    }

    public HzExcelExportUtil addCellLocked(int firstRow, int lastRow, int firstCol, int lastCol) {
        for(; firstRow <= lastRow; ++firstRow) {
            Row row = this._sheet.getRow(firstRow);
            if (row != null) {
                for(int c = firstCol; c <= lastCol; ++c) {
                    Cell cell = row.getCell(c);
                    cell.getCellStyle().setLocked(true);
                }
            }
        }

        return this;
    }

    public HzExcelExportUtil addCellUnLocked(int firstRow, int lastRow, int firstCol, int lastCol) {
        for(; firstRow <= lastRow; ++firstRow) {
            Row row = this._sheet.getRow(firstRow);
            if (row != null) {
                for(int c = firstCol; c <= lastCol; ++c) {
                    Cell cell = row.getCell(c);
                    cell.getCellStyle().setLocked(false);
                }
            }
        }

        return this;
    }

    public HzExcelExportUtil addTitle(String[] headers) {
        return this.addTitle(headers, this._createHeadStyle(this._workbook));
    }

    public HzExcelExportUtil addTitle(String[] headers, CellStyle headStyle) {
        Row row = this._sheet.createRow(this._titleRowIndex);
        row.setHeight((short)400);
        Cell cell = null;

        for(int i = 0; i < headers.length; ++i) {
            cell = row.createCell(i);
            cell.setCellStyle(headStyle);
            if (this._XSSF) {
                cell.setCellValue(new XSSFRichTextString(headers[i]));
            } else {
                cell.setCellValue(new HSSFRichTextString(headers[i]));
            }
        }

        ++this._titleRowIndex;
        ++this._dataRowIndex;
        return this;
    }

    public HzExcelExportUtil setField(String[] fields, Class<?> clazz) throws NoSuchFieldException {
        return this.setField(HzReflectUtil.fieldFormat(clazz, fields));
    }

    public HzExcelExportUtil setField(Field[] fields) {
        this._fields = fields;
        Field[] var2 = this._fields;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field f = var2[var4];
            if (f.getType() == List.class) {
                this._merged = true;
                break;
            }
        }

        return this;
    }

    public HzExcelExportUtil addData(Collection<?> dataset, HzExportExcelDataFormater exportExcelDataFormater) throws InvocationTargetException, IllegalAccessException {
        Iterator<?> iterator = dataset.iterator();
        Row _row = null;
        short _defaultHeight = this._sheet.getDefaultRowHeight();
        int var6 = 0;

        while(true) {
            while(iterator.hasNext()) {
                ++var6;
                Object t = iterator.next();
                ++this._rowColorIndex;
                if (!this._merged) {
                    _row = this._sheet.createRow(this._dataRowIndex);
                    _row.setHeight(_defaultHeight);
                    this._createCell(_row, t, this._rowColorIndex % 2 == 0 ? this._evenBodyStyle : this._oddBodyStyle, -1, exportExcelDataFormater);
                    ++this._dataRowIndex;
                } else {
                    int _size = 0;
                    Field[] __rows = this._fields;
                    int _col = __rows.length;

                    int j;
                    for(j = 0; j < _col; ++j) {
                        Field f = __rows[j];
                        if (f.getType() == List.class) {
                            f.setAccessible(true);
                            List _list = (List)f.get(t);
                            _size = _list != null && _list.size() > _size ? _list.size() : _size;
                            _list = null;
                        }
                    }

                    if (_size == 0) {
                        _row = this._sheet.createRow(this._dataRowIndex);
                        _row.setHeight(_defaultHeight);
                        this._createCell(_row, t, this._rowColorIndex % 2 == 0 ? this._evenBodyStyle : this._oddBodyStyle, 0, exportExcelDataFormater);
                        ++this._dataRowIndex;
                    } else {
                        for(int i = 0; i < _size; ++i) {
                            _row = this._sheet.createRow(this._dataRowIndex);
                            _row.setHeight(_defaultHeight);
                            this._createCell(_row, t, this._rowColorIndex % 2 == 0 ? this._evenBodyStyle : this._oddBodyStyle, i, exportExcelDataFormater);
                            ++this._dataRowIndex;
                        }
                    }

                    if (_size > 1) {
                        __rows = null;
                        _col = 0;
                        Field[] var18 = this._fields;
                        int var19 = var18.length;

                        for(int var20 = 0; var20 < var19; ++var20) {
                            Field f = var18[var20];
                            if (f.getType() != List.class) {
                                CellRangeAddress range = new CellRangeAddress(_row.getRowNum() - _size + 1, _row.getRowNum(), _col, _col);
                                if (this._XSSF) {
                                    this.addMergedRegion(range, (XSSFSheet)this._sheet);
                                } else {
                                    this._sheet.addMergedRegionUnsafe(range);
                                }
                            }

                            ++_col;
                        }
                    }

                    __rows = null;

                    for(_col = this._dataRowIndex - _size; _col < this._dataRowIndex; ++_col) {
                        Row __rows2 = this._sheet.getRow(_col);

                        for(j = 0; j < this._fields.length; ++j) {
                            __rows2.getCell(j).setCellStyle(this._rowColorIndex % 2 == 0 ? this._evenBodyStyle : this._oddBodyStyle);
                        }
                    }
                }
            }

            return this;
        }
    }

    public HzExcelExportUtil setDownList(String fieldName, String[] dataList) {
        int _colIndex = 0;
        Field[] var4 = this._fields;
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field f = var4[var6];
            if (f.getName().equals(fieldName)) {
                break;
            }

            ++_colIndex;
        }

        ExcelErrorUtil.setCellDownList(this._sheet, 0, this._dataRowIndex, _colIndex, _colIndex, dataList);
        return this;
    }

    public HzExcelExportUtil setDownList(int firstRow, int lastRow, int firstCol, int lastCol, String[] dataList) {
        ExcelErrorUtil.setCellDownList(this._sheet, firstRow, lastRow, firstCol, lastCol, dataList);
        return this;
    }

    public void bulid() throws IOException {
        this._workbook.write(this._outputStream);
        this._workbook.close();
    }

    private void _createCell(Row row, Object t, CellStyle style, int listIndex, HzExportExcelDataFormater exportExcelDataFormater) throws IllegalAccessException {
        Object _objvalue = null;
        Cell _cell = null;

        for(int i = 0; i < this._fields.length && this._fields[i] != null; ++i) {
            _cell = row.createCell(i);
            _cell.setCellStyle(style);
            this._fields[i].setAccessible(true);
            Object value = "";
            if (listIndex > -1 && this._fields[i].getType() == List.class) {
                List _list = (List)this._fields[i].get(t);
                value = _list != null && _list.size() > listIndex ? _list.get(listIndex) : value;
            } else {
                value = this._fields[i].get(t);
            }

            if (value == null) {
                if (exportExcelDataFormater != null) {
                    _objvalue = exportExcelDataFormater.format(this._fields[i], value, t);
                }

                if (_objvalue != null) {
                    if (!(_objvalue instanceof Integer) && !(_objvalue instanceof Short)) {
                        if (!(_objvalue instanceof Float) && !(_objvalue instanceof Double)) {
                            _cell.setCellValue(_objvalue.toString());
                        } else {
                            _cell.setCellValue((Double)_objvalue);
                        }
                    } else {
                        _cell.setCellValue((double)(Integer)_objvalue);
                    }
                }
            } else if (value instanceof byte[]) {
                row.setHeightInPoints(60.0F);
                this._sheet.setColumnWidth(i, 2856);
                byte[] bsValue = (byte[])((byte[])value);
                XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 1023, 255, 6, row.getRowNum(), 6, row.getRowNum());
                anchor.setAnchorType(AnchorType.MOVE_AND_RESIZE);
                this._patriarch.createPicture(anchor, this._workbook.addPicture(bsValue, 5));
            } else if (exportExcelDataFormater != null) {
                _objvalue = exportExcelDataFormater.format(this._fields[i], value, t);
                this._formatData(_cell, _objvalue);
            } else {
                this._formatData(_cell, value);
            }
        }

    }

    private void _formatData(Cell cell, Object value) {
        if (value instanceof Timestamp) {
            cell.setCellValue((new DateTime(value)).toString("yyyy-MM-dd HH:mm:ss"));
        } else if (value instanceof Time) {
            cell.setCellValue((new DateTime(value)).toString("HH:mm:ss"));
        } else if (value instanceof Date) {
            cell.setCellValue((new DateTime(value)).toString("yyyy-MM-dd"));
        } else if (value instanceof Integer) {
            cell.setCellValue((double)(Integer)value);
        } else if (value instanceof Long) {
            cell.setCellValue((double)(Long)value);
        } else if (value instanceof Short) {
            cell.setCellValue((double)(Short)value);
        } else if (!(value instanceof Double) && !(value instanceof Float)) {
            if (this._XSSF) {
                cell.setCellValue(new XSSFRichTextString((String)value));
            } else {
                cell.setCellValue(new HSSFRichTextString((String)value));
            }
        } else {
            cell.setCellValue((Double)value);
        }

    }

    private CellStyle _createHeadStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.SEA_GREEN.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font headFont = workbook.createFont();
        headFont.setColor(IndexedColors.BLACK.index);
        headFont.setFontHeightInPoints((short)11);
        headFont.setBold(true);
        cellStyle.setFont(headFont);
        return cellStyle;
    }

    private CellStyle _createOddBodyStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        Font bodyFont = workbook.createFont();
        bodyFont.setBold(false);
        cellStyle.setFont(bodyFont);
        return cellStyle;
    }

    private CellStyle _createEvenBodyStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        Font bodyFont = workbook.createFont();
        bodyFont.setBold(false);
        cellStyle.setFont(bodyFont);
        return cellStyle;
    }

    private void addMergedRegion(CellRangeAddress region, XSSFSheet sheet) {
        CTWorksheet ctWorksheet = sheet.getCTWorksheet();
        CTMergeCells ctMergeCells = ctWorksheet.isSetMergeCells() ? ctWorksheet.getMergeCells() : ctWorksheet.addNewMergeCells();
        CTMergeCell ctMergeCell = ctMergeCells.addNewMergeCell();
        ctMergeCell.setRef(region.formatAsString());
    }
}

