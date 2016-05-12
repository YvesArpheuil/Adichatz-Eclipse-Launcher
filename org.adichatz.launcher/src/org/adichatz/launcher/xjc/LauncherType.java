//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2016.05.12 � 08:41:12 AM CEST 
//


package org.adichatz.launcher.xjc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour launcherType complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="launcherType">
 *   &lt;complexContent>
 *     &lt;extension base="{}nodeType">
 *       &lt;sequence>
 *         &lt;element name="arg" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="antAddedEntries" type="{}antAddedEntriesType"/>
 *         &lt;element name="refresh" type="{}refreshType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="confirmMessage" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="launcherURI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="encoding" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="envType" type="{}envTypeEnum" default="java" />
 *       &lt;attribute name="lastLaunched" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="returnCode" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "launcherType", propOrder = {
    "arg",
    "antAddedEntries",
    "refresh"
})
public class LauncherType
    extends NodeType
{

    protected List<String> arg;
    @XmlElement(required = true)
    protected AntAddedEntriesType antAddedEntries;
    @XmlElement(required = true)
    protected RefreshType refresh;
    @XmlAttribute(name = "confirmMessage")
    protected String confirmMessage;
    @XmlAttribute(name = "launcherURI")
    protected String launcherURI;
    @XmlAttribute(name = "encoding")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String encoding;
    @XmlAttribute(name = "envType")
    protected EnvTypeEnum envType;
    @XmlAttribute(name = "lastLaunched")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastLaunched;
    @XmlAttribute(name = "returnCode")
    protected Integer returnCode;

    /**
     * Gets the value of the arg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArg().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getArg() {
        if (arg == null) {
            arg = new ArrayList<String>();
        }
        return this.arg;
    }

    /**
     * Obtient la valeur de la propri�t� antAddedEntries.
     * 
     * @return
     *     possible object is
     *     {@link AntAddedEntriesType }
     *     
     */
    public AntAddedEntriesType getAntAddedEntries() {
        return antAddedEntries;
    }

    /**
     * D�finit la valeur de la propri�t� antAddedEntries.
     * 
     * @param value
     *     allowed object is
     *     {@link AntAddedEntriesType }
     *     
     */
    public void setAntAddedEntries(AntAddedEntriesType value) {
        this.antAddedEntries = value;
    }

    /**
     * Obtient la valeur de la propri�t� refresh.
     * 
     * @return
     *     possible object is
     *     {@link RefreshType }
     *     
     */
    public RefreshType getRefresh() {
        return refresh;
    }

    /**
     * D�finit la valeur de la propri�t� refresh.
     * 
     * @param value
     *     allowed object is
     *     {@link RefreshType }
     *     
     */
    public void setRefresh(RefreshType value) {
        this.refresh = value;
    }

    /**
     * Obtient la valeur de la propri�t� confirmMessage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfirmMessage() {
        return confirmMessage;
    }

    /**
     * D�finit la valeur de la propri�t� confirmMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfirmMessage(String value) {
        this.confirmMessage = value;
    }

    /**
     * Obtient la valeur de la propri�t� launcherURI.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLauncherURI() {
        return launcherURI;
    }

    /**
     * D�finit la valeur de la propri�t� launcherURI.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLauncherURI(String value) {
        this.launcherURI = value;
    }

    /**
     * Obtient la valeur de la propri�t� encoding.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * D�finit la valeur de la propri�t� encoding.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncoding(String value) {
        this.encoding = value;
    }

    /**
     * Obtient la valeur de la propri�t� envType.
     * 
     * @return
     *     possible object is
     *     {@link EnvTypeEnum }
     *     
     */
    public EnvTypeEnum getEnvType() {
        if (envType == null) {
            return EnvTypeEnum.JAVA;
        } else {
            return envType;
        }
    }

    /**
     * D�finit la valeur de la propri�t� envType.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvTypeEnum }
     *     
     */
    public void setEnvType(EnvTypeEnum value) {
        this.envType = value;
    }

    /**
     * Obtient la valeur de la propri�t� lastLaunched.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastLaunched() {
        return lastLaunched;
    }

    /**
     * D�finit la valeur de la propri�t� lastLaunched.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastLaunched(XMLGregorianCalendar value) {
        this.lastLaunched = value;
    }

    /**
     * Obtient la valeur de la propri�t� returnCode.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getReturnCode() {
        return returnCode;
    }

    /**
     * D�finit la valeur de la propri�t� returnCode.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setReturnCode(Integer value) {
        this.returnCode = value;
    }

}
