package com.apicatalog.multibase;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MultibaseTest {

    final MultibaseDecoder DECODER = MultibaseDecoder.getInstance(Multibase.BASE_58_BTC);

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("testData")
    void testBase58BTCEncode(String expected, byte[] data) {
        String output = Multibase.BASE_58_BTC.encode(data);
        assertEquals(expected, output);
    }
    
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("testData")
    void testDecode(String encoded, byte[] expected) {
        byte[] output = DECODER.decode(encoded);
        assertArrayEquals(expected, output);
    }

    static Stream<Arguments> testData() {
        return Stream.of(
          Arguments.of("zUXE7GvtEk8XTXs1GF8HSGbVA9FCX9SEBPe", "Decentralize everything!!".getBytes()),
          Arguments.of("zStV1DL6CwTryKyV", "hello world".getBytes()),
          Arguments.of("z7paNL19xttacUY", "yes mani !".getBytes())
        );
    }
}
