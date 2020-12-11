import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EncryptionProgram {
	String key = "1234567890123456";
	private Scanner scanner;
	private ArrayList <Character> list;
	private ArrayList <Character> shuffledList;
	private char[] letters;
	private char character;
	EncryptionProgram(){
		
		scanner = new Scanner(System.in);
		new Random();
		list = new ArrayList<Character>();
		shuffledList = new ArrayList<Character>();
		character = ' ';
		newKeyGeneration();
		makeYourSelection();
		
	}
	
	private void makeYourSelection() {
		while(true) {
		System.out.println("*****************************************************************************************");
		System.out.println("**********************What Do You Want To DO!********************************************");
		System.out.println();
		System.out.println("(N)ewKey, (G)etKey, (E)ncrypt, (D)ecrypt, (U)File_EnCryption, (R)File_Decryption, (Q)uit");
		System.out.println("*****************************************************************************************");
		char response = Character.toUpperCase(scanner.nextLine().charAt(0));
		switch (response) {
		case 'N':
			newKeyGeneration();
			break;
			
		case 'G':
			getKey();
			break;
			
		case 'E':
			encrypt();
			break;
			
		case 'D':
			decrypt();
			break;
			
		case 'U':
			
			new FileEncryption();
			try {
				FileEncryption.unreadableEncryptedFile(key, "C:\\Users\\mohas\\Desktop\\text.txt", "C:\\Users\\mohas\\Desktop\\text.enc");
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
					| BadPaddingException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
		case 'R':
			try {
				new FileEncryption();
				FileEncryption.readableDecryptedFile(key, "C:\\Users\\mohas\\Desktop\\text.enc", "C:\\Users\\mohas\\Desktop\\text1.txt");
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
					| BadPaddingException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case 'Q':
			quit();
			break;
			
			default:
				System.out.println("Not a valid answers :(");
		}
	}
	}
	private void newKeyGeneration() {
		character =' ';
		list.clear();
		shuffledList.clear();
		
		for (int i = 1; i<255; i++) {
			list.add(Character.valueOf(character));
			character++;
		}
		
		shuffledList = new ArrayList<Character>(list);
		Collections.shuffle(shuffledList);
		System.out.println("A new key has been generated");
	}
	
	private void getKey() {
		System.out.println("Key: ");
		for(Character x : list) {
			System.out.print(x);
		}
		
		for(Character x : shuffledList) {
			System.out.print(x);
		}
		System.out.println();
	}
	
	private void encrypt() {
		System.out.println("Enter the message to be encrypted");
		String message = scanner.nextLine();
		letters = message.toCharArray();
		
		for (int i = 0; i<letters.length; i++) {
			for (int j = 0; j < list.size(); j++) {
				if (letters[i] == list.get(j)) {
					letters[i] = shuffledList.get(j);
					break;
				}
				
				
			}
		}
		System.out.println("Encrypted: ");
		for (char x: letters) {
			System.out.print(x);
		}
		System.out.println();
	}
	
	private void decrypt() {
		System.out.println("Enter the message to be decrypted");
		String message = scanner.nextLine();
		letters = message.toCharArray();
		
		for (int i = 0; i<letters.length; i++) {
			for (int j = 0; j < shuffledList.size(); j++) {
				if (letters[i] == shuffledList.get(j)) {
					letters[i] = list.get(j);
					break;
				}
				
				
			}
		}
		System.out.println("Decrypted: ");
		for (char x: letters) {
			System.out.print(x);
		}
		System.out.println();
	}
	
	private void quit() {
		System.out.println("Have a wonderful day");
		System.exit(0);
	}
	
}
