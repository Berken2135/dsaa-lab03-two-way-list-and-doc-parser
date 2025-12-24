package dsaa.lab03;

import java.util.*;

public class Main {
    static Scanner scan; // Girdi almak için Scanner nesnesi

    public static void main(String[] args) {
        System.out.println("START"); // Program başlangıcı
        scan = new Scanner(System.in); // Girdi kaynağını başlat
        Document[] doc = null; // Document nesnelerini tutacak dizi
        int currentDocNo = 0; // Şu an aktif olan belge numarası
        int maxNo = -1; // Belge dizisinin maksimum uzunluğu
        boolean halt = false; // Programın bitip bitmediğini kontrol eder

        while (!halt) {
            String line = scan.nextLine(); // Kullanıcıdan bir satır oku

            // Boş satır veya yorum satırıysa geç
            if (line.length() == 0 || line.charAt(0) == '#')
                continue;

            // Komutu çıktı olarak yaz (hata ayıklamak için)
            System.out.println("!" + line);

            String word[] = line.split(" "); // Girdiyi kelimelere ayır

            // "go n" komutu: Belirtilen uzunlukta Document dizisi oluştur
            if (word[0].equalsIgnoreCase("go") && word.length == 2) {
                maxNo = Integer.parseInt(word[1]);
                doc = new Document[maxNo];
                continue;
            }

            // "ch n" komutu: Aktif belgeyi değiştir
            if (word[0].equalsIgnoreCase("ch") && word.length == 2) {
                currentDocNo = Integer.parseInt(word[1]);
                continue;
            }

            // "ld name" komutu: Belgeyi yükle
            if (word[0].equalsIgnoreCase("ld") && word.length == 2) {
                doc[currentDocNo] = new Document(word[1], scan);
                continue;
            }

            // "ha" komutu: Programı sonlandır
            if (word[0].equalsIgnoreCase("ha") && word.length == 1) {
                halt = true;
                continue;
            }

            // "clear" komutu: Aktif belgedeki tüm linkleri sil
            if (word[0].equalsIgnoreCase("clear") && word.length == 1) {
                doc[currentDocNo].link.clear();
                continue;
            }

            // "show" komutu: Belgeyi normal sırayla yazdır
            if (word[0].equalsIgnoreCase("show") && word.length == 1) {
                System.out.println(doc[currentDocNo].toString());
                continue;
            }

            // "reverse" komutu: Belgeyi tersten yazdır
            if (word[0].equalsIgnoreCase("reverse") && word.length == 1) {
                System.out.println(doc[currentDocNo].toStringReverse());
                continue;
            }

            // "size" komutu: Link listesinin boyutunu yazdır
            if (word[0].equalsIgnoreCase("size") && word.length == 1) {
                System.out.println(doc[currentDocNo].link.size());
                continue;
            }

            // "add str" komutu: Belgeye yeni bir link ekle
            if (word[0].equalsIgnoreCase("add") && word.length == 2) {
                System.out.println(doc[currentDocNo].link.add(new Link(word[1])));
                continue;
            }

            // "addi index str" komutu: Belirtilen index'e link ekle
            if (word[0].equalsIgnoreCase("addi") && word.length == 3) {
                int index = Integer.parseInt(word[1]);
                try {
                    doc[currentDocNo].link.add(index, new Link(word[2]));
                } catch (NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }

            // "get index" komutu: Belirtilen index'teki link'i getir
            if (word[0].equalsIgnoreCase("get") && word.length == 2) {
                int index = Integer.parseInt(word[1]);
                try {
                    Link l = doc[currentDocNo].link.get(index);
                    System.out.println(l.ref);
                } catch (NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }

            // "set index str" komutu: Belirtilen index'teki link'i yenisiyle değiştir
            if (word[0].equalsIgnoreCase("set") && word.length == 3) {
                int index = Integer.parseInt(word[1]);
                try {
                    Link l = doc[currentDocNo].link.set(index, new Link(word[2]));
                    System.out.println(l.ref); // Önceki link'in değerini yazdır
                } catch (NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }

            // "index str" komutu: Verilen link'in listedeki index'ini yazdır
            if (word[0].equalsIgnoreCase("index") && word.length == 2) {
                int index = doc[currentDocNo].link.indexOf(new Link(word[1]));
                System.out.println(index);
                continue;
            }

            // "remi index" komutu: Belirtilen index'teki link'i sil
            if (word[0].equalsIgnoreCase("remi") && word.length == 2) {
                int index = Integer.parseInt(word[1]);
                try {
                    Link l = doc[currentDocNo].link.remove(index);
                    System.out.println(l.ref);
                } catch (NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }

            // "rem str" komutu: Verilen link'i listeden sil
            if (word[0].equalsIgnoreCase("rem") && word.length == 2) {
                System.out.println(doc[currentDocNo].link.remove(new Link(word[1])));
                continue;
            }

            // "addl n" komutu: Belirtilen belge numarasındaki link listesini aktif belgeye ekle
            if (word[0].equalsIgnoreCase("addl") && word.length == 2) {
                int number = Integer.parseInt(word[1]);
                doc[currentDocNo].link.add(doc[number].link);
                continue;
            }

            // Bilinmeyen komutlar için uyarı
            System.out.println("Wrong command");
        }

        System.out.println("END OF EXECUTION"); // Programın sonu
        scan.close(); // Scanner kapatılır
    }
}
