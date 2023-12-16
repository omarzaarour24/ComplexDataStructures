package HashMap;
import Arraylist.EfficientArrayList;
import java.util.Iterator;
//https://dzone.com/articles/custom-hashmap-implementation-in-java

public class MyHashMap <K,V> {
    private static final int INITIAL_CAPACITY = 16;
    private EfficientArrayList<Entry<K,V>>[] buckets;
    private int size;

    public MyHashMap() {
        this(INITIAL_CAPACITY);
    }

    public MyHashMap(int initialCapacity) {
        buckets = new EfficientArrayList[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = new EfficientArrayList<>();
        }
        size = 0;
    }

    public void put(K key, V value) {
        int hashCode = key.hashCode();
        int index = Math.abs(hashCode) % buckets.length;

        // Check if the key already exists in the bucket
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        // Key not found, add a new entry
        buckets[index].add(new Entry<>(key, value));
        size++;
        // Check if the load factor exceeds a certain threshold, and if so, resize the buckets.
        if ((double) size / buckets.length > 0.75) {
            resize();
        }
    }

    public V get(K key) {
        int hashCode = key.hashCode();
        int index = Math.abs(hashCode) % buckets.length;

        for (Entry<K, V> entry : buckets[index]) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null; // Key not found
    }

    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = buckets.length * 2;
        EfficientArrayList<Entry<K, V>>[] newBuckets = new EfficientArrayList[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = new EfficientArrayList<>();
        }

        for (EfficientArrayList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                int index = Math.abs(entry.getKey().hashCode()) % newCapacity;
                newBuckets[index].add(entry);
            }
        }

        buckets = newBuckets;
    }

    public boolean containsKey(K key) {
        int bucketIndex = getBucketIndex(key);
        EfficientArrayList<Entry<K, V>> bucket = buckets[bucketIndex];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % buckets.length;
    }
    public EfficientArrayList<K> keySet() {
        EfficientArrayList<K> keys = new EfficientArrayList<>();

        for (EfficientArrayList<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    keys.add(entry.getKey());
                }
            }
        }

        return keys;
    }
    public void remove(K key) {
        int index = getBucketIndex(key);
        EfficientArrayList<Entry<K, V>> bucket = buckets[index];
        if (bucket != null) {
            Iterator<Entry<K, V>> iterator = bucket.iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                if (entry.getKey().equals(key)) {
                    bucket.remove(entry);
                    size--;
                    return;
                }
            }
        }
    }



}