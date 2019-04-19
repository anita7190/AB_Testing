package com.test.ab;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.conn.ssl.SSLSocketFactory;

@SuppressWarnings("deprecation")
public class IndHttp {
	public static int READ_TIMEOUT = 10000; // milliseconds
	public static int CONNECT_TIMEOUT = 10000; // milliseconds


	public static IndHttpResponse httpGet(String url) {
		IndHttpResponse response = new IndHttpResponse();
		try {
			URL urlObj = new URL(url);
			System.out.println(" Connecting to URL: " + url);
			HttpURLConnection conn;
			conn = (HttpURLConnection) urlObj.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(READ_TIMEOUT);
			conn.setConnectTimeout(CONNECT_TIMEOUT);
			response.url = url;
			response.code = conn.getResponseCode();
			response.headersMap = conn.getHeaderFields();

			if (response.code == HttpsURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				while ((line = in.readLine()) != null) {
					sb.append(line);
					// break;
				}
				in.close();
				conn.disconnect();
				response.body = sb.toString();
				// System.out.println(response);
				return response;
			} else {
				// System.out.println(response);
				conn.disconnect();
				return response;
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.body = e.getMessage();
		}
		return response;
	}

	public static IndHttpResponse httpGet(String url, Map<String, String> headers) {
		IndHttpResponse response = new IndHttpResponse();
		try {
			URL urlObj = new URL(url);
			//System.out.println(" Connecting to URL: " + url);
			HttpURLConnection conn;
			conn = (HttpURLConnection) urlObj.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(READ_TIMEOUT);
			conn.setConnectTimeout(CONNECT_TIMEOUT);

			if (!headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					// System.out.println("Key = " + entry.getKey() + ", Value =
					// " + entry.getValue());
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			response.url = url;
			response.code = conn.getResponseCode();
			response.headersMap = conn.getHeaderFields();

			if (response.code == HttpsURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				while ((line = in.readLine()) != null) {
					sb.append(line);
					// break;
				}
				in.close();
				conn.disconnect();
				response.body = sb.toString();
				//System.out.println(response);
				return response;
			} else {
				//System.out.println(response);
				conn.disconnect();
				return response;
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.body = e.getMessage();
		}
		return response;
	}

	public static IndHttpResponse httpsGet(String url, Map<String, String> headers) {

		IndHttpResponse response = new IndHttpResponse();
		try {
			// HostnameVerifier hostnameVerifier =
			// org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
			KeyPinStore keyPinStore = KeyPinStore.getInstance();
			URL urlObj = new URL(url);
			//System.out.println(" Connecting to URL: " + url);
			HttpsURLConnection conn;
			conn = (HttpsURLConnection) urlObj.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(READ_TIMEOUT);
			conn.setConnectTimeout(CONNECT_TIMEOUT);
			conn.setSSLSocketFactory(keyPinStore.getContext().getSocketFactory());
			conn.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);

			if (!headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					// System.out.println("Key = " + entry.getKey() + ", Value =
					// " + entry.getValue());
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			response.url = url;
			response.code = conn.getResponseCode();
			response.headersMap = conn.getHeaderFields();

			if (response.code == HttpsURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				while ((line = in.readLine()) != null) {
					sb.append(line);
					// break;
				}
				in.close();
				conn.disconnect();
				response.body = sb.toString();
				//System.out.println(response);
				return response;
			} else {
				//System.out.println(response);
				conn.disconnect();
				return response;
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.body = e.getMessage();
		}
		return response;
	}

	
	public static IndHttpResponse httpPost(String url, String body) {
		IndHttpResponse response = new IndHttpResponse();

		try {
			URL urlObj = new URL(url);
			//System.out.println(" Connecting to URL: " + url);
			// System.out.println("url for Test is: "+url);
			HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
			conn.setReadTimeout(READ_TIMEOUT);
			conn.setConnectTimeout(CONNECT_TIMEOUT);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(body.getBytes());
			os.flush();
			os.close();
			response.url = url;
			response.code = conn.getResponseCode();
			response.headersMap = conn.getHeaderFields();

			if (response.code == HttpsURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				while ((line = in.readLine()) != null) {
					sb.append(line);
					// break;
				}
				in.close();
				conn.disconnect();
				response.body = sb.toString();
				//System.out.println(response);
				return response;
			} else {
				//System.out.println(response);
				conn.disconnect();
				return response;
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.body = e.getMessage();
			return response;
		}
	}

	public static IndHttpResponse httpPost(String url, String body, Map<String, String> headerMap) {

		IndHttpResponse response = new IndHttpResponse();

		try {

			URL urlObj = new URL(url);
			//System.out.println(" Connecting to URL: " + url);
			HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
			conn.setReadTimeout(READ_TIMEOUT);
			conn.setConnectTimeout(CONNECT_TIMEOUT);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json");

			if (!headerMap.isEmpty()) {
				for (Entry<String, String> entry : headerMap.entrySet()) {
					// System.out.println("Key = " + entry.getKey() + ", Value =
					// " + entry.getValue());
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			OutputStream os = conn.getOutputStream();
			os.write(body.getBytes());
			os.flush();
			os.close();
			response.url = url;
			response.code = conn.getResponseCode();
			response.headersMap = conn.getHeaderFields();

			if (response.code == HttpsURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				while ((line = in.readLine()) != null) {
					sb.append(line);
					// break;
				}
				in.close();
				conn.disconnect();
				response.body = sb.toString();
				//System.out.println(response);
				return response;
			} else {
				//System.out.println(response);
				conn.disconnect();
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.body = e.getMessage();
			return response;
		}
	}

	
	public static IndHttpResponse httpsPost(String url, Map<String, String> headers, String param) {

		IndHttpResponse response = new IndHttpResponse();
		try {
			// HostnameVerifier hostnameVerifier =
			// org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
			KeyPinStore keyPinStore = KeyPinStore.getInstance();
			String charset = "UTF-8";
			URL urlObj = new URL(url);
			//System.out.println(" Connecting to URL: " + url);
			HttpsURLConnection conn;
			conn = (HttpsURLConnection) urlObj.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json;charset=" + charset);

			conn.setReadTimeout(READ_TIMEOUT);
			conn.setConnectTimeout(CONNECT_TIMEOUT);
			conn.setSSLSocketFactory(keyPinStore.getContext().getSocketFactory());
			conn.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);

			if (!headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					// System.out.println("Key = " + entry.getKey() + ", Value =
					// " + entry.getValue());
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}

			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(param);
			writer.flush();
			writer.close();

			response.url = url;
			response.code = conn.getResponseCode();
			response.headersMap = conn.getHeaderFields();

			if (response.code == HttpsURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				while ((line = in.readLine()) != null) {
					sb.append(line);
					// break;
				}
				in.close();
				conn.disconnect();
				response.body = sb.toString();
				//System.out.println(response);
				return response;
			} else {
				System.out.println(response);
				conn.disconnect();
				return response;
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.body = e.getMessage();
		}
		return response;
	}


	public static boolean exists(String URLName) {
		try {
			HttpURLConnection.setFollowRedirects(true);
			// note : you may also need
			// HttpURLConnection.setInstanceFollowRedirects(false)
			HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
			con.setRequestMethod("HEAD");
			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
