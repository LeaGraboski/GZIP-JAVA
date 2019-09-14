package GZIP;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class lz77 {
	boolean isMatch;
	int backWindow;
	int WordSize;
	int isNext;			//used to continue a word
	public lz77(List<Byte> tmp){
		isMatch=false;		//if there is a match
		backWindow=0;
		WordSize=0;
	}

	public static byte[] lzCompress(byte[] ByteFile, Integer backWindow, Integer forwardWindow) throws IOException{
		List<Byte> encodelz = new ArrayList<Byte>();			//the encoded Byte ist
		
		encodelz.add((byte) ('<'));								//insert the first byte 
		encodelz.add((byte) ('0'));								//insert the first byte 	
		encodelz.add((byte) (','));								//insert the first byte 
		encodelz.add((byte) ('0'));								//insert the first byte 		
		encodelz.add((byte) (','));								//insert the first byte 
		encodelz.add((byte) (ByteFile[0]&0xff));				//insert the first byte 
		encodelz.add((byte) ('>'));								//insert the first byte 
		
		for(int i=1; i<ByteFile.length;i++){			//run the entire file
			List<Byte> tmp = new ArrayList<Byte>();
			tmp.add((byte) (ByteFile[i]&0xff));
			lz77 lz=new lz77(tmp);
			int start=Math.max(0, i-backWindow);		//in case of the backWin is bigger then the current index 

			while(start<i && tmp.size()<forwardWindow && i<ByteFile.length){		//run while we didnt get the end of the file, and the current word is smaller then the forwardWindow
				if (ByteFile[start]==ByteFile[i] && !lz.isMatch){					//the firts match sign
					lz.isMatch=true;
					lz.backWindow=i-start;
					lz.WordSize=tmp.size();
					lz.isNext=start;		
					start++;
					i++;
				}

				else if(ByteFile[start]==ByteFile[i] && start-1==lz.isNext){		//a continue of a word after we have a match
					tmp.add((byte) (ByteFile[i]&0xff));
					lz.WordSize=tmp.size();
					lz.isNext++;
					start++;
					i++;
				}

				else if(ByteFile[start]==ByteFile[i] && lz.isMatch){			//if there is another match, check if its bigger match then you already found
					boolean isBiggerWord=true;
					int current=i-lz.WordSize;
					for(int k=start-lz.WordSize; k<start; k++){				//check if the last match signs are match in this case too
						boolean match=true;
						if(ByteFile[k]!=ByteFile[current])
							match=false;
						current++;
						isBiggerWord=match&&isBiggerWord;
					}

					if (isBiggerWord==true){			//if there is better match
						tmp.add((byte) (ByteFile[i]&0xff));
						lz.backWindow=i-start;
						lz.WordSize++;
						lz.isNext=lz.backWindow;
						i++;
						lz.isNext=start;
					}
					start++;
				}
				
				else{					//if there id no match in the current index
					start++;
				}
			}

			byte last;
			if(i>=ByteFile.length)		//if there is not third sign in the last index, insert $ as the last sign
				last=(byte)'$';
			else								
				last=(byte)(ByteFile[i]&0xff);
			
			String b=String.valueOf(lz.backWindow);				//saving the back window as string
			String f=String.valueOf(lz.WordSize);				//saving the forward window as a string
			encodelz.add((byte) ('<'));							//start encoding this match
			
			int counter=0;
			while(counter<b.length()){							//insert the back window into the byte list- while: in case there is more then one number
				encodelz.add((byte) (b.charAt(counter)));
				counter++;
			}
			
			encodelz.add((byte) (','));						//separator 
			
			counter=0;
			
			while(counter<f.length()){						//insert the forward window into the byte list- while: in case there is more then one number
				encodelz.add((byte) (f.charAt(counter)));
				counter++;
			}
			encodelz.add((byte) (','));						//separator 
			encodelz.add((byte) (last));
			encodelz.add((byte) ('>'));						//end encoding this match

		}

		Byte[] ecodedBytes = encodelz.toArray(new Byte[encodelz.size()]);		//insert the Byte list into a Byte array
		byte[] lz77 = new byte[ecodedBytes.length];								//insert the Byte array into a byte array
		for (int k = 0; k < ecodedBytes.length; k++)
		{
			lz77[k] = ecodedBytes[k];
		}
		return lz77;
	}



	
	
	
	
	public static void lzDecompress(byte[] compresString, String saveDecompression) throws IOException{
		List<Byte> decodedlz = new ArrayList<Byte>();
		int index=0;					//the current index of the compress file
		Integer globalIndex=0;			//the current index of the decompress file
	
		while(index<compresString.length-1 || (index<compresString.length-2 && (char)(compresString[compresString.length-2]&0xff)=='$')){		//get in if the file did not come to it's end
			int back=0;			//the back window
			int forward=0;		//the forward window
			index++;

			String Sback="";		
			String Sforward="";
			while((char)(compresString[index]&0xff)!=','){			//reading the back window into Sback
				Sback+=(char)(compresString[index]&0xff);
				index++;
			}

			back= Integer.parseInt(Sback);							//convert the backWin from string to int 
			index++;

			while((char)(compresString[index]&0xff)!=','){			//reading the forward window into Sforward
				Sforward+=(char)(compresString[index]&0xff);
				index++;
			}
			forward=Integer.parseInt(Sforward);						//convert the forwardWin from string to int 
			index++;		//skip the last ','

			int tempGlobal=0;										//used to save the amount of "word"
			int currentCounter=globalIndex-back;					//used for words that the forwardWin is bigger ther the backWin 

			while(forward>0  && back!=0){							//decompress
				decodedlz.add(decodedlz.get(globalIndex-back));
				tempGlobal++;
				back--;
				forward--;
				currentCounter++;
			}
			globalIndex+=tempGlobal;

			while(forward>0  && back==0){						//if the forwardWin is bigger ther the backWin and there is need to take steps forward
				decodedlz.add(decodedlz.get((currentCounter)));
				currentCounter++;
				globalIndex++;
				forward--;
			}
			if ((char)(compresString[index]&0xff)=='$' && index>compresString.length-3){ // if the lase sign is $  --> ignore

			}
			else{
				decodedlz.add((byte)(compresString[index]&0xff));		//insert the third sign (<1, 2, 3>)
				globalIndex++;
				index++;
			}
			index++;		//skip '>'

		}
		
		Byte[] decodedBytes = decodedlz.toArray(new Byte[decodedlz.size()]);			//convert decodedlz to byte array
		byte[] lzDecompress = new byte[decodedBytes.length];
		for (int k = 0; k < decodedBytes.length; k++)
		{
			lzDecompress[k] = decodedBytes[k];
		}
	
		FileOutputStream out1 = new FileOutputStream(saveDecompression);			//writing the decompress on the file
		out1.write(lzDecompress);
		out1.close();
	}

}
