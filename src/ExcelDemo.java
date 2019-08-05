import org.apache.poi.hssf.usermodel.*;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExcelDemo {

    //将英文周几转化为中文
    private static String week2String(String str) {
        if ("mon".equalsIgnoreCase(str) || "星期一".equalsIgnoreCase(str)) {
            return "周一";
        }
        else if ("tue".equalsIgnoreCase(str) || "星期二".equalsIgnoreCase(str)) {
            return "周二";
        }
        else if ("wed".equalsIgnoreCase(str) || "星期三".equalsIgnoreCase(str)) {
            return "周三";
        }
        else if ("thu".equalsIgnoreCase(str) || "星期四".equalsIgnoreCase(str)) {
            return "周四";
        }
        else if ("fri".equalsIgnoreCase(str) || "星期五".equalsIgnoreCase(str)) {
            return "周五";
        }
        else if ("sat".equalsIgnoreCase(str) || "星期六".equalsIgnoreCase(str)) {
            return "周六";
        }
        else if ("sun".equalsIgnoreCase(str) || "星期日".equalsIgnoreCase(str)) {
            return "周日";
        }
        else {
            return "";
        }
    }

    //获取某月共有几天
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    //返回周几
    private static String getWeekday(String date) {//必须yyyy-MM-dd
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdw = new SimpleDateFormat("E");
        Date d = null;
        try {
            d = sd.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return week2String(sdw.format(d));
    }

    //居中
    private static void centralCell(Workbook workbook, Row row, int days) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 1; i <= days; i++) {
            Cell cell = row.getCell(i + 1);
            setBorder(cellStyle);
            cell.setCellStyle(cellStyle);
        }
    }

    //字体加粗 并带框
    private static void BlackFont(Workbook workbook, Sheet sheet, int rowS, int rowE, int column) {

        CellStyle cellStyle = workbook.createCellStyle();
        HSSFFont fontStyle = (HSSFFont) workbook.createFont();
        fontStyle.setFontHeightInPoints((short) 10);//字号

        fontStyle.setFontName("黑体");
        cellStyle.setFont(fontStyle);

        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int row = rowS; row <= rowE; row++) {
            if (sheet.getRow(row).getCell(column) != null) {
                Cell cell = sheet.getRow(row).getCell(column);
                setBorder(cellStyle);        //全粗
                cell.setCellStyle(cellStyle);
            }

        }
    }


    //设置边框
    public static void setBorder(CellStyle cellStyle) {
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

    }
    //
    public static  void setBgColor(Sheet sheet,HSSFCellStyle style, int days,int row){
        for (int column = 2; column <= days + 1; column++) {
            Row rowD = sheet.getRow(row);
            HSSFCell cell = (HSSFCell) rowD.createCell(column);
            setBorder(style);
            cell.setCellStyle(style);
        }
    }


    //合并单元格
    public static void cellRangeAddress(int rowS, int rowE, int columnS, int columE, Sheet sheet) {
        CellRangeAddress cra = new CellRangeAddress(rowS, rowE, columnS, columE);//起始行，终止行，其实列，终止列
        sheet.addMergedRegion(cra);
    }


    public static void createExcel(String year, String month) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        int days = getDaysOfMonth(sdf.parse(year + month));

        HSSFWorkbook workbook = new HSSFWorkbook();
        //FileOutputStream fileOutputStream = new FileOutputStream("/Users/charwayh/JAVA_Project/MVCTest/web/res/住宿" + month + "月份.xls");

        //腾讯云文件位置
        FileOutputStream fileOutputStream = new FileOutputStream("/usr/local/apache-tomcat-8.5.37/webapps/WebTest/res/" + month + "Month.xls");
        Sheet sheet = workbook.createSheet("第一页"); //创建sheet


        //赋值
        //1行
        Row row1 = sheet.createRow(0); //创建一行
        row1.createCell(1).setCellValue(year + "年" + month + "月住宿登记表");  //创建一列，即为一个单元格
        //2行
        Row row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("金浦花园");
        row2.createCell(1).setCellValue("日期");
        for (int i = 1; i <= days; i++) {
            if (month.length() < 2) {
                month = "0" + month;
            }
            row2.createCell(i + 1).setCellValue(getWeekday(year + month + i));
            HSSFCellStyle setBorder = workbook.createCellStyle();

            row2.getCell(i + 1).setCellStyle(setBorder);

        }
        //3行
        Row row3 = sheet.createRow(2);
        row3.createCell(0).setCellValue("房间名");
        row3.createCell(1).setCellValue("床号");
        for (int i = 1; i <= days; i++) {
            row3.createCell(i + 1).setCellValue(i);
        }
        //4行
        Row row4 = sheet.createRow(3);
        row4.createCell(0).setCellValue("纽约");
        row4.createCell(1).setCellValue("四人间1号床");
        //4行~7行
        for (int i = 2; i <= 4; i++) {
            sheet.createRow(i + 2).createCell(1).setCellValue("四人间" + i + "号床");
        }
        //8行
        Row row8 = sheet.createRow(7);
        row8.createCell(0).setCellValue("巴黎");
        row8.createCell(1).setCellValue("三人间1号床");
        //9行
        sheet.createRow(8).createCell(1).setCellValue("三人间2号床");
        //10行
        sheet.createRow(9).createCell(1).setCellValue("三人间3号床");
        //11行
        Row row11 = sheet.createRow(10);
        row11.createCell(0).setCellValue("伦敦");
        row11.createCell(1).setCellValue("两人间1号床");
        //12行
        sheet.createRow(11).createCell(1).setCellValue("两人间2号床");
        //13行
        Row row13 = sheet.createRow(12);
        row13.createCell(0).setCellValue("米兰");
        row13.createCell(1).setCellValue("单人间");
        //14行
        Row row14 = sheet.createRow(13);
        row14.createCell(0).setCellValue("厅");
        row14.createCell(1).setCellValue("沙发床");
        //15行
        sheet.createRow(14).createCell(1).setCellValue("T1");
        //16行
        sheet.createRow(15).createCell(1).setCellValue("T2");
        //17行
        sheet.createRow(16).createCell(2).setCellValue("以上表格是金浦花园小区电梯房8楼，兰色区域是四人间一床75/天，紫色区域是三人间一床80/天，绿色区域是伦敦两人间一床/70/天，粉色区域是米兰单人间一床100/天，白色区域是厅两人间一床70/天，另外煮饭费是每人每天3元，要煮饭的自觉发煮饭费给管理员否则发现了按三倍收取煮饭费");


        //19行
        sheet.createRow(18).createCell(0).setCellValue("备注：金浦花园地址南泉路1261弄，靠近蓝村路，电梯房,价格米兰房间100元/天，伦敦房间一床70元/天，巴黎房间一床80元/天，纽约房间一床75元/天");

        //字体居中
        centralCell(workbook, row2, days);
        centralCell(workbook, row3, days);

        //字体加粗
        BlackFont(workbook, sheet, 1, 15, 1);
        BlackFont(workbook, sheet, 1, 15, 0);


        //合并单元格
        cellRangeAddress(3, 6, 0, 0, sheet);
        cellRangeAddress(7, 9, 0, 0, sheet);
        cellRangeAddress(10, 11, 0, 0, sheet);
        cellRangeAddress(13, 15, 0, 0, sheet);

        //HSSFCellStyle style = (HSSFCellStyle) workbook.createCellStyle();

        //设置背景颜色 4-7行
        HSSFCellStyle style1 = workbook.createCellStyle();
        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style1.setFillForegroundColor(HSSFColor.ROSE.index);
        for (int row = 3; row <= 6; row++) {
            setBgColor(sheet,style1,days,row);
        }


        //设置背景颜色 8-10行
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        for (int row = 7; row <= 9; row++) {
            setBgColor(sheet,style2,days,row);
        }


        //设置背景颜色 11-12行
        HSSFCellStyle style3 = workbook.createCellStyle();
        style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style3.setFillForegroundColor(HSSFColor.TAN.index);
        for (int row = 10; row <= 11; row++) {
            setBgColor(sheet,style3,days,row);
        }


        //设置背景颜色 13行 33行
        HSSFCellStyle style4 = workbook.createCellStyle();
        style4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style4.setFillForegroundColor(HSSFColor.SEA_GREEN.index);
        for (int column = 2; column <= days + 1; column++) {
            Row rowD = sheet.getRow(12);
            HSSFCell cell = (HSSFCell) rowD.createCell(column);
            setBorder(style4);
            cell.setCellStyle(style4);
        }


        //设置背景颜色 14-16行
        HSSFCellStyle style5 = workbook.createCellStyle();
        style5.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style5.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
        for (int row = 13; row <= 15; row++) {
            setBgColor(sheet,style5,days,row);
        }

        //设置字体
        Font fontstyle = workbook.createFont();
        fontstyle.setColor(HSSFColor.PALE_BLUE.index);
        fontstyle.setFontHeightInPoints((short) 18);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(fontstyle);
        sheet.getRow(0).getCell(1).setCellStyle(cellStyle);

        Font redstyle = workbook.createFont();
        redstyle.setColor(HSSFColor.RED.index);
        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setFont(redstyle);
        sheet.getRow(16).getCell(2).setCellStyle(cellStyle2);

        //16行边框补全
        CellStyle cellStyle1 = workbook.createCellStyle();
        setBorder(cellStyle1);
        sheet.getRow(15).createCell(0).setCellStyle(cellStyle1);



        sheet.autoSizeColumn(1, true); //自适应单元格宽高
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    public static void main(String[] args) throws Exception {
        String year = "2019";
        String month = "1";
        createExcel(year, month);

    }
}
