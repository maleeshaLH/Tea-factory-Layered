package lk.Ijse.TeaFctory.bo;

import lk.Ijse.TeaFctory.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory=
                new BOFactory():boFactory;
    }
    public enum BOTypes{
        Customer,EMPLOYEE,RAWSTOCK,SALARY,SUPPLIER,PREPAREDSTOCK,ORDER,ORDERDETAILS,
        STOCKORDER,SUPPLIERORDERDETAILS,SUPPLIERORDER,USER

    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case Customer:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case RAWSTOCK:
                return new RawStockBOIMpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case PREPAREDSTOCK:
                return new PreparedStockBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDERDETAILS:
                return new OrderDetailsBOImpl();
            case STOCKORDER:
                return new StockOrderBOImpl();
            case SUPPLIERORDERDETAILS:
                return new SupplierOrderDetailsBOImpl();
            case SUPPLIERORDER:
                return new SupplierOrderBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
