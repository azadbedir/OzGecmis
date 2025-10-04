package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class ResumePdfGenerator {

    public static void main(String[] args) {
        // Fotoğraf ve PDF dosyası konumları
        String photoPath = "C:\\Users\\Azad\\OneDrive\\Desktop\\azad_fotograf.jpg";
        String outPdf = "C:\\Users\\Azad\\java_eğitim_dersleri\\OzGecmis\\resume.pdf";

        try {
            createResumePdf(photoPath, outPdf);
            System.out.println("✅ PDF başarıyla oluşturuldu: " + outPdf);
        } catch (IOException e) {
            System.err.println("❌ Hata: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private static void createResumePdf(String photoPath, String outputPath) throws IOException {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);

            // ✅ Türkçe karakter desteği olan font yükle
            // Windows'ta Arial genellikle vardır, istersen DejaVuSans da kullanabilirsin.
            File fontFile = new File("C:/Windows/Fonts/arial.ttf");
            PDType0Font fontRegular = PDType0Font.load(doc, fontFile);
            PDType0Font fontBold = PDType0Font.load(doc, fontFile);
            PDType0Font fontItalic = PDType0Font.load(doc, fontFile);

            PDRectangle media = page.getMediaBox();
            float margin = 50;
            float width = media.getWidth() - 2 * margin;
            float startX = margin;
            float startY = media.getHeight() - margin;

            float photoWidth = 110;
            float photoHeight = 140;
            float photoX = startX;
            float photoY = startY - photoHeight;
            float textStartX = photoX + photoWidth + 15;
            float currentY = startY - 10;

            try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {

                // 1️⃣ Fotoğraf ekle
                File f = new File(photoPath);
                if (f.exists()) {
                    PDImageXObject pdImage = PDImageXObject.createFromFileByExtension(f, doc);
                    float imgW = pdImage.getWidth();
                    float imgH = pdImage.getHeight();
                    float scale = Math.min(photoWidth / imgW, photoHeight / imgH);
                    float drawW = imgW * scale;
                    float drawH = imgH * scale;
                    float drawX = photoX + (photoWidth - drawW) / 2f;
                    float drawY = photoY + (photoHeight - drawH) / 2f;
                    cs.drawImage(pdImage, drawX, drawY, drawW, drawH);
                } else {
                    cs.beginText();
                    cs.setFont(fontBold, 10);
                    cs.newLineAtOffset(photoX + 5, photoY + photoHeight / 2f);
                    cs.showText("[Fotoğraf bulunamadı: " + photoPath + "]");
                    cs.endText();
                }

                // 2️⃣ Başlık: İsim
                cs.beginText();
                cs.setFont(fontBold, 22);
                cs.newLineAtOffset(textStartX, currentY);
                cs.showText("Azad Bedir");
                cs.endText();

                // 3️⃣ İletişim bilgileri
                currentY -= 28;
                cs.beginText();
                cs.setFont(fontRegular, 11);
                cs.newLineAtOffset(textStartX, currentY);
                cs.showText("E-posta: azad.bedir@example.com  |  Telefon: +90 555 123 45 67");
                cs.endText();

                currentY -= 16;
                cs.beginText();
                cs.setFont(fontRegular, 11);
                cs.newLineAtOffset(textStartX, currentY);
                cs.showText("Adres: Van, Türkiye  |  Doğum: 24.03.2004");
                cs.endText();

                // 4️⃣ Kısa Özet
                currentY -= 28;
                drawSectionTitle(cs, "Kısa Özet", startX, currentY, fontBold);
                currentY -= 16;
                currentY = drawParagraph(cs,
                        "Yazılım Mühendisliği öğrencisi, Java ile backend geliştirme konusunda deneyimli. " +
                                "Ekip çalışmasına yatkın, temiz kod ve test yazımına önem veren bir geliştirici.",
                        startX, currentY, width, fontRegular, 11, 14);
                currentY -= 16;

                // 5️⃣ Deneyim
                drawSectionTitle(cs, "İş Deneyimi", startX, currentY, fontBold);
                currentY -= 18;
                currentY = drawExperience(cs, "Senior Java Developer", "TechNova Yazılım A.Ş.", "2021 - Günümüz",
                        "Mikroservis mimarisi, REST API tasarımı, performans optimizasyonu ve mentorluk görevleri.",
                        startX, currentY, width, fontRegular, fontBold);
                currentY -= 8;
                currentY = drawExperience(cs, "Java Backend Developer", "KodAtölye Teknoloji", "2018 - 2021",
                        "Kurumsal uygulamalarda iş mantığı geliştirme, veritabanı tasarımı ve CI süreçleri.",
                        startX, currentY, width, fontRegular, fontBold);
                currentY -= 8;
                currentY = drawExperience(cs, "Stajyer Yazılım Geliştirici", "Başlangıç Bilişim", "2017 - 2018",
                        "Proje süreçlerine destek, küçük çaplı uygulama geliştirme ve hata ayıklama.",
                        startX, currentY, width, fontRegular, fontBold);
                currentY -= 14;

                // 6️⃣ Eğitim
                drawSectionTitle(cs, "Eğitim", startX, currentY, fontBold);
                currentY -= 18;
                cs.beginText();
                cs.setFont(fontBold, 11);
                cs.newLineAtOffset(startX, currentY);
                cs.showText("B.Sc. Yazılım Mühendisliği - Kırklareli Üniversitesi");
                cs.endText();

                currentY -= 14;
                cs.beginText();
                cs.setFont(fontRegular, 11);
                cs.newLineAtOffset(startX, currentY);
                cs.showText("2023 - 2027");
                cs.endText();

                currentY -= 22;

                // 7️⃣ Yetenekler
                drawSectionTitle(cs, "Yetenekler", startX, currentY, fontBold);
                currentY -= 16;
                cs.beginText();
                cs.setFont(fontRegular, 11);
                cs.newLineAtOffset(startX, currentY);
                cs.showText("Java, Spring Boot, REST API, SQL, PostgreSQL, Git, Docker, JUnit");
                cs.endText();

                currentY -= 26;

                // 8️⃣ Oluşturulma tarihi
                cs.beginText();
                cs.setFont(fontItalic, 9);
                cs.newLineAtOffset(startX, margin);
                cs.showText("Oluşturuldu: " + LocalDate.now());
                cs.endText();
            }

            doc.save(outputPath);
        }
    }

    private static void drawSectionTitle(PDPageContentStream cs, String title, float x, float y, PDType0Font font) throws IOException {
        cs.beginText();
        cs.setFont(font, 14);
        cs.newLineAtOffset(x, y);
        cs.showText(title);
        cs.endText();
    }

    private static float drawExperience(PDPageContentStream cs,
                                        String role, String company, String period, String desc,
                                        float x, float y, float maxWidth,
                                        PDType0Font fontRegular, PDType0Font fontBold) throws IOException {

        cs.beginText();
        cs.setFont(fontBold, 11);
        cs.newLineAtOffset(x, y);
        cs.showText(role + " — " + company);
        cs.endText();

        cs.beginText();
        cs.setFont(fontRegular, 10);
        cs.newLineAtOffset(x + maxWidth - 130, y);
        cs.showText(period);
        cs.endText();

        y -= 15;
        y = drawParagraph(cs, desc, x, y, maxWidth, fontRegular, 10, 14);
        return y;
    }

    private static float drawParagraph(PDPageContentStream cs, String text, float x, float y, float maxWidth,
                                       PDType0Font font, int fontSize, int leading) throws IOException {
        float curY = y;
        String[] words = text.split("\\s+");
        StringBuilder line = new StringBuilder();
        for (String w : words) {
            String tentative = line.length() == 0 ? w : line + " " + w;
            float textWidth = font.getStringWidth(tentative) / 1000 * fontSize;
            if (textWidth > maxWidth) {
                cs.beginText();
                cs.setFont(font, fontSize);
                cs.newLineAtOffset(x, curY);
                cs.showText(line.toString());
                cs.endText();
                line.setLength(0);
                line.append(w);
                curY -= leading;
            } else {
                if (line.length() > 0) line.append(" ");
                line.append(w);
            }
        }
        if (line.length() > 0) {
            cs.beginText();
            cs.setFont(font, fontSize);
            cs.newLineAtOffset(x, curY);
            cs.showText(line.toString());
            cs.endText();
            curY -= leading;
        }
        return curY;
    }
}
