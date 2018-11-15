package com.capg.paymentwallet.service;

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
                                boolean valid=validations(accountBean);
                                if(valid)
                                {
                                boolean result=dao.createAccount(accountBean);
                                return result;
                }
                                else
                                                throw  new CustomerException(CustomerExceptionMessage.ERROR);
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
                /*public boolean validations(AccountBean accountBean) throws CustomerException {
                                boolean isValid = false;
                                if (accountBean.getCustomerBean().getFirstName().trim().length() < 4) {
                                                throw new CustomerException(CustomerExceptionMessage.FNERROR);
                                } else if (accountBean.getCustomerBean().getLastName().trim().length() < 4) {
                                                throw new CustomerException(CustomerExceptionMessage.LNERROR);
                                } else if (!(String.valueOf(accountBean.getCustomerBean().getPhoneNo())
                                                                .matches("(0)?[6-9][0-9]{9}"))) {
                                                throw new CustomerException(CustomerExceptionMessage.PNOERROR);
                                }  else if (accountBean.getCustomerBean().getAddress().length() == 0) {
                                                throw new CustomerException(CustomerExceptionMessage.ADRERROR);
                                } else if (!(accountBean.getCustomerBean().getEmailId()
                                                                .matches("[A-Z0-9a-z.]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}"))) {
                                                throw new CustomerException(CustomerExceptionMessage.EMAILERROR);
                                } else {
                                                isValid = true;
                                }
                                return isValid;
                }*/
                
     public boolean validations( AccountBean accountBean) 
     {
      boolean isValid = false;
 if ( (isValidFName(accountBean)) && (isValidLName(accountBean)) && (isValidPhoneNum(accountBean)) && (isValidAddr(accountBean)) && (isValidMail(accountBean)))
 {
	 isValid=true;
 }
 return isValid;            
                    		
 }

                private boolean isValidMail(AccountBean accountBean) 
                {
                	if ((accountBean.getCustomerBean().getEmailId()
                            .matches("[A-Z0-9a-z.]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}"))) 
                    {
                    	System.out.println("Email Address should be in abc@def.as format");
                    	isValidAddr(accountBean);
                    	return false;
                    }
               	return true;

                }


				private boolean isValidAddr(AccountBean accountBean) 
                {
                	if (accountBean.getCustomerBean().getAddress().length() < 3) 
                    {
                    	System.out.println("Address should be atleast 3 characters");
                    	isValidAddr(accountBean);	
                    	return false;
                    }
               	return true;
                }


				private boolean isValidPhoneNum(AccountBean accountBean) 
                {
                	 if ((String.valueOf(accountBean.getCustomerBean().getPhoneNo()).matches("(0)?[6-9][0-9]{9}"))) 
                     {
                     	System.out.println("phone number should be 10 digits only");
                     	isValidPhoneNum(accountBean);	
                     	return false;
                     }
                	return true;
				}


				private boolean isValidLName(AccountBean accountBean) 
                {
                	 if (accountBean.getCustomerBean().getLastName().trim().length() < 3) 
                     {
                     	System.out.println("Last Name should be more than 2 characters");
                     	isValidLName(accountBean);
                     	return false;
                     }
                	return true;
				}


				public boolean isValidFName(AccountBean accountBean)
                {
             
                if (accountBean.getCustomerBean().getFirstName().trim().length() < 3) 
                {
                	System.out.println("First Name should be more than 2 characters");
                	isValidFName(accountBean);
                	return false;
                	
                }
                
                	return true ;
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



                
}
