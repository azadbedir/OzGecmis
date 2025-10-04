🧾 Resume PDF Generator (Java + Apache PDFBox)
📖 Proje Açıklaması

Bu proje, Java programlama dili kullanılarak kişisel bir özgeçmiş (CV) dosyasını PDF formatında otomatik olarak oluşturan bir uygulamadır.
Uygulama, kişinin adı, iletişim bilgileri, eğitim geçmişi, iş deneyimleri, becerileri ve fotoğrafını alarak düzenli bir PDF çıktısı üretir.

Proje, Apache PDFBox kütüphanesi sayesinde PDF sayfaları üzerinde yazı, görsel ve biçimlendirme işlemlerini gerçekleştirir.
Ayrıca Türkçe karakter desteği için sistem fontu (örneğin Arial.ttf) kullanılır.

🎯 Projenin Amacı

Java dilinde dosya oluşturma ve kütüphane kullanımı becerilerini geliştirmek

PDF oluşturma sürecini otomatikleştirmek

Gerçek bir özgeçmiş belgesini programatik olarak üretmek

Türkçe karakter desteği ve font yönetimi konularında deneyim kazanmak

🧠 Kullanılan Teknolojiler ve Araçlar
Teknoloji / Araç	Açıklama
Java (JDK 17+)	Ana programlama dili
Apache PDFBox	PDF dosyası oluşturmak, yazı ve görsel eklemek için kullanılan kütüphane
PDType0Font	Türkçe karakter desteği sağlayan font sınıfı
PDImageXObject	PDF içine fotoğraf eklemeyi sağlar
PDPageContentStream	PDF içine metin ve grafik çizmeye yarar
IntelliJ IDEA / VS Code	Geliştirme ortamı
Git + GitHub	Sürüm kontrolü ve proje paylaşımı için
.gitignore	Gereksiz veya geçici dosyaların repoya eklenmesini engeller
README.md	Proje tanıtım ve kullanım dökümanı
💬 Proje Nasıl Çalışır?

Fotoğraf yolu (photoPath) ve oluşturulacak PDF dosya yolu (outPdf) tanımlanır.

Program çalıştığında, yeni bir PDF dokümanı oluşturur (PDDocument).

İçine bir sayfa (PDPage) eklenir.

Türkçe karakter desteği için Arial.ttf fontu yüklenir.

PDF içine:

Fotoğraf

Kişisel bilgiler

Eğitim ve iş deneyimi

Yetenekler

Tarih bilgisi
yazılır ve biçimlendirilir.

Son olarak dosya belirtilen konuma kaydedilir (doc.save()).

🚀 Nasıl Çalıştırılır?
1️⃣ Gerekli kütüphaneleri yükle:

Eğer Maven kullanıyorsan, pom.xml içine şu bağımlılığı ekle:

<dependency>
    <groupId>org.apache.pdfbox</groupId>
    <artifactId>pdfbox</artifactId>
    <version>2.0.30</version>
</dependency>

2️⃣ Fotoğrafını ekle:

Fotoğrafını C:\Users\Azad\OneDrive\Desktop\azad_fotograf.jpg konumuna koy.
İstersen kod içinde bu yolu değiştirebilirsin.

3️⃣ Kodu çalıştır:
ResumePdfGenerator.java


Program başarılı çalışırsa şu şekilde bir çıktı verir:

✅ PDF başarıyla oluşturuldu: C:\Users\Azad\java_eğitim_dersleri\OzGecmis\resume.pdf

🧩 Projedeki Bileşenler
Bileşen	Görevi
PDDocument	Yeni PDF dokümanı oluşturur
PDPage	PDF’e sayfa ekler
PDPageContentStream	PDF içine yazı ve şekil çizer
PDType0Font	Font yükleyip Türkçe karakterleri işler
PDImageXObject	PDF’e fotoğraf ekler
drawSectionTitle()	Başlıkları çizer
drawParagraph()	Paragrafları düzenli şekilde yazar
drawExperience()	İş deneyimlerini başlık + açıklama formatında yazar
🧠 Yapay Zeka ve Kod Yardımı

Proje hazırlanırken şu yapay zeka destekli araçlardan faydalanılmıştır:

Araç / Model	Kullanım Alanı
OpenAI Codex / GPT-5 (ChatGPT)	Kodun yapısını oluşturma, hata ayıklama ve font sorunlarını çözme
GitHub Copilot	IntelliJ IDEA içinde otomatik kod tamamlama önerileri
Cursor AI / Cline IDE Eklentisi	Kod yazımı sırasında otomatik düzenleme ve formatlama önerileri

Bu araçlar, özellikle PDPageContentStream fonksiyonlarının doğru kullanımında, IllegalArgumentException gibi hataların çözümünde, ve Türkçe karakter desteği sağlanırken büyük kolaylık sağlamıştır.
Ancak tüm kodun son hali manuel olarak test edilip düzenlenmiştir.

📅 Geliştirici

👤 Azad Bedir
🎓 Yazılım Mühendisliği Öğrencisi
📧 azad.bedir@example.com

📍 Van, Türkiye

📜 Lisans

Bu proje kişisel eğitim amaçlı hazırlanmıştır.
İzin alınmadan ticari amaçlarla kullanılması yasaktır.
