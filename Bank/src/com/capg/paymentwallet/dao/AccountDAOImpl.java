package com.capg.paymentwallet.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.exception.CustomerExceptionMessage;
import com.capg.paymentwallet.util.Database;

public class AccountDAOImpl implements IAccountDao {

	
	EntityManager em;
	
	@Override
	public boolean createAccount(AccountBean accountBean) throws Exception {
		try{
			
			this.em=JPAManager.createEntityManager();
			em.getTransaction().begin();
			
			em.persist(accountBean);
			
			em.getTransaction().commit();
			JPAManager.closeResources(em);
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	
	}

	@Override
	public boolean updateAccount(AccountBean accountBean) throws Exception {
		try{
			this.em=JPAManager.createEntityManager();
			em.getTransaction().begin();
			
			em.merge(accountBean);
			
			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	
	}

	@Override
	public AccountBean findAccount(int accountId) throws Exception {
		try{
			em=JPAManager.createEntityManager();
			AccountBean accountBean2=em.find(AccountBean.class,accountId);
			JPAManager.closeResources(em);
			return accountBean2;
			
		}catch(Exception e){
			throw new CustomerException(new CustomerExceptionMessage().ACCERROR);
					}
	}

	@Override
	public List<String> getOperations() 
	{
			return Database.getOperations();
	}

}
