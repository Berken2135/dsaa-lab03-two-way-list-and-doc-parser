package dsaa.lab03;

import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Belgeyi temsil eden sınıf
public class Document {
    public String name; // Belge adı
    public TwoWayUnorderedListWithHeadAndTail<Link> link; // Belgeye ait linklerin tutulduğu çift yönlü liste

    // Yapıcı metod: Belge adı verilir ve link listesi başlatılır. Ardından içerik yüklenir.
    public Document(String name, Scanner scan) {
        this.name = name;
        link = new TwoWayUnorderedListWithHeadAndTail<Link>();
        load(scan); // Belgeye ait linkler yüklenir
    }

    // Belge içeriğini tarar ve geçerli linkleri listeye ekler
    public void load(Scanner scan) {
        // "link=" ile başlayan ve boşluk olmayan karakterlerle devam eden desen tanımı
        Pattern pattern = Pattern.compile("link=\\S+");

        while (scan.hasNextLine()) {
            String line = scan.nextLine().trim();

            // "eod" (end of document) satırı gelirse döngü sonlanır
            if (line.equalsIgnoreCase("eod"))
                break;

            // Satırda belirtilen desene uygun ifadeleri ara
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                // "link=" kısmından sonrasını al (asıl link değeri)
                String linkRef = matcher.group().substring(5);

                // Eğer link formatı uygunsa listeye ekle
                if (correctLink(linkRef)) {
                    link.add(new Link(linkRef));
                }
            }
        }
    }

    // Link geçerlilik kontrolü:
    // İlk karakter harf olmalı, sonrasında harf, rakam veya '_' olabilir
    public static boolean correctLink(String link) {
        return link != null && link.matches("[a-zA-Z][a-zA-Z0-9_]*");
    }

    // Belgenin içeriğini düz sıralı şekilde yazdırır
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Document: ").append(name);

        // Link listesini sırayla gezip ekrana yaz
        Iterator<Link> iter = link.iterator();
        while (iter.hasNext()) {
            sb.append("\n").append(iter.next().ref);
        }
        return sb.toString();
    }

    // Belgeyi tersten (reverse) yazdırmak için yardımcı metod
    public String toStringReverse() {
        String retStr = "Document: " + name;
        // link.toStringReverse metodu, listenin tersten yazımını verir
        return retStr + link.toStringReverse();
    }
}
