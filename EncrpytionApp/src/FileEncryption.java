import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class FileEncryption {
	public static void unreadableEncryptedFile(String secretKey, String fileInputPath, String fileOutPath)
			   throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			   IllegalBlockSizeException, BadPaddingException {
			  SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
			  Cipher cipher = Cipher.getInstance("AES");
			  cipher.init(Cipher.ENCRYPT_MODE, key);

			  File fileInput = new File(fileInputPath);
			  FileInputStream inputStream = new FileInputStream(fileInput);
			  byte[] inputBytes = new byte[(int) fileInput.length()];
			  inputStream.read(inputBytes);

			  byte[] outputBytes = cipher.doFinal(inputBytes);

			  File encryptedFileOutput = new File(fileOutPath);
			  FileOutputStream outputStream = new FileOutputStream(encryptedFileOutput);
			  outputStream.write(outputBytes);

			  inputStream.close();
			  outputStream.close();
			  
			  System.out.println("File successfully encrypted!");
			  System.out.println("New File: " + fileOutPath);
			 }

			 public static void readableDecryptedFile(String secretKey, String fileInputPath, String fileOutPath)
			   throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			   IllegalBlockSizeException, BadPaddingException {
			  SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
			  Cipher cipher = Cipher.getInstance("AES");
			  cipher.init(Cipher.DECRYPT_MODE, key);

			  File fileInput = new File(fileInputPath);
			  FileInputStream inputStream = new FileInputStream(fileInput);
			  byte[] inputBytes = new byte[(int) fileInput.length()];
			  inputStream.read(inputBytes);

			  byte[] outputBytes = cipher.doFinal(inputBytes);

			  File fileEncryptOut = new File(fileOutPath);
			  FileOutputStream outputStream = new FileOutputStream(fileEncryptOut);
			  outputStream.write(outputBytes);

			  inputStream.close();
			  outputStream.close();
			 }}