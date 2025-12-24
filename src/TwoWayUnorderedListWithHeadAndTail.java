package dsaa.lab03;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// Çift yönlü, sırasız, baş ve son referanslı liste veri yapısı
public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E> {

    // Liste içinde kullanılacak düğüm (element) sınıfı
    private class Element {
        E object;
        Element next = null; // Sonraki düğüme referans
        Element prev = null; // Önceki düğüme referans

        public Element(E e) {
            this.object = e;
        }

        public Element(E e, Element next, Element prev) {
            this.object = e;
            this.next = next;
            this.prev = prev;
        }
    }

    Element head; // Listenin başı
    Element tail; // Listenin sonu
    int size;     // Eleman sayısı

    // Sırayla ileri doğru gezen iterator
    private class InnerIterator implements Iterator<E> {
        Element curr_node;

        public InnerIterator() {
            curr_node = head;
        }

        @Override
        public boolean hasNext() {
            return curr_node != null;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();

            E object = curr_node.object;
            curr_node = curr_node.next;
            return object;
        }
    }

    // Listeyi ileri ve geri gezmeye olanak sağlayan ListIterator
    private class InnerListIterator implements ListIterator<E> {
        Element last;
        Element next;
        int next_idx;

        public InnerListIterator() {
            next = head;
            next_idx = 0;
            last = null;
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public boolean hasPrevious() {
            return next_idx > 0;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            last = next;
            next = next.next;
            next_idx++;
            return last.object;
        }

        @Override
        public int nextIndex() {
            return next_idx;
        }

        @Override
        public E previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            if (next == null)
                next = tail;
            else
                next = next.prev;
            last = next;
            next_idx--;
            return last.object;
        }

        @Override
        public int previousIndex() {
            return next_idx - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            if (last == null)
                throw new IllegalStateException();
            last.object = e;
        }
    }

    // Yapıcı metod: Boş bir liste oluşturur
    public TwoWayUnorderedListWithHeadAndTail() {
        head = null;
        tail = null;
        size = 0;
    }

    // Listenin sonuna eleman ekler
    @Override
    public boolean add(E e) {
        Element new_element = new Element(e);
        if (head == null) {
            head = tail = new_element;
        } else {
            tail.next = new_element;
            new_element.prev = tail;
            tail = new_element;
        }
        size++;
        return true;
    }

    // Belirtilen index'e eleman ekler
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new NoSuchElementException();

        Element new_element = new Element(element);
        if (index == 0) {
            new_element.next = head;
            if (head != null)
                head.prev = new_element;
            head = new_element;
            if (size == 0)
                tail = new_element;
        } else if (index == size) {
            tail.next = new_element;
            new_element.prev = tail;
            tail = new_element;
        } else {
            Element curr_element = head;
            for (int i = 0; i < index; i++) {
                curr_element = curr_element.next;
            }
            new_element.prev = curr_element.prev;
            new_element.next = curr_element;
            curr_element.prev.next = new_element;
            curr_element.prev = new_element;
        }
        size++;
    }

    // Listeyi temizler
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Elemanın listede olup olmadığını kontrol eder
    @Override
    public boolean contains(E element) {
        Element curr_element = head;
        while (curr_element != null) {
            if (curr_element.object.equals(element))
                return true;
            curr_element = curr_element.next;
        }
        return false;
    }

    // Belirtilen index'teki elemanı döner
    @Override
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new NoSuchElementException();

        Element curr_element = head;
        for (int i = 0; i < index; i++) {
            curr_element = curr_element.next;
        }
        return curr_element.object;
    }

    // Belirtilen index'teki elemanı yenisiyle değiştirir
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size)
            throw new NoSuchElementException();

        Element curr_element = head;
        for (int i = 0; i < index; i++) {
            curr_element = curr_element.next;
        }
        E previous = curr_element.object;
        curr_element.object = element;
        return previous;
    }

    // Verilen elemanın index'ini döner (yoksa -1)
    @Override
    public int indexOf(E element) {
        Element curr_element = head;
        int index = 0;
        while (curr_element != null) {
            if (curr_element.object.equals(element))
                return index;
            curr_element = curr_element.next;
            index++;
        }
        return -1;
    }

    // Liste boş mu?
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Basit ileri iterator
    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    // Gelişmiş çift yönlü iterator
    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    // Belirtilen index'teki elemanı siler
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new NoSuchElementException();

        Element curr_element = head;
        for (int i = 0; i < index; i++) {
            curr_element = curr_element.next;
        }

        if (curr_element.prev != null)
            curr_element.prev.next = curr_element.next;
        else
            head = curr_element.next;

        if (curr_element.next != null)
            curr_element.next.prev = curr_element.prev;
        else
            tail = curr_element.prev;

        size--;
        return curr_element.object;
    }

    // Belirtilen elemanı siler (ilk eşleşeni)
    @Override
    public boolean remove(E e) {
        Element curr_element = head;

        while (curr_element != null) {
            if (curr_element.object.equals(e)) {
                if (curr_element.prev != null)
                    curr_element.prev.next = curr_element.next;
                else
                    head = curr_element.next;

                if (curr_element.next != null)
                    curr_element.next.prev = curr_element.prev;
                else
                    tail = curr_element.prev;

                size--;
                return true;
            }
            curr_element = curr_element.next;
        }
        return false;
    }

    // Liste boyutunu döner
    @Override
    public int size() {
        return size;
    }

    // Listenin tersini yazdıran metot
    public String toStringReverse() {
        ListIterator<E> iter = new InnerListIterator();
        while (iter.hasNext())
            iter.next(); // sona kadar git
        String retStr = "";
        while (iter.hasPrevious()) {
            retStr += "\n" + iter.previous().toString();
        }
        return retStr;
    }

    // Başka bir listeyi mevcut listeye ekler
    public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
        if (this == other)
            return;
        else if (other == null || other.size == 0)
            return;
        else if (this.size == 0) {
            head = other.head;
            tail = other.tail;
        } else {
            tail.next = other.head;
            other.head.prev = tail;
            tail = other.tail;
        }
        size += other.size;

        // Diğer listeyi temizle
        other.head = null;
        other.tail = null;
        other.size = 0;
    }
}
