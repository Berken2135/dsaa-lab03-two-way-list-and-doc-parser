package dsaa.lab03;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// Liste veri yapısı için temel işlevleri tanımlayan arayüz
public interface IList<E> extends Iterable<E> {

    // Listenin sonuna bir eleman ekler
    boolean add(E e);

    // Belirtilen index pozisyonuna bir eleman ekler
    // Eğer index geçersizse NoSuchElementException fırlatır
    void add(int index, E element) throws NoSuchElementException;

    // Listedeki tüm elemanları siler
    void clear();

    // Listenin içinde belirtilen eleman var mı kontrol eder (equals kullanır)
    boolean contains(E element);

    // Belirtilen index'teki elemanı döner
    // Eğer index geçersizse NoSuchElementException fırlatır
    E get(int index) throws NoSuchElementException;

    // Belirtilen index'e yeni bir eleman koyar, önceki elemanı döner
    // Eğer index geçersizse NoSuchElementException fırlatır
    E set(int index, E element) throws NoSuchElementException;

    // Verilen elemanın listedeki index'ini döner, yoksa -1 döner
    int indexOf(E element);

    // Liste boş mu kontrol eder
    boolean isEmpty();

    // Liste üzerinde dolaşmak için iterator döner (sıralı gezinme)
    Iterator<E> iterator();

    // ListIterator döner (ileri ve geri gezinme)
    // Eğer desteklenmiyorsa UnsupportedOperationException fırlatabilir
    ListIterator<E> listIterator() throws UnsupportedOperationException;

    // Belirtilen index'teki elemanı siler ve o elemanı döner
    // Eğer index geçersizse NoSuchElementException fırlatır
    E remove(int index) throws NoSuchElementException;

    // Belirtilen elemanı listeden siler, başarılıysa true döner
    boolean remove(E e);

    // Listedeki eleman sayısını döner
    int size();
}
