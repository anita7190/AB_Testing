package com.test.ab;
import java.util.concurrent.TimeUnit;
/**
 * API Test
 *
 */

public class Base 
{
	
    public static void main( String[] args )
    {
    	Boolean result;
    	System.out.println( "Authentication Started" );
    	ApiCalls a = new ApiCalls();
    	result= a.getAuth();
    	if(result)
    	{
    		System.out.println("Authentication Successful");
    	}
    	
    	result = a.getbanners();
    	
    	if(result)
    	{
    		System.out.println("Banners retrieved  Successfully");
    	}
    	
    	result = a.testStarterKit();
    	if(result)
    	{
    		System.out.println("\n Starter Kit apps are proper");
    	}
    	
    }
}
