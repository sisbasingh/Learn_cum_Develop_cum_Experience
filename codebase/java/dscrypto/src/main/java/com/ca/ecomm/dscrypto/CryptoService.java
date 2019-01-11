package com.ca.ecomm.dscrypto;

import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.secure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.ECDHDecrypter;
import com.nimbusds.jose.crypto.ECDHEncrypter;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.EncryptedJWT;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

/**
 * The class CryptoService exposes URIs to JWE encrypt/decrypt the data supported by JWE using nimbusds library and uses wrappers to call the APIs.
 * @category Crypto
 * @version 1.0
 * @author sisba01
 */
public class CryptoService {
	private static Logger logger;
	private final static String ALGORITHM = "alg";
	private final static String ENCRYPTION = "enc";
	private final static String KEY_TYPE = "kty";
	private final static String KEY = "key";
	private final static String RSA = "RSA";
	private final static String EC = "EC";
	private final static String JWE_HEADER = "jweHeader";
	private final static String KEY_LENGTH = "keyLength";
	private final static String CURVE = "crv";
	//Encryption Methods supported
	private final static String A128GCM = "A128GCM";
	private final static String A128CBC_HS256 = "A128CBC-HS256";
	private final static String P_256 = "P-256";
	private final static String ERROR = "error";
	private final static String RSA_OAEP = "RSA-OAEP";
	private final static String ECDH_ES = "ECDH-ES";
	private final static String ENCRYPTED_DATA = "encryptedData";
	private final static String PLAIN_TEXT = "plainText";
	//Response keys
	private final static String RESULT = "result";
	private final static String MESSAGE_TYPE = "messageType";
	private final static String MESSAGE_CATEGORY = "msgCategory";
	private final static String DECRYPTED_DATA = "decryptedData";
	private final static String SUCCESS = "success";
	private final static String FAILURE = "failure";
	private final static String UMESSAGE = "UMsg";
	private final static String RESPONSE = "Res";
	//Properties file path
	private final static String LOG4J_PROPERTIES_FILE_PATH = "../DSCryptoService/conf/log4j.properties";
	private final static String CRYPTO_PROPERTIES_FILE_PATH = "../DSCryptoService/conf/DSCryptoServiceConfig.properties";
	//Default configs
	private final static int DEFAULT_SERVICE_PORT = 2080;
	private final static String DEFAULT_GET_JWK_PUBLIC_KEY_URI = "/tds2/dscrypto/getpublickey";
	private final static String DEFAULT_JWK_ENCRYPT_URI = "/tds2/dscrypto/encrypt";
	private final static String DEFAULT_JWK_DECRYPT_URI = "/tds2/dscrypto/decrypt";
	private final static String DEFAULT_CREATE_NEW_KEYPAIR_URI = "/tds2/dscrypto/createjwkpair";
	
	private static String SCHEME = "http";
	private static String RSA_PRIVATE_KEYFILE_PATH;
	private static String RSA_PUBLIC_KEYFILE_PATH;
	private static String EC_PRIVATE_KEYFILE_PATH;
	private static String EC_PUBLIC_KEYFILE_PATH;
	private static RSADecrypter RSA_DECRYPTER;
	private static RSAEncrypter RSA_ENCRYPTER;
	private static ECKey EC_PRIVATE_KEY;
	private static String RSA_PUBLIC_KEY_STRING;
	private static String EC_PUBLIC_KEY_STRING;
	
	//URI Path and service port
	private static int SERVICE_PORT;
	private static String GET_JWK_PUBLIC_KEY_URI;
	private static String JWK_ENCRYPT_URI;
	private static String JWK_DECRYPT_URI;
	private static String CREATE_NEW_KEYPAIR_URI;
	
	private static Map<String, String> keyTypeMap;
	private static Map<String, String> publicKeyMap;
	private static Map<String, EncryptionMethod> encryptionMethodsMap;
	private static Map<String, JWEAlgorithm> jweAlgoMap;
	private static Map<String, ECKey.Curve> ecKeyCurveMap;

	/**
	 * This method initializes Logger, encryption/decryption keys and other resources required by the client
	 */
	private static void init() {
		initServiceLogger();
		logger.info("init() started");
		String java_Version = System.getProperty("java.version");
		logger.info("Java Version Used: " + java_Version);
		if(null == java_Version || "".equals(java_Version) || !java_Version.startsWith("1.8")) {
			logger.fatal("Java greater or equal to 1.8 is required");
			System.out.println("Current Java Version : " + java_Version + " Java greater or equal to 1.8 is required");
		}
		logger.info("initializing properties from config file: " + CRYPTO_PROPERTIES_FILE_PATH);
		Properties properties = initProperties();
		if(properties != null){
			initServerConfig(properties);
			initURIPaths(properties);
			initKeyCryptors(properties);
			//Initialize the public key Map
			publicKeyMap = new HashMap<String, String>();
			publicKeyMap.put(RSA, RSA_PUBLIC_KEY_STRING);
			publicKeyMap.put(EC, EC_PUBLIC_KEY_STRING);
		} else {
			logger.error("Error while initializing properties config from file: " + CRYPTO_PROPERTIES_FILE_PATH);
		}
		//Initialize Map resources
		initAlgEncKeyMaps();
		logger.info("Initialization done");
	}
	
	/**
	 * This method is used to initialize logger for crypto service
	 */
	private static void initServiceLogger(){
		logger = Logger.getLogger(CryptoService.class);
		// configure Logger for log4j
		PropertyConfigurator.configure(LOG4J_PROPERTIES_FILE_PATH);
		logger.info("LOG4J Property file cofigured to : " + LOG4J_PROPERTIES_FILE_PATH);
		logger.info("Service logger initialized successfully");
	}
	
	/**
	 * This method reads the Crypto Config Properties file and returns
	 * @return Properties object loaded with Crypto properties file
	 */
	private static Properties initProperties() {
		Properties properties = new Properties();
		try {
			logger.info("Reading DS Crypto properties file: " + CRYPTO_PROPERTIES_FILE_PATH);
			FileInputStream fileInputStream = new FileInputStream(CRYPTO_PROPERTIES_FILE_PATH);
			properties.load(fileInputStream);
			fileInputStream.close();
			logger.info("Read properties file successfully");
			return properties;
		} catch (FileNotFoundException e) {
			logger.error("In initProperties Exception: ", e);
		} catch (IOException e) {
			logger.error("In initProperties Exception: ", e);
		}
		return null;
	}
	
	/**
	 * This method reads Server configuration from Properties and initializes server port and keystore and/or truststore for SSL communication
	 * @param properties
	 */
	private static void initServerConfig(Properties properties) {
		logger.info("Initializing Server config ...");
		int servicePort = Integer.parseInt(properties.getProperty("ServicePort"));
		if(servicePort > 0)
			SERVICE_PORT = servicePort;
		else
			SERVICE_PORT = DEFAULT_SERVICE_PORT;
		// Configure port for jetty server
		port(SERVICE_PORT);
		logger.info("Configured port " + SERVICE_PORT + " for jetty");
		String keyStoreFilePath = properties.getProperty("KeyStoreFilePath");
		String keyStorePassword = properties.getProperty("KeyStorePassword");
		String trustStoreFilePath = properties.getProperty("TrustStoreFilePath");
		String trustStorePassword = properties.getProperty("TrustStorePassword");
		logger.info("Configuring SSL now...");
		if(null != keyStoreFilePath && !"".equals(keyStoreFilePath) && null != keyStorePassword && !"".equals(keyStorePassword)) {
			if(null != trustStoreFilePath && !"".equals(trustStoreFilePath) && null != trustStorePassword && !"".equals(trustStorePassword)) {
				//Configure with keystore and truststore
				secure(keyStoreFilePath, keyStorePassword, trustStoreFilePath, trustStorePassword);
				logger.info("Cofigured SSL with keystore and truststore");
			} else {
				//Configure with keystore only
				secure(keyStoreFilePath, keyStorePassword, null, null);
				logger.info("Configured SSL with keystore only");
			}
			SCHEME = "https";
			logger.info("Configured SSL Successfully");
		} else {
			logger.warn("Invalid keystore and/or truststore information to configure SSL, Security is weak");
		}
	}
	
	/**
	 * This method reads URI paths from Properties and initializes URI paths with valid values either from properties or with default values.
	 * @param properties
	 */
	private static void initURIPaths(Properties properties) {
		String getPublicKeyURIPath = properties.getProperty("GetPublicKeyURIPath");
		String encryptionURIPath = properties.getProperty("EncryptionURIPath");
		String decryptionURIPath = properties.getProperty("DecryptionURIPath");
		String createNewKeyPairURIPath = properties.getProperty("CreateNewKeyPairURIPath");
		if(getPublicKeyURIPath != null && !"".equals(getPublicKeyURIPath))
			GET_JWK_PUBLIC_KEY_URI = getPublicKeyURIPath;
		else
			GET_JWK_PUBLIC_KEY_URI = DEFAULT_GET_JWK_PUBLIC_KEY_URI;
		logger.info("Get JWK Public Key URI configured to : " + GET_JWK_PUBLIC_KEY_URI);
		if(null != encryptionURIPath && !"".equals(decryptionURIPath))
			JWK_ENCRYPT_URI = encryptionURIPath;
		else
			JWK_ENCRYPT_URI = DEFAULT_JWK_ENCRYPT_URI;
		logger.info("Get Encryption URI configured to : " + JWK_ENCRYPT_URI);
		if(null != decryptionURIPath && !"".equals(decryptionURIPath))
			JWK_DECRYPT_URI = decryptionURIPath;
		else
			JWK_DECRYPT_URI = DEFAULT_JWK_DECRYPT_URI;
		logger.info("Get Decryption URI configured to : " + JWK_DECRYPT_URI);
		if(null != createNewKeyPairURIPath && !"".equals(createNewKeyPairURIPath))
			CREATE_NEW_KEYPAIR_URI = createNewKeyPairURIPath;
		else
			CREATE_NEW_KEYPAIR_URI = DEFAULT_CREATE_NEW_KEYPAIR_URI;
		logger.info("Create New Key-pair URI configured to : " + CREATE_NEW_KEYPAIR_URI);
	}
	
	/**
	 * This method reads key file paths from Properties and initializes Encryption/Decryption keys and encryptor/decryptor
	 * @param properties
	 */
	private static void initKeyCryptors(Properties properties) {
		RSA_PRIVATE_KEYFILE_PATH = properties.getProperty("RSAPrivateKeyFilePath");
		RSA_PUBLIC_KEYFILE_PATH = properties.getProperty("RSAPublicKeyFilePath");
		EC_PRIVATE_KEYFILE_PATH = properties.getProperty("ECPrivateKeyFilePath");
		EC_PUBLIC_KEYFILE_PATH = properties.getProperty("ECPublicKeyFilePath");
		
		logger.info("RSA Private key configured to : " + RSA_PRIVATE_KEYFILE_PATH);
		logger.info("RSA Public key configured to : " + RSA_PUBLIC_KEYFILE_PATH);
		logger.info("EC Private key configured to : " + EC_PRIVATE_KEYFILE_PATH);
		logger.info("EC Public key configured to : " + EC_PUBLIC_KEYFILE_PATH);
		logger.info("Reading Crypto Public and Private keys...");
		
		try {
			Scanner scanner = new Scanner(new File(RSA_PRIVATE_KEYFILE_PATH));
			String rsaPrivateKeyString = scanner.useDelimiter("\\Z").next();
			scanner = new Scanner(new File(RSA_PUBLIC_KEYFILE_PATH));
			RSA_PUBLIC_KEY_STRING = scanner.useDelimiter("\\Z").next();
			scanner = new Scanner(new File(EC_PRIVATE_KEYFILE_PATH));
			String ecPrivateKeyString = scanner.useDelimiter("\\Z").next();
			scanner = new Scanner(new File(EC_PUBLIC_KEYFILE_PATH));
			EC_PUBLIC_KEY_STRING = scanner.useDelimiter("\\Z").next();
			scanner.close();
			RSAKey rsaPrivKey = RSAKey.parse(rsaPrivateKeyString);
			logger.info("RSA Private Key initialized");
			RSA_DECRYPTER = new RSADecrypter(rsaPrivKey);
			logger.info("RSA Decrypter initialized"); 
			RSAKey rsaPublicKey = RSAKey.parse(RSA_PUBLIC_KEY_STRING);
			logger.info("RSA Public Key initialized");
			RSA_ENCRYPTER = new RSAEncrypter(rsaPublicKey);
			logger.info("RSA Encrypter initialized");
			EC_PRIVATE_KEY = ECKey.parse(ecPrivateKeyString);
		} catch (FileNotFoundException e) { 
			logger.error("Exception in initKeyCryptors: ", e);
		} catch (ParseException e) {
			logger.error("Exception in initKeyCryptors: ", e);
		} catch (JOSEException e) {
			logger.error("Exception in initKeyCryptors: ", e);
		}
	}
	
	/**
	 * This method initializes maps containing supported key types, algorithms, and encryption methods as specified in DS specs.
	 */
	private static void initAlgEncKeyMaps(){
		logger.info("initing key maps ...");
		//Initialize keyType Map
		keyTypeMap = new HashMap<String, String>();
		keyTypeMap.put(RSA, RSA);
		keyTypeMap.put(EC, EC);
		//Initialize alg-enc Map
		encryptionMethodsMap = new HashMap<String, EncryptionMethod>();
		encryptionMethodsMap.put(A128GCM, EncryptionMethod.A128GCM);
		encryptionMethodsMap.put(A128CBC_HS256, EncryptionMethod.A128CBC_HS256);
		//Initialize JWE algo Map
		jweAlgoMap = new HashMap<String, JWEAlgorithm>();
		jweAlgoMap.put(RSA_OAEP, JWEAlgorithm.RSA_OAEP_256);
		jweAlgoMap.put(ECDH_ES, JWEAlgorithm.ECDH_ES);
		//Initialize ECKey.Curve Map
		ecKeyCurveMap = new HashMap<String, ECKey.Curve>();
		ecKeyCurveMap.put(P_256, ECKey.Curve.P_256);
		logger.info("Initializing key-maps successfully");
	}
	
	/**
	 * This method is used to generate RSA key pair (Private and Public keys) with key length "RSA_KEY_LENGTH" in JWK format
	 * NOTE: This method is not currently in use
	 */
	private static String generateRSAJWKPair(int keyLength){
		String errorMessage = "Unknown Error";
		try {
			logger.info("Generating new RSA key-pair in JWK format with key length " + keyLength);
			// RSA signatures require a public and private RSA key pair, the public key 
			// must be made known to the JWS recipient in order to verify the signatures
			KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
			keyGenerator.initialize(keyLength);
			KeyPair keyPair = keyGenerator.genKeyPair();
			// Convert to JWK format
			JWK jwk = new RSAKey.Builder((RSAPublicKey)keyPair.getPublic())
			    .privateKey((RSAPrivateKey)keyPair.getPrivate())
			    .keyID(UUID.randomUUID().toString()) // Give the key some ID (optional)
			    .build();
			String rsaPrivateKeyString = jwk.toJSONString();
			String rsaPublicKeyString = jwk.toPublicJWK().toJSONString();
			File existingKeyFile = new File(RSA_PUBLIC_KEYFILE_PATH);
			//Create new key file in the same directory
			PrintWriter fileWriter = new PrintWriter(existingKeyFile.getParentFile() + "/NewRSAPrivateKey.jwk");
			fileWriter.write(rsaPrivateKeyString);
			fileWriter.close();
			fileWriter = new PrintWriter(existingKeyFile.getParentFile() + "/NewRSAPublicKey.jwk");
			fileWriter.write(rsaPublicKeyString);
			fileWriter.close();
			logger.info("Successfully Generated new RSA key-pair in JWK format with key length " + keyLength);
			return createSuccessJWKKeyGenResponse(RSA);
		} catch (NoSuchAlgorithmException e) { 
			logger.error("In generateRSAJWKKeyPair Exception: ", e);
			errorMessage = e.getMessage();
		} catch (FileNotFoundException e) { 
			logger.error("In generateRSAJWKKeyPair Exception: ", e);
			errorMessage = e.getMessage();
		}
		return createFailureJSONResponse(errorMessage);
	}
	
	/**
	 * This method is used to generate EC key pair (Private and Public keys) with curve "P-256" in JWK format
	 * NOTE: This method is not currently in use
	 */
	private static String generateECJWKPair(ECKey.Curve ecKeyCurve){
		String errorMessage = "Unknown Error";
		try {
			logger.info("Generating new RSA key-pair in JWK format with key Curve " + ecKeyCurve);
			// Create the EC key pair
			KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("EC");
			keyGenerator.initialize(ecKeyCurve.toECParameterSpec());  //ECKey.Curve.P_256
			KeyPair keyPair = keyGenerator.generateKeyPair();
			// Convert to JWK format
			JWK jwk = new ECKey.Builder(ecKeyCurve, (ECPublicKey)keyPair.getPublic())  //ECKey.Curve.P_256
			    .privateKey((ECPrivateKey) keyPair.getPrivate())
			    .keyID(UUID.randomUUID().toString()) // Give the key some ID (optional)
			    .build();
			String ecPrivateKeyString = jwk.toJSONString();
			String ecPublicKeyString = jwk.toPublicJWK().toJSONString();
			File existingKeyFile = new File(EC_PUBLIC_KEYFILE_PATH);
			//Create new key file in the same directory
			PrintWriter fileWriter = new PrintWriter(existingKeyFile.getParentFile() + "/NewECPrivateKey.jwk");
			fileWriter.write(ecPrivateKeyString);
			fileWriter.close();
			fileWriter = new PrintWriter(existingKeyFile.getParentFile() + "/NewECPublicKey.jwk");
			fileWriter.write(ecPublicKeyString);
			fileWriter.close();
			logger.info("Successfully Generated new EC key-pair in JWK format with key Curve " + ecKeyCurve);
			return createSuccessJWKKeyGenResponse(EC);
		} catch (NoSuchAlgorithmException e) {
			logger.error("In generateECJWKKeyPair Exception", e);
			errorMessage = e.getMessage();
		} catch (InvalidAlgorithmParameterException e) { 
			logger.error("In generateECJWKKeyPair Exception", e);
			errorMessage = e.getMessage();
		} catch (FileNotFoundException e) {
			logger.error("In generateRSAJWKKeyPair Exception: ", e);
			errorMessage = e.getMessage();
		}
		return createFailureJSONResponse(errorMessage);
	}
	
	/**
	 * This method logs the different URIs exposed by the Crypto Service
	 */

	private static void printServerURIInfo(){
		 try {
	            InetAddress ipAddr = InetAddress.getLocalHost();
	            String baseURL = SCHEME + "://" + ipAddr.getHostAddress() + ":" + SERVICE_PORT;
	            logger.info("Get Public Key Service URL Configured to: " + baseURL + GET_JWK_PUBLIC_KEY_URI);
	            logger.info("JWK Encryption Service URL Configured to: " + baseURL + JWK_ENCRYPT_URI);
	            logger.info("JWK Decryption Service URL Configured to: " + baseURL + JWK_DECRYPT_URI);
	            logger.info("Create new JWK key-pair Service URL Configured to: " + baseURL + CREATE_NEW_KEYPAIR_URI);
	        } catch (UnknownHostException ex) {
	            ex.printStackTrace();
	        }
	}
	
	/**
	 * Main method that exposes URIs for different services that returns JSON responses.
	 * @param args
	 */
	public static void main(String[] args) {
		// Init resources
		init();
		printServerURIInfo();
		//These are only for test purpose
		//generateRSAJWKKeyPair(RSA_KEY_LENGTH);
		//generateECJWKKeyPair(ECKey.Curve.P_256);
		logger.info("DS Crypto Service Started");
		
		post("/appCallbackSvc/v1/activate-account", (request, response) -> {
			String requestJson = request.params("requestJson");
			System.out.println("Request received : " + requestJson);
			response.header("Content-Type", "application/json");
			String responseJSONString = "{ \"status\": \"SUCCESS\", "
					+ "\"account_id\": \"0CqBD9miR8KjqiKuRnNZmwkGAQA=\", "
					+ "\"account_type\": \"TM\", "
					+ "\"display_name\": \"XXXX XXXX XXXX 9610\", "
					+ "\"profile_name\": \"SAPROFILE\","
					+ "\"org_name\": \"SAORG\","
					+ "\"user_credential\": \"<response xmlns=\\\"http://xs.arcot.com/ArcotOTPProtocolSvc/2.0\\\" ><status>success</status><aid>u-4620400138109610</aid><displayName>XXXX XXXX XXXX 9610</displayName><resetsupport>false</resetsupport><logoUrl>https://10.131.14.165:8443/vpas/</logoUrl><utc>1517384500</utc><expiry>1548919566</expiry><roam>false</roam><algo><algoType>EMV</algoType><cs>::AA__=000000000000::AIP_=1000::AO__=000000000000::ATC_=0000::CID_=80::CVR_=03A4B000::IAD_=00000000000000::IAF_=00::IPB_=001FFF000000000000FFFF00000000000000::ORG_=M3DSORG::PANS=01::PAN_=4620400138109610::TD__=010101::TECC=0000::TRCC=0000::TT__=00::TVR_=8000000000::TYPE=Visa::UDKA=CC1EACFE6FA5356D::UDKB=058F73A60E780971::UN__=00000000::USER=4620400138109610::VER_=0.0.0::</cs></algo></response>\" }";
			return responseJSONString;
		});
		
		post("/appCallbackSvc/v1/acknowledge-activation", (request, response) -> {
			String requestJson = request.params("requestJson");
			System.out.println("Request received : " + requestJson);
			response.header("Content-Type", "application/json");
			String responseJSONString = "{ \"status\": \"SUCCESS\", "
					+ "\"account_id\": \"0CqBD9miR8KjqiKuRnNZmwkGAQA=\" }";
		    return responseJSONString;
		});
		
		post("/appCallbackSvc/v1/get-txn", (request, response) -> {
			response.header("Content-Type", "application/json");
			String responseJSONString = "{ \"status\": \"SUCCESS\","
					+ "\"sa_transaction_id\": \"g8dsQkx2QNaAk+erkoSh+wkGAQA=\","
					+ "\"transaction_message\": \"<html><body><center><header style=\\\"font-size: 15px;\\\"><b>Transaction Details</b></header><table ><tr><td>Account Id : </td>         <td>g8dsQkx2QNaAk+erkoSh+wkGAQA=</td></tr><tr><td>Merchant : </td><td>Amazon</td></tr><tr><td>Amount : </td><td>&#x20b9; 500</td></tr><tr><td>Date : </td><td>16/2/2018</td></tr></table></center></body></html>\","
					+ "\"account_id\": \"0CqBD9miR8KjqiKuRnNZmwkGAQA=\","
					+ "\"account_type\": \"TM\" }";
		    return responseJSONString;
		});
		
		post("/appCallbackSvc/v1/authenticate-txn", (request, response) -> {
			response.header("Content-Type", "application/json");
			String responseJSONString = "{\"status\": \"SUCCESS\","
					+ "\"attempt_again\": false,"
					+ "\"sa_transaction_id\": \"g8dsQkx2QNaAk+erkoSh+wkGAQA=\","
					+ " \"account_id\": \"0CqBD9miR8KjqiKuRnNZmwkGAQA=\" }";
		    return responseJSONString;
		});
		
		/**
		 * This URI can be used to get the public keys (either RSA or EC) that can be used for encryption
		 */
		post(GET_JWK_PUBLIC_KEY_URI, (request, response) -> {
			response.header("Content-Type", "application/json");
			String jsonRequestString = "";
			String errorMessage = "Unknown Error";
			try {
				jsonRequestString = request.body();
				logger.info("Request getpublickey from IP Address: " + request.ip());
				JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
				JSONObject jsonRequestObject = (JSONObject) parser.parse(jsonRequestString);
				String keyType = jsonRequestObject.getAsString(KEY_TYPE);
				if(publicKeyMap.containsKey(keyType)){
					String responseJSONString = createGetPublicKeySuccessJSON(keyType);
					response.header("Content-Length", String.valueOf(responseJSONString.length()));
					return responseJSONString;
				}
				else
					errorMessage = "Invalid KeyType";
			} catch (Exception e) {
				logger.error("In getpublickey: ", e);
				errorMessage = e.getMessage();
			}
			logger.warn("In getpublickey: " + errorMessage);
			String responseJSONString = createFailureJSONResponse(errorMessage);
			response.header("Content-Length", String.valueOf(responseJSONString.length()));
			return responseJSONString;
		});
			
		/**
		 * This URI can be used to JWE encrypt a plainText with given information in request JSON
		 */
		post(JWK_ENCRYPT_URI, (request, response) -> {
			response.header("Content-Type", "application/json");
			String requestJSONString = "";
			String errorMessage = "Unknown Error";
			try {
				requestJSONString = request.body();
				logger.info("Encryption Reqeust from IP Address: " + request.ip());
				JSONParser jsonParser = new JSONParser(JSONParser.MODE_PERMISSIVE);
				JSONObject reqJSONObject = (JSONObject) jsonParser.parse(requestJSONString);
				String plainText = reqJSONObject.getAsString(PLAIN_TEXT);
				String keyType = reqJSONObject.getAsString(KEY_TYPE);
				if(keyTypeMap.containsKey(keyType)){
					if (null != plainText) {
						if(keyType.equals(RSA)) {
							String algorithm = reqJSONObject.getAsString(ALGORITHM);
							String encryptionMethod = reqJSONObject.getAsString(ENCRYPTION);
							if(!jweAlgoMap.containsKey(algorithm))
								errorMessage = "Algorithm not supported";
							else if(!encryptionMethodsMap.containsKey(encryptionMethod))
								errorMessage = "Encryption Method not supported";
							else {
								String responseJSONString = jweRSAEncrypt(algorithm, encryptionMethod, plainText);
								response.header("Content-Length", String.valueOf(responseJSONString.length()));
								return responseJSONString;
							}
						} else if(keyType.equals(EC)){
							String header = reqJSONObject.getAsString(JWE_HEADER);
							if(null == header && "".equals(header))
								errorMessage = "Invalid JWE Header";
							else {
								String responseJSONString = jweECEncrypt(header, plainText);
								response.header("Content-Length", String.valueOf(responseJSONString.length()));
								return responseJSONString;
							}
						} else {
							errorMessage = "Key Type not supported";
						}
				  } else {
					  errorMessage = "Null plainText";
				  }
				}
			} catch (Exception e) {
				logger.error("In encrypt: ", e);
				errorMessage = e.getMessage();
			}
			logger.warn("In encrypt Error while Encryption : " + errorMessage);
			String responseJSONString = createFailureJSONResponse(errorMessage);
			response.header("Content-Length", String.valueOf(responseJSONString.length()));
			return responseJSONString;
		});

		/**
		 * This URI can be used to JWE decrypt encryptedData with given information in request JSON
		 */
		post(JWK_DECRYPT_URI, (request, response) -> {
			response.header("Content-Type", "application/json");
			String requestJSONString = "";
			String errorMessage = "Unknown Error";
			try {
				requestJSONString = request.body();
				logger.info("Decryption Reqeust from IP Address: " + request.ip());
				JSONParser jsonParser = new JSONParser(JSONParser.MODE_PERMISSIVE);
				JSONObject reqJSONObject = (JSONObject) jsonParser.parse(requestJSONString);
				String keyType = reqJSONObject.getAsString(KEY_TYPE);
				if(keyTypeMap.containsKey(keyType)){
					String encryptedData = reqJSONObject.getAsString(ENCRYPTED_DATA);
					if (null != encryptedData && !encryptedData.equals("")) {
						if(keyType.equals(RSA)){
							String responseString = jweRSADecrypt(encryptedData);
							response.header("Content-Length", String.valueOf(responseString.length()));
							return responseString;
						} else if(keyType.equals(EC)){
							String jweHeader = reqJSONObject.getAsString(JWE_HEADER);
							String responseString = jweECDecrypt(jweHeader, encryptedData);
							response.header("Content-Length", String.valueOf(responseString.length()));
							return responseString;
						}
					} else
						errorMessage = "Invalid encrytpedData for decryption";
				} else
					errorMessage = "Key Type not supported";
			} catch (Exception e) {
				logger.error("In decrypt: ", e);
				errorMessage = e.getMessage();
			}
			logger.warn("In decrypt Error while decryption: " + errorMessage);
			String responseString = createFailureJSONResponse(errorMessage);
			response.header("Content-Length", String.valueOf(responseString.length()));
			return responseString;
		});

		/**
		 * This URI can be used to generate new JWK key-pair based on the given configurations given in JSON request
		 */
		post(CREATE_NEW_KEYPAIR_URI, (request, response) -> {
			response.header("Content-Type", "application/json");
			String requestJSONString = "";
			String errorMessage = "Unknown Error";
			try {
				requestJSONString = request.body();
				logger.info("Create JWK pair request came from : " + request.ip());
				JSONParser jsonParser = new JSONParser(JSONParser.MODE_PERMISSIVE);
				JSONObject reqJSONObject = (JSONObject) jsonParser.parse(requestJSONString);
				String keyType = reqJSONObject.getAsString(KEY_TYPE);
				if (keyTypeMap.containsKey(keyType)) {
					if(keyType.equals(RSA)){
						int keyLength = (int) reqJSONObject.getAsNumber(KEY_LENGTH);
						return generateRSAJWKPair(keyLength);
					} else if(keyType.equals(EC)){
						String ecCurve = reqJSONObject.getAsString(CURVE);
						if(ecKeyCurveMap.containsKey(ecCurve)) {
							return generateECJWKPair(ecKeyCurveMap.get(ecCurve));
						} else {
							errorMessage = "EC key Curve type is not supported";
						}
					} else {
						errorMessage = "Invalid Key Type in request";
					}
				}
				else
					errorMessage = "Invalid Key Type in request JSON";
			} catch (Exception e) { 
				logger.error("Exception while parsing JSON: " + requestJSONString, e);
				errorMessage = e.getMessage();
			}
			return createFailureJSONResponse(errorMessage);
		});
	}
	
	/**
	 * This method is internally used to encrypt plainText using ECDH-ES key with curve mentioned in JWE header.
	 * @param jweHeader
	 * @param plainText
	 * @return encryptedData
	 */
	private static String jweECEncrypt(String jweHeader, String plainText) {
		String errorMessage = "Unknown Error";
		try {
			JWEHeader header = JWEHeader.parse(jweHeader);
			Payload payload = new Payload(plainText);
			JWEObject jweObject = new JWEObject(header, payload);
			//ECKey.Curve.P_256
			ECKey ecKey = new ECKey.Builder(header.getEphemeralPublicKey().getCurve(), (ECPublicKey)header.getEphemeralPublicKey().toECPublicKey())
				    .privateKey((ECPrivateKey) EC_PRIVATE_KEY.toECPrivateKey())
				    .keyID(UUID.randomUUID().toString()) // Give the key some ID (optional)
				    .build();
			// Do the actual encryption
			ECDHEncrypter ecEncrypter = new ECDHEncrypter(ecKey);
			jweObject.encrypt(ecEncrypter);
			// Serialise to JWT compact form
			String jweString = jweObject.serialize();
			return createSuccessEncryptionResponse(EC, jweString);
		} catch (JOSEException e) {
			logger.error("In jweECEncrypt: ", e);
			errorMessage = e.getMessage();
		} catch (ParseException e) {
			logger.error("In jweECEncrypt: ", e);
			errorMessage = e.getMessage();
		}
		return createFailureJSONResponse(errorMessage);
	}

	/**
	 * This method is internally used to decrypt encrypted Data using ECDH-ES key with curve mentioned in JWE header.
	 * @param headerStr
	 * @param serializedPayload
	 * @return
	 */
	private static String jweECDecrypt(String headerStr, String serializedPayload) {
		String errorMessage = "Unknown Error";
		try {
			EncryptedJWT jwt = EncryptedJWT.parse(serializedPayload);
			JWEHeader jweHeader = JWEHeader.parse(headerStr);  //ECKey.Curve.P_256
			ECKey ecKey = new ECKey.Builder(jweHeader.getEphemeralPublicKey().getCurve(), (ECPublicKey)jweHeader.getEphemeralPublicKey().toECPublicKey())
				    .privateKey((ECPrivateKey) EC_PRIVATE_KEY.toECPrivateKey())
				    .keyID(UUID.randomUUID().toString()) // Give the key some ID (optional)
				    .build();
			ECDHDecrypter ecdhDecrypter = new ECDHDecrypter(ecKey);
			//ecdhDecrypter.
			jwt.decrypt(ecdhDecrypter);
			return createSuccessDecryptionResponse(EC, jwt.getPayload() + "");
		} catch (ParseException e) {
			logger.error("In jweECDecrypt: ", e);
			errorMessage = e.getMessage();
		} catch (JOSEException e) {
			logger.error("In jweECDecrypt: ", e);
			errorMessage = e.getMessage();
		}
		return createFailureJSONResponse(errorMessage);
	}

	/**
	 * This method is used to JWE encrypt the plainText using the RSA-OAEP key using given algorithm and encryption method
	 * @param algorithm
	 * @param encryptionMehtod
	 * @param plainText
	 * @return encrypted data
	 */
	private static String jweRSAEncrypt(String algorithm, String encryptionMehtod, String plainText) {
		String errorMessage = "Unknown Error";
		try {
			JWEHeader header = new JWEHeader(jweAlgoMap.get(algorithm), encryptionMethodsMap.get(encryptionMehtod));  //JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A128GCM
			Payload payload = new Payload(plainText);
			JWEObject jweObject = new JWEObject(header, payload);
			// Do the actual encryption
			jweObject.encrypt(RSA_ENCRYPTER);
			// Serialise to JWT compact form
			String jweString = jweObject.serialize();
			return createSuccessEncryptionResponse(RSA, jweString);
		} catch (JOSEException e) {
			logger.error("In jweRSAEncrypt: ", e);
			errorMessage = e.getMessage();
		}
		return createFailureJSONResponse(errorMessage);
	}

	/**
	 * This method is used to JWE decrypt the serializedPayload using the RSA-OAEP
	 * @param serializedPayload
	 * @return decrypted data
	 */
	private static String jweRSADecrypt(String serializedPayload) {
		String errorMessage = "Unknown Error";
		try {
			EncryptedJWT jwt = EncryptedJWT.parse(serializedPayload);
			jwt.decrypt(RSA_DECRYPTER);
			return createSuccessDecryptionResponse(RSA, jwt.getPayload() + "");
		} catch (ParseException e) {
			logger.error("In jweRSADecrypt: ", e);
			errorMessage = e.getMessage();
		} catch (JOSEException e) {
			logger.error("In jweRSADecrypt: ", e);
			errorMessage = e.getMessage();
		}
		return createFailureJSONResponse(errorMessage);
	}

	/**
	 * This method returns the default JSON response object
	 * @return JSON Object
	 */
	private static JSONObject createDefaultJSON() {
		JSONObject json = new JSONObject();
		json.put(MESSAGE_TYPE, UMESSAGE);
		json.put(MESSAGE_CATEGORY, RESPONSE);
		return json;
	}
	
	/**
	 * This method creates Success JSON response for getpublickey request
	 * @param keyType
	 * @return JSON String response
	 */
	private static String createGetPublicKeySuccessJSON(String keyType){
		JSONObject getPublicKeySuccessJSON = new JSONObject();
		getPublicKeySuccessJSON.put(KEY_TYPE, keyType);
		getPublicKeySuccessJSON.put(RESULT, SUCCESS);
		getPublicKeySuccessJSON.put(KEY, publicKeyMap.get(keyType));
		return getPublicKeySuccessJSON.toJSONString();
	}

	/**
	 * This method creates failure JSON Response for any request
	 * @param errorMessage
	 * @return Failure JSON Response String 
	 */
	private static String createFailureJSONResponse(String errorMessage) {
		JSONObject json = createDefaultJSON();
		json.put(RESULT, FAILURE);
		json.put(ERROR, errorMessage);
		return json.toJSONString();
	}

	/**
	 * This method creates Success JSON response for JWE encryption request
	 * @param keyType
	 * @param encryptedData
	 * @return Success JSON Response String 
	 */
	private static String createSuccessEncryptionResponse(String keyType, String encryptedData) {
		JSONObject json = createDefaultJSON();
		json.put(KEY_TYPE, keyType);
		json.put(RESULT, SUCCESS);
		json.put(ENCRYPTED_DATA, encryptedData);
		return json.toJSONString();
	}

	/**
	 * This method creates Success JSON response for JWE decryption request
	 * @param keyType
	 * @param decryptedData
	 * @return Success JSON Response String 
	 */
	private static String createSuccessDecryptionResponse(String keyType, String decryptedData) {
		JSONObject json = createDefaultJSON();
		json.put(KEY_TYPE, keyType);
		json.put(RESULT, SUCCESS);
		json.put(DECRYPTED_DATA, decryptedData);
		return json.toJSONString();
	}
	
	/**
	 * This method creates Success JSON response for new JWK key-pair generation
	 * @param keyType
	 * @return Success JSON Response String
	 */
	private static String createSuccessJWKKeyGenResponse(String keyType) {
		JSONObject json = createDefaultJSON();
		json.put(KEY_TYPE, keyType);
		json.put(RESULT, SUCCESS);
		return json.toJSONString();
	}
}
