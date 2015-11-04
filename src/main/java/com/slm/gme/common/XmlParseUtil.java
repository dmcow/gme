package com.slm.gme.common;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public final class XmlParseUtil
{
    private static XmlParseUtil xmlParseUtil;
    
    private XmlParseUtil()
    {
        
    }
    
    public static XmlParseUtil getInstance()
    {
        if (null == xmlParseUtil)
        {
            xmlParseUtil =  new XmlParseUtil();
        }
        
        return xmlParseUtil;
    }
    
    public Document loadFile(String filePath)
    {
        Document doc = null;
        try
        {
            doc = new SAXReader().read(this.getClass().getClassLoader().getResourceAsStream(filePath));
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        
        return doc;
    }
    
    public Element getRootElement(Document document)
    {
        return document.getRootElement();
    }
    
    @SuppressWarnings("unchecked")
    public List<Element> getRootSubElements(Element rootElement)
    {
        return rootElement.elements();
    }
    
    @SuppressWarnings("unchecked")
    public List<Element> getSubElements(Element rootElement, String name)
    {
        List<Element> subList = (List<Element>)rootElement.elements(name);
        
        return subList;
    }
    
    public String getAttributeValue(Element element, String attributeName)
    {
        return element.attributeValue(attributeName);
    }
    
    public String getContent(Element element, String attributeName)
    {
        return element.elementTextTrim(attributeName);
    }
    
    public static void main(String[] args)
    {
        XmlParseUtil xmlParseUtil = XmlParseUtil.getInstance();
        Document doc = xmlParseUtil.loadFile("gme\\config\\gme.notifyconfig.xml");
        Element element = xmlParseUtil.getRootElement(doc);
        //List<Element> list = getRootSubElements(element);
        List<Element> list2 = xmlParseUtil.getSubElements(element, "notifymodule");
        for (Element em2 : list2)
        {
            System.out.println(xmlParseUtil.getContent(em2, "modulecode"));
            System.out.println(xmlParseUtil.getContent(em2, "modulename"));
            List<Element> list3 = xmlParseUtil.getSubElements(em2, "moduleoperator");
            for (Element em3 : list3)
            {
                System.out.println(xmlParseUtil.getContent(em3, "operatortype"));
                List<Element> list4 = xmlParseUtil.getSubElements(em3, "modulecompts");
                for (Element em4 : list4)
                {
                    List<Element> list5 = xmlParseUtil.getSubElements(em4, "modulecompt");
                    for (Element em5 : list5)
                    {
                        System.out.println(xmlParseUtil.getAttributeValue(em5, "srcComptCode"));
                        System.out.println(xmlParseUtil.getAttributeValue(em5, "destComptCode"));
                        System.out.println(xmlParseUtil.getAttributeValue(em5, "notifyStrategy"));
                    }
                }
            }
            System.out.println("-------------------------");
        }
    }
    
}
