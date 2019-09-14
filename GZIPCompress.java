package GZIP;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GZIPCompress {
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		String toCompress="C:\\Users\\meitarl\\Desktop\\small.jpg";
		String saveCompression="C:\\Users\\meitarl\\Desktop\\output1.jpg";
		main(toCompress, saveCompression, 1000000, 1000000);
	}
	public static void main (String toCompress, String saveCompression, int backWindow, int forwardWindow) throws IOException, ClassNotFoundException{
		Path path1 = Paths.get(toCompress);
		byte[] ByteFile1 = Files.readAllBytes(path1);
		byte[] lzCompress=lz77.lzCompress(ByteFile1, backWindow,forwardWindow);
		
		huffman.huffCompress(lzCompress, saveCompression);
	}
}
