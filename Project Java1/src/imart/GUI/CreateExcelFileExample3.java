/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imart.GUI;

/**
 *
 * @author Admin'
 */
import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class CreateExcelFileExample3 {
  public static void main(String[] args) {
    try {
      //khai báo tên file muốn tạo
      String filename = "E:\\Excel3.xlsx";
      //tạo một đối tượng của lớp HSSFWorkbook
      HSSFWorkbook workbook = new HSSFWorkbook();
      //gọi phương thức creatSheet() và truyền tên file muốn tạo
      HSSFSheet sheet = workbook.createSheet("January");
      //tạo hàng thứ 0 sử dụng phương thức createRow()
      HSSFRow rowhead = sheet.createRow((short) 0);
      //tạo ô bằng cách sử dụng phương thức createCell() và thiết lập giá trị cho ô bằng cách sử dụng phương thức setCellValue()
      rowhead.createCell(0).setCellValue("S.No.");
      rowhead.createCell(1).setCellValue("Customer Name");
      rowhead.createCell(2).setCellValue("Account Number");
      rowhead.createCell(3).setCellValue("e-mail");
      rowhead.createCell(4).setCellValue("Balance");
      //tạo hàng thứ 1
      HSSFRow row = sheet.createRow((short) 1);
      //chèn dữ liệu vào hàng thứ 1
      row.createCell(0).setCellValue("1");
      row.createCell(1).setCellValue("John William");
      row.createCell(2).setCellValue("9999999");
      row.createCell(3).setCellValue("william.john@gmail.com");
      row.createCell(4).setCellValue("700000.00");
      //tạo hàng thứ 2
      HSSFRow row1 = sheet.createRow((short) 2);
      //chèn dữ liệu vào hàng thứ 2
      row1.createCell(0).setCellValue("2");
      row1.createCell(1).setCellValue("Mathew Parker");
      row1.createCell(2).setCellValue("22222222");
      row1.createCell(3).setCellValue("parker.mathew@gmail.com");
      row1.createCell(4).setCellValue("200000.00");
      FileOutputStream fileOut = new FileOutputStream(filename);
      workbook.write(fileOut);
      //đóng stream
      fileOut.close();
      //đóng workbook
      workbook.close();
      //in thông báo tạo thành công
      System.out.println("File Excel đã được tạo thành công.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}