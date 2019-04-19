package com.test.ab;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadToken {

	public static void main(String[] args) 
	{
		 try
	        {
	            FileInputStream file = new FileInputStream(new File("howtodoinjava_demo.xlsx"));
	 
	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(0);
	 
	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext())
	            {
	                Row row = rowIterator.next();
	                //For each row, iterate through all the columns
	                Iterator<Cell> cellIterator = row.cellIterator();
	                 
	                while (cellIterator.hasNext())
	                {
	                    Cell cell = cellIterator.next();
	                    //Check the cell type and format accordingly
	                    switch (cell.getCellType())
	                    {
	                        case Cell.CELL_TYPE_NUMERIC:
	                            System.out.print(cell.getNumericCellValue() + " t");
	                            break;
	                        case Cell.CELL_TYPE_STRING:
	                        	String str=cell.getStringCellValue();
	                            System.out.print(str + " ");
	                            char c = str.charAt(str.length()-1);
	                            int i = c%10;
	                            System.out.println(i);
	                            Cell newcell = row.createCell(2);
	                            newcell.setCellValue(i);
	                            break;
	                    }
	                }
	                System.out.println("");
	            }
	            FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
	            workbook.write(out);
	            file.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }	

	}


