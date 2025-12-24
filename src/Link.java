package dsaa.lab03;

// Link sınıfı, bir bağlantıyı (ref) temsil eder.
public class Link {
    public String ref; // Bağlantının referansı (örneğin bir URL ya da etiket)

    // Gelecekte buraya başka alanlar da eklenebilir.

    // Yapıcı metod: Link nesnesi oluştururken ref alanını başlatır.
    public Link(String ref) {
        this.ref = ref;
    }

    // Nesnenin hash kodunu döndürür.
    // Eğer ref null ise 0 döner, aksi takdirde ref'in hashCode'unu döner.
    @Override
    public int hashCode() {
        return ref == null ? 0 : ref.hashCode();
    }

    // Nesneyi String'e çevirirken sadece ref alanını döner.
    @Override
    public String toString() {
        return ref;
    }

    // İki Link nesnesinin eşit olup olmadığını kontrol eder.
    // Önce aynı nesne olup olmadığına bakar. Eğer değilse,
    // null olup olmadıklarını ve sınıflarının aynı olup olmadığını kontrol eder.
    // Son olarak, ref alanlarının eşitliğini kontrol eder.
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true; // Aynı nesneyse true döner
        else if (obj == null || this.getClass() != obj.getClass())
            return false; // Null ya da farklı sınıf ise eşit değildir

        Link other = (Link) obj; // Tür dönüştürme

        // ref alanı null ise diğerinin de null olması gerekir
        if (this.ref == null)
            return other.ref == null;

        // ref'ler aynıysa true döner
        return this.ref.equals(other.ref);
    }
}
