package blockchain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class BlockChain {
	
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;

	
	public static void main(String[] args) {
		
		//add blocks to blockchain arraylist
		blockchain.add(new Block("Hey! I'm the first block", "0"));
		System.out.println("Trying to mine block 1... ");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block(
						"What Uppp, I'm the second block", 
								blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to mine block 2... ");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("Hi, I'm the third block", 
								blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to mine block 3... ");
		blockchain.get(2).mineBlock(difficulty);
		
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting()
												.create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	}
	
	public static Boolean isChainValid() {
		Block curBlock;
		Block prevBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0','0');
		
		//traverse through the blockchain and check hashes
		for (int i = 1; i < blockchain.size(); i++) {
			curBlock = blockchain.get(i);
			prevBlock = blockchain.get(i - 1);
			
			//compare registered hash and calculated hash
			if (!curBlock.hash.equals(curBlock.calculateHash())) {
				System.out.println("Current hashes are NOT equal");
				return false;
			}
			
			//compare previous hashes
			if (!prevBlock.hash.equals(prevBlock.calculateHash())) {
				System.out.println("Previous hashes are NOT equal");
				return false;
			}
			
			//check if hash is solved
			if (!curBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		
		return true;
	}
	
}