package GZIP;

import java.io.IOException;

public class GZIPDecompress {
	public static void main(String savedCompression, String saveDecompress) throws ClassNotFoundException, IOException{
		byte[] huffmanDecompression=huffman.huffmanDecompress(savedCompression);
		lz77.lzDecompress(huffmanDecompression, saveDecompress);
	}
}
