package com.test.ab;



import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;


public class KeyPinStore {

	private static KeyPinStore instance = null;
	private final String clientCertName = "/home/anita/eclipse-workspace/ab/src/main/java/com/test/ab/client.p12"; // "resources/client.p12"; // 
	private final String serverCertName = "/home/anita/eclipse-workspace/ab/src/main/java/com/test/ab/server.crt"; //  "resources/server.crt"; // 
	private SSLContext sslContext = SSLContext.getInstance("TLS");

    public static synchronized KeyPinStore getInstance() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        if (instance == null) {
            instance = new KeyPinStore();
        }
        return instance;
}
	private KeyPinStore() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException,
			KeyManagementException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		// randomCA.crt should be in the Assets directory (tip from here
		// http://littlesvr.ca/grumble/2014/07/21/android-programming-connect-to-an-https-server-with-self-signed-certificate/)
		InputStream caInput = new BufferedInputStream(new FileInputStream(serverCertName));
		Certificate ca = null;
		try {
			ca = cf.generateCertificate(caInput);
			// System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
			// Log.d(TAG, "KeyPinStore: " + "ca=" + ((X509Certificate) ca).getSubjectDN());
		} catch (Exception e) {
			// Log.d(TAG, "KeyPinStore: ");
			e.printStackTrace();
		} finally {
			caInput.close();
		}

		String keyStoreType = KeyStore.getDefaultType();
		KeyStore keyStore = KeyStore.getInstance(keyStoreType);
		keyStore.load(null, null);
		keyStore.setCertificateEntry("ca", ca);

		String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm);
		trustManagerFactory.init(keyStore);

		keyStore = KeyStore.getInstance("PKCS12");
		InputStream clientInputStream = new FileInputStream(clientCertName);
		

		try {
			keyStore.load(clientInputStream, "".toCharArray());
		} catch (java.security.cert.CertificateException e) {
			e.printStackTrace();
		}

		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
		try {
			keyManagerFactory.init(keyStore, "".toCharArray());
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		}
		KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();

		sslContext.init(keyManagers, trustManagerFactory.getTrustManagers(), null);
	}

	public SSLContext getContext() {
		return sslContext;
	}
}

