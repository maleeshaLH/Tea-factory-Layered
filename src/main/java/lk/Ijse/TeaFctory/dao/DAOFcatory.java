package lk.Ijse.TeaFctory.dao;

import lk.Ijse.TeaFctory.dao.custom.impl.*;

public class DAOFcatory {
    private static DAOFcatory daoFcatory;
    private DAOFcatory(){}
    public static DAOFcatory getDaoFcatory(){
        return (daoFcatory==null)?daoFcatory=
                new DAOFcatory():daoFcatory;
    }
    public enum DAOTypes{
        CUSTOMER,EMPLOYEE,RAWSTOCK,SALARY,SUPPPLIER,PREPAREDSTOCK,ORDER,
        ORDERDETAILS,STOCKORDER,SUPPLIERORDERDETAILS,SUPPLIERORDER,USER
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case RAWSTOCK:
                return new RawStockDAOImpl();
            case SALARY:
                return new SalaryDAOImpl();
            case SUPPPLIER:
                return new SupploerDAOImpl();
            case PREPAREDSTOCK:
                return new PreperedStockDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();
            case STOCKORDER:
                return new StockOrderDAOImpl();
            case SUPPLIERORDERDETAILS:
                return new SupplierOrderDetailsDAOImpl();
            case SUPPLIERORDER:
                return new SupplierOrderDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
