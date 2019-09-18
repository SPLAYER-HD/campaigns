/**
 * 
 */
package com.fi.crm.campaigns.infrastructure.testsms;

import com.fi.crm.campaigns.infrastructure.sms.SendSMS;


/**
 * @author Ing. Jose Augusto Cupasachoa
 */
public class TestSMS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try{

			SendSMS.testSendSMS("+573208393999", "Hello world Jose Cupa!!");
			System.out.println();

			//SendSMS.clockworkSendSMSTest("573208393999", "Hello world Jose Cupa!!!");

		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
