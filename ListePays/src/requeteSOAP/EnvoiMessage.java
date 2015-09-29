package requeteSOAP;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

public class EnvoiMessage {
	private SOAPConnection connection;
	private SOAPConnectionFactory soapConnFactory;
	private MessageFactory messageFactory;
	private SOAPMessage message;
	private SOAPPart soapPart;
	private SOAPEnvelope envelope;
	private SOAPBody body;
	private SOAPElement bodyElement;
	private TransformerFactory transformerFactory;
	private Transformer transformer;
	private Source sourceContent;

	// fonction connexion

	// on construit une connexion
	public void connexion() {
		try {
			soapConnFactory = SOAPConnectionFactory.newInstance();
			connection = soapConnFactory.createConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// ** Cr�ation de l�objet message
	// On construit les diff�rentes parties du message SOAP
	// Il est possible de cr�er le message � partir d�un fichier externe.**/
	public void creationMessage(String operation, String destination, String pays) {
		try {
			messageFactory = MessageFactory.newInstance();
			message = messageFactory.createMessage();
			soapPart = message.getSOAPPart();
			envelope = soapPart.getEnvelope();
			body = envelope.getBody();
			// On cr�e l'�l�ment principal et le namespace'
			QName bodyName = new QName(destination, operation, "m");
			// On cr�e l�enveloppe
			bodyElement = body.addBodyElement(bodyName);
			// On passe les param�tres
			if (pays != "") {
				QName qn1 = new QName("pays");
				bodyElement.addChildElement(qn1).addTextNode(pays);
			}
			// On sauve le message
			message.saveChanges();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public SOAPElement EmmissionReception(String destination, String pays)

	{
		try {
			// On contr�le l'entr�e
			System.out.println("\nENVOI:\n");
			message.writeTo(System.out);
			System.out.println();
			// On envoie le message et on attend la r�ponse
			// On d�finit la destination
			// On envoie le message
			SOAPMessage reply = connection.call(message, destination);

			// traitement de la r�ponse
			// On contr�le la sortie
			//System.out.println("\nREQUEST:\n");
			soapPart = reply.getSOAPPart();
			envelope = soapPart.getEnvelope();
			body = envelope.getBody();
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return body;
	}

}
