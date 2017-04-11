import java.util.*;
import java.io.*;
import java.math.BigInteger;
class RSA_java
	{
		BigInteger p;
		BigInteger q;
	        BigInteger N;
		BigInteger phi;
	        BigInteger e;
	        BigInteger d;
		BigInteger f;
		int bitlength=1024;
		Random r;
		public RSA_java()
		{
			r = new Random();
			p = BigInteger.probablePrime(bitlength,r);
			q = BigInteger.probablePrime(bitlength,r);
			N = p.multiply(q);
			phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
			e = BigInteger.probablePrime(bitlength / 2,r);
			while(phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
			 {
				e.add(BigInteger.ONE);
			 }
			d=e.modInverse(phi);
			f=d.multiply(d);
		}
		public RSA_java(BigInteger e,BigInteger d,BigInteger N)
		{
			this.e=e;
			this.d=d;
			this.N=N;
		}
		public static void main(String args[])
		{
		Test tst = new Test();
		Scanner sc=new Scanner(System.in);
		String teststring;
		System.out.println("enter the string");
		teststring=sc.next();
		System.out.println("the bytecode:"+bytesToString(teststring.getBytes()));
		String s=bytesToString(teststring.getBytes());
		System.out.println("the string:"+s);
		
		byte[] encrypted = tst.encrypt(teststring.getBytes());
		
		byte[] decrypted = tst.decrypt(encrypted);
		System.out.println("Emcrypt bytes:"+bytesToString(encrypted));
		System.out.println("Decrypt Bytes:"+bytesToString(decrypted));
		System.out.println("Decrypted String:"+new String(decrypted));
		System.out.println("N:"+tst.N);
		System.out.println("e:"+tst.e);
		System.out.println("d:"+tst.d);	
		}
		public static String bytesToString(byte[] encrypted)
		{
			String test="";
			for (byte b : encrypted)
			{
				test +=Byte.toString(b);
			}
			return test;
		}
		public byte[] encrypt(byte[] message)
		{
			return (new BigInteger(message)).modPow(e,N).toByteArray();
		}
		public byte[] decrypt(byte[] message)
		{
			return (new BigInteger(message)).modPow(d,N).toByteArray();
		}
	}
	
