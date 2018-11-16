package com.capg.paymentwallet.service;

import java.util.List;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.dao.AccountDAOImpl;
import com.capg.paymentwallet.dao.IAccountDao;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.exception.CustomerExceptionMessage;

public class AccountServiceImpl implements IAccountService{

                @Override
                public boolean createAccount(AccountBean accountBean)
                                                throws Exception {
                                
                                IAccountDao dao=new AccountDAOImpl();
                                
                                boolean result=dao.createAccount(accountBean);
                                return result;
                
                                  }
                

                @Override
                public boolean deposit(AccountBean accountBean, double depositAmount)
                                                throws Exception {
                                accountBean.setBalance(accountBean.getBalance()+depositAmount);
                                IAccountDao dao=new AccountDAOImpl();
                                boolean result=dao.updateAccount(accountBean);
                                return result;
                }

                @Override
                public boolean withdraw(AccountBean accountBean, double withdrawAmount)
                                                throws Exception {
                                if(accountBean.getBalance()>withdrawAmount)
                                {
                                accountBean.setBalance(accountBean.getBalance()-withdrawAmount);
                                IAccountDao dao=new AccountDAOImpl();
                                boolean result=dao.updateAccount(accountBean);
                                return result;
                }else
                {
                                throw new CustomerException(CustomerExceptionMessage.BALERROR);
                }
                                
                }
                @Override
                public boolean fundTransfer(AccountBean transferingAccountBean,AccountBean beneficiaryAccountBean, double transferAmount) throws Exception {
                                if(transferingAccountBean.getBalance()>transferAmount)
                                {
                                transferingAccountBean.setBalance(transferingAccountBean.getBalance()-transferAmount);
                                beneficiaryAccountBean.setBalance(beneficiaryAccountBean.getBalance()+transferAmount);
                                
                                IAccountDao dao=new AccountDAOImpl();
                                boolean result1=dao.updateAccount(transferingAccountBean);
                                boolean result2=dao.updateAccount(beneficiaryAccountBean);
                                return result1 && result2;
                }
                                else
                                {
                                                throw new CustomerException(CustomerExceptionMessage.BALERROR);
                                }

                }



                @Override
                public AccountBean findAccount(int accountId) throws Exception {
                                IAccountDao dao=new AccountDAOImpl();
                                AccountBean bean=dao.findAccount(accountId);
                                return bean;
                }
                
                
     

                public boolean isValidMail(String mail) 
                {
                	if ((mail.matches("[A-Z0-9a-z.]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}"))) 
                    {
                    	return true;
                    }
           
               	System.out.println("Email Address should be in abc@def.as format");
                
            	return false;

                }


				public boolean isValidAddr(String addr) 
                {
                	if (addr.length() < 3) 
                    {System.out.println("Address should be atleast 3 characters");
                	
                	return false;
                		
                    }
                	return true;
               	
                }


				public boolean isValidPhoneNum(String phone) 
                {
                	 if (phone.matches("(0)?[6-9][0-9]{9}")) 
                     {
                		 return true;
                     }
                	 System.out.println("phone number should be 10 digits only");
                     
                  	return false;
                	
				}
				
				public boolean isValidPan(String pan) 
                {
                	 if (pan.matches("^[A-Z]{4}[0-9]{5}[A-Z]{1}")) 
                     {
                			return true;
                     }
                	 System.out.println("PAN should be 10 digits and valid one");
                     
                  	return false;
                
				}


				public boolean isValidLName(String lname) 
                {
                	 if (lname.trim().length() < 3) 
                     {
                     	System.out.println("Last Name should be more than 2 characters");
                     	
                     	return false;
                     }
                	return true;
				}


				public boolean isValidFName(String fname)
                {
             
                if (fname.trim().length() < 3) 
                {
                	System.out.println("First Name should be more than 2 characters");
         
                	return false;
                	
                }
                
                	return true ;
                }
                
				public boolean isValidGender(String gender)
                {
             
                if( ((gender.equalsIgnoreCase("M") )|| (gender.equalsIgnoreCase("F"))) )
                {
                	return true ;
                	
                }
                System.out.println("Gender should be either 'M' or 'F' ");
                
            	return false;
                	
                }
                
                
                
                
                
                
                
                @Override
                public String  gender(String g)
                {    
                
                                if(g.equals("F"))
                                {
                                                return "Mrs";
                                }
                                else if(g.equals("M"))
                                {
                                return "Ms" ;
                                
                }
                                return null;
}


				@Override
				public List<String> getOperations() 
				{
					IAccountDao dao = new AccountDAOImpl();
					
					return dao.getOperations();
				}



                
}
