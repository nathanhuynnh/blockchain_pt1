package blockchain;

import java.util.Date;

public class Block {

	public String hash;
	public String prevHash;
	private String data; // data will be a simple message
	private long timeStamp; //number as milliseconds since 1/1/1970
	private int nonce;
	
	
	//Block Constructor
	public Block(String data, String prevHash) {
		this.data = data;
		this.prevHash = prevHash;
		this.timeStamp = new Date().getTime();
		//This will be done after setting other values.
		this.hash = calculateHash();
	}
	
	//Calculate new hash based on blocks contents
	public String calculateHash() {
		
		String calculatedHash = StringUtil.applySha256(
							prevHash + Long.toString(timeStamp) + 
							Integer.toString(nonce) + data);
		
		return calculatedHash;
	}
	
	/**
    * We will require miners to do proof-of-work by trying different variable 
    * values in the block until its hash starts with a certain number of 0’s.
    * @param difficulty The number of 0's they must solve for.
    */
	public void mineBlock(int difficulty) {
		
		//Create a string with difficulty * "0"
		String target = new String(new char[difficulty]).replace('\0', '0');
		
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		
		System.out.println("Block Mined!!! : " + hash);
	}
}