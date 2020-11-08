package com.fandrproject.frpro.data.util;

/**
 * Created by sml
 * 2020/09/19 23:41
 */
public class HzExcelErrorMsg {
    private int cellIndex;
    private String cellExpMsg;

    public HzExcelErrorMsg(int cellIndex, String msg) {
        this.cellIndex = cellIndex;
        this.cellExpMsg = msg;
    }

    public int getCellIndex() {
        return this.cellIndex;
    }

    public void setCellIndex(int cellIndex) {
        this.cellIndex = cellIndex;
    }

    public String getCellExpMsg() {
        return this.cellExpMsg;
    }

    public void setCellExpMsg(String cellExpMsg) {
        this.cellExpMsg = cellExpMsg;
    }
}
