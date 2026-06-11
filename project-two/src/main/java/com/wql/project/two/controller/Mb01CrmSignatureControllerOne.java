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
        // 使用 try-with-resources 确保流正确关闭
        try {
            // 1. 设置响应头（必须在获取流之前）
            response.setContentType("application/pdf");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode("导出数据.pdf", "UTF-8"));

            // 2. 创建文档对象
            Document document = new Document(PageSize.A4);
            document.setMargins(50, 50, 50, 50); // 设置页边距

            // 3. 创建写入器
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // 4. 支持中文（关键！）
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font titleFont = new Font(bfChinese, 16, Font.BOLD);
            Font headerFont = new Font(bfChinese, 12, Font.BOLD);
            Font contentFont = new Font(bfChinese, 12, Font.NORMAL);

            // 5. 添加标题
            Paragraph title = new Paragraph("用户信息列表", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(30);
            document.add(title);

            // 6. 创建表格（3列）
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            // 设置列宽比例
            float[] columnWidths = {3f, 2f, 3f};
            table.setWidths(columnWidths);

            // 7. 添加表头
            String[] headers = {"姓名", "年龄", "城市"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Paragraph(header, headerFont));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setPadding(10);
                // 关键：表头单元格不重复添加
                table.addCell(cell);
            }

            // 8. 添加数据（一定要按列的顺序一行一行添加）
            List<Map<String, Object>> dataList = getMockData();
            for (Map<String, Object> row : dataList) {
                // 第一列：姓名
                PdfPCell nameCell = new PdfPCell(new Paragraph(row.get("name").toString(), contentFont));
                nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                nameCell.setPadding(8);
                table.addCell(nameCell);

                // 第二列：年龄
                PdfPCell ageCell = new PdfPCell(new Paragraph(row.get("age").toString(), contentFont));
                ageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                ageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ageCell.setPadding(8);
                table.addCell(ageCell);

                // 第三列：城市
                PdfPCell cityCell = new PdfPCell(new Paragraph(row.get("city").toString(), contentFont));
                cityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cityCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cityCell.setPadding(8);
                table.addCell(cityCell);
            }

            // 9. 确保表格完整（如果总单元格数不是3的倍数会出错）
            // 这里数据是3条×3列=9个单元格，正好是3的倍数，不需要补全

            // 10. 添加表格
            document.add(table);

            // 11. 关闭文档（重要：必须先关闭文档再关闭writer）
            document.close();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
            // 尝试返回错误信息
            try {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("PDF生成失败：" + e.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
