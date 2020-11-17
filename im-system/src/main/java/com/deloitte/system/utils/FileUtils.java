package com.deloitte.system.utils;

import com.deloitte.common.utils.StringUtils;
import com.deloitte.framework.exception.BusinessException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件处理工具类
 *
 * @author p&s
 */
public class FileUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 判断文件是不是excel
     *
     * @param fileName
     */
    public static void isExcel(String fileName) {
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        Boolean xlsFlag = StringUtils.equals(fileType, ".xls");
        Boolean xlsxFlag = StringUtils.equals(fileType, ".xlsx");
        if (xlsFlag == false && xlsxFlag == false) {
            throw new BusinessException("请上传excel文件!");
        }
    }

    /**
     * 文件下载
     *
     * @param fileName
     * @param request
     * @param response
     */
    public static void downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response) {

        try {
            logger.info("文件名称fileName为:{}", fileName);
            InputStream inputStream = FileUtils.class.getResourceAsStream("/static/excel/" + URLDecoder.decode(fileName, "utf-8"));
            OutputStream out = response.getOutputStream();

            fileName = URLEncoder.encode(fileName, "UTF-8");
//            response.setContentType("application/force-download");// 设置强制下载不打开
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
//            response.setHeader("Content-Length", String.valueOf(inputStream.available()));

            int b = 0;
            byte[] buffer = new byte[1024];
            while ((b = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, b);
            }
            inputStream.close();
            if (out != null) {
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            logger.error("文件：{}下载失败:{}", fileName, e.toString());
            throw new RuntimeException("文件下载失败！");
        }
    }

    /**
     * 读取excel
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static List<List<String>> readXlsx(InputStream inputStream, int startRow) {
        List<List<String>> result = new ArrayList<List<String>>();
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            // 循环每一页，并处理当前循环页
            for (Sheet xssfSheet : xssfWorkbook) {
                if (xssfSheet == null) {
                    continue;
                }
                // 处理当前页，循环读取每一行
                int minColIx = xssfSheet.getRow(0).getFirstCellNum();
                int maxColIx = xssfSheet.getRow(0).getLastCellNum();
                for (int rowNum = startRow; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                    Row xssfRow = xssfSheet.getRow(rowNum);
                    List<String> rowList = new ArrayList<String>();
                    if (xssfRow != null) {
                        for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                            Cell cell = xssfRow.getCell(colIx);
                            // 单元格为空时
                            if (cell == null) {
                                rowList.add("");
                            } else {
                                cell.setCellType(CellType.STRING);
                                rowList.add(cell.toString());
                            }
                        }
                        result.add(rowList);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("excel文件导入失败:{}", e.toString());
        }
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     * @param fileName
     * @param wb       excel对象
     */
    public static void writeXlsx(HttpServletRequest request, HttpServletResponse response, String fileName, XSSFWorkbook wb) {
        try {
            // 解决文件乱码
            final String userAgent = request.getHeader("user-agent");
            if (userAgent.indexOf("Edge") > 0) {
                fileName = URLEncoder.encode(fileName, "UTF8"); // 其他浏览器

            } else {
                if (userAgent != null && userAgent.indexOf("Firefox") >= 0
                        || userAgent.indexOf("Chrome") >= 0 || userAgent.indexOf("Safari") >= 0) {
                    fileName = new String(fileName.getBytes(), "ISO8859-1");
                } else {
                    fileName = URLEncoder.encode(fileName, "UTF8"); // 其他浏览器
                }
            }
            response.reset();// 清除首部的空白行
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Cache-Control", "no-cache");
            response.flushBuffer();
            final OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }


}
