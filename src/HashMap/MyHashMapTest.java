package HashMap;

import Arraylist.EfficientArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyHashMapTest {

    private MyHashMap<String, Integer> hashMap;

    @BeforeEach
    void setUp() {
        hashMap = new MyHashMap<>();
    }

    @Test
    void testPutAndGet() {
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        assertEquals(1, hashMap.get("one"));
        assertEquals(2, hashMap.get("two"));
        assertEquals(3, hashMap.get("three"));
    }

    @Test
    void testPutDuplicateKey() {
        hashMap.put("one", 1);
        hashMap.put("one", 2);

        assertEquals(2, hashMap.get("one"));
    }

    @Test
    void testGetNonExistingKey() {
        assertNull(hashMap.get("nonexistent"));
    }

    @Test
    void testContainsKey() {
        hashMap.put("one", 1);
        assertTrue(hashMap.containsKey("one"));
        assertFalse(hashMap.containsKey("nonexistent"));
    }

    @Test
    void testSize() {
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        assertEquals(3, hashMap.size());
    }

    @Test
    void testResize() {
        // Insert 17 elements to trigger a resize (initial capacity is 16)
        for (int i = 0; i < 17; i++) {
            hashMap.put("key" + i, i);
        }

        assertEquals(17, hashMap.size());
    }
    @Test
    void testKeySetGoodWeather() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();

        // Good Weather: Add three key-value pairs
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        // Good Weather: Check if the keySet contains all keys
        EfficientArrayList<String> keySet = hashMap.keySet();
        assertTrue(keySet.contains("one"));
        assertTrue(keySet.contains("two"));
        assertTrue(keySet.contains("three"));
    }

    @Test
    void testKeySetEmptyMap() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        // Good Weather: Check keySet on an empty map
        EfficientArrayList<String> keySet = hashMap.keySet();
        assertTrue(keySet.isEmpty());
    }

    @Test
    void testKeySetEdgeCases() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();

        // Edge Case: Add a key with a null value
        hashMap.put("nullValue", null);
        EfficientArrayList<String> keySetWithNull = hashMap.keySet();
        assertTrue(keySetWithNull.contains("nullValue"));

        // Edge Case: Add a key with an empty string value
        hashMap.put("emptyStringValue", 0);
        EfficientArrayList<String> keySetWithEmptyString = hashMap.keySet();
        assertTrue(keySetWithEmptyString.contains("emptyStringValue"));
    }

    @Test
    void testKeySetBadWeather() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();

        // Bad Weather: Attempt to add a key with a null value to the keySet
        hashMap.put("nullValueKey", null);
        EfficientArrayList<String> keySet = hashMap.keySet();
        assertFalse(keySet.contains(null));

        // Bad Weather: Attempt to add a key with an empty string to the keySet
        hashMap.put("", 0);
        assertFalse(keySet.contains(""));
    }
    @Test
    void testRemoveExistingKey() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        assertTrue(hashMap.containsKey("two"));

        hashMap.remove("two");

        assertFalse(hashMap.containsKey("two"));
        assertEquals(2, hashMap.size());
    }

    @Test
    void testRemoveNonExistingKey() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        hashMap.put("one", 1);
        hashMap.put("three", 3);

        assertFalse(hashMap.containsKey("two"));

        // Removing a non-existing key should not change the map
        hashMap.remove("two");

        assertFalse(hashMap.containsKey("two"));
        assertEquals(2, hashMap.size());
    }

    @Test
    void testRemoveLastKeyInBucket() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>(2); // Small initial capacity for testing resizing
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        assertTrue(hashMap.containsKey("three"));

        hashMap.remove("three");
        assertEquals(2, hashMap.size());
        assertFalse(hashMap.containsKey("three"));

    }
    @Test
    void testRemoveKeyFromEmptyMap() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();

        assertFalse(hashMap.containsKey("two"));

        // Removing a key from an empty map should have no effect
        hashMap.remove("two");

        assertFalse(hashMap.containsKey("two"));
        assertEquals(0, hashMap.size());
    }
}