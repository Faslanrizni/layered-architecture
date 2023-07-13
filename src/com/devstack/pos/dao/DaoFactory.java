package com.devstack.pos.dao;

import com.devstack.pos.dao.custome.impl.CustomerDaoImpl;
import com.devstack.pos.dao.custome.impl.ProductDaoImpl;
import com.devstack.pos.dao.custome.impl.UserDaoImpl;
import com.devstack.pos.enums.DaoType;
import org.omg.CORBA.PUBLIC_MEMBER;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}
    static DaoFactory getInstance(){
        return (daoFactory==null)?daoFactory = new DaoFactory():daoFactory;
    }
    public /*SuperDao*/ <T>T getDao(DaoType daoType){ /* applying generics*/
        switch (daoType){
            case USER:
                return (T) new UserDaoImpl();
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case PRODUCT:
                return (T) new ProductDaoImpl();
            default:
                return null;

        }
    }

}
