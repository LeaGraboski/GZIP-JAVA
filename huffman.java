package GZIP;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;




public class huffman {
	private static final int ASCII = 256+51; // the ASCII character is 256 with the most common Strings Combination

	public static void huffCompress(byte[] lzCompression, String saveCompression) throws IOException, ClassNotFoundException {

		Map<String, Integer> commonStrings=HushStringsCombine();		//lz77 spacial dectionary 
		int [] frq=buildFrequency(lzCompression, commonStrings);		//count frequency of every char
		Node n=buildHuffmanTree(frq);									//build huffman tree
		Map<Integer,String> lookup= buildLookupTable(n);				//build lookup table
		BitSet bs=generateEncodedData(lzCompression,lookup, commonStrings);		//genarating the file to encoded huffman data
		byte[] mybytes=bs.toByteArray();		
		treeCompress x = new treeCompress(frq);			//frequency array become an object

		FileOutputStream out = new FileOutputStream(saveCompression);
		ObjectOutputStream out2=new ObjectOutputStream(out);
		out2.writeObject(x);			//write frequency object to the file
		out.write(mybytes);				//write huffman compression to the file
		out.close();	

	}


	@SuppressWarnings("serial")
	public static class treeCompress implements Serializable{		//set the frequency array to object
		int [] AsciiArrey;
		public treeCompress (int [] AsciiArrey){		//setter
			this.AsciiArrey=AsciiArrey;
		}
		public int [] getArray(){		//getter
			return this.AsciiArrey;
		}
	}


	private static int[] buildFrequency(byte [] byteARR, Map<String, Integer> commonStrings){ //build frequency array

		int[] freq = new int[ASCII]; // array with 256+51 places for all ascii chars and dictionary
		for(int i=0; i<byteARR.length; i++){
			int ch=byteARR[i]&0xff;
			String str="";
			str+=(char)ch;
			int index=0;

			for (int j=1; j<4; j++){		//if there is a chars combine that fit to the dictionary
				if (i<byteARR.length-j){
					str+=(char)(byteARR[i+j]&0xff);
					if(commonStrings.get(str) != null){
						ch=commonStrings.get(str);
						index=j;
					}
				}
			}
			i=i+index;
			freq[ch]++;
		}
		return freq;
	}



	static class Node implements Comparable<Node> { // for a binary tree
		private final int character;
		private final int frequency;
		private final Node leftChild;
		private final Node rightChild;

		private Node(final int character, final int frequency,
				final Node leftChild, final Node rightChild) {
			this.character = character;
			this.frequency = frequency;
			this.leftChild = leftChild;
			this.rightChild = rightChild;

		}

		boolean isLeaf() {// check if the node do not have child
			return this.leftChild == null && this.rightChild == null;
		}


		public int compareTo(final Node o) {		//comparing if 2 nodes are equal

			final int frequencyComparison = Integer.compare(this.frequency, o.frequency);
			if (frequencyComparison != 0) {
				return frequencyComparison;
			}

			return Integer.compare(this.character, o.character);
		}
	}


	private static Node buildHuffmanTree (int [] freq) {// building the tree		
		final PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

		for (char i = 0; i < ASCII; i++) {			//every cell in the array is a new node now
			if (freq[i] > 0) {
				priorityQueue.add(new Node(i, freq[i], null, null));
			}
		}
		//move right/left according your frequency
		if (priorityQueue.size() == 1) {
			priorityQueue.add(new Node('\0', 1, null, null));
		}
		while (priorityQueue.size() > 1) {
			final Node left = priorityQueue.poll();
			final Node right = priorityQueue.poll();
			final Node parent = new Node('\0', left.frequency + right.frequency, left, right);
			priorityQueue.add(parent);
		}
		return priorityQueue.poll();

	}// //////////////////////////////////////////////////////////////////////////////


	static Map<Integer, String> buildLookupTable(final Node root) {
		final Map<Integer, String> lookupTable = new HashMap<>();//helps to know the frequency and the encode of every char
		buildLookupTableImpl(root, "", lookupTable);
		return lookupTable;
	}

	private static void buildLookupTableImpl(final Node node, final String str,
			final Map<Integer, String> lookupTable) {

		if (!node.isLeaf()) {
			buildLookupTableImpl(node.leftChild, str + '0', lookupTable); //recursion 
			buildLookupTableImpl(node.rightChild, str + '1', lookupTable);
		} else {
			lookupTable.put(node.character, str);
		}
	}





	private static BitSet generateEncodedData(final byte []byteFile,final Map<Integer,String> lookUpTable, Map<String, Integer> commonStrings){
		BitSet bitset=new BitSet();					//encode from the file to the huffman encoding 
		int lastIndex=0;
		for(int i=0; i<byteFile.length; i++){	
			int ch=byteFile[i]&0xff;
			String str="";
			str+=(char)ch;
			for (int j=1; j<4; j++){				//if there is a match in the dictionary save id as "str"
				if(i<byteFile.length-j)
					str+=(char)(byteFile[i+j]&0xff);
			}
			int index=3;
			while(commonStrings.get(str) == null && str.length()!=1){
				str = str.substring(0, str.length() - 1);
				index--;

			}

			if (commonStrings.get(str) != null){		///if you find a good match in the dictionary

				str=lookUpTable.get(commonStrings.get(str));
				if(i==byteFile.length-1){
					str+='1';
				}
				if(str!=null){
					int size=str.length();
					for(int j=0; j<size; j++){
						if (str.charAt(j) == '1')  
							bitset.set(lastIndex);

						lastIndex++;
					}
				}
				i=i+index;
			}
			else{			//if no match was found in the dictionary
				str=lookUpTable.get((int)(byteFile[i]& 0xff));
				if(i==byteFile.length-1){
					str+='1';
				}
				if(str!=null){
					int size=str.length();
					for(int j=0; j<size; j++){
						if (str.charAt(j) == '1')  
							bitset.set(lastIndex);

						lastIndex++;
					}
				}
			}

		}
		return bitset;
	}


	public static byte[] huffmanDecompress(String savedCompressionPath) throws IOException, ClassNotFoundException{
		Map<Integer, String> commonStrings=StringToKey();

		FileInputStream in = new FileInputStream(savedCompressionPath);
		ObjectInputStream in2 = new ObjectInputStream(in);
		treeCompress x=(treeCompress) in2.readObject();		//read the frequency array object
		long endObject=in.getChannel().position();
		int[] frq= x.getArray();
		Node root=buildHuffmanTree(frq);					//build huffman tree from the frequency array
		in2.close();
		in.close();


		Path path2 = Paths.get(savedCompressionPath);
		byte[] ByteFile = Files.readAllBytes(path2);		//get the rest of the file
		StringBuilder EncodedString=new StringBuilder();
		Node current1 = root;
		String s1="";
		for (int j=ByteFile.length; j>endObject; j--){		//convert the file to a binary string
			s1 = String.format("%8s", Integer.toBinaryString(ByteFile[j-1] & 0xFF)).replace(' ', '0');
			EncodedString.append(s1);
		}

		int i =0;
		while(i<8){					//a sing that the code start from here
			if(EncodedString.charAt(i)=='1'){
				break;
			}
			i++;
		}
		EncodedString=EncodedString.reverse();

		List<Byte> arrays = new ArrayList<Byte>();
		int j=0;
		while (j<EncodedString.length()-i-1){		//prowler in the tree until you find the proper match
			while(!current1.isLeaf()){
				char bit = ((CharSequence) EncodedString).charAt(j);
				if(bit == '1'){
					current1=current1.rightChild;
				}else if (bit =='0'){
					current1=current1.leftChild;
				}else{
					throw new IllegalArgumentException("invalid bit "+bit);
				}
				j++;
			}
			if(current1.character>255){				//if the proper match belong to the dictionary
				String combine=commonStrings.get(current1.character);
				byte[] combineToByte=combine.getBytes();
				for(int k=0; k<combineToByte.length; k++){
					arrays.add(combineToByte[k]);
				}

			}
			else{
				arrays.add((byte)current1.character);
			}
			current1=root;
		}

		Byte[] decodedBytes = arrays.toArray(new Byte[arrays.size()]);  		//convert from Byte<list> to byte array
		byte[] huffmanDecompress = new byte[decodedBytes.length];
		for (int k = 0; k < decodedBytes.length; k++)
		{
			huffmanDecompress[k] = decodedBytes[k];
		}

		return huffmanDecompress;

	}


	static Map<String, Integer> HushStringsCombine(){
		HashMap<String, Integer> commonString = new HashMap<String, Integer>() ; //new dictionary that fit to lz77 compress

		commonString.put("0,0,", 256);
		commonString.put("><", 257);
		commonString.put(",A><", 258);
		commonString.put(",B><", 259);
		commonString.put(",C><", 260);
		commonString.put(",D><", 261);
		commonString.put(",E><", 262);
		commonString.put(",F><", 263);
		commonString.put(",G><", 264);
		commonString.put(",H><", 265);
		commonString.put(",I><", 266);
		commonString.put(",J><", 267);
		commonString.put(",L><", 268);
		commonString.put(",M><", 269);
		commonString.put(",N><", 270);
		commonString.put(",Q><", 271);
		commonString.put(",R><", 272);
		commonString.put(",T><", 273);
		commonString.put(",U><", 274);
		commonString.put(",V><", 275);
		commonString.put(",W><", 276);
		commonString.put(",X><", 277);
		commonString.put(",Y><", 278);
		commonString.put(",Z><", 279);
		commonString.put(",a><", 280);
		commonString.put(",b><", 281);
		commonString.put(",c><", 282);
		commonString.put(",d><", 283);
		commonString.put(",e><", 284);
		commonString.put(",f><", 285);
		commonString.put(",g><", 286);
		commonString.put(",h><", 287);
		commonString.put(",i><", 288);
		commonString.put(",j><", 289);
		commonString.put(",k><", 290);
		commonString.put(",l><", 291);
		commonString.put(",m><", 292);
		commonString.put(",o><", 293);
		commonString.put(",p><", 294);
		commonString.put(",q><", 295);
		commonString.put(",r><", 296);
		commonString.put(",s><", 297);
		commonString.put(",t><", 298);
		commonString.put(",u><", 299);
		commonString.put(",v><", 300);
		commonString.put(",w><", 301);
		commonString.put(",x><", 302);
		commonString.put(",y><", 303);
		commonString.put(",z><", 304);
		commonString.put("<0,0,", 305);
		commonString.put("><0,0,", 306);

		return commonString;

	}




	static Map<Integer, String> StringToKey(){
		HashMap<Integer, String> commonString = new HashMap<Integer, String>() ;		//new dictionary that fit to lz77 compress

		commonString.put(256, "0,0,");
		commonString.put(257, "><");
		commonString.put(258, ",A><");
		commonString.put(259, ",B><");
		commonString.put(260, ",C><");
		commonString.put(261, ",D><");
		commonString.put(262, ",E><");
		commonString.put(263, ",F><");
		commonString.put(264, ",G><");
		commonString.put(265, ",H><");
		commonString.put(266, ",I><");
		commonString.put(267, ",J><");
		commonString.put(268, ",L><");
		commonString.put(269, ",M><");
		commonString.put(270, ",N><");
		commonString.put(271, ",Q><");
		commonString.put(272, ",R><");
		commonString.put(273, ",T><");
		commonString.put(274, ",U><");
		commonString.put(275, ",V><");
		commonString.put(276, ",W><");
		commonString.put(277, ",X><");
		commonString.put(278, ",Y><");
		commonString.put(279, ",Z><");
		commonString.put(280, ",a><");
		commonString.put(281, ",b><");
		commonString.put(282, ",c><");
		commonString.put(283, ",d><");
		commonString.put(284, ",e><");
		commonString.put(285, ",f><");
		commonString.put(286, ",g><");
		commonString.put(287, ",h><");
		commonString.put(288, ",i><");
		commonString.put(289, ",j><");
		commonString.put(290, ",k><");
		commonString.put(291, ",l><");
		commonString.put(292, ",m><");
		commonString.put(293, ",o><");
		commonString.put(294, ",p><");
		commonString.put(295, ",q><");
		commonString.put(296, ",r><");
		commonString.put(297, ",s><");
		commonString.put(298, ",t><");
		commonString.put(299, ",u><");
		commonString.put(300, ",v><");
		commonString.put(301, ",w><");
		commonString.put(302, ",x><");
		commonString.put(303, ",y><");
		commonString.put(304, ",z><");
		commonString.put(305, "<0,0,");
		commonString.put(306, "><0,0,");


		return commonString;

	}

}