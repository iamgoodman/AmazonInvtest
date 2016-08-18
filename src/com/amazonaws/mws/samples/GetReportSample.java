/******************************************************************************* 
 *  Copyright 2009 Amazon Services.
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  
 *  You may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 *  This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 *  CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 *  specific language governing permissions and limitations under the License.
 * ***************************************************************************** 
 *
 *  Marketplace Web Service Java Library
 *  API Version: 2009-01-01
 *  Generated: Wed Feb 18 13:28:48 PST 2009 
 * 
 */



package com.amazonaws.mws.samples;

import java.util.List;



import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.soap.Node;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.amazonaws.mws.*;
import com.amazonaws.mws.model.*;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;
import com.sun.java_cup.internal.runtime.Scanner;

import au.com.bytecode.opencsv.CSVReader;

import com.amazonaws.mws.mock.MarketplaceWebServiceMock;

/**
 *
 * Get Report  Samples
 *
 *
 */
public class GetReportSample {

    /**
     * Just add a few required parameters, and try the service
     * Get Report functionality
     *
     * @param args unused
     * @throws NamingException 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws IOException 
     */
    public static void main(String... args) throws NamingException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {

        /************************************************************************
         * Access Key ID and Secret Access Key ID, obtained from:
         * http://aws.amazon.com
         ***********************************************************************/

    	final String accessKeyId = "0";
        final String secretAccessKey = "0";

        final String appName = "Myawesomeapp";
        final String appVersion = "1.1.0";


        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();

        /************************************************************************
         * Uncomment to set the appropriate MWS endpoint.
         ************************************************************************/
        // US
         config.setServiceURL("https://mws.amazonservices.com");
        // UK
        // config.setServiceURL("https://mws.amazonservices.co.uk");
        // Germany
        // config.setServiceURL("https://mws.amazonservices.de");
        // France
        // config.setServiceURL("https://mws.amazonservices.fr");
        // Italy
        // config.setServiceURL("https://mws.amazonservices.it");
        // Japan
        // config.setServiceURL("https://mws.amazonservices.jp");
        // China
        // config.setServiceURL("https://mws.amazonservices.com.cn");
        // Canada
        // config.setServiceURL("https://mws.amazonservices.ca");
        // India
        // config.setServiceURL("https://mws.amazonservices.in");

        /************************************************************************
         * You can also try advanced configuration options. Available options are:
         *
         *  - Signature Version
         *  - Proxy Host and Proxy Port
         *  - User Agent String to be sent to Marketplace Web Service
         *
         ***********************************************************************/

        /************************************************************************
         * Instantiate Http Client Implementation of Marketplace Web Service        
         ***********************************************************************/

        MarketplaceWebService service = new MarketplaceWebServiceClient(
                accessKeyId, secretAccessKey, appName, appVersion, config);

        /************************************************************************
         * Setup request parameters and uncomment invoke to try out 
         * sample for Get Report 
         ***********************************************************************/

        /************************************************************************
         * Marketplace and Merchant IDs are required parameters for all 
         * Marketplace Web Service calls.
         ***********************************************************************/

        	final String merchantId = "0";
           final String sellerDevAuthToken = "0";

        GetReportRequest request = new GetReportRequest();
        request.setMerchant( merchantId );
        request.setMWSAuthToken(sellerDevAuthToken);

        request.setReportId( "2615142646017030" );

        // Note that depending on the type of report being downloaded, a report can reach 
        // sizes greater than 1GB. For this reason we recommend that you _always_ program to
        // MWS in a streaming fashion. Otherwise, as your business grows you may silently reach
        // the in-memory size limit and have to re-work your solution.
        //
         OutputStream report ;
         
         //file way
	   report = new FileOutputStream( "Y:\\Staffs\\Joey\\Developer\\JoeyAdvisor\\AllopenListing.txt" );
         
      
		
         request.setReportOutputStream( report );
          
        invokeGetReport(service, request);
       
        
        
        
        //use OpenCSV to parse tab delimited listing report, save the parsed value to amazon lisiting object write it to db
        
/*        //each line of value is being seperated by a new line seperator
        CSVReader reader = new CSVReader(new FileReader("Y:\\Staffs\\Joey\\Developer\\JoeyAdvisor\\AllopenListing.txt"),'\n');*/
        
    /*    //seperate each line by new line seperator, and skip the first one as the first line, as it is all the attributes name
        CSVReader reader = new CSVReader(new FileReader("Y:\\Staffs\\Joey\\Developer\\JoeyAdvisor\\AllopenListing.txt"),'\n','\'',1);*/
        
   
        
        //string array of each segement of sentence  in the file that are already being seperated by the line seperator next line
       String[] record;
      
       //save the seperated string to write to db
       String[]vdb;
       String value = "";
       ArrayList<String>tracker = new ArrayList<String>();
       int count =0;
       
       
       //trying java util scanner to read the file
       
       java.util.Scanner scanner = new java.util.Scanner(new File("Y:\\Staffs\\Joey\\Developer\\JoeyAdvisor\\AllopenListing.txt"));
       
       
       scanner.useDelimiter("\n");
      
 
       while (scanner.hasNextLine()) {
    	  
    	  
    	   count ++;
          String line = scanner.nextLine();
          /* String cells[] = line.split("\t");     */                     
       
   /*       System.out.println(line);*/
    	   
    	   System.out.println("current count is " + "" +count);
    	  
       }
       
       
       
       
       System.out.println(count);
       scanner.close();
       
     /*   try {
        	
        	//write to db with assigned attributes
    		
  		
   		 
    		String url = "0";
            String username = "0";
            String password = "0";
            
            System.out.println("Loading driver...");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver loaded!");
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Cannot find the driver in the classpath!", e);
            }
            
            
            
            System.out.println("Connecting database...");
        	
        	
        	//reac each line of record 
			while ((record = reader.readNext()) != null) {
			
				//initializ object
				AmazonListing al = new AmazonListing(value,value, value, value, value, value, value, value, value, value, value, value, value, value, value, value, value, value, value, value, value);
				//information for one listing  is sotred at index 0 only
				
				
				count++;
				
				   //split the information 
				  vdb = value.split("\t");
				 
				  
				 for(int i = 0; i<vdb.length;i++)
				 {
					 
					 System.out.println(vdb[i]);
					 System.out.println("next iteration");
				 }
				  
	 
				 //28 attributes in total assign each attributes to Amazon Listing object to write to db
				 
			 
			try{
			
				
				al.setItemname(vdb[0]);
				
				System.out.println(al.getItemname());
			
			}
			catch(NullPointerException e)
			{
				
				
				al.setItemname("");
				
			}
			
			
			
			
			try{
				
				
				al.setItemdescription(vdb[1]);
				
			System.out.println(al.getItemdescription());
				
				
			
			}
			catch(NullPointerException e)
			{
				
				
				al.setItemdescription("");
				
			}
			
			
			
			
			
			try{
				
				
				al.setListingid(vdb[2]);
				
				System.out.println(al.getListingid());
			
			}
			catch(NullPointerException e)
			{
				
				
				al.setListingid("");
				
			}
			
			
			
			
			try{
				
				
				al.setSku(vdb[3]);
				
				System.out.println(al.getSku());
			
			}
			catch(NullPointerException e)
			{
				
				
				al.setSku("");
				
			}
			
			
			
			try{
				
				
				al.setAmazonprice(vdb[4]);
				
				System.out.println(al.getAmazonprice());
			
			}
			catch(NullPointerException e)
			{
				
				
				al.setAmazonprice("");
				
			}
				 
				 
			
		
			try{
					
					
					al.setQuantity(vdb[5]);
					
					System.out.println(al.getQuantity());
				
				}
				catch(NullPointerException e)
				{
					
					
					al.setQuantity("");
					
				}	 
				 
				 
			

			try{
					
					
					al.setListingopendate(vdb[6]);
					
					System.out.println(al.getListingopendate());
				
				}
				catch(NullPointerException e)
				{
					
					
					al.setListingopendate("");
					
				}	 
				 
			
			
			try{
				
				
				al.setIsmarketplace(vdb[8]);
				
				System.out.println(al.getIsmarketplace());
			
			}
			catch(NullPointerException e)
			{
				
				
				al.setIsmarketplace("");
				
			}	 
			
				 
	
			
			try{
				
				
				al.setProductidtype(vdb[9]);
				
				System.out.println(al.getProductidtype());
			
			}
			catch(NullPointerException e)
			{
				
				
				al.setProductidtype("");
				
			}	 	 
				 
			
			
			
			
			
			try{
				
				
				al.setItemnote(vdb[11]);
				
				System.out.println(al.getItemnote());
			
			}
			catch(NullPointerException e)
			{
				
				
				al.setItemnote("");
				
			}	 	
				
			

			try{
				
				
				al.setItemcondition(vdb[12]);
				
				System.out.println(al.getItemcondition());
			
			}
			catch(NullPointerException e)
			{
				
				
				al.setItemcondition("");
				
			}	 	
			
			
			
			try{
				
				
				al.setAsin(vdb[16]);
				
				System.out.println(al.getAsin());
			
				
			}
			catch(NullPointerException e)
			{
				
				
				al.setAsin("");
				
			}	 	
				 
			
			try{
				
				
				al.setShipinternational(vdb[19]);
				
				System.out.println(al.getShipinternational());
			
			}
			catch(NullPointerException e)
			{
				
				
				al.setShipinternational("");
				
			}	 	
				 
			
			try{
				
				
				al.setExpitidedshipping(vdb[20]);
				
				System.out.println(al.getExpitidedshipping());
			
			}
			catch(NullPointerException e)
			{
				
				
				al.setExpitidedshipping("");
				
			}	 
			
			

			try{
				
				
				al.setProductid(vdb[22]);
				
				System.out.println(al.getProductid());
			
			}
			catch(NullPointerException e)
			{
				
				
				al.setProductid("");
				
			}	 
			
			
			try{
				
				
				al.setPendingquantity(vdb[25]);
				
				System.out.println(al.getPendingquantity());
			
			}
			catch(NullPointerException e)
			{
				
				
				al.setPendingquantity("");
				
			}	 
		

			
			
		try{
				
				
				al.setFuilfillmentchannel(vdb[26]);
				
				System.out.println(al.getFuilfillmentchannel());
			
			}
			catch(NullPointerException e)
			{
				
				
				al.setFuilfillmentchannel("");
				
			}	 
		
		
		
		try{
			
			
			al.setMerchantshippinggroup(vdb[27]);
			
			System.out.println(al.getMerchantshippinggroup());
		
		}
		catch(NullPointerException e)
		{
			
			
			al.setMerchantshippinggroup("");
			
		}	 
			
		
		
		
        //write to db
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
        	
        	
            System.out.println("Database connected!");
            
            
            
            //insert primary Key SKU  to DB, should have used insert for all statement, will combine later

            
                String query = " insert into AmazonListing (SKU)"
                        + " values (?)";
                
                
                PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
                preparedStmt.setString (1, URLEncoder.encode(al.getSku(),"UTF-8"));
                
             // execute the preparedstatement
                preparedStmt.execute();
                System.out.println("inserted");
           
           	
            
            // Update DB
            String query1 = "UPDATE AmazonListing SET ItemName = ?, ItemDescription = ?, ListingID = ?, AmazonPrice = ?, Quantity = ?, ListingOpenDate = ?, "
            		+ "IsItemMarketPlace = ?, ProductId = ?, ItemNote = ?, ItemCondition = ?, ASIN = ?, "
            		+ "ShipInternational = ?, ExpeditedShipping = ?, PendingQuantity = ?,"
            		+ "FulfillmentChannel = ?,MerchantShippingGroup = ?, ProductIdType = ? "
            		
            		+ " WHERE SKU = ?";
              
            
            
            //create the mysql insert preparedstatement
            
            PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query1);
           
            //insert values to be updated to statement , need to use encoder to make it more secure and to prevent quotation marks to mess up db
            
            preparedStmt1.setString(1,URLEncoder.encode(al.getItemname(), "UTF-8"));
            
            preparedStmt1.setString(2,URLEncoder.encode(al.getItemdescription(),"UTF-8"));
            
            preparedStmt1.setString(3,URLEncoder.encode(al.getListingid(),"UTF-8"));
            
            preparedStmt1.setString(4,URLEncoder.encode(al.getAmazonprice(),"UTF-8"));
            
            preparedStmt1.setString(5,URLEncoder.encode(al.getQuantity(),"UTF-8"));
          
            preparedStmt1.setString(6,URLEncoder.encode(al.getListingopendate(),"UTF-8"));
            
            preparedStmt1.setString(7,URLEncoder.encode(al.getIsmarketplace(),"UTF-8"));
            
            preparedStmt1.setString(8,URLEncoder.encode(al.getProductid(),"UTF-8"));
            
            preparedStmt1.setString(9,URLEncoder.encode(al.getItemnote(),"UTF-8"));
            
            preparedStmt1.setString(10,URLEncoder.encode(al.getItemcondition(),"UTF-8"));
            
            preparedStmt1.setString(11,URLEncoder.encode(al.getAsin(),"UTF-8"));
            
            preparedStmt1.setString(12,URLEncoder.encode(al.getShipinternational(),"UTF-8"));
            
            preparedStmt1.setString(13,URLEncoder.encode(al.getExpitidedshipping(),"UTF-8"));
            
          
            
            preparedStmt1.setString(14,URLEncoder.encode(al.getPendingquantity(),"UTF-8"));
            
            preparedStmt1.setString(15,URLEncoder.encode(al.getFuilfillmentchannel(),"UTF-8"));
            
            preparedStmt1.setString(16,URLEncoder.encode(al.getMerchantshippinggroup(),"UTF-8"));
            
            preparedStmt1.setString(17,URLEncoder.encode(al.getProductidtype(),"UTF-8"));
            
            preparedStmt1.setString(18,URLEncoder.encode(al.getSku(),"UTF-8"));
            
            
            
            System.out.println(preparedStmt1);
         // execute the preparedstatement
            preparedStmt1.execute();
            System.out.println("Updated");
            
            
            
            //after inserting, close the connection
            connection.close();
            
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        
		
		
			    System.out.println("end of line");
			    
			}
			
			System.out.println(count);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        */
        
       
     
        
              

              
              

        }  

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
     
       
        
        
 



    /**
     * Get Report  request sample
     * The GetReport operation returns the contents of a report. Reports can potentially be
     * very large (>100MB) which is why we only return one report at a time, and in a
     * streaming fashion.
     *   
     * @param service instance of MarketplaceWebService service
     * @param request Action to invoke
     */
    public static void invokeGetReport(MarketplaceWebService service, GetReportRequest request) {
        try {

            GetReportResponse response = service.getReport(request);


            System.out.println ("GetReport Action Response");
            System.out.println ("=============================================================================");
            System.out.println ();

            System.out.print("    GetReportResponse");
            System.out.println();
            System.out.print("    GetReportResult");
            System.out.println();
            System.out.print("            MD5Checksum");
            System.out.println();
            System.out.print("                " + response.getGetReportResult().getMD5Checksum());
            System.out.println();
            if (response.isSetResponseMetadata()) {
                System.out.print("        ResponseMetadata");
                System.out.println();
                ResponseMetadata  responseMetadata = response.getResponseMetadata();
                if (responseMetadata.isSetRequestId()) {
                    System.out.print("            RequestId");
                    System.out.println();
                    System.out.print("                " + responseMetadata.getRequestId());
                    System.out.println();
                }
            } 
            System.out.println();

            System.out.println("Report");
            System.out.println ("=============================================================================");
            System.out.println();
            System.out.println( request.getReportOutputStream().toString() );
            System.out.println();

            System.out.println(response.getResponseHeaderMetadata());
            System.out.println();


        } catch (MarketplaceWebServiceException ex) {

            System.out.println("Caught Exception: " + ex.getMessage());
            System.out.println("Response Status Code: " + ex.getStatusCode());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("Error Type: " + ex.getErrorType());
            System.out.println("Request ID: " + ex.getRequestId());
            System.out.print("XML: " + ex.getXML());
            System.out.println("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
        }
    }

}
