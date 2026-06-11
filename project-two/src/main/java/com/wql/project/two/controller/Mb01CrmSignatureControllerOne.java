package com.wql.project.two.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.wql.project.one.vo.Mb01CrmSignatureVO;
import com.wql.project.two.feign.Mb01CrmSignatureFeign;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/two/signature")
public class Mb01CrmSignatureControllerOne {
    @Autowired
    Mb01CrmSignatureFeign stockFeignService;

    @GetMapping("/getSingle")
    public Mb01CrmSignatureVO getSingle(@RequestParam("mb01UserId") String mb01UserId) {
        return stockFeignService.getSingle(mb01UserId);
    }

    /**
     * 导出PDF
     */
    @GetMapping("/export")
    public void exportPdf(HttpServletResponse response) {
        try {
            // 1. 创建文档对象
            Document document = new Document(PageSize.A4);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode("导出数据.pdf", "UTF-8"));

            // 2. 创建写入器
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // 3. 支持中文（关键！）
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font titleFont = new Font(bfChinese, 16, Font.BOLD);
            Font headerFont = new Font(bfChinese, 12, Font.BOLD);
            Font contentFont = new Font(bfChinese, 12, Font.NORMAL);

            // 4. 添加标题
            Paragraph title = new Paragraph("用户信息列表", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // 5. 创建表格（3列）
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // 6. 添加表头（使用PdfPCell）
            String[] headers = {"姓名", "年龄", "城市"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Paragraph(header, headerFont));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setPadding(8);
                table.addCell(cell);
            }

            // 7. 添加数据
            List<Map<String, Object>> dataList = getMockData();
            for (Map<String, Object> row : dataList) {
                // 姓名
                PdfPCell nameCell = new PdfPCell(new Paragraph(row.get("name").toString(), contentFont));
                nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                nameCell.setPadding(6);
                table.addCell(nameCell);

                // 年龄
                PdfPCell ageCell = new PdfPCell(new Paragraph(row.get("age").toString(), contentFont));
                ageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                ageCell.setPadding(6);
                table.addCell(ageCell);

                // 城市
                PdfPCell cityCell = new PdfPCell(new Paragraph(row.get("city").toString(), contentFont));
                cityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cityCell.setPadding(6);
                table.addCell(cityCell);
            }

            // 8. 将表格添加到文档
            document.add(table);

            // 9. 关闭文档
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟数据
     */
    private List<Map<String, Object>> getMockData() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> row1 = new HashMap<>();
        row1.put("name", "张三");
        row1.put("age", 25);
        row1.put("city", "北京");
        list.add(row1);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("name", "李四");
        row2.put("age", 30);
        row2.put("city", "上海");
        list.add(row2);

        Map<String, Object> row3 = new HashMap<>();
        row3.put("name", "王五");
        row3.put("age", 28);
        row3.put("city", "广州");
        list.add(row3);

        return list;
    }
}
