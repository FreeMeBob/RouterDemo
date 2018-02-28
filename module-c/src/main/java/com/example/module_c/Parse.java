package com.example.module_c;

import android.content.Context;
import android.provider.DocumentsContract;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Bob on 2018/2/27.
 */

public class Parse {
    public Context context;

    public Parse(Context context){
        this.context=context;
    }
    public void parseXML(){

        try {
            System.out.println("3333333");
            InputStream inputStream=context.getAssets().open("myconfig.xml");
            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=documentBuilderFactory.newDocumentBuilder();
            Document document=builder.parse(inputStream);
            Element element=document.getDocumentElement();
            NodeList nodeList=element.getElementsByTagName("company");
            for(int i=0;i<nodeList.getLength();i++){
                Node node=nodeList.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element1=(Element)node;
                    String companyName=element1.getAttribute("id");
                    System.out.print(companyName+"  ");
                    NodeList nodeList1=element1.getElementsByTagName("location");
                    for(int j=0;j<nodeList1.getLength();j++){
                        Node node1=nodeList1.item(j);
                        if(node1.getNodeType()==Node.ELEMENT_NODE){
                            Element element2=(Element)node1;
                            String location=element2.getTextContent();
                            System.out.print(location+" ");
                        }
                    }
                }
            System.out.println("");
            }
            System.out.print("----->");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("e1----->:" + e);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            System.out.print("e2----->:" + e);
        } catch (SAXException e) {
            e.printStackTrace();
            System.out.print("e3----->:" + e);
        }
        catch (NullPointerException e){
            System.out.print("e4------>"+e);
        }


    }
}
