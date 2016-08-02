
package com.zaizi.sensefymobile.ios.core.info;

import java.io.File;
import java.io.IOException;

import java.util.Arrays;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import com.zaizi.sensefymobile.ios.exceptions.IterableException;
/**
 * 
 * @author ljayasinghe
 *
 */
public class TestCaseValues {
	/**
	 * Defining test case values xml path
	 */
	private static final String TEST_CASE_VALUES_XML = "src/test/resources/TestValues.xml";

	/**
	 * @param testCaseName
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param testCaseName
	 * @return
	 * @throws IterableException
	 */
	public static Iterable<Object[]> documentLibraryTestValues(
			final String testCaseName) throws IterableException {
		Iterable<Object[]> result = null;
		NodeList testdata;
		try {
			testdata = getTestData(testCaseName);
		} catch (ParserConfigurationException e) {
			throw new IterableException("ParserConfigurationException : ", e);
		} catch (SAXException e) {
			throw new IterableException("SAXException : ", e);
		} catch (IOException e) {
			throw new IterableException("IOException : ", e);
		}
		int noOfTestValues = getNumberOfValues(testdata);
		Object[][] bar = new Object[noOfTestValues][];
		int k = 0;
		for (int j = 0; j < testdata.getLength(); j++) {
			Node valueNode = testdata.item(j);
			if (valueNode.getNodeType() == Node.ELEMENT_NODE) {
				Element valueElement = (Element) valueNode;		
				if (("a_sensefymobile_iOS_search").equals(testCaseName)) 
				{
					Object[] foo = 
						{ 
							getValue("searchFile", valueElement),
							getValue("filesearched", valueElement),
							getValue("documentTitle", valueElement),
							getValue("documentsource", valueElement),
							getValue("createddate", valueElement),
							getValue("creator", valueElement),
							getValue("modifieddate", valueElement),
							getValue("modifier", valueElement),
							getValue("language", valueElement),
							getValue("mimetype", valueElement),
							getValue("documentsize", valueElement),
						};
					bar[k] = foo;
				}
				else if (("b_sensefymobile_iOS_datasource").equals(testCaseName)) 
				{
					Object[] foo = 
						{ 
							getValue("searchFile", valueElement),
						};
					bar[k] = foo;
				}
				else if (("c_sensefymobile_iOS_orderby").equals(testCaseName)) 
				{
					Object[] foo = 
						{ 
							getValue("searchFile", valueElement),
							getValue("ascFile", valueElement),
							getValue("descFile", valueElement)
						};
					bar[k] = foo;
				}
				else if (("d_sensefymobile_iOS_sortby").equals(testCaseName)) 
				{
					Object[] foo = 
						{ 
							getValue("searchFile", valueElement),
							getValue("relevanceDoc", valueElement),
							getValue("nameDoc", valueElement),
							getValue("titleDoc", valueElement),
							getValue("created_dateDoc", valueElement),
							getValue("modified_dateDoc", valueElement),
							getValue("creatorDoc", valueElement),
							getValue("modifierDoc", valueElement)
						};
					bar[k] = foo;
				}
				else if (("e_sensefymobile_iOS_facets1").equals(testCaseName)) 
				{
					Object[] foo = 
						{ 
							getValue("searchFile", valueElement),
							getValue("creatorDoc", valueElement),
							getValue("documentTypeDoc", valueElement),
							getValue("languageDoc", valueElement),
							getValue("sizeDoc", valueElement),
							getValue("lastmodifieddateDoc", valueElement),
							getValue("creationdateDoc", valueElement),
						};
					bar[k] = foo;
				}
				else if (("f_sensefymobile_iOS_facets2").equals(testCaseName)) 
				{
					Object[] foo = 
						{ 
							getValue("searchFile", valueElement),
							getValue("creatorDoc", valueElement),
							getValue("documentTypeDoc", valueElement),
							getValue("languageDoc", valueElement),
						};
					bar[k] = foo;
				}
				else if (("g_sensefymobile_iOS_facets3").equals(testCaseName)) 
				{
					Object[] foo = 
						{ 
							getValue("searchFile", valueElement),
							getValue("creator_doctype", valueElement),
							getValue("creator_language", valueElement),
							getValue("creator_size", valueElement),
							getValue("creator_modifiedDate", valueElement),
							getValue("creator_creationDate", valueElement),
							getValue("doctype_lamguage", valueElement),
							getValue("doctype_size", valueElement),
							getValue("doctype_modifiedDate", valueElement),
							getValue("doctype_creationDate", valueElement),
							getValue("language_size", valueElement),
							getValue("language_modifiedDate", valueElement),
							getValue("language_creationDate", valueElement),
							getValue("size_modifiedDate", valueElement),
							getValue("size_creationDate", valueElement),
							getValue("modifieddate_cerationDate", valueElement),
						};
					bar[k] = foo;
				}
				else if (("h_sensefymobile_iOS_facets4").equals(testCaseName)) 
				{
					Object[] foo = 
						{ 
							getValue("searchFile", valueElement),
							getValue("cdl_category", valueElement),
							getValue("cds_category", valueElement),
							getValue("dls_category", valueElement),
							getValue("cls_category", valueElement),
							getValue("cdls_category", valueElement),
						};
					bar[k] = foo;
				}
				else if (("i_sensefymobile_iOS_openDocument").equals(testCaseName)) 
				{
					Object[] foo = 
						{ 
							getValue("searchFile", valueElement),
							getValue("alfresco_username", valueElement),
							getValue("alfresco_password", valueElement),
							getValue("filename", valueElement),
						};
					bar[k] = foo;
				}
				else if (("j_sensefymobile_iOS_loginlogout").equals(testCaseName)) 
				{
					Object[] foo = 
						{ 
							getValue("sensefy_username", valueElement),
							getValue("sensefy_password", valueElement),
						};
					bar[k] = foo;
				}
				k++;
			}
		}
		result = Arrays.asList(bar);
		return result;
	}

	/**
	 * @param testCaseName
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private static NodeList getTestData(final String testCaseName)
			throws ParserConfigurationException, SAXException, IOException {
		File testValues = new File(TEST_CASE_VALUES_XML);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(testValues);
		doc.getDocumentElement().normalize();
		NodeList nodes = doc.getElementsByTagName(testCaseName);
		Node node = nodes.item(0);
		NodeList testdata = node.getChildNodes();
		return testdata;
	}

	/**
	 * @param nodeList
	 * @return
	 */
	private static int getNumberOfValues(final NodeList nodeList) {
		int length = 0;
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				length++;
			}
		}
		return length;
	}

	/**
	 * @param tag
	 * @param element
	 * @return
	 */
	private static String getValue(final String tag, final Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0)
				.getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
}
