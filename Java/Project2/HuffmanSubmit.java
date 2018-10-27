

public class HuffmanSubmit implements Huffman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Huffman huffman = new HuffmanSubmit(); 
		huffman.encode("ur.jpg", "ur.enc", "freq.txt"); 
		huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");
	}

	@Override
	public void decode(String inputFile, String outputFile, String freqFile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encode(String inputFile, String outputFile, String freqFile) {
		// TODO Auto-generated method stub
		
	}

}
